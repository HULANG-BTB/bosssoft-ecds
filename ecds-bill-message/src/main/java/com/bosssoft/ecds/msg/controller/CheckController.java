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


/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:31
 */
@Api(tags = "票据查验")
@RestController
@RequestMapping("/check")
@CrossOrigin
@Slf4j
public class CheckController extends BaseController{

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
     * response中需要的参数：
     * 1. code: 状态码，记录本次查询成功与否
     * 2. msg: 查询信息，一般为状态码对应的信息
     * 3. data: 记录查询成功后票据详细的票面信息，包括明细表
     *
     * @param billId    票据号码
     * @param checkCode 校验码
     * @return 票据查验信息，查验成功包括票据具体信息
     */
    @ApiOperation("票据查验接口")
    @GetMapping("/billCheck")
    public ResponseResult billCheck(@RequestParam String billId, @RequestParam String checkCode) throws JsonProcessingException {
        if (StringUtils.isBlank(billId) || StringUtils.isBlank(checkCode)) {
            // 参数为空
            return new ResponseResult(CommonCode.INVLIDATE);
        }

        // result 查验结果，0伪1真
        int result = 0;
        String response = checkClient.getBillByIdAndCheckCode(billId, checkCode);
        log.info(response);
        QueryResponseResult<?> res = mapper.readValue(response, QueryResponseResult.class);
        // 对返回体进行解析，获取响应状态码及携带的信息
        if (res.isSuccess()) {
            // 查询成功
            result = 1;
        }
        // 处理返回结果,保存查验记录
        String operIp = getRealIp(request);
        checkRecordService.saveRecord(billId, result, operIp);
        if (result == 1) {
            // 查验票据为真
            return new QueryResponseResult<>(CommonCode.SUCCESS,res.data);
        }
        // 查验票据为假
        return new QueryResponseResult<>(CommonCode.FAIL,res.data);


    }

}
