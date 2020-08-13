package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.UnePayBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UnePayBookMapper extends BaseMapper<UnePayBook> {

    @Select("select * from une_Paybook")
    List<UnePayBook> selectAll();
}
