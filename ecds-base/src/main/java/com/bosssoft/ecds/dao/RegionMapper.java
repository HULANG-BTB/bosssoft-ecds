package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.AuthRegion;
import com.bosssoft.ecds.entity.vo.QueryRegionRequestVO;
import com.bosssoft.ecds.entity.vo.RegionExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lpb
 * @create: 2020-08-05 10:49
 */
@Repository
public interface RegionMapper extends BaseMapper<AuthRegion> {

    IPage<AuthRegion> pageList(Page page, @Param("query") QueryRegionRequestVO queryRegionRequest);

    List<RegionExt> getAllRegion();

    List<RegionExt> getProvince();

    List<RegionExt> getCity();

}
