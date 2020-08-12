package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.entity.dto.BillCheckDto;
import com.bosssoft.ecds.entity.po.BillCheckPO;
import com.bosssoft.ecds.dao.BillCheckDao;
import com.bosssoft.ecds.service.BillCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类 审验记录
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
public class BillCheckServiceImpl extends ServiceImpl<BillCheckDao, BillCheckPO> implements BillCheckService {
    @Override
    public List<BillCheckDto> queryBillCheckInfo(String agenIdCode) {
        return null;
    }
}
