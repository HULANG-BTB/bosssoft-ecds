package com.bosssoft.ecds.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.constant.StockOutConstant;
import com.bosssoft.ecds.entity.dto.StockOutChangeDto;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.bosssoft.ecds.entity.po.StockOutnoticeChangePo;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.entity.vo.OutChangePageVo;
import com.bosssoft.ecds.entity.vo.StockOutPageVo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.mapper.StockOutnoticeChangeMapper;
import com.bosssoft.ecds.service.StockOutnoticeChangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.ConverUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@Service
public class StockOutnoticeChangeServiceImpl extends ServiceImpl<StockOutnoticeChangeMapper, StockOutnoticeChangePo> implements StockOutnoticeChangeService {

    @Autowired
    private StockOutnoticeChangeMapper changeMapper;

    /**
     * 通过StockOutVo插入变动数据
     *
     * @param outVo outVo
     *
     * @return 是否成功
     */
    @Override
    public Boolean insertByOutVo(StockOutVo outVo) {
        StockOutChangeDto dto = ConverUtil.outVoToChangeDto(outVo);
        StockOutnoticeChangePo po = Convert.convert(StockOutnoticeChangePo.class, dto);
        this.save(po);
        return null;
    }

    /**
     * 根据pagevo获取出库更改记录
     *
     * @param pageVo 出库改变页vo
     * @param page   页数
     * @param limit  每页限制数
     *
     * @return 出库请求list
     */
    @Override
    public List<StockOutChangeDto> queryByPageVo(OutChangePageVo pageVo, Long page, Long limit) {
        LambdaQueryWrapper<StockOutnoticeChangePo> wrapper = getWrapperValid(pageVo);
        Page<StockOutnoticeChangePo> pageQuery = new Page<>(page, limit);
        Page<StockOutnoticeChangePo> changePoPage = changeMapper.selectPage(pageQuery, wrapper);
        List<StockOutnoticeChangePo> changePos = changePoPage.getRecords();
        return ConverUtil.converList(StockOutChangeDto.class, changePos);
    }

    /**
     * 根据pagevo获得符合条件的数量
     *
     * @param pageVo 出库页vo
     *
     * @return 记录数量
     */
    @Override
    public Long getCount(OutChangePageVo pageVo) {
        LambdaQueryWrapper<StockOutnoticeChangePo> wrapper = getWrapperValid(pageVo);
        return changeMapper.selectCount(wrapper).longValue();
    }

    /**
     * 条件获取：可查看的出库变动数据
     *
     * @return wrapper
     */
    public LambdaQueryWrapper<StockOutnoticeChangePo> getWrapperValid(OutChangePageVo pageVo) {
        LambdaQueryWrapper<StockOutnoticeChangePo> wrapper = Wrappers.<StockOutnoticeChangePo>lambdaQuery();
        wrapper
                .ne(StockOutnoticeChangePo::getChangeState, StockOutConstant.STATE_NEW)
                .ne(StockOutnoticeChangePo::getAltercode, StockOutConstant.ALTER_NO)
        ;
        if (pageVo.getPid() != null) {
            wrapper.eq(StockOutnoticeChangePo::getPid, pageVo.getPid());
        } else {
            if (StrUtil.isNotBlank(pageVo.getOperator())) {
                wrapper.eq(StockOutnoticeChangePo::getOperator, pageVo.getOperator());
            }
            if (pageVo.getAltercode() != null) {
                wrapper.eq(StockOutnoticeChangePo::getAltercode, pageVo.getAltercode());
            }
            if (pageVo.getPeriod() != null && !pageVo.getPeriod().isEmpty()) {
                wrapper.between(StockOutnoticeChangePo::getChangeDate, pageVo.getPeriod().get(0), pageVo.getPeriod().get(1));
            }
        }
        return wrapper;
    }

    /**
     * 批量删除
     * @param dtos 要删除的dto的list
     * @return 是否成功
     */
    @Override
    public Boolean deleteByPos(List<StockOutChangeDto> dtos) {
        List<Long> ids = dtos.stream().map(StockOutChangeDto::getId).collect(Collectors.toList());
        return this.removeByIds(ids);
    }
}
