package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.entity.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */
public interface CbillAccountingService extends IService<CbillAccountingPO> {
    /**
     * 查询入账单据列表
     *
     * @return 入账数据
     */
    ResponseResult listAll();

    /**
     * 分页查询
     *
     * @return
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<CbillAccountingPO> pageDTO);

    /**
     * 根据校验码查询入账单据
     *
     * @return 入账数据
     */
    ResponseResult selectBySerialId(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 通过票据号码查询入账信息
     *
     * @return 入账数据
     */
    ResponseResult selectByBillId(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 通过单位代码查询入账信息
     *
     * @return 入账数据
     */
    ResponseResult selectByAgenIdcode(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 开票阶段插入基础信息
     *
     * @return
     */
    ResponseResult insertAccBaseInfo(AccBaseInfoDTO accBaseInfoDto);

    /**
     * 缴费阶段查询应缴金额
     *
     * @return
     */
    ResponseResult selectAccount(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 缴费阶段插入入账信息
     *
     * @return
     */
    ResponseResult insertAccount(AccIntoInfoDTO accIntoInfoDTO);

    /**
     * 开票前查询入账完成状态
     *
     * @return
     */
    ResponseResult selectStatus(AccBaseInfoDTO accBaseInfoDto);

    /**
     * 开票后插入票据信息
     *
     * @return
     */
    ResponseResult insertBillInfo(AccBillDTO accBillDto);

}
