package com.bosssoft.ecds.service.imp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.unit.WriteOffApplyItemMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyMapper;
import com.bosssoft.ecds.entity.dto.UnitWriteOffApplyQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyDTO;
import com.bosssoft.ecds.entity.po.WriteOffApplyItemPO;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;
import com.bosssoft.ecds.service.UnitWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Page<WriteOffApplyPO> page = new Page<>(queryInfoDTO.getPageNum(),queryInfoDTO.getPageSize());
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
        int result = writeOffApplyMapper.delete(queryWrapper1);
        QueryWrapper<WriteOffApplyItemPO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().eq(WriteOffApplyItemPO::getFPid, no);
        writeOffApplyItemMapper.delete(queryWrapper2);
        return result == 1;
    }

    @Override
    public boolean uploadApply(List<WriteOffApplyDTO> applyDTOList) {
        return false;
    }
}
