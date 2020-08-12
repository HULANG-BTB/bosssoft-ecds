package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.DeptDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.DeptPO;
import com.bosssoft.ecds.dao.DeptDao;
import com.bosssoft.ecds.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vihenne
 * @since 2020-08-09
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, DeptPO> implements DeptService {

    /**
     *
     *
     * @description: 新增部门。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public DeptDTO save(DeptDTO fabDeptDTO) {
        DeptPO fabDeptPO = new DeptPO();
        MyBeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
        super.save(fabDeptPO);
        return fabDeptDTO;
    }

    /**
     *
     *
     * @description: 按部门编码删除部门。
     * @param{FabDeptDTO} fabDeptDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public boolean remove(DeptDTO fabDeptDTO) {
        DeptPO fabDeptPO = new DeptPO();
        MyBeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
        return super.removeById(fabDeptPO.getId());
    }

    /**
     *
     *
     * @description: 用于修改部门信息。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public boolean update(DeptDTO fabDeptDTO) {
        DeptPO fabDeptPO = new DeptPO();
        MyBeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
        return super.updateById(fabDeptPO);
    }

    /**
     *
     *
     * @description: 根据部门编码查询部门。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public DeptDTO getByDeptCode(DeptDTO fabDeptDTO) {
        DeptPO fabDeptPO = new DeptPO();
        MyBeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
        DeptPO fabDeptPO1 = super.getOne(new QueryWrapper<DeptPO>(fabDeptPO));
        return MyBeanUtil.copyProperties(fabDeptPO1, DeptDTO.class);
    }

    /**
     *
     *
     * @description: 根据部门名查询部门。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public DeptDTO getByDeptName(DeptDTO fabDeptDTO) {
        DeptPO fabDeptPO = new DeptPO();
        MyBeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
        DeptPO fabDeptPO1 = super.getOne(new QueryWrapper<DeptPO>(fabDeptPO));
        return MyBeanUtil.copyProperties(fabDeptPO1, DeptDTO.class);
    }

    /**
     *
     *
     * @description: 用于查看部门列表。
     * @return: {List<FabDeptDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public List<DeptDTO> listAll() {
        List<DeptPO> list = super.list();
        List<DeptDTO> fabDeptDTOList = MyBeanUtil.copyListProperties(list, DeptDTO.class);
        return fabDeptDTOList;
    }

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    @Override
    public PageDTO listByPage(PageDTO pageDTO) {
        Page<DeptPO> fabDeptPOPage = new Page<>();
        // 设置分页信息
        fabDeptPOPage.setCurrent(pageDTO.getPage());
        fabDeptPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<DeptPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(DeptPO.F_ISENABLE, pageDTO.getKeyword()).or().like(DeptPO.F_DEPT_CODE, pageDTO.getKeyword()).or().like(DeptPO.F_DEPT_NAME, pageDTO.getKeyword());
        queryWrapper.orderByAsc(DeptPO.F_CREATE_TIME);
        // 读取分页数据
        Page<DeptPO> fabDeptPOPage1 = super.page(fabDeptPOPage, queryWrapper);
        List<DeptPO> records = fabDeptPOPage1.getRecords();
        // 转换数据
        List<DeptDTO> userDTOList = MyBeanUtil.copyListProperties(records, DeptDTO.class);
        pageDTO.setTotal(fabDeptPOPage1.getTotal());
        pageDTO.setItems(userDTOList);
        return pageDTO;
    }

    /**
     * 批量删除角色
     *
     * @param fabDeptDTOList
     * @return
     */
    @Override
    public Boolean removeBatch(List<DeptDTO> fabDeptDTOList) {
        List<Long> ids = new ArrayList<>();
        for (DeptDTO fabDeptDTO : fabDeptDTOList) {
            if (!fabDeptDTO.getId().equals(0L)) {
                ids.add(fabDeptDTO.getId());
            }
        }
        boolean removeResult = super.removeByIds(ids);
        return removeResult;
    }
}
