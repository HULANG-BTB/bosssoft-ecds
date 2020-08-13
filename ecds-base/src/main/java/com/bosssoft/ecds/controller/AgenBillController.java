package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AgenBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.AgenBillVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.AgenBillService;
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
@RequestMapping("/agenBill")
@Api(value = "单位可用票据接口")
public class AgenBillController {

    @Autowired
    private AgenBillService agenBillService;


    /**
     * 批量插入单位可用票据相关信息
     *
     * @param agenBillVOList 输入单位编码和票据编码
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量添加单位可用票据关系", notes = "输入单位编码和票据编码")
    @PostMapping("/updateBatch")
    public ResponseResult updateBatch(@RequestBody List<AgenBillVO> agenBillVOList) {
        List<AgenBillDTO> agenBillDTOList = MyBeanUtil.copyListProperties(agenBillVOList, AgenBillDTO.class);
        return agenBillService.updateBatch(agenBillDTOList);
    }

    /**
     * 分页查询
     *
     * @param pageVO 输入分页信息,limit、page、keyword
     *               keyword为空时普通查询，keyword不为空时分页查询
     * @return limit、page、total、items
     */
    @ApiOperation(value = "分页查询", notes = "输入分页信息,limit、page、keyword，" +
            "keyword为空时普通查询，keyword不为空时模糊查询")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<BillTypePO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return agenBillService.listByPage(pageDTO);
    }

    /**
     * 查询单位所有可用票据
     *
     * @param agenBillVO 输入单位编码
     * @return 返回出单位所有的可用票据
     */
    @ApiOperation(value = "查询单位所有可用票据", notes = "输入单位编码")
    @PostMapping("/getBillAll")
    public QueryResponseResult<List<BillTypePO>> getBill(@RequestBody AgenBillVO agenBillVO) {
        AgenBillDTO agenBillDTO = MyBeanUtil.myCopyProperties(agenBillVO, AgenBillDTO.class);
        return agenBillService.getBill(agenBillDTO);
    }
}

