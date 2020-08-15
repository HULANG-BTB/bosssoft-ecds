package com.bosssoft.ecds.service;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.PageVO;

/**
 * @InterfaceName CbillAccountingQueryService
 * @Description 专注于查询信息
 * @Auther UoweMe
 * @Date 2020/8/14 9:39
 * @Version 1.0
 */
public interface CbillAccountingQueryService {

    /**
     * 查询入账单据列表
     *
     * @return 入账数据
     */
    ResponseResult listAll();

    /**
     * 分页查询全部数据
     *
     * @return
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<CbillAccountingDTO> pageDTO);

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

}
