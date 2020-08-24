package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.po.VoucherPO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.response.QueryResponseResult;
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
public interface VoucherService extends IService<VoucherPO> {

    /**
     * 生成入账凭证
     *
     * @return Boolean
     */
    Boolean generateVoucher(VoucherDTO voucherDTO);

    /**
     * 通过入账凭证号获取电子凭证
     *
     * @return ResponseResult
     */
    ResponseResult getByAccountId(VoucherDTO voucherDTO);

    /**
     * 分页查询入账凭证信息列表
     *
     * @return List<VoucherDTO>
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<VoucherDTO> pageDTO);

    /**
     * 删除入账凭证信息
     *
     * @return ResponseResult
     */
    ResponseResult delete(VoucherDTO voucherDTO);

    /**
     * 批量删除入账凭证信息
     *
     * @return ResponseResult
     */
    ResponseResult batchDelete(List<VoucherDTO> voucherDTOList);

    /**
     * 更新入账凭证信息
     *
     * @return ResponseResult
     */
    ResponseResult updateVoucher(VoucherDTO voucherDTO);
}
