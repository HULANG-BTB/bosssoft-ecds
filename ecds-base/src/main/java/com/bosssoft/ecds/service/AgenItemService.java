package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AgenItemDTO;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenItemPO;
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
     * 插入单位项目关系数据
     *
     * @param agenItemDTO
     * @return
     */
    ResponseResult save(AgenItemDTO agenItemDTO);

    /**
     * 删除单位票据关系数据
     *
     * @param agenItemDTO
     * @return
     */
    ResponseResult delete(AgenItemDTO agenItemDTO);

    /**
     * 分页查询单位的可用票据
     *
     * @param pageDTO
     * @return
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<ItemDTO> pageDTO);

    /**
     * 批量删除单位可用票据
     *
     * @param agenItemDTOList
     * @return boolean
     */
    ResponseResult batchDelete(List<AgenItemDTO> agenItemDTOList);

}
