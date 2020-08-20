package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.vo.billtypevo.AddBillTypeVO;
import com.bosssoft.ecds.entity.vo.billtypevo.BillTypeIdVo;
import com.bosssoft.ecds.entity.vo.billtypevo.QueryTableVO;
import com.bosssoft.ecds.entity.vo.billtypevo.UpdateBillTypeVO;
import com.bosssoft.ecds.service.BillTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.bosssoft.ecds.response.CommonCode.FAIL;

/**
 * @author :Raiz
 * @date :2020/8/5
 */
@RestController
@RequestMapping("/billType/")
@Api(value = "票据种类管理")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillTypeController {

    private final BillTypeService billTypeService;

    @PostMapping("add")
    @ApiOperation(value = "添加票据种类", notes = "票据编码和名称唯一,种类的编码必须以父类的编码为前缀")
    public ResponseResult add(@RequestBody @Valid AddBillTypeVO addBillTypeVO) {
        return billTypeService.add(addBillTypeVO);
    }

    @PostMapping("delete")
    @ApiOperation(value = "逻辑删除票据种类", notes = "逻辑删除")
    public ResponseResult delete(@RequestBody @Valid BillTypeIdVo billTypeIdVo) {
        return billTypeService.delete(billTypeIdVo);
    }

    @ApiOperation(value = "更新票据种类", notes = "票据编码和名称唯一,种类的编码必须以父类的编码为前缀")
    @PostMapping("update")
    public ResponseResult update(@RequestBody @Valid UpdateBillTypeVO updateBillTypeVO) {
        return billTypeService.update(updateBillTypeVO);
    }

    @ApiOperation(value = "根据条件查询票据种类")
    @PostMapping("queryByCondition")
    public ResponseResult queryByPage(@RequestBody @Valid QueryTableVO queryTableVO) {
        return billTypeService.queryByPage(queryTableVO);
    }

    @ApiOperation(value = "查询所有票据分类")
    @PostMapping("queryAllBillSort")
    public ResponseResult queryAllBillSort() {
        return billTypeService.queryAllBillSort();
    }

    @ApiOperation(value = "查询所有票据种类")
    @PostMapping("queryAllBillType")
    public ResponseResult queryAllBillType() {
        return billTypeService.queryAllBillType();
    }

    @ApiOperation(value = "查询票据分类及其种类以树状返回")
    @PostMapping("queryBillTypeTree")
    public ResponseResult queryBillTypeTree() {
        return billTypeService.queryBillTypeTree();
    }

    @PostMapping("exception")
    public ResponseResult exception() {
        throw new CustomException(FAIL, "自定义信息");
    }
}
