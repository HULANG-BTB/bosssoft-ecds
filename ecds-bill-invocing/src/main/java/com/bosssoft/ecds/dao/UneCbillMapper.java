package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.UneCbill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UneCbillMapper extends BaseMapper<UneCbill> {

    @Select("select * from une_cbill")
    List<UneCbill> selectPageVO(Page<UneCbill> page);

    @Select("select count(*) from une_cbill")
    int billCount();

    @Select("select count(*) from une_cbill where f_state=2")
    int passBillCount();

    @Select("select * from une_cbill where f_state=2")
    List<UneCbill> selectPassPageVO(Page<UneCbill> page);
}
