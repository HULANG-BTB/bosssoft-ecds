package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.dto.UnitWriteOffApplyQueryInfoDTO;
import com.bosssoft.ecds.entity.dto.WriteOffApplyDTO;

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
     * @param applyDTOList 上传的申请DTO列表
     * @return 是否成功
     */
    boolean uploadApply(List<WriteOffApplyDTO> applyDTOList);
}
