package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.QueryResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.dao.FabAgenDao;
import com.bosssoft.ecds.dao.FabFinDeptDao;
import com.bosssoft.ecds.entity.po.FabAgenPO;
import com.bosssoft.ecds.entity.po.FabFinDept;
import com.bosssoft.ecds.entity.vo.FabFinDeptVo;
import com.bosssoft.ecds.enums.FabFinDeptResultCode;
import com.bosssoft.ecds.service.FabAgenService;
import com.bosssoft.ecds.service.FabFinDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private FabAgenDao fabAgenDao;
    @Autowired
    private FabAgenService fabAgenService;

    @Override
    /**
     * @description 添加，修改归口部门信息
     * @author lin.wanning
     * @date 2020/8/5
     * @param fabFinDept
     * @return com.boss.dept.entity.vo.ResponseData
     */
    public ResponseResult saveOrUpdateFabFinDept(FabFinDeptVo fabFinDept) {
        //检测参数合法性
        boolean flag = check(fabFinDept);
        if (!flag) {
            return new ResponseResult(CommonCode.INVLIDATE);
        }
        //数据库操作
        //判断是insert操作还是update操作
        Long fId = fabFinDept.getId();
        boolean result = false;
        if (StringUtils.isEmpty(fId)) {
            //insert
            fabFinDept.setId(fId);
            result = save(fabFinDept);

        } else {
            //update
            fabFinDept.setUpdateTime(null);
            result = updateById(fabFinDept);
        }
        if (result == false) {
            //数据库操作失败返回失败信息
            return new ResponseResult(CommonCode.FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }


    /**
     * @param fabFinDept
     * @return boolean
     * @description 检验参数合法性
     * @author lin.wanning
     * @date 2020/8/5
     */
    private boolean check(FabFinDeptVo fabFinDept) {
        //区划 code
        if (fabFinDept.getRgnCodeArray().isEmpty()) {
            return false;
        } else {
            String str = StringUtils.collectionToDelimitedString(fabFinDept.getRgnCodeArray(), ",");
            fabFinDept.setRgnCode(str);
        }
        //部门编码
        if (StringUtils.isEmpty(fabFinDept.getFindeptCode())) {
            return false;
        }
        //名称
        if (StringUtils.isEmpty(fabFinDept.getFindeptName())) {
            return false;
        }
        //是否启用
        if (fabFinDept.getIsEnable() == null) {
            return false;
        }
        return true;
    }

    @Override
    public ResponseResult del(Long id) {

        if (id == null) {
            //返回错误信息
            return new ResponseResult(CommonCode.INVLIDATE);
        }
        //校验改部门底下是否有单位挂靠
        boolean result = checkHasFabAgen(id);
        if (result) {
            //若该部门存在挂靠单位，则无法删除，返回信息
            return new ResponseResult(FabFinDeptResultCode.CATEGORY_HASSON);
        }
        boolean flag = removeById(id);
        if (!flag) {
            //返回数据库插入错误信息
            return new ResponseResult(CommonCode.FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * @param id 归口部门id
     * @return boolean
     * @description 校验归口部门底下是否存在挂靠单位
     * @author lin.wanning
     * @date 2020/8/6
     */
    private boolean checkHasFabAgen(Long id) {
        Integer count = fabAgenDao.selectCount(new LambdaQueryWrapper<FabAgenPO>().eq(FabAgenPO::getFindeptId, id));
        if (count > 0) {
            return true;
        }
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
    public QueryResponseResult findAll(FabFinDeptVo fabFinDeptVo) {
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
        Page<FabFinDept> pageInfo = new Page<FabFinDept>(pageNumber, pageSize);
        //查询参数
        Map<String, Object> map = new HashMap<>();
//        map.put("f_findept_name", fabFinDeptVo.getFindeptName());
        map.put("f_is_enable", fabFinDeptVo.getIsEnable());
        //查询
        IPage<FabFinDept> pages = this.page(pageInfo, new QueryWrapper<FabFinDept>().like("f_findept_name", fabFinDeptVo.getFindeptName()).allEq(map, false));
        QueryResult<FabFinDept> queryResult = new QueryResult<FabFinDept>();
        queryResult.setList(pages.getRecords());
        queryResult.setTotal(pages.getTotal());
        QueryResponseResult<FabFinDept> result = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return result;
    }

    @Override
    /**
     * @description 查询所有启用部门
     * @author lin.wanning
     * @date 2020/8/6
     * @param
     * @return com.boss.dept.entity.vo.ResponseData
     */
    public QueryResponseResult findAllWithEnable() {
        List<FabFinDept> list = fabFinDeptMapper.selectList(new LambdaQueryWrapper<FabFinDept>().eq(FabFinDept::getIsEnable, true));
        return new QueryResponseResult(CommonCode.SUCCESS, list);
    }
}
