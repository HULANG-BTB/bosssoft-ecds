package com.bosssoft.ecds.entity.vo;

import com.bosssoft.ecds.entity.dto.WriteOffBillInvDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffIncomeDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffInvoceDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffMonitorDetailDTO;

import java.util.List;

public class WriteOffDetailVO {
    // 开票总览
    List<WriteOffInvoceDetailDTO> writeOffInvoceDetailDTOList;

    // 收入情况
    List<WriteOffIncomeDetailDTO> writeOffIncomeDetailDTOList;

    // 开票明细
    List<WriteOffBillInvDetailDTO> writeOffBillInvDetailDTOList;

    // 预警记录
    List<WriteOffMonitorDetailDTO> writeOffMonitorDetailDTOList;

    public List<WriteOffInvoceDetailDTO> getWriteOffInvoceDetailDTOList() {
        return writeOffInvoceDetailDTOList;
    }

    public void setWriteOffInvoceDetailDTOList(List<WriteOffInvoceDetailDTO> writeOffInvoceDetailDTOList) {
        this.writeOffInvoceDetailDTOList = writeOffInvoceDetailDTOList;
    }

    public List<WriteOffIncomeDetailDTO> getWriteOffIncomeDetailDTOList() {
        return writeOffIncomeDetailDTOList;
    }

    public void setWriteOffIncomeDetailDTOList(List<WriteOffIncomeDetailDTO> writeOffIncomeDetailDTOList) {
        this.writeOffIncomeDetailDTOList = writeOffIncomeDetailDTOList;
    }

    public List<WriteOffBillInvDetailDTO> getWriteOffBillInvDetailDTOList() {
        return writeOffBillInvDetailDTOList;
    }

    public void setWriteOffBillInvDetailDTOList(List<WriteOffBillInvDetailDTO> writeOffBillInvDetailDTOList) {
        this.writeOffBillInvDetailDTOList = writeOffBillInvDetailDTOList;
    }

    public List<WriteOffMonitorDetailDTO> getWriteOffMonitorDetailDTOList() {
        return writeOffMonitorDetailDTOList;
    }

    public void setWriteOffMonitorDetailDTOList(List<WriteOffMonitorDetailDTO> writeOffMonitorDetailDTOList) {
        this.writeOffMonitorDetailDTOList = writeOffMonitorDetailDTOList;
    }
}
