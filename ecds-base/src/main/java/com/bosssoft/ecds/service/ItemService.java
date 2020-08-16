package com.bosssoft.ecds.service;


import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.itemvo.ItemPageVO;


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
     * 插入项目相关信息
     *
     * @param itemDTO 输入项目相关信息
     * @return 回成功或者失败的code和msg
     */
    ResponseResult save(ItemDTO itemDTO);

    /**
     * 修改项目信息
     *
     * @param itemDTO 输入修改后的项目信息
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult update(ItemDTO itemDTO);

    /**
     * 删除单个项目
     *
     * @param itemDTO 输入需要删除项目的id
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult delete(ItemDTO itemDTO);

    /**
     * 分页查询项目信息
     *
     * @param itemPageVO 输入分页信息,limit、page、keyword、isenable
     *                   keyword为空时普通查询，keyword不为空时模糊查询
     *                   isenable为0时查询出未审核的项目，为1时查询出审核的项
     * @return limit、page、total、items
     */
    ResponseResult listByPage(ItemPageVO<ItemDTO> itemPageVO);

    /**
     * 批量删除项目信息
     *
     * @param itemDTOS 输入需要删除的项目idList
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult batchDelete(List<ItemDTO> itemDTOS);

    /**
     * 主要用于批量审核,修改项目启用状态
     *
     * @param itemDTOS 输入需要修改审核的项目idList
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult batchVerify(List<ItemDTO> itemDTOS);

    /**
     * 查询所有项目信息
     *
     * @return 项目信息集合
     */
    ResponseResult getItemAll();

}
