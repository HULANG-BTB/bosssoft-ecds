package com.bosssoft.ecds.entity.vo;

import com.bosssoft.ecds.entity.dto.ApplyDto;
import com.bosssoft.ecds.entity.dto.ItemDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: qiuheng
 * @create: 2020-08-12 20:43
 **/

public class ApplyVo implements Serializable {
    ApplyDto applyDto;
    List<ItemDto> itemDtoList;

    public ApplyDto getApplyDto() {
        return applyDto;
    }

    public void setApplyDto(ApplyDto applyDto) {
        this.applyDto = applyDto;
    }

    public List<ItemDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(List<ItemDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }
}
