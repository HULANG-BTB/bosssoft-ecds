package com.bosssoft.ecds.controller;


import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.UserVO;
import com.bosssoft.ecds.service.ItemService;
import com.bosssoft.ecds.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 插入项目相关信息
     *
     * @param itemVO
     * @return
     */
    @PostMapping("/save")
    public String addItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = BeanUtil.copyProperties(itemVO, ItemDTO.class);
        int save = itemService.save(itemDTO);
        if (save == 1) {
            return ResponseUtils.getResponse("true", ResponseUtils.ResultType.CREATED);
        } else {
            return ResponseUtils.getResponse("false", ResponseUtils.ResultType.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改项目信息
     *
     * @param itemVO
     * @return
     */
    @PostMapping("/update")
    public String updateItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = BeanUtil.copyProperties(itemVO, ItemDTO.class);
        int update = itemService.update(itemDTO);
        if (update == 1) {
            return ResponseUtils.getResponse("true", ResponseUtils.ResultType.OK);
        } else {
            return ResponseUtils.getResponse("false", ResponseUtils.ResultType.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除项目
     *
     * @param itemVO
     * @return
     */
    @PostMapping("/delete")
    public String deleteItem(@RequestBody ItemVO itemVO) {
        ItemDTO itemDTO = BeanUtil.copyProperties(itemVO, ItemDTO.class);
        int delete = itemService.delete(itemDTO);
        if (delete == 1) {
            return ResponseUtils.getResponse("true", ResponseUtils.ResultType.OK);
        } else {
            return ResponseUtils.getResponse("false", ResponseUtils.ResultType.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/batchdelete")
    public String batchDelete(@RequestBody List<ItemVO> itemVOList){



        return null;
    }


    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @GetMapping("/listbypage")
    public String listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemDTO> pageDTO = BeanUtil.copyProperties(pageVO, PageDTO.class);
        PageVO pageVO1 = itemService.listByPage(pageDTO);
        if (pageVO1 != null) {
            return ResponseUtils.getResponse(pageVO1, ResponseUtils.ResultType.OK);
        } else {
            return ResponseUtils.getResponse(pageVO1, ResponseUtils.ResultType.INTERNAL_SERVER_ERROR);
        }
    }

}

