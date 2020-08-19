package com.bosssoft.ecds.msg.controller;

import com.bosssoft.ecds.msg.entity.dto.CheckRecordDto;
import com.bosssoft.ecds.msg.entity.vo.CheckRecordQueryVo;
import com.bosssoft.ecds.msg.entity.vo.CheckRecordVo;
import com.bosssoft.ecds.msg.entity.vo.PageResult;
import com.bosssoft.ecds.msg.service.CheckRecordService;
import com.bosssoft.ecds.msg.util.ResponseUtils;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:31
 */
@Api(tags = "票据查验记录")
@RestController
@RequestMapping("/checkRecord")
@CrossOrigin
@Slf4j
public class CheckRecordController {

    @Resource
    private CheckRecordService checkRecordService;


    /**
     * 分页查询查验记录
     * @param recordQuery 查验记录查询对象
     * @return 查验记录
     */
    @ApiOperation("查询票据查验记录")
    @PostMapping("/list")
    public String listByPage(@RequestBody CheckRecordQueryVo recordQuery){
        // 获取匹配记录数
        Long total = checkRecordService.getTotal(recordQuery);
        // 查询匹配记录
        List<CheckRecordDto> mails = checkRecordService.listPage(recordQuery, recordQuery.getPage(), recordQuery.getLimit());
        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(total, recordQuery.getLimit(), recordQuery.getPage(), mails);

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.OK.getCode(),
                ResponseUtils.ResultType.OK.getMsg(),
                pageResult);
    }

    /**
     * 邮件删除功能
     * @param id 邮件id
     */
    @ApiOperation("根据Id删除查验记录")
    @DeleteMapping("/deleteCheckRecord")
    public String delCheckRecord(String id) {
        boolean b = checkRecordService.removeById(id);
        if (b) {
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.OK.getCode(),
                    ResponseUtils.ResultType.OK.getMsg(),
                    true);
        }

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.NOT_MODIFIED.getCode(),
                ResponseUtils.ResultType.NOT_MODIFIED.getMsg(),
                false);

    }

    /**
     * 查验记录批量删除功能
     * @param recordVos 邮件集合
     */
    @ApiOperation("批量删除查验记录")
    @DeleteMapping("/deleteCheckRecordBatch")
    public String delCheckRecordBatch(@RequestBody List<CheckRecordVo> recordVos) {
        List<Long> ids = Lists.newArrayList();
        recordVos.forEach(recordVo -> ids.add(recordVo.getId()));
        boolean b = checkRecordService.removeByIds(ids);
        if (b) {
            return ResponseUtils.getResponse(
                    ResponseUtils.ResultType.OK.getCode(),
                    ResponseUtils.ResultType.OK.getMsg(),
                    true);
        }
        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.NOT_MODIFIED.getCode(),
                ResponseUtils.ResultType.NOT_MODIFIED.getMsg(),
                false);

    }


}
