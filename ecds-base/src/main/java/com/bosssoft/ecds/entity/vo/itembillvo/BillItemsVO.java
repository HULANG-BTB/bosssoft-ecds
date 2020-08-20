package com.bosssoft.ecds.entity.vo.itembillvo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName :  BillItemsVO
 * @Description : 批量添加票据与项目的关联
 * @Author : wuliming
 * @Date: 2020-08-10 22:30
 */
@Data
public class BillItemsVO {
    @NotEmpty
    private String billCode;
    @NotEmpty
    private List<String> itemIds;
}
