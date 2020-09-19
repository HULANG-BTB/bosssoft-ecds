package com.bosssoft.ecds.service;

import com.bosssoft.ecds.domain.vo.FbeStockOutVO;

import java.util.List;
import java.util.Map;

/**
 * 出库票据的service层接口
 * @author zouyou
 * @version 1.0
 * @date 2020/8/17 16:55
 */
public interface FbeStockOutService {
    /**
     * 获取所有的出库信息，返回一个VO的list集合
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeStockOutVO>
     * @date 2020/8/25 9:24
     */
    List<FbeStockOutVO> getList();

    /**
     * 查询出库各票据种类的票据数量，返回一个VO的list集合
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeStockOutVO>
     * @date 2020/8/25 9:24
     */
    List<FbeStockOutVO> selectNum();

    /**
     * 查询出库的时间与数量对应的信息，返回一个map集合
     * @author zouyou
     * @return java.util.Map<java.lang.String, int [ ]>
     * @date 2020/8/25 9:25
     */
    Map<String, int[]> selectBar();

    /**
     * 查询近七天的出库票据数量，返回一个list
     * @author zouyou
     * @return java.util.List<java.lang.Integer>
     * @date 2020/8/25 9:25
     */
    List<Integer> selectNumIndex();
}
