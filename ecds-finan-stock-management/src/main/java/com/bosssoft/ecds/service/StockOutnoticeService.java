package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
public interface StockOutnoticeService extends IService<StockOutnoticePo> {

    /**
     * 获取最新的业务单号
     *
     * @return 新业务单号
     */
    Long getNewBussNo();

    /**
     * 根据审核状态获取出库请求信息
     * 0新建（在前台请求新增时默认，无用），
     * 1已保存（未审核），
     * 2已提交（待审核），
     * 3审核通过，
     * 4审核退回
     *
     * @param changeState 审核状态
     * @return 出库请求list
     */
    List<StockOutnoticePo> queryByChangeState(Integer changeState);

}
