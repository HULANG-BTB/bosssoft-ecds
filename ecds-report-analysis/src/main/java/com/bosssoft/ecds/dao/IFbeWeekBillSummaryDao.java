package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.domain.dto.WeekBillSummaryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 查询七天内开票信息
 */
@Mapper
public interface IFbeWeekBillSummaryDao {

    /**
     * 查询 day 天前的一天内开票各单位开票数
     * @param day
     * @return
     */
    @Select("SELECT " +
            "SUM(f_number) f_bill_sum, f_agen_id_code f_agen_id " +
            "FROM fbe_writeoff_billsummary t1, fbe_writeoff t2 " +
            "WHERE TO_DAYS(NOW()) - TO_DAYS( t1.f_create_time ) = #{day} " +
            "and t1.f_pid = t2.f_id GROUP BY f_agen_id_code")
    @Results(id = "weekBillSummaryDto",value = {
            @Result(property = "agenid",column = "f_agen_id"),
            @Result(property = "billnum",column = "f_bill_sum")
    })
    List<WeekBillSummaryDto> selectTodayBillKindInfo(int day);
}
