package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boss.dept.entity.po.FabFinDept;
import com.boss.dept.entity.vo.FabFinDeptVo;
import com.boss.dept.entity.vo.ResponseData;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lin.wanning
 * @since 2020-08-05
 */
public interface FabFinDeptService extends IService<FabFinDept> {
    ResponseData saveOrUpdateFabFinDept(FabFinDept fabFinDept);

    ResponseData del(Long id);

    ResponseData findAll(FabFinDeptVo fabFinDeptVo);

    ResponseData findAllWithEnable();

}
