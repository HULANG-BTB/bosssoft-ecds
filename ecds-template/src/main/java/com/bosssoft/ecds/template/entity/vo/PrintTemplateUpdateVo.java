package com.bosssoft.ecds.template.entity.vo;

import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import lombok.Data;

@Data
public class PrintTemplateUpdateVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 票据代码（前6位）
     */
    private String billCode;

    /**
     * 单位识别码
     */
    private String agenIdCode;

    /**
     * 开票点ID
     */
    private String placeId;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 备注
     */
    private String memo;

    public PrintTemplateDto toDto(){
        PrintTemplateDto dto = new PrintTemplateDto();
        dto.setId(id);
        dto.setAgenIdCode(agenIdCode);
        dto.setPlaceId(placeId);
        dto.setName(name);
        dto.setMemo(memo);

        if (billCode!=null) {
            dto.setRgnCode(billCode.substring(0, 2));
            dto.setTypeId(billCode.substring(2, 4));
            dto.setSortId(billCode.substring(4, 6));
        }

        return dto;
    }
}
