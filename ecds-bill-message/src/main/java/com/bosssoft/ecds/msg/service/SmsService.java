package com.bosssoft.ecds.msg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.msg.entity.dto.SmsDto;
import com.bosssoft.ecds.msg.entity.po.SmsPo;
import com.bosssoft.ecds.msg.entity.vo.SmsQueryVo;

import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/12 10:31
 */
public interface SmsService extends IService<SmsPo> {
    /**
     * 根据用户手机号码和校验码查询票据信息
     *
     * @param tel        11位电话号码
     * @param verifyCode 6位校验码
     * @return 票据信息json格式
     */
    String getBillByKey(String tel, String verifyCode);

    /**
     * 分页查询
     * @param smsQuery 查询对象
     * @param page 当前页面
     * @param limit 当前页面大小
     * @return 查询到匹配的结果集
     */
    List<SmsDto> listPage(SmsQueryVo smsQuery, Long page, Long limit);

    /**
     * 获取数据库中匹配的总记录数
     * @param smsQuery 查询对象
     * @return 总记录数
     */
    Long getTotal(SmsQueryVo smsQuery);

    /**
     * 更新发件状态
     * @param smsDto 短信信息
     * @return 更新成功与否
     */
    boolean updateStatus(SmsDto smsDto);


    /**
     * 调用短信发送接口后，自动保存短信记录
     * @param smsDto 短信信息
     */
    void saveAutoSentSms(SmsDto smsDto);

}
