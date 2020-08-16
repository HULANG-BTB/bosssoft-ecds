package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.GroupDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.groupvo.GroupVO;
import com.bosssoft.ecds.service.GroupService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wzh
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 新建分组相关信息
     *
     * @param groupVO 输入分组相关信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "添加分组信息", notes = "输入分组相关信息")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody GroupVO groupVO) {
        GroupDTO groupDTO = MyBeanUtil.myCopyProperties(groupVO, GroupDTO.class);
        return groupService.save(groupDTO);
    }

    /**
     * 修改分组相关信息
     *
     * @param groupVO 输入修改后的分组相关信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "修改分组信息", notes = "输入修改后的分组相关信息")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody GroupVO groupVO) {
        GroupDTO groupDTO = MyBeanUtil.myCopyProperties(groupVO, GroupDTO.class);
        return groupService.update(groupDTO);
    }

    /**
     * 删除分组信息
     *
     * @param groupVO 输入需要删除项目的id
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "删除分组", notes = "输入需要删除分组的id")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody GroupVO groupVO) {
        GroupDTO groupDTO = MyBeanUtil.myCopyProperties(groupVO, GroupDTO.class);
        return groupService.delete(groupDTO);
    }

    /**
     * 分页查询
     *
     * @param pageVO 输入分页信息,limit、page、keyword、isenable
     *               keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    @ApiOperation(value = "分页查询", notes = "输入分页信息,limit、page、keyword，" +
            "keyword为空时普通查询，keyword不为空时模糊查询")
    @PostMapping("/listByPage")
    public ResponseResult listByPage(@RequestBody PageVO pageVO) {
        PageDTO<GroupVO> pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        return groupService.listByPage(pageDTO);
    }

    /**
     * 查询所有分组信息
     *
     * @return limit、page、total、items
     */
    @ApiOperation(value = "查询所有分组信息")
    @GetMapping("/getGroupName")
    public ResponseResult getGroupName() {
        return groupService.getGroupName();
    }
}

