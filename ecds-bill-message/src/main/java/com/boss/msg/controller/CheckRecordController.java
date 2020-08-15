package com.boss.msg.controller;

import com.boss.msg.entity.dto.CheckRecordDto;
import com.boss.msg.entity.vo.CheckRecordQueryVo;
import com.boss.msg.entity.vo.PageResult;
import com.boss.msg.service.CheckRecordService;
import com.boss.msg.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:31
 */
@RestController
@RequestMapping("/checkRecord")
@CrossOrigin
@Slf4j
public class CheckRecordController {

    @Resource
    private CheckRecordService checkRecordService;


    @PostMapping("/list")
    public String listByPage(@RequestBody CheckRecordQueryVo recordQuery){
        log.info(recordQuery.toString());
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

}
