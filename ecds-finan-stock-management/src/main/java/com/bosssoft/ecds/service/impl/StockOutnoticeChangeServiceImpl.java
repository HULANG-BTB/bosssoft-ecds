package com.bosssoft.ecds.service.impl;

import cn.hutool.core.convert.Convert;
import com.bosssoft.ecds.entity.dto.StockOutChangeDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeChangePo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.mapper.StockOutnoticeChangeMapper;
import com.bosssoft.ecds.service.StockOutnoticeChangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.ConverUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Service
public class StockOutnoticeChangeServiceImpl extends ServiceImpl<StockOutnoticeChangeMapper, StockOutnoticeChangePo> implements StockOutnoticeChangeService {

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
}
