package com.bosssoft.ecds.service;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemPageVO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
public interface ItemService extends IService<ItemPO> {

    /**
     * 插入项目
     *
     * @param itemDTO
     * @return boolean
     */
    ResponseResult save(ItemDTO itemDTO);

    /**
     * 修改项目信息
     *
     * @param itemDTO
     * @return boolean
     */
    ResponseResult update(ItemDTO itemDTO);

    /**
     * 删除项目信息
     *
     * @param itemDTO
     * @return boolean
     */
    ResponseResult delete(ItemDTO itemDTO);

    /**
     * 分页查询项目信息
     *
     * @param itemPageVO
     * @return PageVO
     */
    QueryResponseResult<ItemPageVO> listByPage(ItemPageVO<ItemDTO> itemPageVO);

    /**
     * 批量删除项目信息
     *
     * @param itemDTOS
     * @return boolean
     */
    ResponseResult batchDelete(List<ItemDTO> itemDTOS);

    /**
     * 批量审核
     *
     * @param itemDTOS
     * @return
     */
    ResponseResult batchVerify(List<ItemDTO> itemDTOS);

    /**
     * 查询所有项目信息
     *
     * @param
     * @return
     */
    ResponseResult getItemAll();

}
