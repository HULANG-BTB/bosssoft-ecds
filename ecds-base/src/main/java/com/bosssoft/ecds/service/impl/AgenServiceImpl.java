package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.agendto.AgenDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.agendto.AgenInfoDTO;
import com.bosssoft.ecds.entity.dto.PagesDTO;
import com.bosssoft.ecds.entity.po.AgenPO;
import com.bosssoft.ecds.dao.AgenDao;
import com.bosssoft.ecds.entity.po.DeptPO;
import com.bosssoft.ecds.service.AgenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.service.DeptService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
@DS("master")
public class AgenServiceImpl extends ServiceImpl<AgenDao, AgenPO> implements AgenService {

    @Autowired
    private AgenDao agenDao;

    @Autowired
    private DeptService deptService;

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

        DeptPO deptPO = new DeptPO();
        deptPO.setDeptCode(fabAgenPO.getDeptCode());
        deptPO = deptService.getOne(new QueryWrapper<DeptPO>(deptPO));

        fabAgenPO.setRgnId(deptPO.getRgnId());
        String rgn = deptPO.getRgnId();
        fabAgenPO.setProvinceId(rgn.substring(0,2));
        fabAgenPO.setCityId(rgn.substring(0,4));
        fabAgenPO.setCountyId(rgn);

        fabAgenPO.setPidCode("");
        fabAgenPO.setOrgCode("");
        fabAgenPO.setTypeCode("");

        fabAgenPO.setOperator("0");
        fabAgenPO.setOperatorId(0L);

        Date data = new Date();
        fabAgenPO.setIsleaf(true);
        fabAgenPO.setIsenable(false);
        fabAgenPO.setIstickAgen(false);
        fabAgenPO.setIsunpaid(false);
        fabAgenPO.setIsalarmAgen(false);
        fabAgenPO.setCreateTime(data);
        fabAgenPO.setUpdateTime(data);
        fabAgenPO.setLogicDelete(false);
        fabAgenPO.setVersion(1);
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
     * @description: 根据ID查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public AgenDTO getById(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        AgenPO fabAgenPO1 = super.getById(fabAgenPO.getId());
        return MyBeanUtil.copyProperties(fabAgenPO1, AgenDTO.class);
    }


    /**
     *
     *
     * @description: 根据ID查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public String getAgenCount(AgenDTO fabAgenDTO) {
        AgenPO fabAgenPO = new AgenPO();
        MyBeanUtil.copyProperties(fabAgenDTO, fabAgenPO);
        QueryWrapper<AgenPO> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq(AgenPO.F_DEPT_CODE,fabAgenPO.getDeptCode());
        int count = super.list(queryWrapper).size();
        return String.format("%03d",count);
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
        QueryWrapper<AgenPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenPO.F_AGEN_NAME, fabAgenPO.getAgenName());
        fabAgenPO = super.getOne(queryWrapper);
        return MyBeanUtil.copyProperties(fabAgenPO, AgenDTO.class);
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
     * @param pagesDTO
     * @return
     */
    @Override
    public PagesDTO listByPage(PagesDTO pagesDTO) {
        Page<AgenPO> fabAgenPOPage = new Page<>();
        // 设置分页信息
        fabAgenPOPage.setCurrent(pagesDTO.getPage());
        fabAgenPOPage.setSize(pagesDTO.getLimit());
        // 读取分页数据
        QueryWrapper<AgenPO> queryWrapper = new QueryWrapper<>();
        if(pagesDTO.getKeyword().get("isenable") != null) {
            String s = (String) pagesDTO.getKeyword().get("isenable");
            if(s.equals("false")){
                queryWrapper.eq(AgenPO.F_ISENABLE, false);
            }
            if(s.equals("true")){
                queryWrapper.eq(AgenPO.F_ISENABLE, true);
            }
        }

        if(pagesDTO.getKeyword().get("deptName") != null && pagesDTO.getKeyword().get("deptName").equals("")==false){
            queryWrapper.and(wrapper -> wrapper.like(AgenPO.F_DEPT_NAME, pagesDTO.getKeyword().get("deptName")));
        }
        if(pagesDTO.getKeyword().get("agenName") != null && pagesDTO.getKeyword().get("agenName").equals("")==false){
            queryWrapper.and(wrapper -> wrapper.like(AgenPO.F_AGEN_NAME, pagesDTO.getKeyword().get("agenName")));
        }


        queryWrapper.orderByAsc(AgenPO.F_CREATE_TIME);
        // 读取分页数据
        Page<AgenPO> fabAgenPOPage1 = super.page(fabAgenPOPage, queryWrapper);
        List<AgenPO> records = fabAgenPOPage1.getRecords();
        // 转换数据
        List<AgenDTO> userDTOList = MyBeanUtil.copyListProperties(records, AgenDTO.class);
        pagesDTO.setTotal(fabAgenPOPage1.getTotal());
        pagesDTO.setItems(userDTOList);
        return pagesDTO;
    }

    /**
     * 未审核单位分页读取
     *
     * @param pagesDTO
     * @return
     */
    @Override
    public PagesDTO checkListByPage(PagesDTO pagesDTO) {
        Page<AgenPO> fabAgenPOPage = new Page<>();
        // 设置分页信息
        fabAgenPOPage.setCurrent(pagesDTO.getPage());
        fabAgenPOPage.setSize(pagesDTO.getLimit());
        // 读取分页数据
        QueryWrapper<AgenPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenPO.F_ISENABLE, false);

        if(pagesDTO.getKeyword().get("deptName") != null && pagesDTO.getKeyword().get("deptName").equals("")==false){
            queryWrapper.and(wrapper -> wrapper.like(AgenPO.F_DEPT_NAME, pagesDTO.getKeyword().get("deptName")));
        }
        if(pagesDTO.getKeyword().get("agenCode") != null && pagesDTO.getKeyword().get("agenCode").equals("")==false){
            queryWrapper.and(wrapper -> wrapper.like(AgenPO.F_AGEN_CODE, pagesDTO.getKeyword().get("agenCode")));
        }
        if(pagesDTO.getKeyword().get("agenName") != null && pagesDTO.getKeyword().get("agenName").equals("")==false){
            queryWrapper.and(wrapper -> wrapper.like(AgenPO.F_AGEN_NAME, pagesDTO.getKeyword().get("agenName")));
        }
        queryWrapper.orderByAsc(AgenPO.F_CREATE_TIME);
        // 读取分页数据
        Page<AgenPO> fabAgenPOPage1 = super.page(fabAgenPOPage, queryWrapper);
        List<AgenPO> records = fabAgenPOPage1.getRecords();
        // 转换数据
        List<AgenDTO> userDTOList = MyBeanUtil.copyListProperties(records, AgenDTO.class);
        pagesDTO.setTotal(fabAgenPOPage1.getTotal());
        pagesDTO.setItems(userDTOList);
        return pagesDTO;
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
     * 批量审核单位
     *
     * @param fabAgenDTOList
     * @return
     */
    @Override
    public Boolean checkBatch(List<AgenDTO> fabAgenDTOList) {
        for (AgenDTO fabAgenDTO : fabAgenDTOList) {
            fabAgenDTO.setIsenable(true);
        }
        List<AgenPO> agenPOList = MyBeanUtil.copyListProperties(fabAgenDTOList, AgenPO.class);
        boolean removeResult = super.updateBatchById(agenPOList);
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

    /**
     * 通过单位名称，查询单位信息,包括单位的开票点
     *
     * @param agenName 单位名称
     * @return 区划id，单位识别码，单位编码，开票点id，开票点编码，开票点名称
     */
    @Override
    public AgenInfoDTO getDetailByUnitName(String agenName) {
        return agenDao.getDetailByUnitName(agenName);
    }
}
