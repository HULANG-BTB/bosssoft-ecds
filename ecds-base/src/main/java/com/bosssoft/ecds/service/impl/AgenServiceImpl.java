package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.AgenDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenPO;
import com.bosssoft.ecds.dao.AgenDao;
import com.bosssoft.ecds.service.AgenService;
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
public class AgenServiceImpl extends ServiceImpl<AgenDao, AgenPO> implements AgenService {

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
    public AgenDTO save(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
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
    public boolean remove(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
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
    public boolean update(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
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
    public AgenDTO getByAgenCode(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        AgenPO fabAgenPO1 = super.getOne(new QueryWrapper<AgenPO>(fabAgenPO));
        return MyBeanUtil.copyProperties(fabAgenPO1, AgenDTO.class);
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
    public AgenDTO getByAgenName(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        AgenPO fabAgenPO1 = super.getOne(new QueryWrapper<AgenPO>(fabAgenPO));
        return MyBeanUtil.copyProperties(fabAgenPO1, AgenDTO.class);
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
    public List<AgenDTO> listAll() {
        List<AgenPO> list = super.list();
        List<AgenDTO> fabAgenDTOList = MyBeanUtil.copyListProperties(list, AgenDTO.class);
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
        Page<AgenPO> fabAgenPOPage = new Page<>();
        // 设置分页信息
        fabAgenPOPage.setCurrent(pageDTO.getPage());
        fabAgenPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<AgenPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(AgenPO.F_DEPT_CODE, pageDTO.getKeyword()).or().like(AgenPO.F_AGEN_CODE, pageDTO.getKeyword()).or().like(AgenPO.F_AGEN_NAME, pageDTO.getKeyword())
                .or().like(AgenPO.F_SORT_CODE, pageDTO.getKeyword()).or().like(AgenPO.F_ISENABLE, pageDTO.getKeyword()).or().like(AgenPO.F_IND_CODE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(AgenPO.F_CREATE_TIME);
        // 读取分页数据
        Page<AgenPO> fabAgenPOPage1 = super.page(fabAgenPOPage, queryWrapper);
        List<AgenPO> records = fabAgenPOPage1.getRecords();
        // 转换数据
        List<AgenDTO> userDTOList = MyBeanUtil.copyListProperties(records, AgenDTO.class);
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
    public Boolean removeBatch(List<AgenDTO> fabAgenDTOList) {
        List<Long> ids = new ArrayList<>();
        for (AgenDTO fabAgenDTO : fabAgenDTOList) {
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
    public List<AgenDTO> getByDeptCode(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        List<AgenPO> fabAgenPOList = super.list(new QueryWrapper<AgenPO>(fabAgenPO));
        return MyBeanUtil.copyListProperties(fabAgenPOList, AgenDTO.class);
    }
}
