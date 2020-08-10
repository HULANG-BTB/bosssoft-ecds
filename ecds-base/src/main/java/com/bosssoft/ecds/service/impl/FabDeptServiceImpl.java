package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.FabDeptDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.FabDeptPO;
import com.bosssoft.ecds.dao.FabDeptDao;
import com.bosssoft.ecds.entity.po.PermissionPO;
import com.bosssoft.ecds.service.FabDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FabDeptServiceImpl extends ServiceImpl<FabDeptDao, FabDeptPO> implements FabDeptService {

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
    public FabDeptDTO save(FabDeptDTO fabDeptDTO) {
        FabDeptPO fabDeptPO = new FabDeptPO();
        BeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
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
    public boolean remove(FabDeptDTO fabDeptDTO) {
        FabDeptPO fabDeptPO = new FabDeptPO();
        BeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
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
    public boolean update(FabDeptDTO fabDeptDTO) {
        FabDeptPO fabDeptPO = new FabDeptPO();
        BeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
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
    public FabDeptDTO getByDeptCode(FabDeptDTO fabDeptDTO) {
        FabDeptPO fabDeptPO = new FabDeptPO();
        BeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
        FabDeptPO fabDeptPO1 = super.getOne(new QueryWrapper<FabDeptPO>(fabDeptPO));
        return BeanUtil.copyProperties(fabDeptPO1, FabDeptDTO.class);
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
    public FabDeptDTO getByDeptName(FabDeptDTO fabDeptDTO) {
        FabDeptPO fabDeptPO = new FabDeptPO();
        BeanUtil.copyProperties(fabDeptDTO, fabDeptPO);
        FabDeptPO fabDeptPO1 = super.getOne(new QueryWrapper<FabDeptPO>(fabDeptPO));
        return BeanUtil.copyProperties(fabDeptPO1, FabDeptDTO.class);
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
    public List<FabDeptDTO> listAll() {
        List<FabDeptPO> list = super.list();
        List<FabDeptDTO> fabDeptDTOList = BeanUtil.copyListProperties(list, FabDeptDTO.class);
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
        Page<FabDeptPO> fabDeptPOPage = new Page<>();
        // 设置分页信息
        fabDeptPOPage.setCurrent(pageDTO.getPage());
        fabDeptPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<FabDeptPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(FabDeptPO.F_ID, pageDTO.getKeyword()).or().like(FabDeptPO.F_DEPT_CODE, pageDTO.getKeyword()).or().like(FabDeptPO.F_DEPT_NAME, pageDTO.getKeyword());
        queryWrapper.orderByAsc(FabDeptPO.F_CREATE_TIME);
        // 读取分页数据
        Page<FabDeptPO> fabDeptPOPage1 = super.page(fabDeptPOPage, queryWrapper);
        List<FabDeptPO> records = fabDeptPOPage1.getRecords();
        // 转换数据
        List<FabDeptDTO> userDTOList = BeanUtil.copyListProperties(records, FabDeptDTO.class);
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
    public Boolean removeBatch(List<FabDeptDTO> fabDeptDTOList) {
        List<Long> ids = new ArrayList<>();
        for (FabDeptDTO fabDeptDTO : fabDeptDTOList) {
            if (!fabDeptDTO.getId().equals(0L)) {
                ids.add(fabDeptDTO.getId());
            }
        }
        boolean removeResult = super.removeByIds(ids);
        return removeResult;
    }
}
