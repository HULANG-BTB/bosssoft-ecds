package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.dao.BillReturnDao;
import com.bosssoft.ecds.entity.dto.BillReturnDto;
import com.bosssoft.ecds.entity.po.BillReturnPO;
import com.bosssoft.ecds.service.BillReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 *  归档退票
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
public class BillReturnServiceImpl extends ServiceImpl<BillReturnDao, BillReturnPO> implements BillReturnService {
    @Override
    public List<BillReturnDto> queryBillReturnInfo(String agenIdCode) {
        return null;
    }

}
