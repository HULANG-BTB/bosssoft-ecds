package com.bosssoft.ecds.service.serviceimp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.UneCbillItemMapper;
import com.bosssoft.ecds.dao.UneCbillMapper;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.UneCbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UneCbillServiceImpl implements UneCbillService {

    @Autowired
    private UneCbillMapper uneCbillMapper;

    @Autowired
    private UneCbillItemMapper uneCbillItemMapper;

    /**
     * 根据ID查找某张具体的票据信息
     * @param id
     * @return
     */
    @Override
    public UneCbill getUneCBillById(String id) {
        return uneCbillMapper.selectById(id);
    }

    /**
     * 分页查询开票记录
     * @param page
     * @return
     */
    @Override
    public IPage<UneCbill> selectUnecBillPage(Page<UneCbill> page) {
        List<UneCbill> cbillList = uneCbillMapper.selectPageVO(page);
        return page.setRecords(cbillList);
    }

    /**
     * 保存开票主表以及开票明细
     * @param uneCbill
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int addUneCbill(UneCbill uneCbill) {

        return 0;
    }
}
