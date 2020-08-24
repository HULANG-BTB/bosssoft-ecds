package com.bosssoft.ecds.msg.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.bosssoft.ecds.msg.entity.dto.MessageDto;
import com.bosssoft.ecds.msg.entity.dto.SmsDto;
import com.bosssoft.ecds.msg.entity.vo.*;
import com.bosssoft.ecds.msg.service.SendSmsService;
import com.bosssoft.ecds.msg.service.SmsService;
import com.bosssoft.ecds.msg.util.DozerUtils;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;


/**
 * @author zhangxiaohui
 */
@Api(tags = "短信发送及短信发信记录")
@RestController
@RequestMapping("/sms")
//@CrossOrigin
@Slf4j
public class SmsController extends BaseController {

    @Resource
    private SendSmsService sendsmsService;

    @Resource
    private SmsService smsService;

    /**
     * 短信发送接口
     *
     * @param smsVo 短信收件人，及内容是必须的
     * @return 发件成功与否
     */
    @ApiOperation("发送短信")
    @PostMapping("/send")
    public ResponseResult sendSms(@RequestBody SendSmsVo smsVo) throws ClientException, ExecutionException, InterruptedException {
        log.info(smsVo.toString());
        SmsDto smsDto = DozerUtils.map(smsVo, SmsDto.class);
        SmsDto sentSms = sendsmsService.sendSms(smsDto).get();
        boolean isSent = sentSms.getIsSent();
        // 向布隆过滤器中添加记录，异步
        addSmsToBloomFilter(sentSms);
        // 保存短信记录，异步
        smsService.saveAutoSentSms(sentSms);
        return getRes(isSent);
    }

    /**
     * 开票模块每次调用发信接口后
     * 分别向短信查验和票据查验的布隆过滤器中存入新数据
     * 1. 短信查验的bloomValue为短信查验字段 tel + verifyCode 的字符串
     * 2. 票据Id查验的bloomValue为票据查询字段 bilId + checkCode 的字符串
     */
    public void addSmsToBloomFilter(SmsDto smsDto) {
        boolean isSent = smsDto.getIsSent();
        if (isSent) {
            // 向SMS布隆过滤器中存入新增数据
            String smsBloomValue = smsDto.getSmsTo() + smsDto.getVerifyCode();
            redisBloomFilter.addByBloomFilter(bloomFilterHelper, REDIS_BLOOM_KEY_SMS, smsBloomValue);
        }
        // 向BILL布隆过滤器中存入新增数据
        String content = smsDto.getContent();
        MessageDto messageDto = JSON.parseObject(content, MessageDto.class);
        String billIdBloomValue = messageDto.getFBillId() + messageDto.getFCheckCode();
        redisBloomFilter.addByBloomFilter(bloomFilterHelper, REDIS_BLOOM_KEY_BILL_ID, billIdBloomValue);
    }

    /**
     * 根据用户手机号码和校验码查询票据信息
     *
     * @param tel        11位电话号码
     * @param verifyCode 6位校验码,不区分大小写，一律转为小写进行查询
     * @return 票据信息json格式
     */
    @ApiOperation("通过手机号和校验码获取票据信息")
    @GetMapping("/getBill")
    public ResponseResult getBillByKey(String tel, String verifyCode) {
        log.info("-- tel:" + tel + ";verifyCode:" + verifyCode);
        // 电话号码参数校验
        String telRegex = "^1\\d{10}$";
        if (tel == null || !Pattern.matches(telRegex, tel)) {
            return new ResponseResult(CommonCode.INVLIDATE);
        }
        // 校验码参数校验
        String verifyCodeRegex = "^[A-Za-z0-9]{6}$";
        if (verifyCode == null || !Pattern.matches(verifyCodeRegex, verifyCode)) {
            return new ResponseResult(CommonCode.INVLIDATE);
        }

        // 使用布隆过滤器，过滤非法请求
        String value = tel + verifyCode;
        boolean bloom = redisBloomFilter.includeByBloomFilter(bloomFilterHelper, REDIS_BLOOM_KEY_SMS, value);
        log.info("bloom >>> bill >>> " + value + " : " + bloom);
        if (!bloom) {
            log.info("手机号票据查验失败，被布隆过滤器拦截");
            return ResponseResult.FAIL();
        }

        // 参数无误，查询票据
        String bill = smsService.getBillByKey(tel, verifyCode.toLowerCase());
        if (StringUtils.isNotBlank(bill)) {
            return new QueryResponseResult<>(CommonCode.SUCCESS, bill);
        }
        return new ResponseResult(CommonCode.FAIL);

    }

    /**
     * 分页查询邮件
     * 根据Id,isSent,mailTo字段查询匹配的邮件
     * page是当前页码，limit是每页大小
     *
     * @param smsQuery 分页查询对象
     */
    @ApiOperation("查询短信发信记录")
    @PostMapping("/list")
    public QueryResponseResult<PageResult> listPage(@RequestBody SmsQueryVo smsQuery) {
        // 获取匹配记录数
        Long total = smsService.getTotal(smsQuery);
        // 查询匹配记录
        List<SmsDto> mails = smsService.listPage(smsQuery, smsQuery.getPage(), smsQuery.getLimit());
        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(total, smsQuery.getLimit(), smsQuery.getPage(), mails);

        return new QueryResponseResult<>(CommonCode.SUCCESS, pageResult);
    }

    /**
     * 短信更新功能
     * 实现未发件的邮件状态更新为已发件
     *
     * @param smsVo 分页查询对象
     * @return ResponseResult
     */
    @ApiOperation("更新短信发信状态为已发送")
    @PutMapping("/updateStatus")
    public ResponseResult updateStatus(@RequestBody SmsVo smsVo) {
        SmsDto smsDto = DozerUtils.map(smsVo, SmsDto.class);
        // 修改isSent
        boolean b = smsService.updateStatus(smsDto);
        return getRes(b);

    }

}
