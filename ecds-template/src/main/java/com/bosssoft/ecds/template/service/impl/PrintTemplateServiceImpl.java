package com.bosssoft.ecds.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;
import com.bosssoft.ecds.template.entity.vo.PrintTemplateVo;
import com.bosssoft.ecds.template.mapper.PrintTemplateMapper;
import com.bosssoft.ecds.template.service.PrintTemplateService;
import com.bosssoft.ecds.template.service.TemplateDefaultService;
import com.bosssoft.ecds.template.util.BeanCopyUtil;
import com.bosssoft.ecds.template.util.excel.SampleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.dom4j.DocumentException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 打印模板表 服务实现类
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-17
 */
@Slf4j(topic = "kafka_logger")
@Service
public class PrintTemplateServiceImpl extends ServiceImpl<PrintTemplateMapper, PrintTemplatePo> implements PrintTemplateService {

    @Resource
    PrintTemplateMapper printTemplateMapper;

    @Autowired
    TemplateDefaultService templateDefaultService;

    @Override
    public List<PrintTemplateDto> listAll() {
        List<PrintTemplatePo> templatePOs = this.list();
        return BeanCopyUtil.copyListProperties(templatePOs, PrintTemplateDto::new);
    }

    @Override
    public boolean add(PrintTemplateDto templateDTO) {
        PrintTemplatePo templatePO = new PrintTemplatePo();
        BeanUtils.copyProperties(templateDTO, templatePO);
        templatePO.setVersion(0L);
        templatePO.setCreateTime(LocalDateTime.now());
        templatePO.setUpdateTime(LocalDateTime.now());
        return this.save(templatePO);
    }

    @Override
    public boolean remove(Long id) {
        String billCode = genBillCode(this.getById(id));
        // 被删除的可能是默认的打印模板，一并从默认模板表里面删除
        templateDefaultService.removeDefault("print", billCode);
        return this.removeById(id);
    }

    @Override
    public PrintTemplateDto getDtoById(Long id) {
        PrintTemplatePo printTemplatePO = this.getById(id);
        PrintTemplateDto printTemplateDTO = new PrintTemplateDto();
        BeanUtils.copyProperties(printTemplatePO, printTemplateDTO);
        return printTemplateDTO;
    }

    @Override
    public IPage<PrintTemplateVo> getPageVO(Long current, Long size) {
        return printTemplateMapper.selectTemplateVo(new Page<>(current, size));
    }

    @Override
    public List<PrintTemplateVo> searchList(String billCode, String name) {
        if (name==null)
            name="";
        String rgnCode = "";
        String typeId = "";
        String sortId = "";

        if (billCode!=null && billCode.length()==6) {
            rgnCode = billCode.substring(0, 2);
            typeId = billCode.substring(2, 4);
            sortId = billCode.substring(4, 6);
        }

        QueryWrapper<PrintTemplatePo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                    .eq(!"".equals(rgnCode), PrintTemplatePo::getRgnCode, rgnCode)
                    .eq(!"".equals(typeId), PrintTemplatePo::getTypeId, typeId)
                    .eq(!"".equals(sortId), PrintTemplatePo::getSortId, sortId)
                    .like(!"".equals(name), PrintTemplatePo::getName, name);

        List<PrintTemplatePo> poList = printTemplateMapper.selectList(queryWrapper);

        return BeanCopyUtil.copyListProperties(poList, PrintTemplateVo::new);
    }

    @Override
    public String convertExcel(InputStream inputStream) {
        String ftlTemplate = "";

        StringWriter writer = new StringWriter();
        try {
            SampleUtils.excelToXml(inputStream, writer);
            StringReader reader = new StringReader(writer.toString());
            ftlTemplate = SampleUtils.xmlToFtl(reader, "billDTO");
        } catch (IOException | InvalidFormatException | DocumentException e) {
            e.printStackTrace();
        }

        return ftlTemplate;
    }

    @Override
    public PrintTemplateDto getByBillCode(String billCode) {

        if (billCode==null || billCode.length()!=6)
            return null;
        PrintTemplatePo templatePo = null;

        // 如果默认模板表里面有记录
        Long id = templateDefaultService.getDefault("print", billCode);
        if (id!=0) {
            templatePo = printTemplateMapper.selectById(id);
            if (templatePo==null) {
                return null;
            }
        }
        // 如果没有就从数据库里面找一条
        else {
            String rgnCode = billCode.substring(0, 2);
            String typeId = billCode.substring(2, 4);
            String sortId = billCode.substring(4, 6);

            templatePo =
                    printTemplateMapper.selectFirstByBillCode(rgnCode, typeId, sortId);
            if (templatePo==null)
                return null;
        }

        PrintTemplateDto templateDto = new PrintTemplateDto();
        BeanUtils.copyProperties(templatePo, templateDto);
        return templateDto;
    }

    @Override
    public boolean setDefault(Long id) {
        PrintTemplatePo t = printTemplateMapper.selectById(id);
        if (t==null) return false;
        String billCode = t.getRgnCode() + t.getTypeId() + t.getSortId();
        return templateDefaultService.setDefault("print", billCode, id);
    }

    @Override
    public boolean edit(PrintTemplateDto templateDto) {
        PrintTemplatePo templatePo = new PrintTemplatePo();
        BeanUtils.copyProperties(templateDto, templatePo);
        templatePo.setUpdateTime(LocalDateTime.now());
        int res = printTemplateMapper.updateById(templatePo);
        return res!=0;
    }

    @Override
    public boolean isDefault(Long id) {
        return templateDefaultService.isDefault("print", id);
    }

    private String genBillCode(PrintTemplatePo t) {
        return t==null ? "" : t.getRgnCode() + t.getTypeId() + t.getSortId();
    }
}
