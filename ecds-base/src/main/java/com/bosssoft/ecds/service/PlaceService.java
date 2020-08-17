package com.bosssoft.ecds.service;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PlaceDTO;
import com.bosssoft.ecds.entity.po.PlacePO;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface PlaceService extends IService<PlacePO> {

    /**
     * 插入单位开票点相关信息
     *
     * @param placeDTO 单位开票点相关信息
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult save(PlaceDTO placeDTO);

    /**
     * 修改开票点信息
     *
     * @param placeDTO 修改后的开票点信息
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult update(PlaceDTO placeDTO);

    /**
     * 删除单个开票点
     *
     * @param placeDTO 需要删除的开票点id
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult delete(PlaceDTO placeDTO);

    /**
     * 分页查询开票点信息
     *
     * @param pageDTO 输入分页信息,limit、page、keyword、isenable
     *                keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<PlaceDTO> pageDTO);

    /**
     * 批量删除开票点
     *
     * @param placeDTOList 需要删除的开票点idList
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult batchDelete(List<PlaceDTO> placeDTOList);

    /**
     * 主要用于批量审核,修改开票点启用状态，输入需要修改
     *
     * @param placeDTOList 需要修改审核的开票点id
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult batchVerify(List<PlaceDTO> placeDTOList);
}
