package com.bosssoft.ecds.msg.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
/**
 * @author zhangxiaohui
 */
@Slf4j
public class SmsUtil {
    private SmsUtil(){}

    /**
     * 短信API产品名称（短信产品名固定，无需修改）
     */
    static final String PRODUCT = "Dysmsapi";
    /**
     * regionId，暂时不支持多region
     */
    static final String REGION_ID = "cn-hangzhou";

    /**
     * 短信API产品域名（接口地址固定，无需修改）
     */
    static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 初始化ascClient需要的几个参数
     *
     * @return ascClient
     */
    public static IAcsClient getSmsClient(String accessKeyId,String accessKeySecret) {

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId,
                accessKeySecret);

        // static final String ENDPOINT_NAME = "cn-hangzhou"; 被废弃方法所需的参数
        // DefaultProfile.addEndpoint(ENDPOINT_NAME, REGION_ID, PRODUCT, DOMAIN);废弃
        DefaultProfile.addEndpoint(REGION_ID, PRODUCT,DOMAIN);
        return new DefaultAcsClient(profile);
    }

    /**
     * 封装请求对象
     * @param phoneNumbers 短信通知对象
     * @param signName sms签名名称
     * @param templateCode  sms模版code
     * @param obj 通知信息
     *
     * 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
     * request.setSmsUpExtendCode("90997");
     * 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
     * request.setOutId("yourOutId");
     */
    public static SendSmsRequest getSmsRequest(String phoneNumbers, String signName, String templateCode, Object obj) {

        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setSysMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //参考：request.setTemplateParam("{\"变量1\":\"值1\",\"变量2\":\"值2\",\"变量3\":\"值3\"}")
        request.setTemplateParam(JSON.toJSONString(obj));

        return request;

    }
}
