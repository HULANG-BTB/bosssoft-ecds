package com.bosssoft.ecds.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author AloneH
 * @since 2020-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserPO对象", description="")
public class UserVO extends Model<UserVO> {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称", required = true)
    @NotNull(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "所属单位编码", required = true)
    @NotNull(message = "所属单位不能为空")
    private String agenCode;

    @ApiModelProperty(value = "电话号码", required = true)
    @NotNull(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "角色列表")
    private List<RoleVO> roles;

}