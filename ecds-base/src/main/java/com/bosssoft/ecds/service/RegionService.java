package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.vo.AddRegionVO;
import com.bosssoft.ecds.entity.vo.EditRegionVO;
import com.bosssoft.ecds.entity.vo.QueryRegionRequestVO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;

/**
 * @author: lpb
 * @create: 2020-08-05 11:25
 */
public interface RegionService {

    /**
     * 获取省市区三级全部信息
     * @return
     */
    QueryResponseResult getAllRegion();

    /**
     * 获取分页的区划信息
     * @param page 页码
     * @param size 每页数量
     * @param queryRegionRequest 查询参数
     * @return
     */
    QueryResponseResult list(Integer page, Integer size, QueryRegionRequestVO queryRegionRequest);

    /**
     * 添加新的区划信息
     * @param addRegion 区划信息
     * @return
     */
    ResponseResult add(AddRegionVO addRegion);

    /**
     * 更新区划信息
     * @param editRegion 区划信息
     * @return
     */
    ResponseResult edit(EditRegionVO editRegion);

    /**
     * 删除现有的区划信息
     * @param id 待删除区划的ID
     * @return
     */
    ResponseResult delete(Long id);

    /**
     * 获取所有省份分类信息
     * @return
     */
    QueryResponseResult getProvinceCategory();

    /**
     * 获取所有省市的分类信息
     * @return
     */
    QueryResponseResult getCityCategory();

    /**
     * 根据父节点id获取祖父节点id
     * @param pid 父节点id
     * @return
     */
    QueryResponseResult getGrandId(Long pid);

    /**
     * 根据业务上级节点获取业务上级及其上级id
     * @param pid 业务上级节点id
     * @return
     */
    QueryResponseResult getBusIds(Long pid);
}
