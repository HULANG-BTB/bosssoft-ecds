package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.entity.dto.BillDestructionDto;
import com.bosssoft.ecds.entity.po.BillDestructionPO;
import com.bosssoft.ecds.dao.BillDestructionDao;
import com.bosssoft.ecds.service.BillDestructionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 *  归档票据销毁
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
public class BillDestructionServiceImpl extends ServiceImpl<BillDestructionDao, BillDestructionPO> implements BillDestructionService {
    @Override
    public List<BillDestructionDto> queryBillDestruction(String agenIdCode) {
        return null;
    }
}
