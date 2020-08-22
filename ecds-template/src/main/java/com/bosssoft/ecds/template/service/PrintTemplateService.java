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

    /**
     * 根据票据代码选出一个打印模板
     * 如果默认模板表有记录，就返回默认，没有就选择数据库找到的第一条记录
     * 再没有就返回 null
     * @param billCode 票据代码前6位
     */
    PrintTemplateDto getByBillCode(String billCode);
}
