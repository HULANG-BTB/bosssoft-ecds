package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.FabAgenDTO;
import com.bosssoft.ecds.entity.dto.FabDeptDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.FabAgenPO;
import com.bosssoft.ecds.dao.FabAgenDao;
import com.bosssoft.ecds.service.FabAgenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.BeanUtil;
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
public class FabAgenServiceImpl extends ServiceImpl<FabAgenDao, FabAgenPO> implements FabAgenService {

    /**
     *
     *
     * @description: 新增单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public FabAgenDTO save(FabAgenDTO fabAgenDTO) {
        FabAgenPO fabAgenPO = new FabAgenPO();
        BeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        super.save(fabAgenPO);
        return fabAgenDTO;
    }

    /**
     *
     *
     * @description: 按单位编码删除单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public boolean remove(FabAgenDTO fabAgenDTO) {
        FabAgenPO fabAgenPO = new FabAgenPO();
        BeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        return super.removeById(fabAgenPO.getId());
    }

    /**
     *
     *
     * @description: 用于修改单位信息。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public boolean update(FabAgenDTO fabAgenDTO) {
        FabAgenPO fabAgenPO = new FabAgenPO();
        BeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        return super.updateById(fabAgenPO);
    }

    /**
     *
     *
     * @description: 根据单位编码查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public FabAgenDTO getByAgenCode(FabAgenDTO fabAgenDTO) {
        FabAgenPO fabAgenPO = new FabAgenPO();
        BeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        FabAgenPO fabAgenPO1 = super.getOne(new QueryWrapper<FabAgenPO>(fabAgenPO));
        return BeanUtil.copyProperties(fabAgenPO1, FabAgenDTO.class);
    }

    /**
     *
     *
     * @description: 根据单位名查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public FabAgenDTO getByAgenName(FabAgenDTO fabAgenDTO) {
        FabAgenPO fabAgenPO = new FabAgenPO();
        BeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        FabAgenPO fabAgenPO1 = super.getOne(new QueryWrapper<FabAgenPO>(fabAgenPO));
        return BeanUtil.copyProperties(fabAgenPO1, FabAgenDTO.class);
    }

    /**
     *
     *
     * @description: 用于查看单位列表。
     * @return: {List<FabAgenDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public List<FabAgenDTO> listAll() {
        List<FabAgenPO> list = super.list();
        List<FabAgenDTO> fabAgenDTOList = BeanUtil.copyListProperties(list, FabAgenDTO.class);
        return fabAgenDTOList;
    }

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    @Override
    public PageDTO listByPage(PageDTO pageDTO) {
        Page<FabAgenPO> fabAgenPOPage = new Page<>();
        // 设置分页信息
        fabAgenPOPage.setCurrent(pageDTO.getPage());
        fabAgenPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<FabAgenPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(FabAgenPO.F_ID, pageDTO.getKeyword()).or().like(FabAgenPO.F_AGEN_CODE, pageDTO.getKeyword()).or().like(FabAgenPO.F_AGEN_NAME, pageDTO.getKeyword());
        queryWrapper.orderByAsc(FabAgenPO.F_CREATE_TIME);
        // 读取分页数据
        Page<FabAgenPO> fabAgenPOPage1 = super.page(fabAgenPOPage, queryWrapper);
        List<FabAgenPO> records = fabAgenPOPage1.getRecords();
        // 转换数据
        List<FabAgenDTO> userDTOList = BeanUtil.copyListProperties(records, FabAgenDTO.class);
        pageDTO.setTotal(fabAgenPOPage1.getTotal());
        pageDTO.setItems(userDTOList);
        return pageDTO;
    }

    /**
     * 批量删除单位
     *
     * @param fabAgenDTOList
     * @return
     */
    @Override
    public Boolean removeBatch(List<FabAgenDTO> fabAgenDTOList) {
        List<Long> ids = new ArrayList<>();
        for (FabAgenDTO fabAgenDTO : fabAgenDTOList) {
            if (!fabAgenDTO.getId().equals(0L)) {
                ids.add(fabAgenDTO.getId());
            }
        }
        boolean removeResult = super.removeByIds(ids);
        return removeResult;
    }

    /**
     *
     *
     * @description: 根据部门编码查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {List<FabAgenDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public List<FabAgenDTO> getByDeptCode(FabAgenDTO fabAgenDTO) {
        FabAgenPO fabAgenPO = new FabAgenPO();
        BeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        List<FabAgenPO> fabAgenPOList = super.list(new QueryWrapper<FabAgenPO>(fabAgenPO));
        return BeanUtil.copyListProperties(fabAgenPOList, FabAgenDTO.class);
    }
}
