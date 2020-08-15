package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.bosssoft.ecds.mapper.StockOutnoticeItemMapper;
import com.bosssoft.ecds.service.StockOutnoticeItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.ConverUtil;
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
}
