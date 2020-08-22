package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.po.StockReturnPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.DateVO;
import com.bosssoft.ecds.entity.vo.StockReturnCheckVO;
import com.bosssoft.ecds.entity.vo.StockReturnItemVO;
import com.bosssoft.ecds.entity.vo.StockReturnVO;
import org.springframework.transaction.annotation.Transactional;

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
     * 根据一条退票业务码查询一组明细信息
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
    List<StockReturnVO> stockReturnVOlistByDateOrNo(DateVO dateVO);

    /**
     * 分页查询
     * @param dateVO
     * @return
     */
    public PageResult stockReturnVOListByPage(DateVO dateVO);

    /**
     * 根据业务单号来查询单个退票以及附属明细信息
     * @param stockReturnVO1 业务单号
     * @return
     */
    public StockReturnVO stockRetrunVOByNo (StockReturnVO stockReturnVO1);

    /**
     * 审核 创建时间，起始号和终止号是否正常,正常更改退票主表中的审核状态字段，
     * 并将财政票据表退票属性更改
     * @param stockReturnCheckVO
     * @return
     */
    boolean CheckStatusByNo(StockReturnCheckVO stockReturnCheckVO);

}
