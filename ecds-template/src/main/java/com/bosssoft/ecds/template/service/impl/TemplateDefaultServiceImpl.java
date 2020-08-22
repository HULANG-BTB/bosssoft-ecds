package com.bosssoft.ecds.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.template.entity.po.TemplateDefaultPo;
import com.bosssoft.ecds.template.mapper.TemplateDefaultMapper;
import com.bosssoft.ecds.template.service.TemplateDefaultService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 默认模板表，保存默认的模板编号 服务实现类
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-22
 */
@Service
public class TemplateDefaultServiceImpl extends ServiceImpl<TemplateDefaultMapper, TemplateDefaultPo> implements TemplateDefaultService {

}
