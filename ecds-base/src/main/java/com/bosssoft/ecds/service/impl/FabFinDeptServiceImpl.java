package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.FabFinDeptDao;
import com.bosssoft.ecds.entity.po.FabFinDept;
import com.bosssoft.ecds.entity.vo.FabFinDeptVo;
import com.bosssoft.ecds.service.FabFinDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lin.wanning
 * @since 2020-08-05
 */
@Service
public class FabFinDeptServiceImpl extends ServiceImpl<FabFinDeptDao, FabFinDept> implements FabFinDeptService {
    @Autowired
    private FabFinDeptDao fabFinDeptMapper;

    @Override
    /**
     * @description 添加，修改归口部门信息
     * @author lin.wanning
     * @date 2020/8/5
     * @param fabFinDept
     * @return com.boss.dept.entity.vo.ResponseData
     */
    public String saveOrUpdateFabFinDept(FabFinDept fabFinDept) {
        //检测参数合法性
        boolean flag = check(fabFinDept);
        if (!flag) {

        }
        //数据库操作
        //判断是insert操作还是update操作
        Long fId = fabFinDept.getFId();
        boolean result = false;
        if (StringUtils.isEmpty(fId)) {
            //insert
            //通过id生成工具生成id
            fId = null;
            //获取当前操作用户信息，插入到经办人字段
            fabFinDept.setFId(fId);
            //当前操作用户
            String currentUser = null;
            fabFinDept.setFOperator(currentUser);
            //操作时间
            fabFinDept.setFCreateTime(LocalDateTime.now());
            result = save(fabFinDept);

        } else {
            //修改时间
            fabFinDept.setFUpdateTime(LocalDateTime.now());
            //update
            result = updateById(fabFinDept);
        }
        if (result == false) {
            //数据库操作失败返回失败信息
        }
        return null;
    }


    /**
     * @param fabFinDept
     * @return boolean
     * @description 检验参数合法性
     * @author lin.wanning
     * @date 2020/8/5
     */
    private boolean check(FabFinDept fabFinDept) {
        //区划 code
        if (StringUtils.isEmpty(fabFinDept.getFRgnCode())) {
            return false;
        }
        //部门编码
        if (StringUtils.isEmpty(fabFinDept.getFFindeptCode())) {
            return false;
        }
        //名称
        if (StringUtils.isEmpty(fabFinDept.getFFindeptName())) {
            return false;
        }
        //是否启用
        if (fabFinDept.getFIsEnable() == null) {
            return false;
        }
        return true;
    }

    @Override
    public String del(Long id) {
        if (id == null) {
            //返回错误信息
        }
        //校验改部门底下是否有单位挂靠
        boolean result = checkFabAgen(id);
        if (result) {
            //若该部门存在挂靠单位，则无法删除，返回信息
        }
        boolean flag = removeById(id);
        if (!flag) {
            //返回数据库插入错误信息
        }
        return null;
    }

    /**
     * @param id 归口部门id
     * @return boolean
     * @description 校验归口部门底下是否存在挂靠单位
     * @author lin.wanning
     * @date 2020/8/6
     */
    private boolean checkFabAgen(Long id) {
        // TODO: 2020/8/6
        return false;
    }

    /**
     * @param fabFinDeptVo 前端查询条件
     * @return com.boss.dept.entity.vo.ResponseData
     * @description 管理端，获取所有归口部门信息
     * @author lin.wanning
     * @date 2020/8/6
     */
    @Override
    public String findAll(FabFinDeptVo fabFinDeptVo) {
        //分页参数校验
        int pageNumber = fabFinDeptVo.getPageNumber() == null ? 1 : fabFinDeptVo.getPageNumber();
        int pageSize = fabFinDeptVo.getPageSize() == null ? 10 : fabFinDeptVo.getPageSize();
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        //设置分页
        Page<FabFinDept> pageInfo = new Page(pageNumber, pageSize);
        //查询参数
        Map<String, Object> map = new HashMap<>();
        map.put("f_findept_name", fabFinDeptVo.getFFindeptName());
        map.put("f_is_enable", fabFinDeptVo.getFIsEnable());
        //查询
        IPage<FabFinDept> pages = this.page(pageInfo, new QueryWrapper<FabFinDept>().allEq(map, false));
        return null;
    }

    @Override
    /**
     * @description 查询所有启用部门
     * @author lin.wanning
     * @date 2020/8/6
     * @param
     * @return com.boss.dept.entity.vo.ResponseData
     */
    public String findAllWithEnable() {
        fabFinDeptMapper.selectList(new LambdaQueryWrapper<FabFinDept>().eq(FabFinDept::getFIsEnable, true));
        return null;
    }
}
