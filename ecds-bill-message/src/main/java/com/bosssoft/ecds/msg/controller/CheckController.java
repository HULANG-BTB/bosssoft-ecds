package com.bosssoft.ecds.msg.controller;

import com.bosssoft.ecds.msg.service.CheckRecordService;
import com.bosssoft.ecds.msg.service.client.CheckClient;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:31
 */
@Api(tags = "票据查验")
@RestController
@RequestMapping("/check")
@CrossOrigin
@Slf4j
public class CheckController extends BaseController {

    /**
     * 调用开票模块的查询接口
     */
    @Resource
    private CheckClient checkClient;

    @Resource
    private CheckRecordService checkRecordService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private ObjectMapper mapper;

    /**
     * 调用远程接口查询开票信息
     * 用户查询 -> 提交票据号码及校验码 -> 参数校验 -> 调用远程接口查询
     * 接口返回结果 -> 保存查验记录 -> 返回查验结果给前端
     * @param billId    票据号码
     * @param checkCode 校验码
     * @return 票据查验信息，查验成功包括票据具体信息
     */
    @ApiOperation("票据查验接口")
    @GetMapping("/billCheck")
    public ResponseResult billCheck(@RequestParam String billId, @RequestParam String checkCode) throws JsonProcessingException {
        // 参数校验
        if (StringUtils.isBlank(billId) || StringUtils.isBlank(checkCode)) {
            return new ResponseResult(CommonCode.INVLIDATE);
        }
        // result 查验结果，0伪1真
        int result = 0;
        String resStr = checkClient.getBillByIdAndCheckCode(billId, checkCode);
        log.info(resStr);
        Map<?, ?> response = mapper.readValue(resStr, Map.class);
        Object isSuccess = response.get("success");
        // 对返回体进行解析，获取响应状态码及携带的信息
        if ((boolean) isSuccess) {
            // 查询成功
            result = 1;
        }
        // 处理返回结果,保存查验记录

        checkRecordService.saveRecord(billId, result);
        if (result == 1) {
            // 查验票据为真
            return new QueryResponseResult<>(CommonCode.SUCCESS, response.get("data"));
        }
        // 查验票据为假
        return new QueryResponseResult<>(CommonCode.FAIL, response.get("data"));
    }

}
