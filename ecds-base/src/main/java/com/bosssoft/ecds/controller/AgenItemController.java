package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.agendto.AgenItemDTO;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.AgenItemVO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
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
 * 前端控制器
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
     * 批量维护单位可用项目相关信息
     *
     * @param agenItemVOList 输入单位编码和项目编码
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量添加单位可用项目关系", notes = "输入单位编码和项目编码")
    @PostMapping("/updateBatch")
    public ResponseResult updateBatch(@RequestBody List<AgenItemVO> agenItemVOList) {
        List<AgenItemDTO> agenBillDTOList = MyBeanUtil.copyListProperties(agenItemVOList, AgenItemDTO.class);
        return agenItemService.updateBatch(agenBillDTOList);
    }

    /**
     * 查询单位所有可用项目
     *
     * @param agenItemVO 输入单位编码
     * @return 返回出单位所有的可用项目
     */
    @ApiOperation(value = "查询单位所有可用项目", notes = "输入单位编码")
    @PostMapping("/getItemAll")
    public QueryResponseResult<List<ItemVO>> getItem(@RequestBody AgenItemVO agenItemVO) {
        AgenItemDTO agenItemDTO = MyBeanUtil.myCopyProperties(agenItemVO, AgenItemDTO.class);
        return agenItemService.getItemAll(agenItemDTO);
    }

    /**
     * 分页查询
     *
     * @param pageVO 输入分页信息,limit、page、keyword
     *               keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    @ApiOperation(value = "分页查询", notes = "输入分页信息,limit、page、keyword，" +
            "keyword为空时普通查询，keyword不为空时模糊查询")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<ItemDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return agenItemService.listByPage(pageDTO);
    }
}

