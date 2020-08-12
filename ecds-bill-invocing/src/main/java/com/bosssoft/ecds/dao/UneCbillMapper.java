package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.po.UneCbill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UneCbillMapper extends BaseMapper<UneCbill> {
    IPage<UneCbill> selectPageVO();
}
