package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.domain.dto.TodayBillSummaryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 查询今日票据相关信息
 * @author LiDaShan
 * @date 2020/8/24
 */
@Mapper
public interface ITodayBillSummaryDao {

    /**
     * 查询一小时内，每6分钟内的开票数量
     * @return
     */
    @Select("SELECT\n" +
            "\tbill_num,\n" +
            "\tbill_money,\n" +
            "\ttime \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( NOW(), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 5 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 0 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 6 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 11 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 6 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 12 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 17 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 12 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 18 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 23 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 18 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 24 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 29 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 24 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 30 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 35 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 30 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 36 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 41 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 36 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 42 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 47 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 42 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 48 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 53 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 48 MINUTE ) UNION ALL\n" +
            "\tSELECT\n" +
            "\t\tIFNULL(SUM( f_number ),0) AS bill_num,\n" +
            "\t\tIFNULL(SUM(f_amt),0) AS bill_money,\n" +
            "\t\tDATE_FORMAT( DATE_SUB( NOW(), INTERVAL 54 MINUTE ), '%Y-%m-%d %H:%i' ) AS time \n" +
            "\tFROM\n" +
            "\t\tfbe_writeoff_billsummary \n" +
            "\tWHERE\n" +
            "\t\tf_create_time BETWEEN DATE_SUB( NOW(), INTERVAL 59 MINUTE ) \n" +
            "\t\tAND DATE_SUB( NOW(), INTERVAL 54 MINUTE ) \n" +
            "\t\t\n" +
            "\t) AS t1")
    @Results( id = "todayBillSummaryDto",value = {
            @Result(property = "billNum",column = "bill_num"),
            @Result(property = "summaryTime",column = "time"),
            @Result(property = "billMoney",column = "bill_money")
    })
    List<TodayBillSummaryDto> selectHourBillInfo();
}
