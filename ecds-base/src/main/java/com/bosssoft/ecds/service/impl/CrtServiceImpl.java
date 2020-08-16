package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.AgenDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.CrtDTO;
import com.bosssoft.ecds.entity.dto.PagesDTO;
import com.bosssoft.ecds.entity.po.CrtPO;
import com.bosssoft.ecds.dao.CrtDao;
import com.bosssoft.ecds.service.AgenService;
import com.bosssoft.ecds.service.CrtService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private AgenService agenService;
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
        //查询单位相关其他字段
        AgenDTO agenDTO = new AgenDTO();
        agenDTO.setAgenName(uabCrtPO.getAgenName());
        agenDTO = agenService.getByAgenName(agenDTO);
        uabCrtPO.setAddress(agenDTO.getAddr());
        uabCrtPO.setFinmgr(agenDTO.getFinMgr());
        uabCrtPO.setVersion(0);
        uabCrtPO.setAgenCode(agenDTO.getAgenCode());
        uabCrtPO.setLinkman(agenDTO.getLinkMan());
        uabCrtPO.setLinkmanTel(agenDTO.getLinkTel());
        uabCrtPO.setOperator(agenDTO.getOperator());
        uabCrtPO.setOperatorId(agenDTO.getOperatorId());
        //获取准购证ID
        uabCrtPO.setCrtCode("112233");
        //获取时间
        Date data = new Date();
        uabCrtPO.setCreateTime(data);
        uabCrtPO.setUpdateTime(data);
        uabCrtPO.setIssuedate(data);
        //保存准购证
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
     * @description: 根据id查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @Override
    public CrtDTO getById(CrtDTO uabCrtDTO) {
        CrtPO uabCrtPO = new CrtPO();
        MyBeanUtil.copyProperties(uabCrtDTO, uabCrtPO);
        CrtPO uabCrtPO1 = super.getById(uabCrtPO.getId());
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
     * @param pagesDTO
     * @return
     */
    @Override
    public PagesDTO listByPage(PagesDTO pagesDTO) {
        Page<CrtPO> uabCrtPOPage = new Page<>();
        // 设置分页信息
        uabCrtPOPage.setCurrent(pagesDTO.getPage());
        uabCrtPOPage.setSize(pagesDTO.getLimit());
        // 读取分页数据
        QueryWrapper<CrtPO> queryWrapper = new QueryWrapper<>();
        if(pagesDTO.getKeyword().get("crtName") != null && pagesDTO.getKeyword().get("crtName") != ""){
            queryWrapper.like(CrtPO.F_CRT_NAME, pagesDTO.getKeyword().get("crtName"));
        }
        if(pagesDTO.getKeyword().get("agenName") != null && pagesDTO.getKeyword().get("agenName") != ""){
            queryWrapper.and(wrapper -> wrapper.like(CrtPO.F_AGEN_NAME, pagesDTO.getKeyword().get("agenName")));
        }
        if(pagesDTO.getKeyword().get("crtCode") != null && pagesDTO.getKeyword().get("crtCode") != ""){
            queryWrapper.and(wrapper -> wrapper.like(CrtPO.F_CRT_CODE, pagesDTO.getKeyword().get("crtCode")));
        }
        queryWrapper.orderByAsc(CrtPO.F_CREATE_TIME);
        // 读取分页数据
        Page<CrtPO> uabCrtPOPage1 = super.page(uabCrtPOPage, queryWrapper);
        List<CrtPO> records = uabCrtPOPage1.getRecords();
        // 转换数据
        List<CrtDTO> userDTOList = MyBeanUtil.copyListProperties(records, CrtDTO.class);
        pagesDTO.setTotal(uabCrtPOPage1.getTotal());
        pagesDTO.setItems(userDTOList);
        return pagesDTO;
    }

    /**
     * 准购证审核分页读取领购证
     *
     * @param pagesDTO
     * @return
     */
    @Override
    public PagesDTO checkListByPage(PagesDTO pagesDTO) {
        Page<CrtPO> uabCrtPOPage = new Page<>();
        // 设置分页信息
        uabCrtPOPage.setCurrent(pagesDTO.getPage());
        uabCrtPOPage.setSize(pagesDTO.getLimit());
        // 读取分页数据
        QueryWrapper<CrtPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CrtPO.F_ISENABLE, false);
        if(pagesDTO.getKeyword().get("crtName") != null && pagesDTO.getKeyword().get("crtName") != ""){
            queryWrapper.like(CrtPO.F_CRT_NAME, pagesDTO.getKeyword().get("crtName"));
        }
        if(pagesDTO.getKeyword().get("agenName") != null && pagesDTO.getKeyword().get("agenName") != ""){
            queryWrapper.and(wrapper -> wrapper.like(CrtPO.F_AGEN_NAME, pagesDTO.getKeyword().get("agenName")));
        }
        if(pagesDTO.getKeyword().get("crtCode") != null && pagesDTO.getKeyword().get("crtCode") != ""){
            queryWrapper.and(wrapper -> wrapper.like(CrtPO.F_CRT_CODE, pagesDTO.getKeyword().get("crtCode")));
        }
        queryWrapper.orderByAsc(CrtPO.F_CREATE_TIME);
        // 读取分页数据
        Page<CrtPO> uabCrtPOPage1 = super.page(uabCrtPOPage, queryWrapper);
        List<CrtPO> records = uabCrtPOPage1.getRecords();
        // 转换数据
        List<CrtDTO> userDTOList = MyBeanUtil.copyListProperties(records, CrtDTO.class);
        pagesDTO.setTotal(uabCrtPOPage1.getTotal());
        pagesDTO.setItems(userDTOList);
        return pagesDTO;
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

    /**
     * 批量审核领购证
     *
     * @param uabCrtDTOList
     * @return
     */
    @Override
    public Boolean checkBatch(List<CrtDTO> uabCrtDTOList) {
        for (CrtDTO uabCrtDTO : uabCrtDTOList) {
            uabCrtDTO.setIsenable(true);
        }
        List<CrtPO> crtPOList = MyBeanUtil.copyListProperties(uabCrtDTOList,CrtPO.class);
        boolean removeResult = super.updateBatchById(crtPOList);
        return removeResult;
    }
}
