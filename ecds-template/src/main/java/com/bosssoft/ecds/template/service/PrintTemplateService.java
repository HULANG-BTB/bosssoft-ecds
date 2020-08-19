package com.bosssoft.ecds.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDTO;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePO;

import java.util.List;

/**
 * <p>
 * 打印模板表 服务类
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-17
 */
public interface PrintTemplateService extends IService<PrintTemplatePO> {
    List<PrintTemplateDTO> listAll();

    boolean add(PrintTemplateDTO templateDTO);

    boolean remove(Long id);

    PrintTemplateDTO getDtoById(Long id);
}
