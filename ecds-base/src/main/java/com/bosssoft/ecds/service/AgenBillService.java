package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AgenBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenBillPO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.PageVO;

import javax.management.Query;
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
     * 批量插入单位票据关系数据
     *
     * @param agenBillDTOList
     * @return
     */
    ResponseResult updateBatch(List<AgenBillDTO> agenBillDTOList);

    /**
     * 查询单位的可用票据，供单位端使用
     * 分页展示
     *
     * @param pageDTO
     * @return
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<BillTypePO> pageDTO);

    /**
     * 查询单位所有的可用票据，不通过分页显示
     *
     * @param agenBillDTO 输入单位编码
     * @return
     */
    QueryResponseResult<List<BillTypePO>> getBill(AgenBillDTO agenBillDTO);
}
