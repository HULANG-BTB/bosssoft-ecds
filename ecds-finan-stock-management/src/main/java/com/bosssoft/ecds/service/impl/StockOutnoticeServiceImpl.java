package com.bosssoft.ecds.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.entity.vo.StockOutPageVo;
import com.bosssoft.ecds.mapper.StockOutnoticeMapper;
import com.bosssoft.ecds.service.StockOutnoticeItemService;
import com.bosssoft.ecds.service.StockOutnoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.ConverUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@Service
public class StockOutnoticeServiceImpl extends ServiceImpl<StockOutnoticeMapper, StockOutnoticePo> implements StockOutnoticeService {
    @Autowired
    private StockOutnoticeMapper outMapper;
    @Autowired
    private StockOutnoticeItemService itemService;

    /**
     * 新增一个空数据
     * 返回主键id
     *
     * @return id
     */
    @Override
    public StockOutnoticePo addNewBuss(String author) {
        StockOutnoticePo outnoticePo = new StockOutnoticePo();
        outnoticePo.setAuthor(author);
        outMapper.insert(outnoticePo);
        return outnoticePo;
    }

    /**
     * 根据审核状态获取出库请求信息
     * 0新建（在前台请求新增时默认，无用），
     * 1已保存（未审核），
     * 2已提交（待审核），
     * 3审核通过，
     * 4审核退回
     *
     * @param changeState 审核状态
     *
     * @return 出库请求list
     */
    @Override
    public List<StockOutDto> queryByChangeState(Integer changeState, Long page, Long limit) {
        Page<StockOutnoticePo> pageQuery = new Page<>(page, limit);
        QueryWrapper<StockOutnoticePo> wrapper = new QueryWrapper<>();
        wrapper.eq(StockOutnoticePo.F_CHANGE_STATE, changeState);
        Page<StockOutnoticePo> stockOutnoticePoPage = outMapper.selectPage(pageQuery, wrapper);
        List<StockOutnoticePo> stockOutnoticePos = stockOutnoticePoPage.getRecords();
        return ConverUtil.converList(StockOutDto.class, stockOutnoticePos);
//        List<StockOutnoticePo> stockOutnoticePos = this
//                .lambdaQuery()
//                .eq(StockOutnoticePo::getChangeState, changeState)
//                .list();
//        stockOutnoticePos.forEach(stockOutnoticePo -> {
////            StockOutVo stockOutVo = ;
//            List<StockOutItemDto> stockOutItemDtos = stockOutnoticeItemService.queryItemByPid();
//
//        });
    }

    /**
     * 根据pagevo获取出库请求信息
     * <p>
     * 其中，审核状态changeState:
     * 0新建（在前台请求新增时默认，无用），
     * 1已保存（未审核），
     * 2已提交（待审核），
     * 3审核通过，
     * 4审核退回
     *
     * @param pageVo 出库页vo
     * @param page   页数
     * @param limit  每页限制数
     *
     * @return 出库请求list
     */
    @Override
    public List<StockOutDto> queryByPageVo(StockOutPageVo pageVo, Long page, Long limit) {
        QueryWrapper<StockOutnoticePo> wrapper = getWrapper(pageVo);
        Page<StockOutnoticePo> pageQuery = new Page<>(page, limit);
        Page<StockOutnoticePo> stockOutnoticePoPage = outMapper.selectPage(pageQuery, wrapper);
        List<StockOutnoticePo> stockOutnoticePos = stockOutnoticePoPage.getRecords();
        return ConverUtil.converList(StockOutDto.class, stockOutnoticePos);
    }

    /**
     * 根据pagevo获得符合条件的数量
     *
     * @param pageVo 出库页vo
     *
     * @return 记录数量
     */
    @Override
    public Long getCount(StockOutPageVo pageVo) {
        QueryWrapper<StockOutnoticePo> wrapper = getWrapper(pageVo);
        return outMapper.selectCount(wrapper).longValue();
    }

    /**
     * 根据pageVo获得符合各项条件的wrapper
     *
     * @param pageVo StockOutPageVo前端需要展示list时请求的vo
     *
     * @return wrapper
     */
    public QueryWrapper<StockOutnoticePo> getWrapper(StockOutPageVo pageVo) {
        QueryWrapper<StockOutnoticePo> wrapper = new QueryWrapper<>();
        if (pageVo.getId() != null) {
            wrapper.eq(StockOutnoticePo.F_ID, pageVo.getId());
        } else {
            if (StrUtil.isNotBlank(pageVo.getAuthor())) {
                wrapper.eq(StockOutnoticePo.F_AUTHOR, pageVo.getAuthor());
            }
            if (pageVo.getChangeState() != null) {
                wrapper.eq(StockOutnoticePo.F_CHANGE_STATE, pageVo.getChangeState());
            }
            if (pageVo.getPeriod() != null && !pageVo.getPeriod().isEmpty()) {
                wrapper.between(StockOutnoticePo.F_DATE, pageVo.getPeriod().get(0), pageVo.getPeriod().get(1));
            }
        }
        return wrapper;
    }

    /**
     * 检测判断出库请求中的数据是否合规
     *
     * @param outDto      出库Dto
     *
     * @return 合规true，违规false
     */
    @Override
    public Boolean checkSave(StockOutDto outDto) {
        /**
         * 1.票据领用人信息有效
         */

        /**
         * 2.编制日期正确
         * eg：编制日期不可长于当前日期
         */

        return true;
    }

    /**
     * 更新审核状态
     *
     * @param id          出库表主键
     * @param changeState 审核状态
     *
     * @return 是否成功
     */
    @Override
    public Boolean updateChangeState(Long id, Integer changeState) {
        StockOutnoticePo outnoticePo = new StockOutnoticePo();
        outnoticePo.setId(id);
        outnoticePo.setChangeState(changeState);
        int result = outMapper.updateById(outnoticePo);
        log.info("---result:{}", result);
        return result == 1;
    }

    /**
     * 更新审核状态ByDtoList
     *
     * @param outDtos     出库表dto的list
     * @param changeState 审核状态
     *
     * @return 是否成功
     */
    @Override
    public Boolean updateChangeState(List<StockOutDto> outDtos, Integer changeState) {
        if (outDtos == null || outDtos.isEmpty()) {
            return false;
        }
        LambdaUpdateWrapper<StockOutnoticePo> wrapper = Wrappers.lambdaUpdate();
        outDtos.forEach(dto -> wrapper.eq(StockOutnoticePo::getId, dto.getId()).or());
        wrapper.set(StockOutnoticePo::getChangeState, changeState);
        this.update(wrapper);
        return true;
    }

    /**
     * 批量删除 byPoList
     *
     * @param pos 要删除的po的list
     *
     * @return 是否成功
     */
    @Override
    public Boolean deleteByPos(List<StockOutnoticePo> pos) {
        List<Long> ids = new ArrayList<>();
        pos.forEach(po -> {
            ids.add(po.getId());
        });
        return this.removeByIds(ids);
    }

}
