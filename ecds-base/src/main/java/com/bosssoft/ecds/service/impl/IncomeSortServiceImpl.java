package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.constant.IncomeSortConstant;
import com.bosssoft.ecds.constant.PageConstant;
import com.bosssoft.ecds.dao.IncomeSortDao;
import com.bosssoft.ecds.dao.IncomeSortSubjectDao;
import com.bosssoft.ecds.dao.ItemDao;
import com.bosssoft.ecds.entity.dto.IncomeSortDTO;
import com.bosssoft.ecds.entity.dto.IncomeSortShowDTO;
import com.bosssoft.ecds.entity.po.IncomeSortPO;
import com.bosssoft.ecds.entity.po.IncomeSortSubjectPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.vo.incomesortvo.AddIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.DeleteIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.FuzzyQueryIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.PageIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.UpdateIncomeSortVO;
import com.bosssoft.ecds.enums.InComeResultCode;
import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.QueryResult;
import com.bosssoft.ecds.service.IncomeSortService;
import com.bosssoft.ecds.utils.CharacterCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/5 11:30
 */
@Service
public class IncomeSortServiceImpl implements IncomeSortService {

    @Autowired
    private IncomeSortDao incomeSortDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private IncomeSortSubjectDao incomeSortSubjectDao;

    /**
     * 构建收入类别树
     *
     * @param IncomeSortDTOList
     * @param pid
     * @return
     */
    private List<IncomeSortDTO> buildIncomeSortDTOTree(List<IncomeSortDTO> IncomeSortDTOList, Long pid) {
        List<IncomeSortDTO> treeList = new ArrayList<>();
        IncomeSortDTOList.forEach(IncomeSortDTO -> {
            if (pid.equals(IncomeSortDTO.getParentId())) {
                IncomeSortDTO.setIncomeSortDTOList(buildIncomeSortDTOTree(IncomeSortDTOList, IncomeSortDTO.getId()));
                treeList.add(IncomeSortDTO);
            }
        });
        return treeList;
    }

    private <T> Page<T> getPage(Integer page, Integer size, Page<T> pageInfo) {
        if (page < PageConstant.DEFAULT_PAGE_NO) {
            page = PageConstant.DEFAULT_PAGE_NO;
        }
        if (size < PageConstant.DEFAULT_PAGE_SIZE) {
            size = PageConstant.DEFAULT_PAGE_SIZE;
        }
        pageInfo = new Page<>(page, size);
        return pageInfo;
    }

    /**
     * 将模糊查询条件进行包装
     *
     * @param fuzzyQueryIncomeSortVO 查询条件
     * @return
     */
    private QueryWrapper<IncomeSortPO> wrapIncomeSortVO(FuzzyQueryIncomeSortVO fuzzyQueryIncomeSortVO) {
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        //只能查询没有删除的信息
        queryWrapper.eq(IncomeSortConstant.F_LOGIC_DELETE, IncomeSortConstant.LOGC_DELETE_NUM);
        if (fuzzyQueryIncomeSortVO.getId() != null) {
            queryWrapper.eq(IncomeSortConstant.F_PARENT_ID, fuzzyQueryIncomeSortVO.getId())
                    .or().eq(IncomeSortConstant.F_ID, fuzzyQueryIncomeSortVO.getId());
        } else {
            if (!org.springframework.util.StringUtils.isEmpty(fuzzyQueryIncomeSortVO.getName())) {
                queryWrapper.like(IncomeSortConstant.F_NAME, fuzzyQueryIncomeSortVO.getName());
            }
            if (!org.springframework.util.StringUtils.isEmpty(fuzzyQueryIncomeSortVO.getCode())) {
                queryWrapper.like(IncomeSortConstant.F_CODE, fuzzyQueryIncomeSortVO.getCode());
            }
        }
        queryWrapper.orderByAsc(IncomeSortConstant.F_LEVEL);
        queryWrapper.orderByAsc(IncomeSortConstant.F_CODE);
        return queryWrapper;
    }


    @Override

    public QueryResponseResult getAll() {
        List<IncomeSortDTO> incomeSortDTOS = incomeSortDao.getAll();
        List<IncomeSortDTO> incomeSortTree = buildIncomeSortDTOTree(incomeSortDTOS, IncomeSortConstant.INIT_ALL_NUM);
        QueryResult<IncomeSortDTO> queryResult = new QueryResult<>();
        queryResult.setList(incomeSortTree);
        queryResult.setTotal(incomeSortTree.size());
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }


    @Override
    public QueryResponseResult pageQueryByName(FuzzyQueryIncomeSortVO fuzzyQueryIncomeSortVO) {
        if (fuzzyQueryIncomeSortVO == null) {
            fuzzyQueryIncomeSortVO = new FuzzyQueryIncomeSortVO();
        }
        Integer page = fuzzyQueryIncomeSortVO.getPageNum();
        Integer size = fuzzyQueryIncomeSortVO.getPageSize();
        Page<IncomeSortPO> pageTemp = new Page<>();
        Page<IncomeSortPO> pageInfo = getPage(page, size, pageTemp);
        QueryWrapper<IncomeSortPO> queryWrapper = wrapIncomeSortVO(fuzzyQueryIncomeSortVO);
        IPage<IncomeSortPO> iPage = incomeSortDao.selectPage(pageInfo, queryWrapper);
        QueryResult<IncomeSortPO> queryResult = new QueryResult<>();
        queryResult.setList(iPage.getRecords());
        queryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);

    }

    @Override
    public QueryResponseResult pageQueryById(PageIncomeSortVO pageIncomeSortVO) {
        Integer page = pageIncomeSortVO.getPageNum();
        Integer size = pageIncomeSortVO.getPageSize();
        Page<IncomeSortPO> pageTemp = new Page<>();
        Page<IncomeSortPO> pageInfo = getPage(page, size, pageTemp);
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        if (pageIncomeSortVO.getId() != null) {
            queryWrapper.eq(IncomeSortConstant.F_LOGIC_DELETE, IncomeSortConstant.LOGC_DELETE_NUM);
            queryWrapper.eq(IncomeSortConstant.F_PARENT_ID, pageIncomeSortVO.getId());
        }
        IPage<IncomeSortPO> iPage = incomeSortDao.selectPage(pageInfo, queryWrapper);
        QueryResult<IncomeSortPO> queryResult = new QueryResult<>();
        queryResult.setList(iPage.getRecords());
        queryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public Boolean checkByName(String name) {
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(IncomeSortConstant.F_LOGIC_DELETE, IncomeSortConstant.LOGC_DELETE_NUM);
        queryWrapper.eq(IncomeSortConstant.F_NAME, name);
        if (incomeSortDao.selectOne(queryWrapper) != null) {
            throw new CustomException(InComeResultCode.INCOME_NAME_EXISTS);
        }
        return true;
    }

    @Override
    public Boolean checkByCode(String code, Long parentId) {
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(IncomeSortConstant.F_LOGIC_DELETE, IncomeSortConstant.LOGC_DELETE_NUM);
        queryWrapper.eq(IncomeSortConstant.F_CODE, code);
        if (incomeSortDao.selectOne(queryWrapper) != null) {
            throw new CustomException(InComeResultCode.INCOME_CODE_EXISTS);
        }

        //如果是父级id为0,则编码长度只能为1位
        if (parentId.equals(IncomeSortConstant.INIT_ALL_NUM) && (code.length() != 1)) {
            throw new CustomException(InComeResultCode.INCOME_CODE_NUM_ERROR);
        }
        String cTemp = incomeSortDao.getCode(parentId);
        if (!CharacterCheckUtil.characterComparison(cTemp, code)) {
            throw new CustomException(InComeResultCode.INCOME_CODE_FORM_ERROR);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(UpdateIncomeSortVO updateIncomeSortVO) {
        UpdateWrapper<IncomeSortPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(IncomeSortConstant.F_ID, updateIncomeSortVO.getId());
        //获取原信息版本号
        IncomeSortPO temp = incomeSortDao.selectById(updateIncomeSortVO.getId());
        IncomeSortPO incomeSort = new IncomeSortPO();
        incomeSort.setVersion(temp.getVersion());
        incomeSort.setName(updateIncomeSortVO.getName());
        incomeSort.setRemark(updateIncomeSortVO.getRemark());
        incomeSort.setLeaf(updateIncomeSortVO.getLeaf());
        Date date = new Date();
        incomeSort.setUpdateTime(date);
        incomeSortDao.update(incomeSort, updateWrapper);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(AddIncomeSortVO addIncomeSortVO) {
        checkByName(addIncomeSortVO.getName());
        Long parentId = addIncomeSortVO.getParentId();
        if (parentId == null) {
            parentId = IncomeSortConstant.INIT_ALL_NUM;
        }
        checkByCode(addIncomeSortVO.getCode(), parentId);
        IncomeSortPO incomeSort = new IncomeSortPO();
        //编码
        incomeSort.setCode(addIncomeSortVO.getCode());
        //名称
        incomeSort.setName(addIncomeSortVO.getName());

        Boolean leaf = true;
        Integer level = IncomeSortConstant.LEVEL_NUM;
        if (parentId.equals(IncomeSortConstant.INIT_ALL_NUM)) {
            leaf = false;
        } else {
            level = incomeSortDao.getLevel(parentId) + 1;
        }
        //是否底级
        incomeSort.setLeaf(leaf);
        //层级
        incomeSort.setLevel(level);
        //父级ID
        incomeSort.setParentId(parentId);
        //备注
        incomeSort.setRemark(addIncomeSortVO.getRemark());
        Date date = new Date();
        //创建时间
        incomeSort.setCreateTime(date);
        //更新时间
        incomeSort.setUpdateTime(date);
        //版本号
        incomeSort.setVersion(IncomeSortConstant.VERSION_NUM);
        incomeSort.setLogicDelete(false);
        incomeSortDao.insert(incomeSort);
        return true;
    }

    @Override
    public QueryResponseResult<QueryResult<IncomeSortDTO>> getFirstIncomeSort() {
        List<IncomeSortDTO> incomeSortDTOList = incomeSortDao.getFirst();
        QueryResult<IncomeSortDTO> queryResult = new QueryResult<>();
        queryResult.setList(incomeSortDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public Boolean delete(DeleteIncomeSortVO deleteIncomeSortVO) {
        Long id = deleteIncomeSortVO.getId();
        checkDelete(id);
        incomeSortDao.deleteById(id);
        return true;
    }

    /**
     * 对外接口，删除预算科目,删除成功或无须删除都返回true
     */
    private boolean checkDelete(Long incomeId) {
        //判断收入类别是否存在
        IncomeSortDTO incomeSortDTO = incomeSortDao.getOneById(incomeId);
        if (incomeSortDTO == null) {
            throw new CustomException(InComeResultCode.INCOME_NAME_NOT_EXISTS);
        }
        //判断项目里是否存在收入类别信息
        QueryWrapper<ItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_logic_delete", IncomeSortConstant.LOGC_DELETE_NUM);
        queryWrapper.eq("f_incom_sort_code", incomeSortDTO.getCode());
        if (itemDao.selectOne(queryWrapper) != null) {
            throw new CustomException(InComeResultCode.ITEM_EXISTS);
        }
        //判断预算科目里是否存在收入类别信息
        QueryWrapper<IncomeSortSubjectPO> poQueryWrapper = new QueryWrapper<>();
        poQueryWrapper.eq("f_logic_delete", IncomeSortConstant.LOGC_DELETE_NUM);
        poQueryWrapper.eq("f_income_sort_id", incomeId);
        if (incomeSortSubjectDao.selectOne(poQueryWrapper) != null) {
            throw new CustomException(InComeResultCode.SUBJECT_EXISTS);
        }
        return true;
    }

    @Override
    public QueryResponseResult<QueryResult<IncomeSortShowDTO>> selectAll() {
        List<IncomeSortShowDTO> incomeSortDTOList = incomeSortDao.selectAll();
        QueryResult<IncomeSortShowDTO> queryResult = new QueryResult<>();
        queryResult.setList(incomeSortDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public QueryResponseResult<IncomeSortShowDTO> getBySubjectId(Long subjectId) {
        if (subjectId == null) {
            throw new CustomException(InComeResultCode.SUBJECT_ID_IS_NULL);
        }
        IncomeSortShowDTO incomeSortDTO = incomeSortDao.getBySubjectId(subjectId);
        return new QueryResponseResult<>(CommonCode.SUCCESS, incomeSortDTO);
    }


}
