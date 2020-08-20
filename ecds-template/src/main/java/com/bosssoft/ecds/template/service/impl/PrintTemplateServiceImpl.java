package com.bosssoft.ecds.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.template.entity.dto.PrintTemplateDto;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;
import com.bosssoft.ecds.template.entity.vo.PrintTemplateVo;
import com.bosssoft.ecds.template.mapper.PrintTemplateMapper;
import com.bosssoft.ecds.template.service.PrintTemplateService;
import com.bosssoft.ecds.template.util.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    PrintTemplateMapper printTemplateMapper;

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
}
