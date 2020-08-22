package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.financial.MonitorRecordMapper;
import com.bosssoft.ecds.dao.financial.WriteOffBillItemMapper;
import com.bosssoft.ecds.dao.financial.WriteOffBillSummaryMapper;
import com.bosssoft.ecds.dao.financial.WriteOffMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyIncomeMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyItemMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyMapper;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.*;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinancialWriteOffServiceImpl implements FinancialWriteOffService {

    @Autowired(required = false)
    private WriteOffApplyMapper writeOffApplyMapper;

    @Autowired(required = false)
    private WriteOffApplyItemMapper writeOffApplyItemMapper;

    @Autowired(required = false)
    private WriteOffApplyIncomeMapper writeOffApplyIncomeMapper;

    @Autowired(required = false)
    private WriteOffMapper writeOffMapper;

    @Autowired(required = false)
    private WriteOffBillItemMapper writeOffBillItemMapper;

    @Autowired(required = false)
    private WriteOffBillSummaryMapper writeOffBillSummaryMapper;

    @Autowired(required = false)
    private MonitorRecordMapper monitorRecordMapper;

    /**
     * 获取单位端传来的核销信息
     * 接收一段时间的下级单位传来的核销信息
     * 通过单位编码获取--这里暂时不做Dialog获取单位名称了
     *
     * @param fAgenIdCode
     * @return java.util.List
     */
    @Override
    public List<WriteOffReceiveDTO> receive(String fAgenIdCode) {
        // 参数应该是一段时间，或者是没有参数
        // object可以为空
        // 这里先用获取所有的List代替，应该是通过单位编码获取List
        List<WriteOffApplyPO> writeOffApplyPOList = writeOffApplyMapper.getWriteOffApplyPOByAgenIdCode(fAgenIdCode);
        List<WriteOffReceiveDTO> list = new ArrayList<>(writeOffApplyPOList.size());
        for (WriteOffApplyPO writeOffApplyPO : writeOffApplyPOList){
            WriteOffReceiveDTO writeOffReceiveDTO = new WriteOffReceiveDTO();
            writeOffReceiveDTO.setFNo(writeOffApplyPO.getFNo());
            writeOffReceiveDTO.setFDate(writeOffApplyPO.getFDate());
            writeOffReceiveDTO.setFMemo(writeOffApplyPO.getFMemo());
            writeOffReceiveDTO.setFAuthor(writeOffApplyPO.getFAuthor());
            list.add(writeOffReceiveDTO);
        }
        return list;
    }

    /**
     * 退回单位端传来的核销信息
     * 需要退回的核销信息根据单位ID回到单位端进行修改
     *
     * @param list
     * @return
     */
    @Override
    public boolean sendBack(List<WriteOffReceiveDTO> list) {
        // 退回操作最终是修改单位端需要核销的数据的状态
        // 批量更新 f_check_result以及 f_is_upload
        for (WriteOffReceiveDTO writeOffReceiveDTO : list){
            writeOffApplyMapper.updateWriteOffApply(writeOffReceiveDTO.getFNo());
        }
        return true;
    }

    @Override
    public boolean setResult(WriteOffDetailDTO writeOffDetailDTO , WriteOffResultDTO writeOffResultDTO) {
        // 点击审验后， 对审验结果进行保存
        // 保存审验结果
//        setDetails(writeOffDetailDTO);
        // 跟新审核结果
        return false;
    }
    /**
     * 存入核销结果
     *
     * @param writeOffResultDTO
     * @return java.lang.Object
     */
    @Override
    public boolean setResult(WriteOffResultDTO writeOffResultDTO) {
        if (writeOffResultDTO.getRes().equals("pass")){
            writeOffApplyMapper.setResult(writeOffResultDTO.getFNo(), 2);
        } else {
            writeOffApplyMapper.setResult(writeOffResultDTO.getFNo(), 3);
        }
        return false;
    }

    @Override
    public WriteOffDetailDTO getDetail(String fPid) {
        WriteOffDetailDTO writeOffDetailDTO = new WriteOffDetailDTO();
        // 开票总览 & 收入情况 & 开票明细
        List<WriteOffInvoceDetailDTO> writeOffInvoceDetailDTOList = new ArrayList<>();
        List<WriteOffIncomeDetailDTO> writeOffIncomeDetailDTOList = new ArrayList<>();
        List<WriteOffBillInvDetailDTO> writeOffBillInvDetailDTOList = new ArrayList<>();

        List<WriteOffApplyItemPO> writeOffApplyItemPOList = writeOffApplyItemMapper.getWriteOffApplyItemList(fPid);
        for (WriteOffApplyItemPO writeOffApplyItemPO : writeOffApplyItemPOList){
            WriteOffInvoceDetailDTO writeOffInvoceDetailDTO = new WriteOffInvoceDetailDTO();
            writeOffInvoceDetailDTO.setFBillCode(writeOffApplyItemPO.getFBillCode());
            writeOffInvoceDetailDTO.setFBillName(writeOffApplyItemPO.getFBillName());
            writeOffInvoceDetailDTO.setFNumber(writeOffApplyItemPO.getFNumber());
            writeOffInvoceDetailDTO.setFAmt(writeOffApplyItemPO.getFAmt());
            writeOffInvoceDetailDTOList.add(writeOffInvoceDetailDTO);

            WriteOffBillInvDetailDTO writeOffBillInvDetailDTO = new WriteOffBillInvDetailDTO();
            writeOffBillInvDetailDTO.setFBatchNo(writeOffApplyItemPO.getFBatchNo());
            writeOffBillInvDetailDTO.setFNumber(writeOffApplyItemPO.getFNumber());
            writeOffBillInvDetailDTO.setFBillNo1(writeOffApplyItemPO.getFBillNo1());
            writeOffBillInvDetailDTO.setFBillNo2(writeOffApplyItemPO.getFBillNo2());
            writeOffBillInvDetailDTO.setFAmt(writeOffApplyItemPO.getFAmt());
            writeOffBillInvDetailDTOList.add(writeOffBillInvDetailDTO);
        }
        List<WriteOffApplyIncomePO> writeOffApplyIncomePOList = writeOffApplyIncomeMapper.getWriteOffApplyIncomeList(fPid);
        for (WriteOffApplyIncomePO writeOffApplyIncomePO: writeOffApplyIncomePOList){
            WriteOffIncomeDetailDTO writeOffIncomeDetailDTO = new WriteOffIncomeDetailDTO();
            writeOffIncomeDetailDTO.setFItemCode(writeOffApplyIncomePO.getFItemCode());
            writeOffIncomeDetailDTO.setFItemName(writeOffApplyIncomePO.getFItemName());
            writeOffIncomeDetailDTO.setFUnits(writeOffApplyIncomePO.getFUnits());
            writeOffIncomeDetailDTO.setFAmt(writeOffApplyIncomePO.getFAmt());
            writeOffIncomeDetailDTOList.add(writeOffIncomeDetailDTO);
        }

        writeOffDetailDTO.setWriteOffInvoceDetailDTOList(writeOffInvoceDetailDTOList);
        writeOffDetailDTO.setWriteOffBillInvDetailDTOList(writeOffBillInvDetailDTOList);
        writeOffDetailDTO.setWriteOffIncomeDetailDTOList(writeOffIncomeDetailDTOList);

        return writeOffDetailDTO;
    }

    /**
     * 获取核销信息详情
     *
     * @param writeOffDetailDTO
     * @return java.lang.Object
     */
    public boolean setDetails(WriteOffDetailDTO writeOffDetailDTO) {
        // 获取票据详细信息

        return true;
    }

}
