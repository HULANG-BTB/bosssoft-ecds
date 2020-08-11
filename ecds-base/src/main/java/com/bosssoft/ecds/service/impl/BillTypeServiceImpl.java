package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.common.response.ResultCode;
import com.bosssoft.ecds.dao.BillTypeDao;
import com.bosssoft.ecds.entity.dto.BillSortDTO;
import com.bosssoft.ecds.entity.dto.BillTypeDTO;
import com.bosssoft.ecds.entity.dto.BillTypeShowDTO;
import com.bosssoft.ecds.entity.dto.BillTypeTableDTO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.billtypevo.AddBillTypeVO;
import com.bosssoft.ecds.entity.vo.billtypevo.BillTypeIdVo;
import com.bosssoft.ecds.entity.vo.billtypevo.QueryTableVO;
import com.bosssoft.ecds.entity.vo.billtypevo.UpdateBillTypeVO;
import com.bosssoft.ecds.service.BillTypeService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.bosssoft.ecds.constant.BillTypeConstant.*;
import static com.bosssoft.ecds.enums.BillTypeEnum.*;

/**
 * @author :Raiz
 * @date :2020/8/5
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillTypeServiceImpl implements BillTypeService {

    private final BillTypeDao billTypeDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult add(AddBillTypeVO addBillTypeVO) {
        if (checkBillTypeRepeat(addBillTypeVO.getName(), addBillTypeVO.getCode(), null)) {
            return new ResponseResult(BILL_TYPE_NAME_EXIST);
        }
        BillTypePO billTypePO = new BillTypePO();
        BeanCopier beanCopier = BeanCopier.create(AddBillTypeVO.class, BillTypePO.class, false);
        beanCopier.copy(addBillTypeVO, billTypePO, null);

        // 赋于对象属性
        billTypePO.setOperatorId(1L);
        billTypePO.setOperator("操作员");
        billTypePO.setCheckLeaf(1 - billTypePO.getCheckSort());
        billTypePO.setLevel(1 - billTypePO.getCheckSort());

        // 叶子插入
        if (addBillTypeVO.getPid() != null) {
            ResultCode result = checkParent(billTypePO);
            if (result != SUCCESS) {
                return new ResponseResult(result);
            }
        }

        int result = billTypeDao.insert(billTypePO);
        if (result > 0) {
            return ResponseResult.SUCCESS();
        } else {
            return new ResponseResult(ADD_BILL_TYPE_FAIL);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult update(UpdateBillTypeVO updateBillTypeVO) {
        if (checkBillTypeRepeat(updateBillTypeVO.getName(), updateBillTypeVO.getCode(), updateBillTypeVO.getId())) {
            return new ResponseResult(BILL_TYPE_NAME_EXIST);
        }

        BillTypePO billTypePO = new BillTypePO();
        BeanCopier beanCopier = BeanCopier.create(UpdateBillTypeVO.class, BillTypePO.class, false);
        beanCopier.copy(updateBillTypeVO, billTypePO, null);

        if (updateBillTypeVO.getPid() != null) {
            ResultCode result = checkParent(billTypePO);
            if (result != SUCCESS) {
                return new ResponseResult(result);
            }
        }
        BillTypePO oldBillTypePO = billTypeDao.selectById(billTypePO.getId());
        billTypePO.setVersion(oldBillTypePO.getVersion());
        int result = billTypeDao.updateById(billTypePO);
        if (result > 0) {
            return ResponseResult.SUCCESS();
        } else {
            return new ResponseResult(UPDATE_BILL_TYPE_FAIL);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult delete(BillTypeIdVo billTypeIdVo) {
        Long id = billTypeIdVo.getId();
        int check = billTypeDao.selectCount(new LambdaQueryWrapper<BillTypePO>().eq(BillTypePO::getPid, id));
        if (check > 0) {
            return new ResponseResult(BILL_TYPE_CHILD_EXIST);
        }

        BillTypePO billTypePO = new BillTypePO();
        billTypePO.setId(id);
        billTypePO.setLogicDelete(LOGIC_DELETE);
        if (billTypeDao.updateById(billTypePO) > 0) {
            return new ResponseResult(SUCCESS);
        } else {
            return new ResponseResult(DELETE_BILL_TYPE_FAIL);
        }
    }

    @Override
    public ResponseResult queryAllBillSort() {
        List<BillSortDTO> billSortDTOList = billTypeDao.getBillSort();

        return new QueryResponseResult<>(SUCCESS, billSortDTOList);
    }

    @Override
    public ResponseResult queryAllBillType() {
        List<BillTypeDTO> billTypeDTOList = billTypeDao.getBillType();

        return new QueryResponseResult<>(SUCCESS, billTypeDTOList);
    }

    @Override
    public ResponseResult queryBillTypeTree() {
        List<BillTypeShowDTO> bilTypeList = billTypeDao.getAllBillType();
        List<BillTypeShowDTO> parentList = bilTypeList.stream().filter(filter -> filter.getCheckSort() == 1).collect(Collectors.toList());
        List<BillTypeShowDTO> childrenList = bilTypeList.stream().filter(filter -> filter.getCheckSort() == 0).collect(Collectors.toList());

        ListMultimap<Long, BillTypeShowDTO> myMultimap = ArrayListMultimap.create();
        for (BillTypeShowDTO billType : childrenList) {
            myMultimap.put(billType.getPid(), billType);
        }

        for (BillTypeShowDTO parentBillType : parentList) {
            parentBillType.setChildren(myMultimap.get(parentBillType.getId()));
        }

        return new QueryResponseResult<>(SUCCESS, parentList);
    }

    @Override
    public ResponseResult queryByPage(QueryTableVO queryTableVO) {
        Integer pageNum = queryTableVO.getPageNum();
        Integer pageSize = queryTableVO.getPageSize();

        PageMethod.startPage(pageNum, pageSize);
        List<BillTypeTableDTO> showList = billTypeDao.queryTable(queryTableVO);
        PageInfo<BillTypeTableDTO> pageInfo = new PageInfo<>(showList);

        return new QueryResponseResult<>(SUCCESS, pageInfo);
    }

    private boolean checkBillTypeRepeat(String name, String code, Long id) {
        LambdaQueryWrapper<BillTypePO> lambdaQueryWrapper = new LambdaQueryWrapper<BillTypePO>().and(wrapper -> wrapper.eq(BillTypePO::getName, name)
                .or().eq(BillTypePO::getCode, code)).eq(BillTypePO::getLogicDelete, NOT_LOGIC_DELETE);

        if (id != null) {
            lambdaQueryWrapper.ne(BillTypePO::getId, id);
        }
        int check = billTypeDao.selectCount(lambdaQueryWrapper);
        return check > 0;
    }

    private ResultCode checkParent(BillTypePO billTypePO) {
        BillTypePO parent = billTypeDao.selectById(billTypePO.getPid());
        // 验证父节点是否存在
        if (parent == null) {
            return BILL_TYPE_DONT_EXIST;
        }
        // 验证父节点是否为分类
        if (parent.getCheckSort().equals(BILL_TYPE)) {
            return BILL_TYPE_ILLEGAL;
        }
        //验证编码是否符合规则并赋予对象种类编码
        String code = billTypePO.getCode();
        String parentCode = parent.getCode();
        int index = code.indexOf(parentCode);
        if (index != 0) {
            return BILL_CODE_ILLEGAL;
        }
        String typeCode = code.substring(index + 2);

        billTypePO.setParentCode(parentCode);
        billTypePO.setTypeCode(typeCode);
        return SUCCESS;
    }
}
