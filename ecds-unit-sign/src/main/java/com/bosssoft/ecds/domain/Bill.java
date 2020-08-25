package com.bosssoft.ecds.domain;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/11
 * @Content:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("票据信息实体")
public class Bill implements Serializable {

    private String testStr;
}
