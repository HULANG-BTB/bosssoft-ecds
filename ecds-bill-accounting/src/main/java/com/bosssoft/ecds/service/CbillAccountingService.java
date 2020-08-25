package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.AccBaseInfoDTO;
import com.bosssoft.ecds.entity.dto.AccBillDTO;
import com.bosssoft.ecds.entity.dto.AccIntoInfoDTO;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.response.ResponseResult;

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
     * 开票阶段插入基础信息
     *
     * @return
     */
    ResponseResult insert(AccBaseInfoDTO accBaseInfoDto);

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

    /**
     * 删除入账信息
     *
     * @return
     */
    ResponseResult delete(CbillAccountingDTO cbillAccountingDTO);

    /**
     * 批量删除入账信息
     *
     * @return
     */
    ResponseResult batchDelete(List<CbillAccountingDTO> cbillAccountingDTOList);

    /**
     * 修改入账状态
     *
     * @return
     */
    ResponseResult updateStatus(CbillAccountingDTO cbillAccountingDTO);
}
