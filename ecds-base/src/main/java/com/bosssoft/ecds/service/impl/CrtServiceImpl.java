package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.CrtDTO;
import com.bosssoft.ecds.entity.po.CrtPO;
import com.bosssoft.ecds.dao.CrtDao;
import com.bosssoft.ecds.service.CrtService;
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
 * @since 2020-08-10
 */
@Service
@DS("slave")
public class CrtServiceImpl extends ServiceImpl<CrtDao, CrtPO> implements CrtService {

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
    public CrtDTO save(CrtDTO uabCrtDTO) {
        CrtPO uabCrtPO = new CrtPO();
        MyBeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
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
    public boolean remove(CrtDTO uabCrtDTO) {
        CrtPO uabCrtPO = new CrtPO();
        MyBeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
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
    public boolean update(CrtDTO uabCrtDTO) {
        CrtPO uabCrtPO = new CrtPO();
        MyBeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
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
    public CrtDTO getByCrtCode(CrtDTO uabCrtDTO) {
        CrtPO uabCrtPO = new CrtPO();
        MyBeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        CrtPO uabCrtPO1 = super.getOne(new QueryWrapper<CrtPO>(uabCrtPO));
        return MyBeanUtil.copyProperties(uabCrtPO1, CrtDTO.class);
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
    public List<CrtDTO> getByAgenCode(CrtDTO uabCrtDTO) {
        CrtPO uabCrtPO = new CrtPO();
        MyBeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        List<CrtPO> uabCrtPOList = super.list(new QueryWrapper<CrtPO>(uabCrtPO));
        return MyBeanUtil.copyListProperties(uabCrtPOList, CrtDTO.class);
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
    public List<CrtDTO> listAll() {
        List<CrtPO> list = super.list();
        List<CrtDTO> uabCrtDTOList = MyBeanUtil.copyListProperties(list, CrtDTO.class);
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
        Page<CrtPO> uabCrtPOPage = new Page<>();
        // 设置分页信息
        uabCrtPOPage.setCurrent(pageDTO.getPage());
        uabCrtPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<CrtPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(CrtPO.F_CRT_NAME, pageDTO.getKeyword()).or().like(CrtPO.F_AGEN_CODE, pageDTO.getKeyword()).or().like(CrtPO.F_CRT_CODE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(CrtPO.F_CREATE_TIME);
        // 读取分页数据
        Page<CrtPO> uabCrtPOPage1 = super.page(uabCrtPOPage, queryWrapper);
        List<CrtPO> records = uabCrtPOPage1.getRecords();
        // 转换数据
        List<CrtDTO> userDTOList = MyBeanUtil.copyListProperties(records, CrtDTO.class);
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
    public Boolean removeBatch(List<CrtDTO> uabCrtDTODTOList) {
        List<Long> ids = new ArrayList<>();
        for (CrtDTO uabCrtDTO : uabCrtDTODTOList) {
            if (!uabCrtDTO.getId().equals(0L)) {
                ids.add(uabCrtDTO.getId());
            }
        }
        boolean removeResult = super.removeByIds(ids);
        return removeResult;
    }
}
