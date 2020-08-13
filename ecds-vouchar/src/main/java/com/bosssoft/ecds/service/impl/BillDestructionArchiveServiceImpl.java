package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillDestructionArchiveDao;
import com.bosssoft.ecds.entity.dto.BillDestructionDto;
import com.bosssoft.ecds.entity.po.BillDestructionArchivePO;
import com.bosssoft.ecds.service.BillDestructionArchiveService;
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
public class BillDestructionArchiveServiceImpl extends ServiceImpl<BillDestructionArchiveDao, BillDestructionArchivePO> implements BillDestructionArchiveService {
    @Override
    public List<BillDestructionDto> queryBillDestruction(String agenIdCode) {
        return null;
    }
}
