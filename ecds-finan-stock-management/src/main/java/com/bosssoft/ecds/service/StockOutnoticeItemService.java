package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author misheep
 * @since 2020-08-13
 */
public interface StockOutnoticeItemService extends IService<StockOutnoticeItemPo> {

    /**
     * 通过pid获取出库明细
     * @param pid 出库表id
     * @return 明细list
     */
    List<StockOutItemDto> queryItemByPid(Long pid);

    /**
     * 新增，更新或删除明细
     * @param itemPos 明细的list
     * @return 是否成功
     */
    Boolean saveChange(List<StockOutnoticeItemPo> itemPos, Long pid);

    /**
     * 检测判断出库请求中的数据是否合规
     *
     * @param outItemDtos 出库明细Dto的list
     *
     * @return 合规true，违规false
     */
    Boolean checkSave(List<StockOutItemDto> outItemDtos);

}
