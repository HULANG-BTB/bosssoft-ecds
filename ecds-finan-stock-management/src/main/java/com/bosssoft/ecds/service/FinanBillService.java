package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
public interface FinanBillService extends IService<FinanBillPo> {
    /**
     *              发放出库
     * ******************************************************************************
     */

    /**
     *              主体业务外界调用
     * *********************************************
     */

    /**
     * 出库操作
     */

    /**
     *              查 get query
     * *********************************************
     */

    /**
     * 根据票据编码获取全部票据
     *
     * @param billCode 票据编码
     *
     * @return FinanBillPo
     */
    FinanBillPo getOneByBillCode(Long billCode);

    /**
     * 根据票据编码获取票据，true可用false不可用
     *
     * @param billCode 票据编码
     * @param valid 是否可用
     *
     * @return FinanBillPo
     */
    FinanBillPo getOneByBillCode(Long billCode, Boolean valid);

    /**
     * 根据仓库id获取全部票据
     *
     * @param warehouseId 仓库id
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryByWarehouseId(Long warehouseId);

    /**
     * 根据仓库id获取票据，true可用false不可用
     *
     * @param warehouseId 仓库id
     * @param valid 是否可用
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryByWarehouseId(Long warehouseId, Boolean valid);

    /**
     * 根据票据代码获取全部票据
     *
     * @param billPrecode 票据代码
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryByBillPrecode(Long billPrecode);

    /**
     * 根据票据代码获取票据，true可用false不可用
     *
     * @param billPrecode 票据代码
     * @param valid 是否可用
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryByBillPrecode(Long billPrecode, Boolean valid);

    /**
     * 根据创建年份获取该年的全部票据
     *
     * @param year 年份
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryByYear(String year);

    /**
     * 根据创建年份获取该年的票据，true可用false不可用
     *
     * @param year 年份
     * @param valid 是否可用
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryByYear(String year, Boolean valid);

    /**
     * 根据票据编码，查找多条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param startId 开始号
     * @param endId 结束号
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryBills(String billPrecode, String startId, String endId);

    /**
     * 根据票据编码，查找多条财政票据记录，true可用false不可用
     *
     * @param billPrecode 票据代码
     * @param startId 开始号
     * @param endId 结束号
     * @param valid 是否可用
     *
     * @return FinanBillPo的list
     */
    List<FinanBillPo> queryBills(String billPrecode, String startId, String endId, Boolean valid);


    /**
     * 条件获取：可发放票（未发放，未作废，未失效（在生效范围内），未核销）
     *
     * @return QueryWrapper
     */
    QueryWrapper<FinanBillPo> conditionPutoutValid();

    /**
     * 条件获取：不可发放票（已发放，已作废，已失效（不在生效范围内），已核销）
     *
     * @return QueryWrapper
     */
    QueryWrapper<FinanBillPo> conditionPutoutInvalid();

    /**
     * 条件获取：获取全部（null）or可用（true）or不可用（false）票
     *
     * @param valid 是否可用
     *
     * @return QueryWrapper
     */
    QueryWrapper<FinanBillPo> conditionValid(Boolean valid);

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
     *
     * @return 0成功1失败
     */
    Integer deleteOneBill(String billPrecode, String billId);

    /**
     * 根据票据编码，删除单条财政票据记录，true可用，false不可用
     *
     * @param billPrecode 票据代码
     * @param billId 票据号
     * @param valid 是否可用
     *
     * @return 0成功1失败
     */
    Integer deleteOneBill(String billPrecode, String billId, Boolean valid);

    /**
     * 根据票据编码，删除多条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param startId 起始号
     * @param endId 终止号
     *
     * @return 票据记录数
     */
    Integer deleteBills(String billPrecode, String startId, String endId);

    /**
     * 根据票据编码，删除多条财政票据记录，true可用，false不可用
     *
     * @param billPrecode 票据代码
     * @param startId 起始号
     * @param endId 终止号
     * @param valid 是否可用
     *
     * @return 票据记录数
     */
    Integer deleteBills(String billPrecode, String startId, String endId, Boolean valid);

    /**
     *              改 update
     * **************************************************
     */

}
