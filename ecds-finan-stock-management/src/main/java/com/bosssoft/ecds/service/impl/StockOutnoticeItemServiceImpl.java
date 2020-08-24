package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.bosssoft.ecds.mapper.StockOutnoticeItemMapper;
import com.bosssoft.ecds.service.StockOutnoticeItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.ConverUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author misheep
 * @since 2020-08-13
 */
@Slf4j
@Service
public class StockOutnoticeItemServiceImpl extends ServiceImpl<StockOutnoticeItemMapper, StockOutnoticeItemPo> implements StockOutnoticeItemService {

    /**
     * 通过pid获取出库明细
     *
     * @param pid 出库表id
     *
     * @return 明细list
     */
    @Override
    public List<StockOutItemDto> queryItemByPid(Long pid) {
        List<StockOutnoticeItemPo> stockOutnoticeItemPos = this
                .lambdaQuery()
                .eq(StockOutnoticeItemPo::getPid, pid)
                .list();
        return ConverUtil.converList(StockOutItemDto.class, stockOutnoticeItemPos);
    }

    /**
     * 新增，更新或删除明细
     *
     * @param itemPos 明细的list
     *
     * @return 是否成功
     */
    @Override
    public Boolean saveChange(List<StockOutnoticeItemPo> itemPos, Long pid) {
        /**
         * 如果不做此判断，则前端传入无明细的表的时候,
         * 默认清空明细
         */
        /*
        if(itemPos == null || itemPos.isEmpty()) {
            return false;
        }
         */
        LambdaQueryWrapper<StockOutnoticeItemPo> wrapper = Wrappers.<StockOutnoticeItemPo>lambdaQuery();
        itemPos.forEach(po -> {
            wrapper.ne(StockOutnoticeItemPo::getId, po.getId());
        });
        wrapper.eq(StockOutnoticeItemPo::getPid, pid);
        log.info("delete pid:{}",pid);
        this.remove(wrapper);
        this.saveOrUpdateBatch(itemPos);
        return true;
    }

    /**
     * 检测判断出库请求中的数据是否合规
     *
     * @param outItemDtos 出库明细Dto的list
     *
     * @return 合规true，违规false
     */
    @Override
    public Boolean checkSave(List<StockOutItemDto> outItemDtos) {
        /**
         * 3.发放票据数量与票号可以对应
         */

        /**
         * 4.发放票据数量不得高于限制值（如：10000）
         */

        /**
         * 5.将要发放的票据在库中真实存在且可发放
         */

        return true;
    }
}
