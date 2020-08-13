package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AgenBillDTO;
import com.bosssoft.ecds.entity.dto.AgenItemDTO;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenItemPO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-12
 */
public interface AgenItemService extends IService<AgenItemPO> {

    /**
     * 分页查询单位的可用票据
     *
     * @param pageDTO
     * @return
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<ItemDTO> pageDTO);

    /**
     * 查询单位所有的可用项目，不通过分页显示
     *
     * @param agenItemDTO 输入单位编码
     * @return
     */
    QueryResponseResult<List<ItemVO>> getItemAll(AgenItemDTO agenItemDTO);


    /**
     * 批量维护单位项目关系数据
     *
     * @param agenItemDTOList
     * @return
     */
    ResponseResult updateBatch(List<AgenItemDTO> agenItemDTOList);

}
