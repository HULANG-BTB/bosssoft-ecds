package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.QueryResult;
import com.bosssoft.ecds.dao.IncomeSortDao;
import com.bosssoft.ecds.entity.dto.IncomeSortDTO;
import com.bosssoft.ecds.entity.po.IncomeSortPO;

import com.bosssoft.ecds.entity.vo.incomesortvo.AddIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.FuzzyQueryIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.PageIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.UpdateIncomeSortVO;
import com.bosssoft.ecds.service.IncomeSortService;

import com.bosssoft.ecds.utils.CharacterCheckUtil;
import com.bosssoft.ecds.utils.SnowflakeIdWorker;
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
        if (page < 0) {
            page = 0;
        }
        if (size < 10) {
            size = 10;
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
    private QueryWrapper wrapIncomeSortVO(FuzzyQueryIncomeSortVO fuzzyQueryIncomeSortVO) {
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        if (!org.springframework.util.StringUtils.isEmpty(fuzzyQueryIncomeSortVO.getName())) {
            queryWrapper.like("f_name", fuzzyQueryIncomeSortVO.getName());
        }
        if (!org.springframework.util.StringUtils.isEmpty(fuzzyQueryIncomeSortVO.getCode())) {
            queryWrapper.like("f_code", fuzzyQueryIncomeSortVO.getCode());
        }
        return queryWrapper;
    }


    @Override

    public QueryResponseResult getAll() {
        List<IncomeSortDTO> incomeSortDTOS = incomeSortDao.getAll();
        List<IncomeSortDTO> incomeSortTree = buildIncomeSortDTOTree(incomeSortDTOS, 0L);
        QueryResult<IncomeSortDTO> queryResult = new QueryResult<>();
        queryResult.setList(incomeSortTree);
        queryResult.setTotal(incomeSortTree.size());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }


    @Override
    public QueryResponseResult pageQueryByName(FuzzyQueryIncomeSortVO fuzzyQueryIncomeSortVO) {
        if (fuzzyQueryIncomeSortVO == null) {
            fuzzyQueryIncomeSortVO = new FuzzyQueryIncomeSortVO();
        }
        Integer page = fuzzyQueryIncomeSortVO.getPage();
        Integer size = fuzzyQueryIncomeSortVO.getSize();
        Page<IncomeSortPO> pageTemp = new Page<>();
        Page<IncomeSortPO> pageInfo = getPage(page, size, pageTemp);
        QueryWrapper queryWrapper = wrapIncomeSortVO(fuzzyQueryIncomeSortVO);

        IPage<IncomeSortPO> iPage = incomeSortDao.selectPage(pageInfo, queryWrapper);

        QueryResult<IncomeSortPO> queryResult = new QueryResult<>();
        queryResult.setList(iPage.getRecords());
        queryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);

    }

    @Override
    public QueryResponseResult pageQueryById(PageIncomeSortVO pageIncomeSortVO) {
        Integer page = pageIncomeSortVO.getPage();
        Integer size = pageIncomeSortVO.getSize();
        Page<IncomeSortPO> pageTemp = new Page<>();
        Page<IncomeSortPO> pageInfo = getPage(page, size, pageTemp);
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        if(pageIncomeSortVO.getId()!=null){
            queryWrapper.eq("f_id", pageIncomeSortVO.getId());
        }
        IPage<IncomeSortPO> iPage = incomeSortDao.selectPage(pageInfo, queryWrapper);
        QueryResult<IncomeSortPO> queryResult = new QueryResult<>();
        queryResult.setList(iPage.getRecords());
        queryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    @Override
    public Boolean checkByName(String name) {
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_name", name);
        if (incomeSortDao.selectOne(queryWrapper) != null) {
//            throw new MyIncomeSortException(InComeResultCode.INCOME_NAME_EXISTS);
        }
        return true;
    }

    @Override
    public Boolean checkByCode(String code, Long parentId) {

        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_code", code);
        if (incomeSortDao.selectOne(queryWrapper) != null) {
//            throw new MyIncomeSortException(InComeResultCode.INCOME_CODE_EXISTS);
        }
        if (parentId.equals(0L)) {
            if (code.length() == 1) {
                return true;
            }else {
//                throw new MyIncomeSortException(InComeResultCode.INCOME_CODE_NUM_ERROR);
            }
        }
        String cTemp = incomeSortDao.getCode(parentId);
        if (!CharacterCheckUtil.characterComparison(cTemp, code)) {
//            throw new MyIncomeSortException(InComeResultCode.INCOME_CODE_FORM_ERROR);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(UpdateIncomeSortVO updateIncomeSortVO) {
        UpdateWrapper<IncomeSortPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("f_id", updateIncomeSortVO.getId());
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
        checkByCode(addIncomeSortVO.getCode(), addIncomeSortVO.getParentId());
        IncomeSortPO incomeSort = new IncomeSortPO();
        //ID
        incomeSort.setId(SnowflakeIdWorker.generateId());
        //编码
        incomeSort.setCode(addIncomeSortVO.getCode());
        //名称
        incomeSort.setName(addIncomeSortVO.getName());
        Long parentId = addIncomeSortVO.getParentId();
        Boolean leaf = true;
        Integer level = new Integer(1);
        if (parentId.equals(0L)) {
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
        incomeSort.setVersion(0);
        incomeSortDao.insert(incomeSort);
        return true;
    }

    @Override
    public QueryResponseResult getFirstIncomeSort() {
        return null;
    }


}
