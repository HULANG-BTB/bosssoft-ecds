package com.bosssoft.ecds.service;

import com.bosssoft.ecds.domain.vo.FbeStockInVO;

import java.util.List;
import java.util.Map;

/**
 * 入库票据的service层接口
 * @author zouyou
 * @version 1.0
 * @date 2020/8/17 16:55
 */
public interface FbeStockInService {
    /**
     *  获取所有的入库信息，返回一个VO的list集合
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeStockInVO>
     * @date 2020/8/25 9:20
     */
    List<FbeStockInVO> getList();

    /**
     * 查询入库各票据种类的票据数量，返回一个VO的list集合
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeStockInVO>
     * @date 2020/8/25 9:20
     */
    List<FbeStockInVO> selectNum();

    /**
     * 查询入库的时间与数量对应的信息，返回一个map集合
     * @author zouyou
     * @return java.util.Map<java.lang.String, int [ ]>
     * @date 2020/8/25 9:20
     */
    Map<String, int[]> selectBar();

    /**
     * 查询近七天的入库票据数量，返回一个list
     * @author zouyou
     * @return java.util.List<java.lang.Integer>
     * @date 2020/8/25 9:20
     */
    List<Integer> selectNumIndex();
}
