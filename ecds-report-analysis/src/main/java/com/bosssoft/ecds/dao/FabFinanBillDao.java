package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.domain.po.FabFinanBillPO;
import com.bosssoft.ecds.domain.search.FabFinanBillSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author syf
 */
@Repository
@Mapper
public interface FabFinanBillDao extends BaseMapper<FabFinanBillPO> {


    @Select("<script> "+
    "select * from fab_finan_bill " +
    "where f_logic_delete=0 "+
    "<if test='type !=null and type!=\"\"' >"+
    "and f_bill_precode=#{type}"+
    "</if> "+
    "<if test='billName !=null and billName!=\"\"' >"+
    "and f_bill_name like CONCAT('%',#{billName},'%')"+
    "</if> "+
    "<if test='dateList !=null and dateList.size()&gt;0'  >"+
    "and f_eff_date between"+
    "<foreach collection='dateList' item='item' separator='and' >"+
    "#{item}"+
    "</foreach>"+
    "</if> "+
    "</script>"
    )
    List<FabFinanBillPO> selectSearch(FabFinanBillSearch fabFinanBillSearch);

}