package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.dao.financial.MonitorRecordMapper;
import com.bosssoft.ecds.dao.financial.WriteOffBillItemMapper;
import com.bosssoft.ecds.dao.financial.WriteOffBillSummaryMapper;
import com.bosssoft.ecds.dao.financial.WriteOffMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyMapper;
import com.bosssoft.ecds.entity.dto.WriteOffReceiveDTO;
import com.bosssoft.ecds.entity.dto.WriteOffResultDTO;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;
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
        List<WriteOffApplyPO> writeOffApplyPOList = writeOffApplyMapper.selectList(null);
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
        // 批量更新 f_check_result
        for (WriteOffReceiveDTO writeOffReceiveDTO : list){
            writeOffApplyMapper.update(null, null);
        }
        return false;
    }

    /**
     * 获取核销信息详情
     *
     * @param object
     * @return java.lang.Object
     */
    @Override
    public Object getDetails(Object object) {
        // 获取票据详细信息
        // 将WriteOffDetail细化
        return null;
    }

    /**
     * 存入核销结果
     *
     * @param writeOffResultDTO
     * @return java.lang.Object
     */
    @Override
    public boolean setResult(WriteOffResultDTO writeOffResultDTO) {
        // 点击审验后， 对审验结果进行保存
        // 又是Update 操作
        writeOffApplyMapper.update(null, null);
        return false;
    }

}
