package com.bosssoft.ecds.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.template.entity.po.TemplateDefaultPo;
import com.bosssoft.ecds.template.mapper.TemplateDefaultMapper;
import com.bosssoft.ecds.template.service.TemplateDefaultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

    @Resource
    TemplateDefaultMapper templateDefaultMapper;

    @Override
    public boolean setDefault(String type, String billCode, Long id) {
        TemplateDefaultPo defaultPo = new TemplateDefaultPo();
        defaultPo.setBillcode(billCode);
        defaultPo.setType(type);
        defaultPo.setDefaultId(id);
        defaultPo.setCreateTime(LocalDateTime.now());
        defaultPo.setUpdateTime(LocalDateTime.now());

        UpdateWrapper<TemplateDefaultPo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(TemplateDefaultPo::getBillcode, billCode)
                .eq(TemplateDefaultPo::getType, type)
                .set(TemplateDefaultPo::getDefaultId, id);

        return this.saveOrUpdate(defaultPo, updateWrapper);
    }

    @Override
    public Long getDefault(String type, String billCode) {
        Long defaultId = 0L;
        QueryWrapper<TemplateDefaultPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(TemplateDefaultPo::getType, type)
                .eq(TemplateDefaultPo::getBillcode, billCode);

        TemplateDefaultPo def = this.getOne(queryWrapper, false);
        if (def != null) {
            defaultId = def.getDefaultId();
        }

        return defaultId;
    }

    @Override
    public boolean isDefault(String type, Long defaultId) {
        QueryWrapper<TemplateDefaultPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(TemplateDefaultPo::getType, type)
                .eq(TemplateDefaultPo::getDefaultId, defaultId);
        TemplateDefaultPo po = this.getOne(queryWrapper, false);
        return po!=null;
    }

    @Override
    public boolean removeDefault(String type, String billCode) {

        QueryWrapper<TemplateDefaultPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(TemplateDefaultPo::getType, type)
                .eq(TemplateDefaultPo::getBillcode, billCode);

        int rows = templateDefaultMapper.delete(queryWrapper);

        return rows>0;
    }
}
