package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.entity.dto.WriteOffDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffIncomeDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffInvoceDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffMonitorDetailDTO;
import com.bosssoft.ecds.service.MonitorRecordService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MonitorRecordServiceImpl implements MonitorRecordService {

    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
    @Override
    public List<WriteOffMonitorDetailDTO> getMonitorDetail(WriteOffDetailDTO writeOffDetailDTO) {
        List<WriteOffMonitorDetailDTO> list = new ArrayList<>();
        // 开票总览
        List<WriteOffInvoceDetailDTO> writeOffInvoceDetailDTOList = writeOffDetailDTO.getWriteOffInvoceDetailDTOList();
        for (WriteOffInvoceDetailDTO writeOffInvoceDetailDTO : writeOffInvoceDetailDTOList){
            if ((writeOffInvoceDetailDTO.getFAmt().intValue()>100000)) {
                WriteOffMonitorDetailDTO writeOffMonitorDetailDTO = new WriteOffMonitorDetailDTO();
                writeOffMonitorDetailDTO.setFDate(sdf.format(new Date()));
                writeOffMonitorDetailDTO.setItemName("开票总览-票号:" + writeOffInvoceDetailDTO.getFBillCode() + "开票异常");
                writeOffMonitorDetailDTO.setItemContent("开票数额超过100000");
                list.add(writeOffMonitorDetailDTO);
            }
        }
        // 收入情况
        List<WriteOffIncomeDetailDTO> writeOffIncomeDetailDTOList = writeOffDetailDTO.getWriteOffIncomeDetailDTOList();
        for (WriteOffIncomeDetailDTO writeOffIncomeDetailDTO : writeOffIncomeDetailDTOList){
            if ((writeOffIncomeDetailDTO.getFAmt().intValue()>1000000)){
                WriteOffMonitorDetailDTO writeOffMonitorDetailDTO = new WriteOffMonitorDetailDTO();
                writeOffMonitorDetailDTO.setFDate(sdf.format(new Date()));
                writeOffMonitorDetailDTO.setItemName("收入情况-收费项目:"+writeOffIncomeDetailDTO.getFItemCode()+"异常");
                writeOffMonitorDetailDTO.setItemContent("开票数额超过1000000");
                list.add(writeOffMonitorDetailDTO);
            }
        }
        // 开票明细
        return list;
    }
}
