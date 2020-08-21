package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.po.BillPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
@SuppressWarnings("ALL")
public interface BillDao {

    /**
     * 向对应的票号池中添加票号
     *
     * @param table 票号池对应的数据表
     * @param list  插入的数据列表
     * @return int
     */
    int insertBill(@Param("table") String table, @Param("list") List<BillPo> list);

    /**
     * 获取对应票据池剩余票号量
     *
     * @param table
     * @return int
     */
    int retrieveNumber(@Param("table") String table);

    /**
     * 返回数据库中所有可用票号池对应的种类代码
     *
     * @return List
     */
    List<String> retrieveBillTypeCode();

    /**
     * 从票据池中取出指定的票据和数量
     *
     * @param table  票据池对应的数据表
     * @param number 票据数量
     * @return List
     */
    List retrieveList(@Param("table") String table, @Param("number") int number);

    /**
     * 取票完成后删除票据池中取出票据的记录
     *
     * @param table
     * @param list
     * @return int
     */
    int deleteList(@Param("table") String table, @Param("list") List<Integer> list);
}
