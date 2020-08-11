package com.bosssoft.ecds.security.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bosssoft.ecds.security.entity.po.RolePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserVO
 * @Author AloneH
 * @Date 2020/8/9 19:31
 * @Description TODO
 **/
@Data
@Accessors(chain = true)
@ApiModel(value="UserPO对象", description="")
public class UserVO extends Model<UserVO> {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "权限列表")
    private List<RoleVO> roles;

}
