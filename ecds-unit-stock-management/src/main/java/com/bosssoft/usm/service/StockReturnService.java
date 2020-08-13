package com.bosssoft.usm.service;

import com.bosssoft.usm.entity.po.StockReturnPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.usm.entity.vo.DateVO;
import com.bosssoft.usm.entity.vo.StockReturnItemVO;
import com.bosssoft.usm.entity.vo.StockReturnVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhuWen
 * @since 2020-08-11
 */
public interface StockReturnService extends IService<StockReturnPO> {

    /**
     * 添加退票申请
     * @param stockReturnVO
     * @return
     */
    String addStockReturnPO(StockReturnVO stockReturnVO);

    /**
     * 查询所有的退票申请主信息
     * @return
     */
    List<StockReturnVO> stockRetrunVOList();

    /**
     * 根据一条退票业务码查询退票明细信息
     * @param no 业务单号
     * @return
     */
    List<StockReturnItemVO> stockReturnItemVOList(Long no);

    /**
     * 根据业务单号查询单条信息
     * @param no 业务单号
     * @return
     */
    StockReturnVO stockRetrunVOListByNo(Long no);

    /**
     * 根据编制日期查询
     * @param dateVO 编制起止日期
     * @return
     */
    List<StockReturnVO> stockReturnVOlistByDate(DateVO dateVO);

    /**
     * 修改退票主表的退票原因
     * @param stockReturnVO 退票主表VO
     * @return
     */
    String updateStockReturnPO(StockReturnVO stockReturnVO);

    /**
     * 修改退票明细表信息
     * @param stockReturnItemVOS 退票明细列表项
     * @param no 业务单号
     * @return
     */
    String updateStockReturnItemPO(List<StockReturnItemVO> stockReturnItemVOS,Long no);

    /**
     * 根据业务单号删除退票申请
     * @param no
     * @return
     */
    String deleteStockReturn(Long no);

}
