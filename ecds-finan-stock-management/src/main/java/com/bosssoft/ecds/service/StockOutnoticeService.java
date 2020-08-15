package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.StockOutItemVo;
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
public interface StockOutnoticeService extends IService<StockOutnoticePo> {

    /**
     * 新增一个空数据
     * 返回主键id
     * @return id
     */
    Long addNewBuss(String author);

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
    List<StockOutDto> queryByChangeState(Integer changeState, Long page, Long limit);

    /**
     * 根据审核状态获得数量
     *
     * @param changeState 审核状态
     * @return 记录数量
     */
    Long getCount(Integer changeState);

    /**
     * 检测判断出库请求中的数据是否合规
     *
     * @param outDto 出库Dto
     * @param outItemDtos 出库明细Dto的list
     *
     * @return 合规true，违规false
     */
    Boolean checkSave(StockOutDto outDto, List<StockOutItemDto> outItemDtos);

}
