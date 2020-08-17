package com.bosssoft.ecds.msg.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.File;
import java.util.List;

/**
 * @author zhangxiaohui
 */
@Data
@ToString

public class SendMailVo {


    /**
     * 邮件接收人，若有多个收件人，使用 , 分割
     */
    private String mailTo;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件附件
     */
    @JsonIgnore
    private List<File> files;

}
