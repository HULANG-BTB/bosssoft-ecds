package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.po.VoucherPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */
public interface VoucherService extends IService<VoucherPO> {

    /**
     * 生成入账凭证
     *
     * @return Boolean
     */
    Boolean generateVoucher(VoucherDTO voucherDTO);

    /**
     * 查看入账凭证信息列表
     *
     * @return List<VoucherDTO>
     */
    ResponseResult listAll();

    /**
     * 通过入账凭证号获取电子凭证
     *
     * @return VoucherDTO
     */
    ResponseResult getByAccountId(VoucherDTO voucherDTO);

}
