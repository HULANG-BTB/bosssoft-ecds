package com.bosssoft.ecds.service.impl;

import cn.hutool.core.convert.Convert;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.mapper.StockOutnoticeMapper;
import com.bosssoft.ecds.service.StockOutnoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
@Service
public class StockOutnoticeServiceImpl extends ServiceImpl<StockOutnoticeMapper, StockOutnoticePo> implements StockOutnoticeService {
    @Autowired
    private StockOutnoticeMapper stockOutnoticeMapper;
    /**
     * 业务单号
     * 默认0代表未更新，需要查询数据库更新
     */
    private Long BUSSNO = 0L;

    /**
     * 获取最新的业务单号
     *
     * @return 新业务单号
     */
    @Override
    public Long getNewBussNo() {
        BUSSNO = Long.valueOf(stockOutnoticeMapper.selectCount(null).toString()) ;
        return null;
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
    public List<StockOutnoticePo> queryByChangeState(Integer changeState) {
        return null;
    }

}
