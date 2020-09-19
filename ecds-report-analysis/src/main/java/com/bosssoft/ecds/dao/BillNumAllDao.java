package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.domain.search.FbbStockDestroySearch;
import com.bosssoft.ecds.domain.vo.BillNumAllVO;
import com.bosssoft.ecds.domain.vo.MultiBillVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author syf
 * @Date 2020/8/24 16:53
 */
@Mapper
@Repository
public interface BillNumAllDao extends BaseMapper<BillNumAllVO> {
    @Select("select sum(f_amount) from fbb_stock where (YEAR(CURDATE())-YEAR(f_create_time))<7 and f_logic_delete=0 and f_bill_code=#{bill} GROUP BY YEAR(f_create_time) ORDER BY YEAR(f_create_time)")
    List<Integer> getPastYears(@Param("bill") String bill);

    @Select("select sum(f_amount) from fbb_stock where YEAR(f_create_time)=YEAR(#{date}) and f_bill_code=#{billType} GROUP BY MONTH(f_create_time) ORDER BY MONTH(f_create_time)")
    List<Integer> getPastMonths(FbbStockDestroySearch fbbStockDestroySearch);

    @Select("SELECT SUM(f_amount) as value ,f_bill_name as name from fbb_stock where YEAR(f_create_time)=YEAR(#{date}) GROUP BY f_bill_code ")
    List<MultiBillVO> getMultiBillNum(@Param("date") String date);


}
