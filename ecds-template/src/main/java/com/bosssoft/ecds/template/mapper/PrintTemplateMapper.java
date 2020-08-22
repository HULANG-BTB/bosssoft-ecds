package com.bosssoft.ecds.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.template.entity.po.PrintTemplatePo;
import com.bosssoft.ecds.template.entity.vo.PrintTemplateVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 打印模板表 Mapper 接口
 * </p>
 *
 * @author Lazyb0x
 * @since 2020-08-17
 */
public interface PrintTemplateMapper extends BaseMapper<PrintTemplatePo> {

    IPage<PrintTemplateVo> selectTemplateVo(Page<?> page);

    PrintTemplatePo selectFirstByBillCode(
            @Param("rgnCode") String rgnCode,
            @Param("typeId") String typeId,
            @Param("sortId") String sortId);
}
