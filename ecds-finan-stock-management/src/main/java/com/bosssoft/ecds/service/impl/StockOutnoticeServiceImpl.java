package com.bosssoft.ecds.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
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
     * 业务单号
     * 默认0代表未更新，需要查询数据库更新
     */
    private Long BUSSNO = 0L;

    /**
     * 新增一个空数据
     * 返回主键id
     *
     * @return id
     */
    @Override
    public Long addNewBuss(String author) {
        StockOutnoticePo outnoticePo = new StockOutnoticePo();
        outnoticePo.setAuthor(author);
        outMapper.insert(outnoticePo);
        return outnoticePo.getId();
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
     *
     * 其中，审核状态changeState:
     * 0新建（在前台请求新增时默认，无用），
     * 1已保存（未审核），
     * 2已提交（待审核），
     * 3审核通过，
     * 4审核退回
     *
     * @param pageVo 出库页vo
     * @param page 页数
     * @param limit 每页限制数
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
     * 根据审核状态获得数量
     *
     * @param changeState 审核状态
     *
     * @return 记录数量
     */
    @Override
    public Long getCount(Integer changeState) {
        QueryWrapper<StockOutnoticePo> wrapper = new QueryWrapper<>();
        wrapper.eq(StockOutnoticePo.F_CHANGE_STATE, changeState);
        return outMapper.selectCount(wrapper).longValue();
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
     * @param pageVo StockOutPageVo前端需要展示list时请求的vo
     * @return wrapper
     */
    public QueryWrapper<StockOutnoticePo> getWrapper(StockOutPageVo pageVo) {
        QueryWrapper<StockOutnoticePo> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(pageVo.getAuthor())) {
            wrapper.eq(StockOutnoticePo.F_AUTHOR, pageVo.getAuthor());
        } else {
            if(pageVo.getId() != null) {
                wrapper.eq(StockOutnoticePo.F_ID, pageVo.getId());
            }
            if(pageVo.getChangeState() != null) {
                wrapper.eq(StockOutnoticePo.F_CHANGE_STATE, pageVo.getChangeState());
            }
            if(pageVo.getPeriod() != null && pageVo.getPeriod().size()==2) {
                wrapper.between(StockOutnoticePo.F_DATE, pageVo.getPeriod().get(0), pageVo.getPeriod().get(1));
            }
        }
        return wrapper;
    }

    /**
     * 检测判断出库请求中的数据是否合规
     *
     * @param outDto      出库Dto
     * @param outItemDtos 出库明细Dto的list
     *
     * @return 合规true，违规false
     */
    @Override
    public Boolean checkSave(StockOutDto outDto, List<StockOutItemDto> outItemDtos) {
        return true;
    }


    public List<StockOutDto> listPage(StockOutDto stockOutDto, Long page, Long limit) {
        Page<StockOutnoticePo> pageQuery = new Page<>(page, limit);
        QueryWrapper<StockOutnoticePo> query = new QueryWrapper<>();
        Page<StockOutnoticePo> stockOutnoticePoPage = outMapper.selectPage(pageQuery, query);
        List<StockOutnoticePo> stockOutnoticePos = stockOutnoticePoPage.getRecords();
        return ConverUtil.converList(StockOutDto.class, stockOutnoticePos);
    }

}
