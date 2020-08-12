package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.AccBaseInfoDTO;
import com.bosssoft.ecds.entity.dto.AccBillDTO;
import com.bosssoft.ecds.entity.dto.AccIntoInfoDTO;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;

import java.util.List;

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
    List<CbillAccountingDTO> listAll();

    /**
     * 根据校验码查询入账单据
     *
     * @return 入账数据
     */
    CbillAccountingDTO selectBySerialId(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 通过票据号码查询入账信息
     *
     * @return 入账数据
     */
    CbillAccountingDTO selectByBillId(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 通过单位代码查询入账信息
     *
     * @return 入账数据
     */
    List<CbillAccountingDTO> selectByAgenIdcode(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 开票阶段插入基础信息
     *
     * @return
     */
    Boolean insertAccBaseInfo(AccBaseInfoDTO accBaseInfoDto);

    /**
     * 缴费阶段查询应缴金额
     *
     * @return
     */
    CbillAccountingDTO selectAccount(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 缴费阶段插入入账信息
     *
     * @return
     */
    Boolean insertAccount(AccIntoInfoDTO accIntoInfoDTO);

    /**
     * 开票前查询入账完成状态
     *
     * @return
     */
    Boolean selectStatus(AccBaseInfoDTO accBaseInfoDto);

    /**
     * 开票后插入票据信息
     *
     * @return
     */
    Boolean insertBillInfo(AccBillDTO accBillDto);

}
