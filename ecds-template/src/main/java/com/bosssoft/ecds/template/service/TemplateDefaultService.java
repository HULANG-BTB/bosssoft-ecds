package com.bosssoft.ecds.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.template.entity.po.TemplateDefaultPo;

/**
 * <p>
 * 默认模板表，保存默认的模板编号 服务类
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-22
 */
public interface TemplateDefaultService extends IService<TemplateDefaultPo> {

    boolean setDefault(String print, String billCode, Long id);

    Long getDefault(String type, String billCode);

    boolean removeDefault(String type, String billCode);
}
