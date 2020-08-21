package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.WriteOffApplyIncomePO;
import com.bosssoft.ecds.entity.po.WriteOffApplyItemPO;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-12
 */
public interface UnitWriteOffService {
    /**
     * 分页查询
     * @param queryInfoDTO 查询申请对象DTO
     * @return 分页对象
     */
    IPage<WriteOffApplyDTO> selectApplyPage(UnitWriteOffApplyQueryInfoDTO queryInfoDTO);

    /**
     * 删除核销申请
     * @param no 删除申请的业务单号
     * @return 是否成功
     */
    boolean deleteApply(String no);

    /**
     * 上报核销申请
     * @param noList 上报的申请业务单号列表
     * @return 是否成功
     */
    boolean uploadApply(List<String> noList);

    /**
     * 撤销核销申请
     * @param noList 撤销的申请业务单号列表
     * @return 是否成功
     */
    boolean rescindApply(List<String> noList);

    /**
     * 查询申请明细
     * @param queryInfoDTO 明细查询DTO
     * @return 分页对象
     */
    IPage<WriteOffApplyItemDTO> selectItemPage(UnitWriteOffItemAndIncomeQueryInfoDTO queryInfoDTO);

    /**
     * 查询收费项目
     * @param queryInfoDTO 项目查询DTO
     * @return 分页对象
     */
    IPage<WriteOffApplyIncomeDTO> selectIncomePage(UnitWriteOffItemAndIncomeQueryInfoDTO queryInfoDTO);

    /**
     * 从接口中获取数据并整合
     * @param billQueryDTO 数据查询DTO
     * @return 获取之后整合的数据
     */
    BillInfoDTO getData(BillQueryDTO billQueryDTO);

    /**
     * 添加或者更新申请
     * @param applyDTO 申请DTO
     * @return 是否成功
     */
    boolean addOrUpdateApply(ApplyDTO applyDTO);

    /**
     * 获取申请列表
     * @param fAgenIdCode 单位代码
     * @return 申请列表
     */
    List<WriteOffApplyPO> getWriteOffApplyPOByAgenIdCode(String fAgenIdCode);

    /**
     * 批量更新申请数据（退回）
     * @param no 业务单号
     */
    void updateWriteOffApply(String no);

    /**
     * 设置结果
     * @param no 业务单号
     * @param result 结果代码
     */
    void setResult(String no, int result);

    /**
     * 获取item列表
     * @param fPid 业务单号
     * @return item列表
     */
    List<WriteOffApplyItemPO> getWriteOffApplyItemList(String fPid);

    /**
     * 获取income列表
     * @param fPid 业务单号
     * @return income列表
     */
    List<WriteOffApplyIncomePO> getWriteOffApplyIncomeList(String fPid);
}
