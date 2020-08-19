package com.bosssoft.ecds.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;
import com.bosssoft.ecds.template.mapper.PrintTemplateMapper;
import com.bosssoft.ecds.template.service.PrintTemplateService;
import com.bosssoft.ecds.template.util.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class PrintTemplateServiceImpl extends ServiceImpl<PrintTemplateMapper, PrintTemplatePo> implements PrintTemplateService {

//    @Resource
//    PrintTemplateMapper printTemplateMapper;

    @Override
    public List<PrintTemplateDto> listAll() {
        List<PrintTemplatePo> templatePOs = this.list();
        return BeanCopyUtil.copyListProperties(templatePOs, PrintTemplateDto::new);
    }

    @Override
    public boolean add(PrintTemplateDto templateDTO) {
        PrintTemplatePo templatePO = new PrintTemplatePo();
        BeanUtils.copyProperties(templateDTO, templatePO);
        return this.save(templatePO);
    }

    @Override
    public boolean remove(Long id) {
        return this.removeById(id);
    }

    @Override
    public PrintTemplateDto getDtoById(Long id) {
        PrintTemplatePo printTemplatePO = this.getById(id);
        PrintTemplateDto printTemplateDTO = new PrintTemplateDto();
        BeanUtils.copyProperties(printTemplatePO, printTemplateDTO);
        return printTemplateDTO;
    }
}
