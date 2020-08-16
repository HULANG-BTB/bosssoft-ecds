package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.GroupItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.groupvo.GroupItemVO;
import com.bosssoft.ecds.service.GroupItemService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wzh
 * @since 2020-08-14
 */
@RestController
@RequestMapping("/groupItem")
public class GroupItemController {
    @Autowired
    private GroupItemService groupItemService;

    /**
     * 插入项目与分组相关信息
     *
     * @param groupItemVO 项目与分组相关信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "添加项目分组信息", notes = "项目与分组相关信息")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody GroupItemVO groupItemVO) {
        GroupItemDTO groupItemDTO = MyBeanUtil.myCopyProperties(groupItemVO, GroupItemDTO.class);
        return groupItemService.save(groupItemDTO);
    }

    /**
     * 删除项目分组信息
     *
     * @param groupItemVO 输入需要删除项目分组信息的id
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "删除项目分组关系", notes = "输入需要删除项目分组信息的id")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody GroupItemVO groupItemVO) {
        GroupItemDTO groupItemDTO = MyBeanUtil.myCopyProperties(groupItemVO, GroupItemDTO.class);
        return groupItemService.delete(groupItemDTO);
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
        PageDTO<GroupItemVO> pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        return groupItemService.listByPage(pageDTO);
    }

    /**
     * 通过分组编码获得项目信息
     *
     * @param groupItemVO 输入分组编码
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "通过分组编码获得项目信息", notes = "输入分组编码")
    @PostMapping("/getItemInfo")
    public ResponseResult getItemInfo(@RequestBody GroupItemVO groupItemVO) {
        GroupItemDTO groupItemDTO = MyBeanUtil.myCopyProperties(groupItemVO, GroupItemDTO.class);
        return groupItemService.getItemInfo(groupItemDTO);
    }

}

