package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.StockReturnPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.DateVO;
import com.bosssoft.ecds.entity.vo.StockReturnItemVO;
import com.bosssoft.ecds.entity.vo.StockReturnVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *退票申请服务
 * @author ZhuWen
 * @since 2020-08-13
 */
public interface StockReturnService extends IService<StockReturnPO> {

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
     * 自动审核 创建时间，起始号和终止号是否正常
     * @param no
     * @return
     */
    String CheckStatusByNo(Long no);

}
