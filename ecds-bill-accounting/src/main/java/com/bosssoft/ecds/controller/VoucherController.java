package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.vo.VoucherVO;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult listAll(){
        return voucherService.listAll();

    }

    @PostMapping("/getByAccountId")
    @ResponseBody
    @ApiImplicitParam(name = "accountId", value = "电子凭证号", dataType = "Long")
    @ApiOperation(value = "通过电子凭证号查询电子凭证")
    public ResponseResult getByAccountId(@RequestBody VoucherVO voucherVO){
        VoucherDTO voucherDTO = new VoucherDTO();
        MyBeanUtil.copyProperties(voucherVO,voucherDTO);
        return voucherService.getByAccountId(voucherDTO);
    }

}

