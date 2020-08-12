package com.boss.msg.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.boss.msg.entity.dto.MailDto;
import com.boss.msg.entity.po.MailPo;

import java.util.List;

/**
 * @author zhangxiaohui
 */
public interface MailService extends IService<MailPo> {
    /**
     * 分页查询
     * @param mailDto 查询对象
     * @param page 当前页面
     * @param limit 当前页面大小
     * @return 查询到匹配的结果集
     */
    List<MailDto> listPage(MailDto mailDto,Long page,Long limit);

    /**
     * 获取数据库中匹配的总记录数
     * @param mailDto 查询对象
     * @return 总记录数
     */
    Long getTotal(MailDto mailDto);

    /**
     * 更新发件状态
     * @param mailDto 邮件信息
     * @return 更新成功与否
     */
    boolean updateStatus(MailDto mailDto);
}
