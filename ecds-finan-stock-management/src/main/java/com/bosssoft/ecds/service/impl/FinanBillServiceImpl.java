package com.bosssoft.ecds.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.bosssoft.ecds.mapper.FinanBillMapper;
import com.bosssoft.ecds.service.FinanBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@Service
public class FinanBillServiceImpl extends ServiceImpl<FinanBillMapper, FinanBillPo> implements FinanBillService {

    @Autowired
    private FinanBillMapper finanBillMapper;

    /**
     *              发放出库
     * ******************************************************************************
     */

    /**
     *              主体业务外界调用
     * *********************************************
     */

    /**
     *              查 get query
     * *********************************************
     */

    /**
     * 根据票据编码获取全部票据
     *
     * @param billCode 票据编码
     */
    @Override
    public FinanBillPo getOneByBillCode(Long billCode) {
        return getOneByBillCode(billCode, null);
    }

    /**
     * 根据票据编码获取票据，true可用false不可用
     *
     * @param billCode 票据编码
     * @param valid 是否可用
     */
    @Override
    public FinanBillPo getOneByBillCode(Long billCode, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper.eq("f_bill_code", billCode);
        return finanBillMapper.selectOne(queryWrapper);
    }

    /**
     * 根据仓库id获取全部票据
     *
     * @param warehouseId 仓库id
     */
    @Override
    public List<FinanBillPo> queryByWarehouseId(Long warehouseId) {
        return queryByWarehouseId(warehouseId, null);
    }

    /**
     * 根据仓库id获取票据，true可用false不可用
     *
     * @param warehouseId 仓库id
     * @param valid 是否可用
     */
    @Override
    public List<FinanBillPo> queryByWarehouseId(Long warehouseId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_warehouse_id", warehouseId)
                .groupBy("f_bill_precode");
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 根据票据代码获取全部票据
     *
     * @param billPrecode 票据代码
     */
    @Override
    public List<FinanBillPo> queryByBillPrecode(Long billPrecode) {
        return queryByBillPrecode(billPrecode, null);
    }

    /**
     * 根据票据代码获取票据，true可用false不可用
     *
     * @param billPrecode 票据代码
     * @param valid 是否可用
     */
    @Override
    public List<FinanBillPo> queryByBillPrecode(Long billPrecode, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper.eq("f_bill_precode", billPrecode);
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 根据创建年份获取该年的全部票据
     *
     * @param year 年份
     */
    @Override
    public List<FinanBillPo> queryByYear(String year) {
        return queryByYear(year, null);
    }

    /**
     * 根据创建年份获取该年的票据，true可用false不可用
     *
     * @param year 年份
     * @param valid 是否可用
     */
    @Override
    public List<FinanBillPo> queryByYear(String year, Boolean valid) {
        DateTime startTime = DateUtil.parse(year + "-01-01"); //年初
        DateTime endTime = DateUtil.endOfYear(startTime); //年底
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper.between("f_create_time", startTime, endTime);
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 根据票据编码，查找多条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param startId 开始号
     * @param endId 结束号
     */
    @Override
    public List<FinanBillPo> queryBills(String billPrecode, String startId, String endId) {
        return queryBills(billPrecode, startId, endId, null);
    }

    /**
     * 根据票据编码，查找多条财政票据记录，true可用false不可用
     *
     * @param billPrecode 票据代码
     * @param startId 开始号
     * @param endId 结束号
     * @param valid 是否可用
     */
    @Override
    public List<FinanBillPo> queryBills(String billPrecode, String startId, String endId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_bill_precode", billPrecode)
                .between("f_bill_id", startId, endId)
        ;
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 条件获取：可发放票（未发放，未作废，未失效（在生效范围内），未核销）
     */
    @Override
    public QueryWrapper<FinanBillPo> conditionPutoutValid() {
        QueryWrapper<FinanBillPo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("f_is_putout", 0)
                .eq("f_is_invalid", 0)
                .le("f_eff_date", DateTime.now())
                .ge("f_exp_date", DateTime.now())
                .eq("f_hx_status", 0)
        ;
        return queryWrapper;
    }

    /**
     * 条件获取：不可发放票（已发放，已作废，已失效（不在生效范围内），已核销）
     */
    @Override
    public QueryWrapper<FinanBillPo> conditionPutoutInvalid() {
        QueryWrapper<FinanBillPo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("f_is_putout", 1)
                .or()
                .eq("f_is_invalid", 1)
                .or()
                .ge("f_eff_date", DateTime.now())
                .or()
                .le("f_exp_date", DateTime.now())
                .or()
                .eq("f_hx_status", 1)
        ;
        return queryWrapper;
    }

    /**
     * 条件获取：获取全部（null）or可用（true）or不可用（false）票
     *
     * @param valid 是否可用
     */
    @Override
    public QueryWrapper<FinanBillPo> conditionValid(Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper;
        //获取全部
        if (valid == null) {
            queryWrapper = new QueryWrapper<>();
        }
        if (valid) {
            //获取可用
            queryWrapper = conditionPutoutValid();
        } else {
            //获取不可用
            queryWrapper = conditionPutoutInvalid();
        }
        return queryWrapper;
    }

    /**
     *              增 insert
     * ***************************************************
     */


    /**
     *              删 delete
     * ***************************************************
     */

    /**
     * 根据票据编码，删除单条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param billId 票据号
     */
    @Override
    public Integer deleteOneBill(String billPrecode, String billId) {
        return deleteOneBill(billPrecode, billId, null);
    }

    /**
     * 根据票据编码，删除单条财政票据记录，true可用，false不可用
     *
     * @param billPrecode 票据代码
     * @param billId 票据号
     * @param valid 是否可用
     */
    @Override
    public Integer deleteOneBill(String billPrecode, String billId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_bill_precode", billPrecode)
                .eq("f_bill_id", billId)
        ;
        return finanBillMapper.delete(queryWrapper);
    }

    /**
     * 根据票据编码，删除多条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param startId 起始号
     * @param endId 终止号
     */
    @Override
    public Integer deleteBills(String billPrecode, String startId, String endId) {
        return deleteBills(billPrecode, startId, endId, null);
    }

    /**
     * 根据票据编码，删除多条财政票据记录，true可用，false不可用
     *
     * @param billPrecode 票据代码
     * @param startId 起始号
     * @param endId 终止号
     * @param valid 是否可用
     */
    @Override
    public Integer deleteBills(String billPrecode, String startId, String endId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_bill_precode", billPrecode)
                .between("f_bill_id", startId, endId)
        ;
        return finanBillMapper.delete(queryWrapper);
    }

    /**
     *              改 update
     * **************************************************
     */

}
