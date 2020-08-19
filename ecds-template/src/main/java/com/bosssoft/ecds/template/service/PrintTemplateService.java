package com.bosssoft.ecds.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;

import java.util.List;

/**
 * <p>
 * 打印模板表 服务类
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-17
 */
public interface PrintTemplateService extends IService<PrintTemplatePo> {
    List<PrintTemplateDto> listAll();

    boolean add(PrintTemplateDto templateDTO);

    boolean remove(Long id);

    PrintTemplateDto getDtoById(Long id);
}
