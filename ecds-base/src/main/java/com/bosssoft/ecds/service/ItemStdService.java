package com.bosssoft.ecds.service;

import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemStdDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemStdPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.ItemStdVO;
import com.bosssoft.ecds.entity.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
public interface ItemStdService extends IService<ItemStdPO> {

    /**
     * 插入项目标准相关信息
     *
     * @param itemStdDTO 输入项目标准相关信息
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult save(ItemStdDTO itemStdDTO);

    /**
     * 修改项目标准信息
     *
     * @param itemStdDTO 修改后的项目标准信息
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult update(ItemStdDTO itemStdDTO);

    /**
     * 删除单个项目标准
     *
     * @param itemStdDTO 需要删除的项目标准的id
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult delete(ItemStdDTO itemStdDTO);

    /**
     * 分页查询项目标准信息
     *
     * @param pageDTO 输入分页信息,limit、page、keyword、isenable
     *                keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<ItemStdDTO> pageDTO);

    /**
     * 批量删除项目标准信息
     *
     * @param itemStdDTOS 需要删除的项目标准的idList
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult batchDelete(List<ItemStdDTO> itemStdDTOS);

    /**
     * 主要用于批量审核,修改项目标准启用状态，输入需要修改
     *
     * @param itemStdDTOS 需要修改审核的项目idList
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult batchVerify(List<ItemStdDTO> itemStdDTOS);

    /**
     * 通过项目编码查询项目标准
     *
     * @param itemStdDTO 输入项目编码
     * @return 项目标准相关信息
     */
    QueryResponseResult<ItemStdVO> getByItemCode(ItemStdDTO itemStdDTO);

}
