package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.agendto.AgenEnableBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenEnableBillPO;
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
public interface AgenEnableBillService extends IService<AgenEnableBillPO> {

    /**
     * 批量维护单位票据关系数据
     *
     * @param agenBillDTOList 输入单位编码和票据编码
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult updateBatch(List<AgenEnableBillDTO> agenBillDTOList);

    /**
     * 查询单位的可用票据，供单位端使用
     * 分页展示
     *
     * @param pageDTO 输入分页信息,limit、page、keyword
     *                keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<BillTypePO> pageDTO);

    /**
     * 查询单位所有的可用票据，不通过分页显示
     *
     * @param agenBillDTO 输入单位编码
     * @return 返回出单位所有的可用票据
     */
    QueryResponseResult<List<BillTypePO>> getBill(AgenEnableBillDTO agenBillDTO);
}
