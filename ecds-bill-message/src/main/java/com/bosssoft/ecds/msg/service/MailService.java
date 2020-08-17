package com.bosssoft.ecds.msg.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.msg.entity.dto.MailDto;
import com.bosssoft.ecds.msg.entity.po.MailPo;
import com.bosssoft.ecds.msg.entity.vo.MailQueryVo;

import java.util.List;

/**
 * @author zhangxiaohui
 */
public interface MailService extends IService<MailPo> {
    /**
     * 分页查询
     * @param mailQuery 查询对象
     * @param page 当前页面
     * @param limit 当前页面大小
     * @return 查询到匹配的结果集
     */
    List<MailDto> listPage(MailQueryVo mailQuery, Long page, Long limit);

    /**
     * 获取数据库中匹配的总记录数
     * @param mailQuery 查询对象
     * @return 总记录数
     */
    Long getTotal(MailQueryVo mailQuery);

    /**
     * 更新发件状态
     * @param mailDto 邮件信息
     * @return 更新成功与否
     */
    boolean updateStatus(MailDto mailDto);

    /**
     * 手动添加邮件记录
     * @param mailDto 邮件详情
     * @return 添加状态
     */
    boolean saveMail(MailDto mailDto);
}
