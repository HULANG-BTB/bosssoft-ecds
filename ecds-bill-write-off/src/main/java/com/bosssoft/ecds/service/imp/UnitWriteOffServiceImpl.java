package com.bosssoft.ecds.service.imp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.unit.WriteOffApplyItemMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyMapper;
import com.bosssoft.ecds.entity.dto.UnitWriteOffApplyQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.UnitWriteOffItemQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyItemDTO;
import com.bosssoft.ecds.entity.po.WriteOffApplyItemPO;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;
import com.bosssoft.ecds.service.UnitWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UnitWriteOffServiceImpl implements UnitWriteOffService {
    private final WriteOffApplyMapper writeOffApplyMapper;
    private final WriteOffApplyItemMapper writeOffApplyItemMapper;

    @Autowired(required = false)
    public UnitWriteOffServiceImpl (WriteOffApplyMapper writeOffApplyMapper, WriteOffApplyItemMapper writeOffApplyItemMapper) {
        this.writeOffApplyMapper = writeOffApplyMapper;
        this.writeOffApplyItemMapper = writeOffApplyItemMapper;
    }

    @Override
    public IPage<WriteOffApplyDTO> selectApplyPage(UnitWriteOffApplyQueryInfoDTO queryInfoDTO) {
        Page<WriteOffApplyPO> page = new Page<>(queryInfoDTO.getPageNum(), queryInfoDTO.getPageSize());
        QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WriteOffApplyPO::getFAgenIdCode, queryInfoDTO.getAgenIdCode());
        if (queryInfoDTO.getNo() != null && !"".equals(queryInfoDTO.getNo())) {
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, queryInfoDTO.getNo());
        }
        if (queryInfoDTO.getStartDate() != null && queryInfoDTO.getEndDate()!= null) {
            queryWrapper.lambda().ge(WriteOffApplyPO::getFDate, queryInfoDTO.getStartDate());
            queryWrapper.lambda().le(WriteOffApplyPO::getFDate, queryInfoDTO.getEndDate());
        }
        IPage<WriteOffApplyPO> writeOffApplyPOIPage = writeOffApplyMapper.selectPage(page, queryWrapper);
        IPage<WriteOffApplyDTO> applyDTOIPage = Convert.convert(new TypeReference<IPage<WriteOffApplyDTO>>() {}, writeOffApplyPOIPage);
        applyDTOIPage.setRecords(Convert.toList(WriteOffApplyDTO.class, writeOffApplyPOIPage.getRecords()));
        return applyDTOIPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteApply(String no) {
        QueryWrapper<WriteOffApplyPO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.lambda().eq(WriteOffApplyPO::getFNo, no);
        WriteOffApplyPO applyPO = writeOffApplyMapper.selectOne(queryWrapper1);
        if (applyPO.getFIsUpload() == 2 || applyPO.getFChangeState() == 2) {
            return false;
        }
        int result = writeOffApplyMapper.delete(queryWrapper1);
        QueryWrapper<WriteOffApplyItemPO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().eq(WriteOffApplyItemPO::getFPid, no);
        writeOffApplyItemMapper.delete(queryWrapper2);
        return result == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadApply(List<String> noList) {
        AtomicInteger result = new AtomicInteger();
        noList.forEach(item -> {
            QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, item);
            WriteOffApplyPO applyPO = writeOffApplyMapper.selectOne(queryWrapper);
            applyPO.setFIsUpload(2);
            applyPO.setFChangeState(1);
            applyPO.setFCheckResult(null);
            result.addAndGet(writeOffApplyMapper.updateById(applyPO));
        });
        return result.get() == noList.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rescindApply(List<String> noList) {
        int result = 0;
        for (String item : noList) {
            QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, item);
            WriteOffApplyPO applyPO = writeOffApplyMapper.selectOne(queryWrapper);
            if (applyPO.getFChangeState() == 2) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
            applyPO.setFIsUpload(1);
            result += writeOffApplyMapper.updateById(applyPO);
        }
        return result == noList.size();
    }

    @Override
    public IPage<WriteOffApplyItemDTO> selectItemPage(UnitWriteOffItemQueryInfoDTO queryInfoDTO) {
        Page<WriteOffApplyItemPO> page = new Page<>(queryInfoDTO.getPageNum(), queryInfoDTO.getPageSize());
        QueryWrapper<WriteOffApplyItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WriteOffApplyItemPO::getFPid, queryInfoDTO.getNo());
        IPage<WriteOffApplyItemPO> writeOffApplyItemPOIPage = writeOffApplyItemMapper.selectPage(page, queryWrapper);
        IPage<WriteOffApplyItemDTO> applyItemDTOIPage = Convert.convert(new TypeReference<IPage<WriteOffApplyItemDTO>>() {}, writeOffApplyItemPOIPage);
        applyItemDTOIPage.setRecords(Convert.toList(WriteOffApplyItemDTO.class, writeOffApplyItemPOIPage.getRecords()));
        return applyItemDTOIPage;
    }

    @Override
    public List<WriteOffApplyItemDTO> selectItems(String no) {
        QueryWrapper<WriteOffApplyItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WriteOffApplyItemPO::getFPid, no);
        List<WriteOffApplyItemPO> itemPOList = writeOffApplyItemMapper.selectList(queryWrapper);
        return Convert.toList(WriteOffApplyItemDTO.class, itemPOList);
    }
}
