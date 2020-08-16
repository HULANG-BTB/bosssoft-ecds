package com.bosssoft.ecds.service.serviceimp;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.UneCbillItemMapper;
import com.bosssoft.ecds.dao.UneCbillMapper;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.entity.vo.UneCbillVo;
import com.bosssoft.ecds.service.UneCbillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public UneCbillVo getUneCBillById(String id) {
        /**
         * 对象类型转换
         */
        UneCbill uneCbill = uneCbillMapper.selectById(id);
        UneCbillVo uneCbillVo = new UneCbillVo();
        BeanUtil.copyProperties(uneCbill, uneCbillVo);
        return uneCbillVo;
    }

    /**
     * 分页查询开票记录
     * 将查询到的PO
     * @param page
     * @return
     */
    @Override
    public IPage<UneCbillVo> selectUnecBillPage(Page<UneCbill> page) {
        List<UneCbill> cbillList = uneCbillMapper.selectPageVO(page);
        List<UneCbillVo> cbillVos = new ArrayList<>();
        /**
         * 对象类型转换
         */
        for (UneCbill uneCbill : cbillList) {
            UneCbillVo uneCbillVo = new UneCbillVo();
            BeanUtil.copyProperties(uneCbill, uneCbillVo);
            cbillVos.add(uneCbillVo);
        }
        long currentPage = page.getCurrent();
        long pageSize = page.getSize();
        long total = page.getTotal();
        Page<UneCbillVo> page1 = new Page<>(currentPage, pageSize, total);
        return page1.setRecords(cbillVos);
    }

    /**
     * 保存开票主表以及开票明细
     * @param uneCbill
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int addUneCbill(UneCbill uneCbill) {
        return uneCbillMapper.insert(uneCbill);
    }

    @Override
    public int billCount() {
        return uneCbillMapper.billCount();
    }

    /**
     *根据票据ID和校验码查询一张数据
     * @param queryWrapper
     * @return
     */
    @Override
    public UneCbill getBillByIdAndCheckCode(QueryWrapper<UneCbill> queryWrapper) {
        return uneCbillMapper.selectOne(queryWrapper);
    }
}
