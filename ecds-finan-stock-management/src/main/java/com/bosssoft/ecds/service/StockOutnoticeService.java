package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.StockOutItemVo;
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
public interface StockOutnoticeService extends IService<StockOutnoticePo> {

    /**
     * 新增一个空数据
     * 返回主键id
     * @return id
     */
    StockOutnoticePo addNewBuss(String author);

    /**
     * 废弃
     *
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
    List<StockOutDto> queryByPageVo(StockOutPageVo pageVo, Long page, Long limit);

    /**
     * 根据pagevo获得符合条件的数量
     *
     * @param pageVo 出库页vo
     * @return 记录数量
     */
    Long getCount(StockOutPageVo pageVo);

    /**
     * 检测判断出库请求中的数据是否合规
     *
     * @param outDto 出库Dto
     *
     * @return 合规true，违规false
     */
    Boolean checkSave(StockOutDto outDto);

    /**
     * 更新审核状态ById
     *
     * @param id 出库表主键
     * @param changeState 审核状态
     *
     * @return 是否成功
     */
    Boolean updateChangeState(Long id, Integer changeState);

    /**
     * 更新审核状态ByDtoList
     *
     * @param outDtos 出库表dto的list
     * @param changeState 审核状态
     *
     * @return 是否成功
     */
    Boolean updateChangeState(List<StockOutDto> outDtos, Integer changeState);

    /**
     * 批量删除 byPoList
     * @param pos 要删除的po的list
     * @return 是否成功
     */
    Boolean deleteByPos(List<StockOutnoticePo> pos);

}
