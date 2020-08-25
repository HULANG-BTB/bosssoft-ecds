package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.domain.po.FbeStockInPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zouyou
 * @version 1.0
 * @description 生命周期模块的dao
 * @date 2020/8/24 18:38
 */
@Mapper
@Repository
public interface FbeStockInDao extends BaseMapper<FbeStockInPO> {
    /**
     * 返回最近7天入库票据的数量
     * @author zouyou
     * @return java.util.List<java.lang.Integer>
     * @date 2020/8/24 23:30
     */
    @Select(" select IFNULL(b.f_number,0) as f_number,a.click_date" +
            " from (" +
            " SELECT curdate() as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 1 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 2 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 3 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 4 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 5 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 6 day) as click_date" +
            ") a left join (" +
            "  SELECT SUM(f_number) as f_number,DATE(f_create_time) as f_create_time FROM fbe_stock_in_item " +
            " GROUP BY f_create_time" +
            ") b on a.click_date = b.f_create_time ORDER BY click_date;")
    List<Integer> selectNumIndex();

    /**
     * 查询入库票据所有种类票据的数量
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.po.FbeStockInPO>
     * @date 2020/8/24 23:30
     */
    @Select("SELECT f_bill_name,SUM(f_number) as f_number from fbe_stock_in_item GROUP BY f_bill_name")
    List<FbeStockInPO> selectNum();

    /**
     * 查询对应时间的入库票据数量
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.po.FbeStockInPO>
     * @date 2020/8/24 23:31
     */
    @Select("SELECT f_number,f_create_time FROM `fbe_stock_in_item` ")
    List<FbeStockInPO> selectBar();
}
