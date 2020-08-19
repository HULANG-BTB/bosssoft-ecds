package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.ApplyDto;
import com.bosssoft.ecds.entity.po.ApplyPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.po.ItemPo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
public interface ApplyService extends IService<ApplyPo> {
    ApplyPo selectById(Long id);

    boolean insertApplyInfo(ApplyDto applyDto);

    List<ApplyPo> getApplyList();

    /**
    * @description: 通过单位ID获取票据销毁申请列表
    * @param: [agenIdCode]
    * @return: java.util.List<com.bosssoft.ecds.entity.po.ApplyPo>
    * @date: 2020/8/16
    */
    List<ApplyPo> getApplyListByAgenIdCode(String agenIdCode);

    /**
    * @description: 通过票据销毁申请ID获取票据销毁申请主表信息
    * @param: [fDestroyNo]
    * @return: com.bosssoft.ecds.entity.po.ApplyPo
    * @date: 2020/8/18
    */
    ApplyPo getApplyInfoByDestroyNo(String fDestroyNo);

    /**
    * @description: 更新票据销毁申请主表
    * @param: [applyDto]
    * @return: boolean
    * @date: 2020/8/17
    */
    int updateApplyInfo(ApplyDto applyDto);

    int deleteApplyInfoByDestroyNo(String fDestroyNo);

}
