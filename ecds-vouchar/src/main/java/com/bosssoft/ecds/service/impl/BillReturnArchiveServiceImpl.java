package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillReturnArchiveDao;
import com.bosssoft.ecds.entity.dto.BillReturnDto;
import com.bosssoft.ecds.entity.po.BillReturnArchivePO;
import com.bosssoft.ecds.service.BillReturnArchiveService;
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
public class BillReturnArchiveServiceImpl extends ServiceImpl<BillReturnArchiveDao, BillReturnArchivePO> implements BillReturnArchiveService {
    @Override
    public List<BillReturnDto> queryBillReturnInfo(String agenIdCode) {
        return null;
    }

}
