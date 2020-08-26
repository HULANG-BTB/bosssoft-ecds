package com.bosssoft.ecds.utils;


import com.bosssoft.ecds.domain.po.FbeLifePO;
import com.bosssoft.ecds.domain.po.FbeStockInPO;
import com.bosssoft.ecds.domain.po.FbeStockOutPO;
import com.bosssoft.ecds.domain.vo.FbeLifeVO;
import com.bosssoft.ecds.domain.vo.FbeStockInVO;
import com.bosssoft.ecds.domain.vo.FbeStockOutVO;

/**
 * PO转成VO的工具类
 * @author zouyou
 * @version 1.0
 * @date 2020/8/18 10:27
 */
public class POToVO {

    private POToVO() {
    }
    /**
     * 传入一个FbeStockInPO对象，转成FbeStockInVO实体类对象
     * @param fbeStockInPO
     * @author zouyou
     * @return com.bosssoft.ecds.domain.vo.FbeStockInVO
     * @date 2020/8/25 9:26
     */
    public static FbeStockInVO stockInPOToVO(FbeStockInPO fbeStockInPO) {
        if (fbeStockInPO == null) {
            return null;
        }
        FbeStockInVO fbeStockInVO = new FbeStockInVO();
        fbeStockInVO.setFBillCode(fbeStockInPO.getFBillCode());
        fbeStockInVO.setFBillName(fbeStockInPO.getFBillName());
        fbeStockInVO.setFNumber(fbeStockInPO.getFNumber());
        fbeStockInVO.setFBillNo1(fbeStockInPO.getFBillNo1());
        fbeStockInVO.setFBillNo2(fbeStockInPO.getFBillNo2());
        fbeStockInVO.setFCreateTime(fbeStockInPO.getFCreateTime());
        fbeStockInVO.setFOperator(fbeStockInPO.getFOperator());
        return fbeStockInVO;
    }

    /**
     * 传入一个FbeStockOutPO对象，转成FbeStockOutVO实体类对象
     * @param fbeStockOutPO
     * @author zouyou
     * @return com.bosssoft.ecds.domain.vo.FbeStockOutVO
     * @date 2020/8/25 9:26
     */
    public static FbeStockOutVO stockOutPOToVO(FbeStockOutPO fbeStockOutPO) {
        if (fbeStockOutPO == null) {
            return null;
        }
        FbeStockOutVO fbeStockOutVO = new FbeStockOutVO();
        fbeStockOutVO.setFPid(fbeStockOutPO.getFPid());
        fbeStockOutVO.setFNo(fbeStockOutPO.getFNo());
        fbeStockOutVO.setFBillPrecode(fbeStockOutPO.getFBillPrecode());
        fbeStockOutVO.setFBillName(fbeStockOutPO.getFBillName());
        fbeStockOutVO.setFNumber(fbeStockOutPO.getFNumber());
        fbeStockOutVO.setFBillNo1(fbeStockOutPO.getFBillNo1());
        fbeStockOutVO.setFBillNo2(fbeStockOutPO.getFBillNo2());
        fbeStockOutVO.setFCreateTime(fbeStockOutPO.getFCreateTime());
        fbeStockOutVO.setFUpdateTime(fbeStockOutPO.getFUpdateTime());
        return fbeStockOutVO;
    }

    /**
     * 传入一个FbeLifePO对象，转成FbeLifeVO实体类对象
     * @param fbeLifePO
     * @author zouyou
     * @return com.bosssoft.ecds.domain.vo.FbeLifeVO
     * @date 2020/8/25 9:26
     */
    public static FbeLifeVO fbeLifePOToVO(FbeLifePO fbeLifePO) {
        if (fbeLifePO == null) {
            return null;
        }
        FbeLifeVO fbeLifeVO = new FbeLifeVO();
        fbeLifeVO.setFBillCode(fbeLifePO.getFBillCode());
        fbeLifeVO.setFBillName(fbeLifePO.getFBillName());
        fbeLifeVO.setFStockInAuthor(fbeLifePO.getFStockInAuthor());
        fbeLifeVO.setFStockInCreateTime(fbeLifePO.getFStockInCreateTime());
        fbeLifeVO.setFStockInBillSource(fbeLifePO.getFStockInBillSource());
        fbeLifeVO.setFStockInWarehouseId(fbeLifePO.getFStockInWarehouseId());
        fbeLifeVO.setFStockInChangeStatus(fbeLifePO.getFStockInChangeStatus());
        fbeLifeVO.setFStockOutAuthor(fbeLifePO.getFStockOutAuthor());
        fbeLifeVO.setFStockOutCreateTime(fbeLifePO.getFStockOutCreateTime());
        fbeLifeVO.setFStockOutBillSource(fbeLifePO.getFStockOutBillSource());
        fbeLifeVO.setFStockOutWarehouseId(fbeLifePO.getFStockOutWarehouseId());
        fbeLifeVO.setFStockOutChangeStatus(fbeLifePO.getFStockOutChangeStatus());
        fbeLifeVO.setFWriteoffAmt(fbeLifePO.getFWriteoffAmt());
        fbeLifeVO.setFWriteoffOperator(fbeLifePO.getFWriteoffOperator());
        fbeLifeVO.setFWriteoffCreateTime(fbeLifePO.getFWriteoffCreateTime());
        fbeLifeVO.setFWriteoffUnitName(fbeLifePO.getFWriteoffUnitName());
        fbeLifeVO.setFDestroryUnitName(fbeLifePO.getFDestroryUnitName());
        fbeLifeVO.setFDestroryApplyMan(fbeLifePO.getFDestroryApplyMan());
        fbeLifeVO.setFDestroyType(fbeLifePO.getFDestroyType());
        fbeLifeVO.setFDestroryStatus(fbeLifePO.getFDestroryStatus());
        fbeLifeVO.setFDestroryCreateTime(fbeLifePO.getFDestroryCreateTime());
        return fbeLifeVO;
    }
}
