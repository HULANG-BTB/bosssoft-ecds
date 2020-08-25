package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.VoucherVO;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/ecds-bill-accounting/voucher")
@Api(tags = "电子凭证")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @PostMapping("/listByPage")
    @ApiOperation(value = "所有电子凭证信息")
    public QueryResponseResult<PageVO> listByPage(@RequestBody @Validated PageVO pageVO){
        PageDTO<VoucherDTO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return voucherService.listByPage(pageDTO);
    }

    @PostMapping("/getByAccountId")
    @ApiImplicitParam(name = "accountId", value = "电子凭证号", dataType = "Long")
    @ApiOperation(value = "通过电子凭证号查询电子凭证")
    public ResponseResult getByAccountId(@RequestBody @Validated VoucherVO voucherVO){
        VoucherDTO voucherDTO = new VoucherDTO();
        MyBeanUtil.copyProperties(voucherVO,voucherDTO);
        return voucherService.getByAccountId(voucherDTO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除电子凭证")
    public ResponseResult delete(@RequestBody VoucherVO voucherVO){
        VoucherDTO voucherDTO = MyBeanUtil.myCopyProperties(voucherVO, VoucherDTO.class);
        return voucherService.delete(voucherDTO);
    }

    @PostMapping("/batchDelete")
    @ApiOperation(value = "批量删除电子凭证")
    public ResponseResult batchDelete(@RequestBody List<VoucherVO> voucherVOList){
        List<VoucherDTO> voucherDTOList = MyBeanUtil.copyListProperties(voucherVOList, VoucherDTO::new);
        return voucherService.batchDelete(voucherDTOList);
    }

    @PutMapping("/updateVoucher")
    @ApiOperation(value = "更新电子凭证信息")
    public ResponseResult updateVoucher(@RequestBody VoucherVO voucherVO){
        VoucherDTO voucherDTO = MyBeanUtil.myCopyProperties(voucherVO, VoucherDTO.class);
        return voucherService.updateVoucher(voucherDTO);
    }
}

