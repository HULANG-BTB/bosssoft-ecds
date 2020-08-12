package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AgenBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenBillPO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-12
 */
public interface AgenBillService extends IService<AgenBillPO> {

    /**
     * 插入单位票据关系数据
     *
     * @param agenBillDTO
     * @return
     */
    ResponseResult save(AgenBillDTO agenBillDTO);

    /**
     * 删除单位票据关系数据
     *
     * @param agenBillDTO
     * @return
     */
    ResponseResult delete(AgenBillDTO agenBillDTO);

    /**
     * 分页查询单位的可用票据
     *
     * @param pageDTO
     * @return
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<BillTypePO> pageDTO);

    /**
     * 批量删除单位可用票据
     *
     * @param agenBillDTOList
     * @return boolean
     */
    ResponseResult batchDelete(List<AgenBillDTO> agenBillDTOList);

}
