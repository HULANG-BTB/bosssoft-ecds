package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.BillItemDTO;
import com.bosssoft.ecds.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.entity.dto.UneCbillItemDto;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.entity.vo.UneCbillVo;

import java.util.Date;
import java.util.List;

public interface UneCbillService {
    /**
     * 获取单张票据信息
     * @param id
     * @return
     */
    UneCbill getUneCBillById(String id);

    /**
     * 分页查询开票信息
     * @param page
     * @return
     */
    IPage<UneCbillVo> selectUnecBillPage(Page<UneCbill> page);

    /**
     * 新增开票
     * @param uneCbill
     * @param itemDtos
     * @return
     */
    int addUneCbill(UneCbill uneCbill, List<UneCbillItemDto> itemDtos);

    /**
     * 开票数量
     * @return
     */
    int billCount();

    /**
     * 根据ID和校验码获取票据信息
     * @param queryWrapper
     * @return
     */
    UneCbill getBillByIdAndCheckCode(QueryWrapper<UneCbill> queryWrapper);

    /**
     * 获取票据明细
     * @param billId
     * @return
     */
    List<UneCbillItem> getItems(String billId);

    /**
     * 根据电话和校验码查询相关票据
     * @param queryWrapper
     * @return
     */
    UneCbill getBillByTelAndCheckCode(QueryWrapper<UneCbill> queryWrapper);

    /**
     * 将uneCbill转换成获取模板的Dto
     * @param uneCbill
     * @return
     */
    NontaxBillDTO convert(UneCbill uneCbill);

    /**
     * 将uneCbill转换成获取模板的Dto
     * @param uneCbillItem
     * @return
     */
    BillItemDTO convertToItem(UneCbillItem uneCbillItem);

    /**
     * 查询需要核销的票据列表
     * @param satrt
     * @param end
     * @return
     */
    List<UneCbill> writeOff(String satrt, String end);

    /**
     * 查询审核通过票据列表
     * @return
     */
    int passBillCount();

    /**
     * 分页查询通过审核票据信息
     * @param page
     * @return
     */
    IPage<UneCbillVo> selectPassBillPage(Page<UneCbill> page);

    /**
     * 获取模板所需的DTO
     * @param uneCbill
     * @return
     */
    NontaxBillDTO getNontaxBillDto(UneCbill uneCbill, List<UneCbillItem> uneCbillItems);

    /**
     * 通过billId和billNo查询bill信息
     * @param billId
     * @param billNo
     * @return
     */
    UneCbill getUneCbillByIdAndNo(String billId, String billNo);

}
