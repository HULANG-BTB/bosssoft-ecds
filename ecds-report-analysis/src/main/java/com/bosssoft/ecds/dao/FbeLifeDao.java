package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.domain.po.FbeLifePO;
import com.bosssoft.ecds.domain.search.LifeSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zouyou
 * @version 1.0
 * @description 生命周期模块的dao层
 * @date 2020/8/24 18:38
 */
@Mapper
@Repository
public interface FbeLifeDao {

    /**
     * 根据code查询对应票据的生命周期
     * @param lifeSearch
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.po.FbeLifePO>
     * @date 2020/8/24 22:50
     */
    @Select("select f_bill_code," +
            " f_bill_name," +
            " f_stock_in_author," +
            " f_stock_in_warehouse_id," +
            " f_stock_in_create_time," +
            " f_stock_out_author," +
            " f_stock_in_change_status," +
            " f_stock_out_create_time," +
            " f_stock_out_change_status," +
            " f_stock_out_warehouse_id," +
            " f_writeoff_amt," +
            " f_writeoff_operator," +
            " f_writeoff_create_time," +
            " f_writeoff_unit_name," +
            " f_destrory_unit_name," +
            " f_destrory_apply_man," +
            " f_destroy_type," +
            " f_destrory_status," +
            " f_destrory_create_time from fbe_life where f_bill_code = #{code}")
    List<FbeLifePO> getLifeList(LifeSearch lifeSearch);
}
