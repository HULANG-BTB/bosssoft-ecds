package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AgenItemDTO;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.AgenItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.AgenItemService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
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
 * @since 2020-08-12
 */
@RestController
@RequestMapping("/agenItem")
@Api(value = "单位可用项目接口")
public class AgenItemController {
    @Autowired
    private AgenItemService agenItemService;

    /**
     * 插入单位可用项目相关信息
     *
     * @param agenItemVO 可用项目相关信息
     * @return
     */
    @ApiOperation(value = "添加单位可用项目关系")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody AgenItemVO agenItemVO) {
        AgenItemDTO agenItemDTO = MyBeanUtil.myCopyProperties(agenItemVO, AgenItemDTO.class);
        return agenItemService.save(agenItemDTO);
    }

    /**
     * 删除项目
     *
     * @param agenItemVO
     * @return
     */
    @ApiOperation(value = "删除单位可用项目关系")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody AgenItemVO agenItemVO) {
        AgenItemDTO agenItemDTO = MyBeanUtil.myCopyProperties(agenItemVO, AgenItemDTO.class);
        return agenItemService.delete(agenItemDTO);
    }

    /**
     * 批量删除
     *
     * @param agenItemVOList
     * @return
     */
    @ApiOperation(value = "批量删除单位可用项目关系")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<AgenItemVO> agenItemVOList) {
        List<AgenItemDTO> agenItemDTOList = MyBeanUtil.copyListProperties(agenItemVOList, AgenItemDTO::new);
        return agenItemService.batchDelete(agenItemDTOList);
    }

    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return agenItemService.listByPage(pageDTO);
    }

}

