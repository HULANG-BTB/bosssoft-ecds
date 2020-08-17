package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.itemdto.ItemDTO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemPageVO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.ItemService;
import com.bosssoft.ecds.service.SubjectService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */

@RestController
@RequestMapping("/item")
@Api(value = "项目管理接口")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SubjectService subjectServicec;
    /**
     * 插入项目相关信息
     *
     * @param itemVO 输入项目相关信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "添加项目", notes = "输入项目相关信息")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        return itemService.save(itemDTO);
    }

    /**
     * 修改项目信息
     *
     * @param itemVO 输入修改后的项目信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "修改项目", notes = "输入修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        return itemService.update(itemDTO);
    }

    /**
     * 主要用于批量审核,修改项目启用状态
     *
     * @param itemVOList 输入需要修改审核的项目idList
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量审核", notes = "输入需要修改审核的项目idList")
    @PostMapping("/batchVerify")
    public ResponseResult batchVerify(@RequestBody List<ItemVO> itemVOList) {
        List<ItemDTO> itemDTOS = MyBeanUtil.copyListProperties(itemVOList, ItemDTO::new);
        return itemService.batchVerify(itemDTOS);
    }

    /**
     * 删除单个项目
     *
     * @param itemVO 输入需要删除项目的id
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "删除项目", notes = "输入需要删除项目的id")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = MyBeanUtil.myCopyProperties(itemVO, ItemDTO.class);
        return itemService.delete(itemDTO);
    }

    /**
     * 批量删除
     *
     * @param itemVOList 输入需要删除的项目idList
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量删除", notes = "输入需要删除的项目idList")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<ItemVO> itemVOList) {
        List<ItemDTO> itemDTOS = MyBeanUtil.copyListProperties(itemVOList, ItemDTO::new);
        return itemService.batchDelete(itemDTOS);
    }

    /**
     * 分页查询
     *
     * @param itemPageVO 输入分页信息,limit、page、keyword、isenable
     *                   keyword为空时普通查询，keyword不为空时模糊查询
     *                   isenable为0时查询出未审核的项目，为1时查询出审核的项目
     * @return limit、page、total、items
     */
    @ApiOperation(value = "分页查询", notes = "输入分页信息,limit、page、keyword，" +
            "keyword为空时普通查询，keyword不为空时模糊查询")
    @PostMapping("/listByPage")
    public ResponseResult listByPage(@RequestBody ItemPageVO<ItemDTO> itemPageVO) {
        return itemService.listByPage(itemPageVO);
    }

    /**
     * 查询所有项目信息
     *
     * @return 项目信息集合
     */
    @ApiOperation(value = "查询所有项目信息，不分页展示")
    @GetMapping("/getItemAll")
    public ResponseResult getItemAll() {
        return itemService.getItemAll();
    }

    /**
     * 查询预算科目树
     *
     * @return 项目信息集合
     */
    @ApiOperation(value = "查询预算科目树")
    @GetMapping("/getItemTree")
    public ResponseResult getItemTree() {
        List<SubjectVO> all = subjectServicec.getAll("2020");
        return new QueryResponseResult<>(CommonCode.SUCCESS,all);
    }
}

