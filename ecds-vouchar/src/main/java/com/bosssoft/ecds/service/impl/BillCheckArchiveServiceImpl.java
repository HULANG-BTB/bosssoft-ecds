package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillCheckArchiveDao;
import com.bosssoft.ecds.entity.dto.BillCheckDto;
import com.bosssoft.ecds.entity.po.BillCheckArchivePO;
import com.bosssoft.ecds.service.BillCheckArchiveService;
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
public class BillCheckArchiveServiceImpl extends ServiceImpl<BillCheckArchiveDao, BillCheckArchivePO> implements BillCheckArchiveService {
    @Override
    public List<BillCheckDto> queryBillCheckInfo(String agenIdCode) {
        return null;
    }
}
