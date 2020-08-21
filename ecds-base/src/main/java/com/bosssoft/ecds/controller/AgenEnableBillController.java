package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.agendto.AgenEnableBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.AgenEnableBillVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.AgenEnableBillService;
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
public class AgenEnableBillController {

    @Autowired
    private AgenEnableBillService agenBillService;


    /**
     * 批量插入单位可用票据相关信息
     *
     * @param agenBillVOList 输入单位编码和票据编码
     * @return 返回成功或者失败的code和msg
     */
    @ApiOperation(value = "批量添加单位可用票据关系", notes = "输入单位编码和票据编码")
    @PostMapping("/updateBatch")
    public ResponseResult updateBatch(@RequestBody List<AgenEnableBillVO> agenBillVOList) {
        List<AgenEnableBillDTO> agenBillDTOList = MyBeanUtil.copyListProperties(agenBillVOList, AgenEnableBillDTO.class);
        return agenBillService.updateBatch(agenBillDTOList);
    }

    /**
     * 查询单位的可用票据，供单位端使用
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
    public QueryResponseResult<List<BillTypePO>> getBill(@RequestBody AgenEnableBillVO agenBillVO) {
        AgenEnableBillDTO agenBillDTO = MyBeanUtil.myCopyProperties(agenBillVO, AgenEnableBillDTO.class);
        return agenBillService.getBill(agenBillDTO);
    }


}

