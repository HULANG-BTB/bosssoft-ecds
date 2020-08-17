package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.itemdto.ItemStdDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemStdVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.ItemStdService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/itemstd")
@Api(value = "项目标准管理接口")
public class ItemStdController {

    @Autowired
    private ItemStdService itemStdService;

    /**
     * 插入项目标准相关信息
     *
     * @param itemStdVO 输入项目标准相关信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "添加项目标准", notes = "输入项目标准相关信息")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.save(itemStdDTO);
    }

    /**
     * 修改项目标注信息
     *
     * @param itemStdVO 修改后的项目标准信息
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "修改项目标准", notes = "修改后的项目标准信息")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.update(itemStdDTO);
    }

    /**
     * 删除单个项目标准
     *
     * @param itemStdVO 需要删除的项目标准的id
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "删除项目标准", notes = "需要删除的项目标准的id")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "Long")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.delete(itemStdDTO);
    }

    /**
     * 批量删除
     *
     * @param itemVOList 需要删除的项目标准的idList
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量删除项目标准", notes = "需要删除的项目标准的idList")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<ItemStdVO> itemVOList) {
        List<ItemStdDTO> itemStdDTOList = MyBeanUtil.copyListProperties(itemVOList, ItemStdDTO::new);
        return itemStdService.batchDelete(itemStdDTOList);
    }

    /**
     * 主要用于批量审核,修改项目标准启用状态，输入需要修改
     *
     * @param itemStdVOList 需要修改审核的项目idList
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量审核", notes = "需要修改审核的项目idList")
    @PostMapping("/batchVerify")
    public ResponseResult batchVerify(@RequestBody List<ItemStdVO> itemStdVOList) {
        List<ItemStdDTO> itemStdDTOList = MyBeanUtil.copyListProperties(itemStdVOList, ItemStdDTO::new);
        return itemStdService.batchVerify(itemStdDTOList);
    }

    /**
     * 分页查询
     *
     * @param pageVO 输入分页信息,limit、page、keyword、isenable
     *               keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    @ApiOperation(value = "分页查询项目标准", notes = "输入分页信息,limit、page、keyword，" +
            "keyword为空时普通查询，keyword不为空时模糊查询")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemStdDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return itemStdService.listByPage(pageDTO);
    }

    /**
     * 通过项目编码查询项目标准
     *
     * @param itemStdVO 输入项目编码
     * @return 项目标准相关信息
     */
    @ApiOperation(value = "通过项目编码查询项目标准", notes = "输入项目编码")
    @PostMapping("/getItemStd")
    public QueryResponseResult<ItemStdVO> getItemStd(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.copyProperties(itemStdVO, ItemStdDTO.class);
        return itemStdService.getByItemCode(itemStdDTO);
    }
}

