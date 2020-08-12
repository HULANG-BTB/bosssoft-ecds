package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.vo.VoucherVO;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/api/voucher")
@Api(tags = "电子凭证")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/listAll")
    @ResponseBody
    @ApiOperation(value = "所有电子凭证信息")
    public String listAll(){
        List<VoucherDTO> voucherDTOList = voucherService.listAll();
        List<VoucherVO> voucherVOList = MyBeanUtil.copyListProperties(voucherDTOList,VoucherVO.class);
        return ResponseUtils.getResponse(voucherVOList, ResponseUtils.ResultType.OK);
    }

    @PostMapping("/getByAccountId")
    @ResponseBody
    @ApiImplicitParam(name = "accountId", value = "电子凭证号", dataType = "Long")
    @ApiOperation(value = "通过电子凭证号查询电子凭证")
    public String getByAccountId(@RequestBody VoucherVO voucherVO){
        VoucherDTO voucherDTO = new VoucherDTO();
        MyBeanUtil.copyProperties(voucherVO,voucherDTO);
        voucherService.getByAccountId(voucherDTO);
        MyBeanUtil.copyProperties(voucherDTO,voucherVO);
        return ResponseUtils.getResponse(voucherVO, ResponseUtils.ResultType.OK);
    }

}

