package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.ItemStdDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.ItemStdVO;
import com.bosssoft.ecds.entity.vo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.ItemStdService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.utils.ResponseUtils;
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
 *  前端控制器
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/itemstd")
public class ItemStdController {
    @Autowired
    private ItemStdService itemStdService;

    /**
     * 插入项目相关信息
     *
     * @param itemStdVO
     * @return
     */
    @ApiOperation(value = "添加项目标准")
    @PostMapping("/save")
    public String addItem(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        boolean save = itemStdService.save(itemStdDTO);
        return ResponseUtils.getResponse(save, ResponseUtils.ResultType.CREATED);
    }

    /**
     * 修改项目信息
     *
     * @param itemStdVO
     * @return
     */
    @ApiOperation(value = "修改项目标准")
    @PostMapping("/update")
    public String updateItem(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        boolean update = itemStdService.update(itemStdDTO);
        return ResponseUtils.getResponse(update, ResponseUtils.ResultType.OK);
    }

    /**
     * 删除项目
     *
     * @param itemStdVO
     * @return
     */
    @ApiOperation(value = "删除项目标准")
    @ApiImplicitParam(name = "id", value = "项目id", dataType = "Long")
    @PostMapping("/delete")
    public String deleteItem(@RequestBody ItemStdVO itemStdVO) {
        ItemStdDTO itemStdDTO = MyBeanUtil.myCopyProperties(itemStdVO, ItemStdDTO.class);
        boolean delete = itemStdService.delete(itemStdDTO);
        return ResponseUtils.getResponse(delete, ResponseUtils.ResultType.OK);
    }

    /**
     * 批量删除
     *
     * @param itemVOList
     * @return
     */
    @ApiOperation(value = "批量删除项目标准")
    @PostMapping("/batchdelete")
    public String batchDelete(@RequestBody List<ItemStdVO> itemVOList) {
        List<ItemStdDTO> itemStdDTOList = MyBeanUtil.copyListProperties(itemVOList, ItemStdDTO::new);
        boolean batchdelete = itemStdService.batchdelete(itemStdDTOList);
        return ResponseUtils.getResponse(batchdelete, ResponseUtils.ResultType.OK);
    }


    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "分页查询项目标准")
    @PostMapping("/listbypage")
    public String listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemStdDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        PageVO pageVO1 = itemStdService.listByPage(pageDTO);
        if (pageVO1 != null) {
            return ResponseUtils.getResponse(pageVO1, ResponseUtils.ResultType.OK);
        } else {
            return ResponseUtils.getResponse(pageVO1, ResponseUtils.ResultType.INTERNAL_SERVER_ERROR);
        }
    }

}

