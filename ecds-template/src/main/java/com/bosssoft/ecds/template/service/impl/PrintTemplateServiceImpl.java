package com.bosssoft.ecds.template.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDTO;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePO;
import com.bosssoft.ecds.template.mapper.PrintTemplateMapper;
import com.bosssoft.ecds.template.service.PrintTemplateService;
import com.bosssoft.ecds.template.util.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 打印模板表 服务实现类
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-17
 */
@Slf4j
@Service
public class PrintTemplateServiceImpl extends ServiceImpl<PrintTemplateMapper, PrintTemplatePO> implements PrintTemplateService {

//    @Resource
//    PrintTemplateMapper printTemplateMapper;

    @Override
    public List<PrintTemplateDTO> listAll() {
        List<PrintTemplatePO> templatePOs = this.list();
        return BeanCopyUtil.copyListProperties(templatePOs, PrintTemplateDTO::new);
    }

    @Override
    public boolean add(PrintTemplateDTO templateDTO) {
        PrintTemplatePO templatePO = new PrintTemplatePO();
        BeanUtils.copyProperties(templateDTO, templatePO);
        return this.save(templatePO);
    }

    @Override
    public boolean remove(Long id) {
        return this.removeById(id);
    }

    @Override
    public PrintTemplateDTO getDtoById(Long id) {
        PrintTemplatePO printTemplatePO = this.getById(id);
        PrintTemplateDTO printTemplateDTO = new PrintTemplateDTO();
        BeanUtils.copyProperties(printTemplatePO, printTemplateDTO);
        return printTemplateDTO;
    }
}
