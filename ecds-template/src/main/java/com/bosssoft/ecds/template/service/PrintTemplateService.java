package com.bosssoft.ecds.template.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;
import com.bosssoft.ecds.template.entity.vo.PrintTemplateVo;

import java.io.InputStream;
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

    IPage<PrintTemplateVo> getPageVO(Long current, Long size);

    List<PrintTemplateVo> searchList(String billCode, String name);

    String convertExcel(InputStream inputStream);
}
