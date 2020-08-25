package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.domain.dto.TodayBillKindDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 查询今日票据种类相关信息
 * @author LiDashan
 * @date 2020/8/24
 */
@Mapper
public interface IFbeTodayBillKindDao {

    /**
     * 查询今日各单位开票数量
     * @return
     */
    @Select("SELECT  `f_bill_name`,SUM(f_number) as `f_bill_num` " +
            "FROM `fbe_writeoff_billitem` " +
            "where f_create_time>DATE_SUB(CURDATE(), INTERVAL 1 DAY)  " +
            "GROUP BY f_bill_name  ")
    @Results( id="TodayBillKindPO",value = {
            @Result(property = "billname",column = "f_bill_name"),
            @Result(property = "billnum",column = "f_bill_num")
    })
    List<TodayBillKindDto> selectTodayBillKindInfo();
}
