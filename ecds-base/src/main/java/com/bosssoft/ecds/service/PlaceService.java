package com.bosssoft.ecds.service;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PlaceDTO;
import com.bosssoft.ecds.entity.po.PlacePO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
public interface PlaceService extends IService<PlacePO> {
    /**
     * 插入开票点信息
     *
     * @param placeDTO
     * @return boolean
     */
    ResponseResult save(PlaceDTO placeDTO);

    /**
     * 修改开票点信息
     *
     * @param placeDTO
     * @return boolean
     */
    ResponseResult update(PlaceDTO placeDTO);

    /**
     * 删除开票点
     *
     * @param placeDTO
     * @return boolean
     */
    ResponseResult delete(PlaceDTO placeDTO);

    /**
     * 分页查询开票点信息
     *
     * @param pageDTO
     * @return PageVO
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<PlaceDTO> pageDTO);

    /**
     * 批量删除开票点
     *
     * @param placeDTOList
     * @return boolean
     */
    ResponseResult batchDelete(List<PlaceDTO> placeDTOList);

    /**
     * 批量审核
     *
     * @param placeDTOList
     * @return
     */
    ResponseResult batchVerify(List<PlaceDTO> placeDTOList);
}
