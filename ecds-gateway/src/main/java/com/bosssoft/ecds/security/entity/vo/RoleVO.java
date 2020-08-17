package com.bosssoft.ecds.security.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName RoleVO
 * @Author AloneH
 * @Date 2020/8/9 19:41
 * @Description
 *      UserPO 对象
 **/
@Data
@Accessors(chain = true)
@ApiModel(value="UserPO对象", description="")
public class RoleVO extends Model<RoleVO> {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "角色Key")
    private String role;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "权限列表")
    private List<PermissionVO> permissions;

}
