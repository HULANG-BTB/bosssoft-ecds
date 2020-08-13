package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillApplyArchiveDao;
import com.bosssoft.ecds.entity.dto.BillApplyDto;
import com.bosssoft.ecds.entity.po.BillApplyArchivePO;
import com.bosssoft.ecds.service.BillApplyArchiveService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 *  归档领用
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Service
public class BillApplyArchiveServiceImpl extends ServiceImpl<BillApplyArchiveDao, BillApplyArchivePO> implements BillApplyArchiveService {
    /**
     * 财政端
     */

    @Override
    public List<BillApplyDto> getBillApplyInfos() {
        return null;
    }

    @Override
    public void finaBillApplyArchive() {
        /**
         * 获取所有单位的票据申领信息
         */

        /**
         * 信息处理
         */

        /**
         * 申领信息归档
         */
    }

    /**
     * 单位端
     */

    @Override
    public BillApplyDto getBillApplyInfo(String agenCode) {
        return null;
    }

    @Override
    public void unitBillApplyArchive() {

    }
}
