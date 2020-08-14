package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.financial.MonitorRecordMapper;
import com.bosssoft.ecds.dao.financial.WriteOffBillItemMapper;
import com.bosssoft.ecds.dao.financial.WriteOffBillSummaryMapper;
import com.bosssoft.ecds.dao.financial.WriteOffMapper;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialWriteOffServiceImpl implements FinancialWriteOffService {

    @Autowired(required = false)
    private WriteOffMapper writeOffMapper;

    @Autowired(required = false)
    private WriteOffBillItemMapper writeOffBillItemMapper;

    @Autowired(required = false)
    private WriteOffBillSummaryMapper writeOffBillSummaryMapper;

    @Autowired(required = false)
    private MonitorRecordMapper monitorRecordMapper;

    /**
     * 获取单位端传来的核销信息
     * 接收一段时间的下级单位传来的核销信息
     *
     * @param object
     * @return java.util.List
     */
    @Override
    public List<Object> receive(Object object) {
        // 参数应该是一段时间，或者是没有参数
        // object可以为空

        return null;
    }

    /**
     * 退回单位端传来的核销信息
     * 需要退回的核销信息根据单位ID回到单位端进行修改
     *
     * @param list
     * @return
     */
    @Override
    public boolean sendBack(List<Object> list) {
        // 这里传入的是需要退回的核销信息的list
        // list不能为空, 前端进行判断，为空无法继续
        for (Object object: list) {
            // 根据单位ID退回具体的单位
        }

        return false;
    }

    /**
     * 获取核销信息详情
     *
     * @param object
     * @return java.lang.Object
     */
    @Override
    public Object getDetails(Object object) {
        // 获取票据详细信息

        return null;
    }

    /**
     * 获取单位电子档案
     *
     * @param object
     * @return java.lang.Object
     */
    @Override
    public Object getUnitDetails(Object object) {
        // 获取单位详细信息

        return null;
    }

    /**
     * 存入核销结果
     *
     * @param object
     * @return java.lang.Object
     */
    @Override
    public boolean setResult(Object object) {
        // 点击审验后， 对审验结果进行保存

        return false;
    }
}
