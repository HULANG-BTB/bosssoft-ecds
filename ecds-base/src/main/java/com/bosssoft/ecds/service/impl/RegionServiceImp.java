package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResult;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.dao.RegionMapper;
import com.bosssoft.ecds.entity.po.AuthRegion;
import com.bosssoft.ecds.entity.vo.AddRegionVO;
import com.bosssoft.ecds.entity.vo.EditRegionVO;
import com.bosssoft.ecds.entity.vo.QueryRegionRequestVO;
import com.bosssoft.ecds.entity.vo.RegionExt;
import com.bosssoft.ecds.enums.RegionCode;
import com.bosssoft.ecds.service.RegionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lpb
 * @create: 2020-08-05 11:26
 */
@Service
public class RegionServiceImp implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * 获取省市区三级全部信息
     * @return
     */
    @Override
    public QueryResponseResult<List> getAllRegion() {
        List<RegionExt> allRegion = regionMapper.getAllRegion();
        for(RegionExt ext : allRegion){
            if(ext.getChildren().size() != 0){
                for(RegionExt extC : ext.getChildren()){
                    if(extC.getChildren().size() == 0){
                        extC.setChildren(null);
                    }
                }
            }else{
                ext.setChildren(null);
            }
        }
        return new QueryResponseResult(CommonCode.SUCCESS,allRegion);
    }

    /**
     * 获取分页的区划信息
     * @param page               页码
     * @param size               每页数量
     * @param queryRegionRequest 查询参数
     * @return
     */
    @Override
    public QueryResponseResult list(Integer page, Integer size, QueryRegionRequestVO queryRegionRequest) {
        if(queryRegionRequest == null){
            queryRegionRequest = new QueryRegionRequestVO();
        }
        if(page <= 0){
            page = 1;
        }
        if(size <= 0){
            size = 10;
        }
        QueryWrapper queryWrapper = wrapRegionRequest(queryRegionRequest);
        Page<AuthRegion> pageInfo = new Page<>(page,size);
//        IPage<AuthRegion> iPage = regionMapper.selectPage(pageInfo, queryWrapper);
        IPage<AuthRegion> iPage = regionMapper.pageList(pageInfo,queryRegionRequest);
        QueryResult<AuthRegion> queryResult = new QueryResult<>();
        queryResult.setList(iPage.getRecords());
        queryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 添加新的区划信息
     * @param addRegion 区划信息
     * @return
     */
    @Override
    @Transactional
    public ResponseResult add(String userName, Long uid, AddRegionVO addRegion) {
        if(StringUtils.isEmpty(addRegion.getName())){
           return ResponseResult.FAIL();
        }
        QueryWrapper<AuthRegion> queryWrapper = new QueryWrapper();
        queryWrapper.eq("f_name",addRegion.getName()).or()
            .eq("f_code",addRegion.getCode());
        List<Map<String, Object>> maps = regionMapper.selectMaps(queryWrapper);
        if(maps.size() > 0){
            return new ResponseResult(RegionCode.REGION_NAME_EXISTS);
        }
        AuthRegion authRegion = new AuthRegion();
        BeanUtils.copyProperties(addRegion,authRegion);
        authRegion.setOperator(userName);
        authRegion.setOperatorId(uid);
        authRegion.setCreateTime(new Date());
        regionMapper.insert(authRegion);
        return ResponseResult.SUCCESS();
    }

    /**
     * 更新区划信息
     * @param editRegion 区划信息
     * @return
     */
    @Override
    @Transactional
    public ResponseResult edit(EditRegionVO editRegion) {
        AuthRegion authRegion = regionMapper.selectById(editRegion.getId());
        if(authRegion == null){
            return new ResponseResult(RegionCode.REGION_NOTEXISTS);
        }
        BeanUtils.copyProperties(editRegion,authRegion);
        authRegion.setUpdateTime(new Date());
        regionMapper.updateById(authRegion);
        return ResponseResult.SUCCESS();
    }

    /**
     * 删除现有的区划信息
     * @param id 待删除区划的ID
     * @return
     */
    @Override
    @Transactional
    public ResponseResult delete(Long id) {
        Map<String,Object> map = new HashMap<>();
        map.put("f_parentid",id);
        List<AuthRegion> list = regionMapper.selectByMap(map);
        if(list.size() > 0){
            return new ResponseResult(RegionCode.CATEGORY_HASSON);
        }
        AuthRegion authRegion = regionMapper.selectById(id);
        if(authRegion == null){
            return  new ResponseResult(RegionCode.REGION_NOTEXISTS);
        }
        regionMapper.deleteById(id);
        return ResponseResult.SUCCESS();
    }

    /**
     * 获取所有省份分类信息
     * @return
     */
    @Override
    public QueryResponseResult getProvinceCategory() {
        List<RegionExt> province = regionMapper.getProvince();
        return new QueryResponseResult(CommonCode.SUCCESS,province);
    }

    /**
     * 获取所有省市的分类信息
     * @return
     */
    @Override
    public QueryResponseResult getCityCategory() {
        List<RegionExt> city = regionMapper.getCity();
        return new QueryResponseResult(CommonCode.SUCCESS,city);
    }

    /**
     * 根据父节点id获取祖父节点id
     * @param pid 父节点id
     * @return
     */
    @Override
    public QueryResponseResult getGrandId(Long pid) {
        AuthRegion authRegion = regionMapper.selectById(pid);
        if(authRegion == null){
            return new QueryResponseResult(RegionCode.REGION_NOTEXISTS,null);
        }
        return new QueryResponseResult(CommonCode.SUCCESS,authRegion.getParentId());
    }

    /**
     * 将查询条件进行包装
     * @param queryRegionRequest 查询条件
     * @return
     */
    private QueryWrapper wrapRegionRequest(QueryRegionRequestVO queryRegionRequest){
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryRegionRequest.getId())){
            queryWrapper.eq("f_id",queryRegionRequest.getId());
        }
        if(!StringUtils.isEmpty(queryRegionRequest.getParentId())){
            queryWrapper.eq("f_id",queryRegionRequest.getParentId())
                    .or()
                    .eq("f_parentid",queryRegionRequest.getParentId());
        }
        return queryWrapper;
    }
}
