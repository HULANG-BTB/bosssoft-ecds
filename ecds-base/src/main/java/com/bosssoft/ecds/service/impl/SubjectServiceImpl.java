package com.bosssoft.ecds.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.common.exception.CustomException;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.constant.SubjectConstant;
import com.bosssoft.ecds.dao.IncomeSortDao;
import com.bosssoft.ecds.dao.ItemDao;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.IncomeSortPO;
import com.bosssoft.ecds.entity.po.IncomeSortSubjectPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.bosssoft.ecds.dao.SubjectDao;
import com.bosssoft.ecds.entity.vo.subjectvo.*;
import com.bosssoft.ecds.enums.SubjectResultCode;
import com.bosssoft.ecds.service.IncomeSortSubjectService;
import com.bosssoft.ecds.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.CharacterCheckUtil;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.utils.SubjectDataListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, SubjectPO> implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private IncomeSortSubjectService incomeSortSubjectService;
    @Autowired
    private IncomeSortDao incomeSortDao;
    @Autowired
    private ItemDao itemDao;

    /**
     * 分页条件查询接口
     *
     * @param subjectQueryVO
     * @return
     */
    @Override
    public QueryResponseResult listPage(SubjectQueryVO subjectQueryVO) {
        Map map = new HashMap();
//        拿到右侧数据，转为vo
        Map mapRight = listPageRight(subjectQueryVO);
        List<SubjectPO> list = (List<SubjectPO>) mapRight.get("rightList");
        List<SubjectVO> rightList = MyBeanUtil.copyListProperties(list, SubjectVO::new);
        map.put("rightList", rightList);
        map.put("total", mapRight.get("total"));
        List<SubjectVO> leftList = getAll(subjectQueryVO.getYear());
        map.put("leftList", leftList);
        return new QueryResponseResult(CommonCode.SUCCESS, map);
    }

    /**
     * 分页条件查询接口,返回右侧数据
     *
     * @param subjectQueryVO
     * @return
     */
    public Map listPageRight(SubjectQueryVO subjectQueryVO) {
//        父id不存在则抛异常
        SubjectPO parentPO = null;
        if (subjectQueryVO.getId() != 0) {
            parentPO = this.getById(subjectQueryVO.getId());
            if (parentPO == null) {
                throw new CustomException(SubjectResultCode.PARENT_ERROR);
            }
        }
//        0.分页
        Page<SubjectPO> page = new Page<>(subjectQueryVO.getPage(), subjectQueryVO.getLimit());
//        1.排序
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(SubjectPO.F_SUB_CODE);
//        2.条件查询
        Long id = subjectQueryVO.getId();
        String name = subjectQueryVO.getName();
        String year = subjectQueryVO.getYear();
        queryWrapper.eq(SubjectPO.F_YEAR, year);
        if (!StringUtils.isBlank(name)) {
            queryWrapper.like(SubjectPO.F_SUB_NAME, name);
        }
        if (id != 0) {
            queryWrapper.eq(SubjectPO.F_PARENT_ID, id);
        }
//        3.右侧主体数据（其中id=0则返回某年所有数据）
        IPage<SubjectPO> subjectPOPage = baseMapper.selectPage(page, queryWrapper);
        List<SubjectPO> records = subjectPOPage.getRecords();
        long total = subjectPOPage.getTotal();
        Map map = new HashMap();
        if (id != 0) {
//            if:普通查询和模糊查询包含父科科目
            if ((StringUtils.isBlank(name) || parentPO.getName().contains(name))) {
                if (subjectQueryVO.getPage() == 1) {
                    List<SubjectPO> rightList = new ArrayList<>();
                    rightList.add(parentPO);
                    rightList.addAll(1, records);
                    map.put("rightList", rightList);
                    map.put("total", total + 1);
                } else {
                    map.put("rightList", records);
                    map.put("total", total + 1);
                }
            } else {
                map.put("rightList", records);
                map.put("total", total);
            }
        } else {
            map.put("rightList", records);
            map.put("total", total);
        }
        return map;
    }

    /**
     * 添加预算科目
     *
     * @param subjectDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryResponseResult add(SubjectDTO subjectDTO) {
        SubjectPO subjectPO = MyBeanUtil.copyProperties(subjectDTO, SubjectPO.class);
//        校验父级科目是否存在、是否底级，编码规范,并确定level
        SubjectPO parentPO = null;
        if (subjectPO.getParentId() != SubjectConstant.INIT_PARENT_ID) {
            parentPO = baseMapper.selectById(subjectPO.getParentId());
            if (parentPO == null) {
                throw new CustomException(SubjectResultCode.PARENT_ERROR);
            }
            if (CharacterCheckUtil.characterComparison(parentPO.getCode(), subjectPO.getCode()) == false) {
                throw new CustomException(SubjectResultCode.CODE_ERROR);
            }
            if (parentPO.getLeaf()) {
                return new QueryResponseResult(SubjectResultCode.LEVEL_ERROR, null);
            }
            subjectPO.setLevel(parentPO.getLevel() + 1);
        } else {
            subjectPO.setLevel(SubjectConstant.INIT_LEVEL);
        }
//        只能添加当年科目
        if (!subjectPO.getYear().equals(getCurrentYear())) {
            throw new CustomException(SubjectResultCode.INSERT_ERROR);
        }
        int maxLevel = getMaxLevelFromIncome();
//        添加的科目有对应收入类别
        if (subjectPO.getLevel() <= maxLevel) {
            return addExists(subjectPO);
        }
//        添加的科目无对应收入类别
        if (subjectPO.getLevel() == 4) {
            subjectPO.setLeaf(true);
        }
        try{
            this.save(subjectPO);
        }catch (DuplicateKeyException e){
            throw new CustomException(SubjectResultCode.DUPLICATE_ERROR);
        }
        return new QueryResponseResult(CommonCode.SUCCESS, null);
    }

    /**
     * 预算科目存在对应的收入类别
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public QueryResponseResult addExists(SubjectPO subjectPO) {
//        查询是否有对应的收入类别
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(IncomeSortPO.F_LEVEL, subjectPO.getLevel());
        queryWrapper.eq(IncomeSortPO.F_NAME, subjectPO.getName());
        IncomeSortPO incomeSortPO = incomeSortDao.selectOne(queryWrapper);
        if (incomeSortPO == null) {
            throw new CustomException(SubjectResultCode.INCOMESORT_NOTEXISTS);
        }
//        2级科目还需要判断添加位置是否正确，即2级科目的父科目与2级收入的父科目对应
        boolean result = true;
        if (subjectPO.getLevel() == 1) {
            try{
                this.save(subjectPO);
            }catch (DuplicateKeyException e){
                throw new CustomException(SubjectResultCode.DUPLICATE_ERROR);
            }
            result = incomeSortSubjectService.add(incomeSortPO.getId(), subjectPO.getId());
        } else {
            QueryWrapper<IncomeSortSubjectPO> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq(IncomeSortSubjectPO.F_INCOME_SORT_ID, incomeSortPO.getParentId());
            queryWrapper1.eq(IncomeSortSubjectPO.F_SUBJECT_ID, subjectPO.getParentId());
            IncomeSortSubjectPO incomeSortSubjectPO = incomeSortSubjectService.getOne(queryWrapper1);
            if (incomeSortSubjectPO == null) {
                throw new CustomException(SubjectResultCode.INCOMESORT_NOTEXISTS);
            } else {
                try{
                    this.save(subjectPO);
                }catch (DuplicateKeyException e){
                    throw new CustomException(SubjectResultCode.DUPLICATE_ERROR);
                }
                result = incomeSortSubjectService.add(incomeSortPO.getId(), subjectPO.getId());
            }
        }
        if (result == true) {
            return new QueryResponseResult(CommonCode.SUCCESS, null);
        } else {
            throw new CustomException(CommonCode.FAIL);
        }
    }

    /**
     * 修改预算科目
     *
     * @param updateSubjectVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryResponseResult update(UpdateSubjectVO updateSubjectVO) {
        SubjectPO oldSubjectPO = baseMapper.selectById(updateSubjectVO.getId());
        if (oldSubjectPO == null) {
            return new QueryResponseResult(SubjectResultCode.SUBJECT_NOTEXISTS, null);
        }
//        只允许修改今年的数据
        if (!oldSubjectPO.getYear().equals(getCurrentYear())) {
            return new QueryResponseResult(SubjectResultCode.UPDATE_DATE_ERROR, null);
        }
//        不允许修改被禁用的预算科目
        if (updateSubjectVO.getEnable().equals(false) && updateSubjectVO.getEnable().equals(oldSubjectPO.getEnable())) {
            return new QueryResponseResult(SubjectResultCode.ENABLE_ERROR, null);
        }
        MyBeanUtil.copyProperties(updateSubjectVO, oldSubjectPO);
        int result = baseMapper.updateById(oldSubjectPO);
        if (result == 1) {
            return new QueryResponseResult(CommonCode.SUCCESS, null);
        } else {
            return new QueryResponseResult(CommonCode.FAIL, null);
        }

    }

    /**
     * 删除预算科目
     *
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryResponseResult delete(Long id) {
        SubjectPO subjectPO = baseMapper.selectById(id);
        if (subjectPO == null) {
            return new QueryResponseResult(SubjectResultCode.SUBJECT_NOTEXISTS, null);
        }
        if (subjectPO.getEnable() == false) {
            return new QueryResponseResult(SubjectResultCode.ENABLE_ERROR, null);
        }
        deleteRec(subjectPO);
        return new QueryResponseResult(CommonCode.SUCCESS, null);
    }

    /**
     * 递归删除预算科目
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRec(SubjectPO subjectPO) {
//        判断item表中是否有相应科目
        QueryWrapper<ItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ItemPO.F_SUBJECT, subjectPO.getCode());
        ItemPO itemPO = itemDao.selectOne(queryWrapper);
        if (itemPO != null) {
            throw new CustomException(CommonCode.FAIL);
        }
//        先递归删除子科目
        List<SubjectPO> subjectPOS = selectByPid(subjectPO.getId());
        if (subjectPOS != null) {
            subjectPOS.forEach(childrenPO -> {
                boolean result = deleteRec(childrenPO);
                if (result == false) {
                    throw new CustomException(CommonCode.FAIL);
                }
            });
        }
        if (subjectPO.getLevel() <= getMaxLevelFromIncome()) {
            incomeSortSubjectService.deleteBySid(subjectPO.getId());
        }
        return baseMapper.deleteById(subjectPO.getId()) == 1 ? true : false;

    }

    /**
     * 复制预算科目
     *
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryResponseResult copy(Long id) {
        SubjectPO subjectPO = baseMapper.selectById(id);
        if (subjectPO == null) {
            return new QueryResponseResult(SubjectResultCode.SUBJECT_NOTEXISTS, null);
        }
        if (subjectPO.getLevel() != 1) {
            return new QueryResponseResult(SubjectResultCode.COPY_ERROR, null);
        }
        copyRec(subjectPO, SubjectConstant.INIT_PARENT_ID);
        return new QueryResponseResult(CommonCode.SUCCESS, null);


    }

    /**
     * 递归复制预算科目
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean copyRec(SubjectPO subjectPO, Long pid) {
        List<SubjectPO> childrenPOS = selectByPid(subjectPO.getId());
        subjectPO.setYear(getCurrentYear());
        subjectPO.setId(null);
        subjectPO.setParentId(pid);
        subjectPO.setLeaf(false);
        SubjectDTO subjectDTO = MyBeanUtil.copyProperties(subjectPO, SubjectDTO.class);
        add(subjectDTO);
//        获取当前subjectPO的id
        SubjectPO newSubjectPO = selectByYearAndCode(getCurrentYear(), subjectPO.getCode());
        if (childrenPOS == null || childrenPOS.size() == 0 || childrenPOS.get(0) == null) {
            return true;
        }
        childrenPOS.forEach(childrenPO -> {
            copyRec(childrenPO, newSubjectPO.getId());
        });
        return true;
    }

    /**
     * 根据pid查询子预算科目
     *
     * @param pid
     * @return
     */
    public List<SubjectPO> selectByPid(Long pid) {
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SubjectPO.F_PARENT_ID, pid);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据subjectQueryVO返回要导出的数据
     *
     * @param
     * @return
     */
    @Override
    public List<SubjectExcelData> selectExcel(SubjectQueryVO subjectQueryVO) {
        Map map = listPageRight(subjectQueryVO);
        List<SubjectPO> list = (List<SubjectPO>) map.get("rightList");
        List<SubjectExcelData> excelList = MyBeanUtil.copyListProperties(list, SubjectExcelData::new);
        for (int i = 0; i <list.size() ; i++) {
            SubjectPO subjectPO = list.get(i);
            String enable = subjectPO.getEnable().equals(true)?"是":"否";
            String leaf = subjectPO.getLeaf().equals(true)?"是":"否";
            excelList.get(i).setEnable(enable);
            excelList.get(i).setLeaf(leaf);
        }
        return excelList;
    }

    /**
     * 导入数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryResponseResult upload(MultipartFile file, Long id) throws IOException {
        EasyExcel.read(file.getInputStream(), SubjectImportData.class, new SubjectDataListener(this, id)).sheet().doRead();
        return new QueryResponseResult(CommonCode.SUCCESS, null);
    }

    /**
     * 导入数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QueryResponseResult upload(List<SubjectImportData> list, Long id) {
        List<SubjectDTO> subjectDTOS = MyBeanUtil.copyListProperties(list, SubjectDTO::new);
        subjectDTOS.forEach(subjectDTO -> {
            subjectDTO.setParentId(id);
            add(subjectDTO);
        });
        return new QueryResponseResult(CommonCode.SUCCESS, null);
    }

    /**
     * 根据年份和编码查询科目对象
     */
    public SubjectPO selectByYearAndCode(String year, String code) {
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SubjectPO.F_YEAR, year);
        queryWrapper.eq(SubjectPO.F_SUB_CODE, code);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 构建预算科目树
     *
     * @param subjectVOList
     * @param pid
     * @return
     */
    public List<SubjectVO> buildSubjectVOTree(List<SubjectVO> subjectVOList, Long pid) {
        List<SubjectVO> treeList = new ArrayList<>();
        subjectVOList.forEach(SubjectVO -> {
            if (pid.equals(SubjectVO.getParentId())) {
                SubjectVO.setSubjectVOS(buildSubjectVOTree(subjectVOList, SubjectVO.getId()));
                treeList.add(SubjectVO);
            }
        });
        return treeList;
    }

    /**
     * 返回预算科目树
     *
     * @param year
     * @return
     */
    public List<SubjectVO> getAll(String year) {
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_year", year);
        List<SubjectPO> subjectPOS = subjectDao.selectList(queryWrapper);
        List<SubjectVO> subjectVOS = MyBeanUtil.copyListProperties(subjectPOS, SubjectVO::new);
        List<SubjectVO> subjectVOTree = buildSubjectVOTree(subjectVOS, SubjectConstant.INIT_PARENT_ID);
        return subjectVOTree;
    }


    /**
     * 返回前两级预算科目树
     *
     * @param year
     * @return
     */
    public List<SubjectVO> getSecondTree(String year) {
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SubjectPO.F_PARENT_ID, SubjectConstant.INIT_PARENT_ID);
//        第一级list
        List<SubjectPO> subjectPOS1 = baseMapper.selectList(queryWrapper);
        List<SubjectVO> subjectVOS1 = MyBeanUtil.copyListProperties(subjectPOS1, SubjectVO::new);
        subjectVOS1.forEach(subjectVO -> {
            QueryWrapper<SubjectPO> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq(SubjectPO.F_PARENT_ID, subjectVO.getId());
//        第二级list
            List<SubjectPO> subjectPOS2 = baseMapper.selectList(queryWrapper2);
            List<SubjectVO> subjectVOS2 = MyBeanUtil.copyListProperties(subjectPOS2, SubjectVO::new);
            subjectVO.setSubjectVOS(subjectVOS2);
        });
        return subjectVOS1;
    }


    /**
     * 查看收入类别最大层级
     */
    public int getMaxLevelFromIncome() {
        return incomeSortDao.getMaxLevelFromIncome();
    }

    /**
     * 返回当下年份
     */
    public String getCurrentYear() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 返回当前时间
     */
    public String getCurrentDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 返回导出文件名
     */
    @Override
    public String getFileName(Long id) {
        String name = "";
        if (id == 0) {
            name += "全部预算科目" + getCurrentDateTime();
            return name;
        }
        SubjectPO subjectPO = baseMapper.selectById(id);
        if (subjectPO == null) {
            name += "不存在预算科目" + getCurrentDateTime();
            return name;
        }
        return subjectPO.getName() + getCurrentDateTime();
    }
}


