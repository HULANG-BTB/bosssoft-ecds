package com.bosssoft.ecds.service.imp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.unit.WriteOffApplyMapper;
import com.bosssoft.ecds.entity.dto.UnitWriteOffApplyQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyDTO;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;
import com.bosssoft.ecds.service.UnitWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitWriteOffServiceImpl implements UnitWriteOffService {
    private final WriteOffApplyMapper writeOffApplyMapper;

    @Autowired(required = false)
    public UnitWriteOffServiceImpl (WriteOffApplyMapper writeOffApplyMapper) {
        this.writeOffApplyMapper = writeOffApplyMapper;
    }

    @Override
    public IPage<WriteOffApplyDTO> selectApplyPage(UnitWriteOffApplyQueryInfoDTO queryInfoDTO) {
        Page<WriteOffApplyPO> page = new Page<>(queryInfoDTO.getPageNum(),queryInfoDTO.getPageSize());
        QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
        if (queryInfoDTO.getNo() != null && !"".equals(queryInfoDTO.getNo())) {
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, queryInfoDTO.getNo());
        }
        if (queryInfoDTO.getRangeDate() != null && queryInfoDTO.getRangeDate().length == 2) {
            queryWrapper.lambda().ge(WriteOffApplyPO::getFDate, queryInfoDTO.getRangeDate()[0]);
            queryWrapper.lambda().le(WriteOffApplyPO::getFDate, queryInfoDTO.getRangeDate()[1]);
        }
        IPage<WriteOffApplyPO> writeOffApplyPOIPage = writeOffApplyMapper.selectPage(page, queryWrapper);
        IPage<WriteOffApplyDTO> applyDTOIPage = Convert.convert(new TypeReference<IPage<WriteOffApplyDTO>>() {}, writeOffApplyPOIPage);
        applyDTOIPage.setRecords(Convert.toList(WriteOffApplyDTO.class, writeOffApplyPOIPage.getRecords()));
        return applyDTOIPage;
    }
}
