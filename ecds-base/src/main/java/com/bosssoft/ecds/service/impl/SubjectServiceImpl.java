package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.common.exception.CustomException;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.constant.SubjectConstant;
import com.bosssoft.ecds.dao.IncomeSortDao;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.IncomeSortPO;
import com.bosssoft.ecds.entity.po.IncomeSortSubjectPO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.bosssoft.ecds.dao.SubjectDao;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectQueryVO;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectVO;
import com.bosssoft.ecds.entity.vo.subjectvo.UpdateSubjectVO;
import com.bosssoft.ecds.enums.SubjectResultCode;
import com.bosssoft.ecds.service.IncomeSortSubjectService;
import com.bosssoft.ecds.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.CharacterCheckUtil;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 分页条件查询接口
     *
     * @param subjectQueryVO
     * @return
     */
    @Override
    public QueryResponseResult<Map<String, SubjectVO>> listPage(SubjectQueryVO subjectQueryVO) {
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
        queryWrapper.orderByAsc("f_sub_code");
//        2.条件查询
        Long id = subjectQueryVO.getId();
        String name = subjectQueryVO.getName();
        String year = subjectQueryVO.getYear();
        queryWrapper.eq("f_year", year);
        if (!StringUtils.isBlank(name)) {
            queryWrapper.like("f_sub_name", name);
        }
        if (id != 0) {
            queryWrapper.eq("f_parent_id", id);
        }
//        3.左侧菜单和右侧主体数据（其中id==0则返回某年所有数据,否则返回某年某级科目和其子科目）
        IPage<SubjectPO> subjectPOPage = baseMapper.selectPage(page, queryWrapper);
        List<SubjectPO> records = subjectPOPage.getRecords();
        List<SubjectVO> subjectVOS = MyBeanUtil.copyListProperties(records, SubjectVO::new);
        long total = subjectPOPage.getTotal();
        List<SubjectVO> leftList = getAll(year);
        Map map = new HashMap();
        map.put("leftList", leftList);
        if (id != 0) {
            List<SubjectVO> rightList = new ArrayList<>();
            SubjectVO parentVO = MyBeanUtil.copyProperties(parentPO, SubjectVO.class);
            rightList.add(parentVO);
            rightList.addAll(1, subjectVOS);
            map.put("rightList", rightList);
        } else {
            map.put("rightList", subjectVOS);
        }
        map.put("total", total);
        return new QueryResponseResult<Map<String, SubjectVO>>(CommonCode.SUCCESS, map);
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
                return new QueryResponseResult(SubjectResultCode.CODE_ERROR, null);
            }
            if (parentPO.getLeaf()) {
                return new QueryResponseResult(SubjectResultCode.LEVEL_ERROR, null);
            }
            subjectPO.setLevel(parentPO.getLevel() + 1);
        } else {
            subjectPO.setLevel(SubjectConstant.INIT_LEVEL);
        }
//        只能添加当年科目
        if (!subjectPO.getYear() .equals(getCurrentYear())) {
            return new QueryResponseResult(SubjectResultCode.INSERT_ERROR, null);
        }
        int maxLevel = getMaxLevelFromIncome();
//        添加的科目有对应收入类别
        if (subjectPO.getLevel() <= maxLevel) {
            return addExists(subjectPO);
        }
//        添加的科目无对应收入类别
        this.save(subjectPO);
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
        queryWrapper.eq("f_level", subjectPO.getLevel());
        queryWrapper.eq("f_name", subjectPO.getName());
        IncomeSortPO incomeSortPO = incomeSortDao.selectOne(queryWrapper);
        if (incomeSortPO == null) {
            return new QueryResponseResult(SubjectResultCode.INCOMESORT_NOTEXISTS, null);
        }
        boolean result=true;
        if (subjectPO.getLevel() == 1) {
            this.save(subjectPO);
            result=incomeSortSubjectService.add(incomeSortPO.getId(), subjectPO.getId());
        } else {
            QueryWrapper<IncomeSortSubjectPO> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("f_income_sort_id", incomeSortPO.getParentId());
            queryWrapper1.eq("f_subject_id", subjectPO.getParentId());
            IncomeSortSubjectPO incomeSortSubjectPO = incomeSortSubjectService.getOne(queryWrapper1);
            if (incomeSortSubjectPO == null) {
                return new QueryResponseResult(SubjectResultCode.INCOMESORT_NOTEXISTS, null);
            } else {
                this.save(subjectPO);
                result=incomeSortSubjectService.add(incomeSortPO.getId(), subjectPO.getId());
            }
        }
        if(result==true){
            return new QueryResponseResult(CommonCode.SUCCESS, null);
        }else {
            return new QueryResponseResult(CommonCode.FAIL, null);
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
        if(oldSubjectPO==null){
            return new QueryResponseResult(SubjectResultCode.SUBJECT_NOTEXISTS, null);
        }
//        只允许修改今年的数据
        if(!oldSubjectPO.getYear().equals(getCurrentYear())){
            return new QueryResponseResult(SubjectResultCode.UPDATE_DATE_ERROR, null);
        }
//        不允许修改被禁用的预算科目
        if (updateSubjectVO.getEnable() == false && updateSubjectVO.getEnable() == oldSubjectPO.getEnable()) {
            return new QueryResponseResult(SubjectResultCode.ENABLE_ERROR, null);
        }
//        不允许直接修改存在收入类别的科目的名称
        if(!updateSubjectVO.getName().equals(oldSubjectPO.getName())&&oldSubjectPO.getLevel()<=getMaxLevelFromIncome()){
            return new QueryResponseResult(SubjectResultCode.UPDATE_ERROR, null);
        }
        MyBeanUtil.copyProperties(updateSubjectVO,oldSubjectPO);
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
        if(subjectPO.getEnable()==false){
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
        List<SubjectPO> subjectPOS = selectByPid(subjectPO.getId());
        if (subjectPOS != null) {
            subjectPOS.forEach(childrenPO -> {
                deleteRec(childrenPO);
            });
        }
        if (subjectPO.getLevel() <= getMaxLevelFromIncome()) {
            incomeSortSubjectService.deleteBySid(subjectPO.getId());
        }
        baseMapper.deleteById(subjectPO.getId());
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
        queryWrapper.eq("f_parent_id", pid);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 构建预算科目树
     *
     * @param subjectVOList
     * @param pid
     * @return
     */
    private List<SubjectVO> buildSubjectVOTree(List<SubjectVO> subjectVOList, Long pid) {
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
    private List<SubjectVO> getAll(String year) {
        QueryWrapper<SubjectPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_year", year);
        List<SubjectPO> subjectPOS = subjectDao.selectList(queryWrapper);
        List<SubjectVO> subjectVOS = MyBeanUtil.copyListProperties(subjectPOS, SubjectVO::new);
        List<SubjectVO> subjectVOTree = buildSubjectVOTree(subjectVOS, SubjectConstant.INIT_PARENT_ID);
        return subjectVOTree;
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
    public static String getCurrentYear(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(new Date());
    }
    /**
     * 对外接口，更新预算科目,更新成功或无须更新都返回true
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateFromInconme(Long incomeId,String code){
        IncomeSortSubjectPO incomeSortSubjectPO = incomeSortSubjectService.selectByIncomeId(incomeId);
        SubjectPO subjectPO = baseMapper.selectById(incomeSortSubjectPO.getSubjectId());
        subjectPO.setCode(code);
        if(!subjectPO.getYear().equals(getCurrentYear())){
            return true;
        }
        return baseMapper.updateById(subjectPO)==1?true:false;
    }
    /**
     * 对外接口，删除预算科目,删除成功或无须删除都返回true
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFromImcome(Long incomeId){
        IncomeSortSubjectPO incomeSortSubjectPO = incomeSortSubjectService.selectByIncomeId(incomeId);
        if(incomeSortSubjectPO==null){
            return true;
        }
        SubjectPO subjectPO = baseMapper.selectById(incomeSortSubjectPO.getSubjectId());
        if(!subjectPO.getYear().equals(getCurrentYear())){
            return true;
        }
        return deleteRec(subjectPO);
    }
}
