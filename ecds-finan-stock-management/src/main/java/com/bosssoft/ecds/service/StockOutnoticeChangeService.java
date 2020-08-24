package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.dto.StockOutChangeDto;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeChangePo;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.entity.vo.OutChangePageVo;
import com.bosssoft.ecds.entity.vo.StockOutPageVo;
import com.bosssoft.ecds.entity.vo.StockOutVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
public interface StockOutnoticeChangeService extends IService<StockOutnoticeChangePo> {

    /**
     * 通过StockOutVo插入变动数据
     *
     * @param outVo outVo
     * @return 是否成功
     */
    Boolean insertByOutVo(StockOutVo outVo);

    /**
     * 根据pagevo获取出库更改记录
     *
     * @param pageVo 出库改变页vo
     * @param page 页数
     * @param limit 每页限制数
     * @return 出库请求list
     */
    List<StockOutChangeDto> queryByPageVo(OutChangePageVo pageVo, Long page, Long limit);

    /**
     * 根据pagevo获得符合条件的数量
     *
     * @param pageVo 出库页vo
     * @return 记录数量
     */
    Long getCount(OutChangePageVo pageVo);


    /**
     * 批量删除
     * @param dtos 要删除的dto的list
     * @return 是否成功
     */
    Boolean deleteByPos(List<StockOutChangeDto> dtos);

}
