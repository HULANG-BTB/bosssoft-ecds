package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.dto.UnitWriteOffApplyQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.UnitWriteOffItemQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyItemDTO;

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
     * 查询申请明细（已经上报无法更改的情况）
     * @param queryInfoDTO 明细查询DTO
     * @return 分页对象
     */
    IPage<WriteOffApplyItemDTO> selectItem(UnitWriteOffItemQueryInfoDTO queryInfoDTO);
}
