package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.po.FabFinDept;
import com.bosssoft.ecds.entity.vo.FabFinDeptVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lin.wanning
 * @since 2020-08-05
 */
public interface FabFinDeptService extends IService<FabFinDept> {
    String saveOrUpdateFabFinDept(FabFinDept fabFinDept);

    String del(Long id);

    String findAll(FabFinDeptVo fabFinDeptVo);

    String findAllWithEnable();

}
