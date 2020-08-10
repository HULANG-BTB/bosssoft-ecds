package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.UabCrtDTO;
import com.bosssoft.ecds.entity.po.UabCrtPO;
import com.bosssoft.ecds.dao.UabCrtDao;
import com.bosssoft.ecds.service.UabCrtService;
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
 * @since 2020-08-10
 */
@Service
@DS("slave")
public class UabCrtServiceImpl extends ServiceImpl<UabCrtDao, UabCrtPO> implements UabCrtService {

    /**
     *
     *
     * @description: 新增领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public UabCrtDTO save(UabCrtDTO uabCrtDTO) {
        UabCrtPO uabCrtPO = new UabCrtPO();
        BeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        super.save(uabCrtPO);
        return uabCrtDTO;
    }

    /**
     *
     *
     * @description: 按领购证编码删除领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public boolean remove(UabCrtDTO uabCrtDTO) {
        UabCrtPO uabCrtPO = new UabCrtPO();
        BeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        return super.removeById(uabCrtPO.getId());
    }

    /**
     *
     *
     * @description: 用于修改领购证信息。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public boolean update(UabCrtDTO uabCrtDTO) {
        UabCrtPO uabCrtPO = new UabCrtPO();
        BeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        return super.updateById(uabCrtPO);
    }

    /**
     *
     *
     * @description: 根据准购证号查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public UabCrtDTO getByCrtCode(UabCrtDTO uabCrtDTO) {
        UabCrtPO uabCrtPO = new UabCrtPO();
        BeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        UabCrtPO uabCrtPO1 = super.getOne(new QueryWrapper<UabCrtPO>(uabCrtPO));
        return BeanUtil.copyProperties(uabCrtPO1, UabCrtDTO.class);
    }

    /**
     *
     *
     * @description: 根据单位编码查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {List<UabCrtDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public List<UabCrtDTO> getByAgenCode(UabCrtDTO uabCrtDTO) {
        UabCrtPO uabCrtPO = new UabCrtPO();
        BeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        List<UabCrtPO> uabCrtPOList = super.list(new QueryWrapper<UabCrtPO>(uabCrtPO));
        return BeanUtil.copyListProperties(uabCrtPOList, UabCrtDTO.class);
    }

    /**
     *
     *
     * @description: 用于查看领购证列表。
     * @return: {List<UabCrtDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public List<UabCrtDTO> listAll() {
        List<UabCrtPO> list = super.list();
        List<UabCrtDTO> uabCrtDTOList = BeanUtil.copyListProperties(list,UabCrtDTO.class);
        return uabCrtDTOList;
    }

    /**
     * 分页读取领购证
     *
     * @param pageDTO
     * @return
     */
    @Override
    public PageDTO listByPage(PageDTO pageDTO) {
        Page<UabCrtPO> uabCrtPOPage = new Page<>();
        // 设置分页信息
        uabCrtPOPage.setCurrent(pageDTO.getPage());
        uabCrtPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<UabCrtPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(UabCrtPO.F_ID, pageDTO.getKeyword()).or().like(UabCrtPO.F_AGEN_CODE, pageDTO.getKeyword()).or().like(UabCrtPO.F_CRT_CODE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(UabCrtPO.F_CREATE_TIME);
        // 读取分页数据
        Page<UabCrtPO> uabCrtPOPage1 = super.page(uabCrtPOPage, queryWrapper);
        List<UabCrtPO> records = uabCrtPOPage1.getRecords();
        // 转换数据
        List<UabCrtDTO> userDTOList = BeanUtil.copyListProperties(records, UabCrtDTO.class);
        pageDTO.setTotal(uabCrtPOPage1.getTotal());
        pageDTO.setItems(userDTOList);
        return pageDTO;
    }

    /**
     * 批量删除领购证
     *
     * @param uabCrtDTODTOList
     * @return
     */
    @Override
    public Boolean removeBatch(List<UabCrtDTO> uabCrtDTODTOList) {
        List<Long> ids = new ArrayList<>();
        for (UabCrtDTO uabCrtDTO : uabCrtDTODTOList) {
            if (!uabCrtDTO.getId().equals(0L)) {
                ids.add(uabCrtDTO.getId());
            }
        }
        boolean removeResult = super.removeByIds(ids);
        return removeResult;
    }
}
