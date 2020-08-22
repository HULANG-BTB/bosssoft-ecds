package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.encryption.Decrypt;
import com.bosssoft.ecds.encryption.Encrypt;
import com.bosssoft.ecds.entity.vo.incomesortvo.AddIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.DeleteIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.FuzzyQueryIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.UpdateIncomeSortVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.IncomeSortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 收入类别前端控制器
 *
 * @author: Jianbinbing
 * @Date: 2020/8/5 19:09
 */
@RestController
@RequestMapping("/incomeSort")
@Slf4j
@Api(tags = "收入类别管理接口")
public class IncomeSortController {

    @Autowired
    private IncomeSortService incomeSortService;

    @ApiOperation(value = "获取所有收入类别信息", notes = "返回一个多级的树形结构数据")
    @GetMapping("/getAll")
    @Encrypt
    public QueryResponseResult getAll() {
        return incomeSortService.getAll();
    }

    @ApiOperation(value = "分页查询收入类别信息", notes = "通过名称(可以为空)、编码(可以为空)模糊查询")
    @PostMapping("/pageQuery")
    public QueryResponseResult pageQuery(@RequestBody @Validated FuzzyQueryIncomeSortVO fuzzyQueryIncomeSortVO) {
        return incomeSortService.pageQueryByName(fuzzyQueryIncomeSortVO);
    }

    @ApiOperation(value = "分页查询收入类别信息", notes = "通过id查询显示子级收入类别")
    @PostMapping("/pageQueryById")
    public QueryResponseResult pageQueryById(@RequestBody @Validated FuzzyQueryIncomeSortVO fuzzyQueryIncomeSortVO) {
        return incomeSortService.pageQueryByName(fuzzyQueryIncomeSortVO);
    }

    @ApiOperation(value = "更新收入类别信息", notes = "可以修改收入名称、是否底级、备注信息等")
    @PostMapping("/update")
    @Decrypt
    public ResponseResult update(@RequestBody @Validated UpdateIncomeSortVO updateIncomeSortVO) {
        incomeSortService.update(updateIncomeSortVO);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @ApiOperation(value = "新增收入类别信息", notes = "类别名称和编码唯一")
    @PostMapping("/insert")
    @Decrypt
    public ResponseResult insert(@RequestBody  AddIncomeSortVO addIncomeSortVO) {
        incomeSortService.add(addIncomeSortVO);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @ApiOperation(value = "查询第一级的收入类别")
    @PostMapping("/getFirst")
    public QueryResponseResult getFirst() {
        return incomeSortService.getFirstIncomeSort();
    }

    @ApiOperation(value = "删除指定的收入类别", notes = "逻辑删除")
    @PostMapping("delete")
    public ResponseResult delete(@RequestBody DeleteIncomeSortVO deleteIncomeSortVO) {
        incomeSortService.delete(deleteIncomeSortVO);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @ApiOperation(value = "对外提供收入类别所有信息")
    @GetMapping("selectAll")
    public QueryResponseResult select() {
        return incomeSortService.selectAll();
    }

    @ApiOperation(value = "对外提供收入类别信息")
    @GetMapping("getBySubjectId")
    public QueryResponseResult getBySubjectId(Long subjectId) {
        return incomeSortService.getBySubjectId(subjectId);
    }

}
