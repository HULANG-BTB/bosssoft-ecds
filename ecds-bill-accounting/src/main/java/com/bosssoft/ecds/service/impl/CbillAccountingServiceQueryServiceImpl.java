package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.exception.CustomException;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.common.response.ResultCode;
import com.bosssoft.ecds.dao.CbillAccountingDao;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.entity.vo.CbillAccountingVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.enums.CbillAccountingCode;
import com.bosssoft.ecds.service.CbillAccountingQueryService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static com.bosssoft.ecds.enums.CbillAccountingCode.*;

/**
 * @ClassName CbillAccountingServiceQueryServiceImpl
 * @Description TODO
 * @Auther UoweMe
 * @Date 2020/8/14 9:39
 * @Version 1.0
 */
@Service
public class CbillAccountingServiceQueryServiceImpl extends ServiceImpl<CbillAccountingDao, CbillAccountingPO> implements CbillAccountingQueryService {

    /**
     * 查询入账单据列表
     *
     * @return 入账数据
     */
    @Transactional(rollbackFor = {CustomException.class})
    @Override
    public ResponseResult listAll() {
        List<CbillAccountingDTO> accountingDTOList = MyBeanUtil.copyListProperties(super.list(), CbillAccountingDTO.class);
        List<CbillAccountingVO> accountingVOList = MyBeanUtil.copyListProperties(accountingDTOList, CbillAccountingVO.class);
        return new QueryResponseResult<>(SUCCESS,accountingVOList);
    }

    /**
     * 分页查询
     * pageDTO.getKeyword() 无数据输入时实现查询全部数据，有数据输入时进行模糊查询
     *
     * @param pageDTO
     * @return
     */
    @Transactional(rollbackFor = {CustomException.class, MethodArgumentNotValidException.class,Exception.class})
    @Override
    public QueryResponseResult<PageVO> listByPage(PageDTO<CbillAccountingDTO> pageDTO) {

        Page<CbillAccountingPO> accountingPOPage = new Page<>();
        //设置分页信息
        accountingPOPage.setCurrent(pageDTO.getPage());
        accountingPOPage.setSize(pageDTO.getLimit());
        //读取分页数据
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        //对单位代码进行批量查询
        queryWrapper.isNull(pageDTO.getKeyword())
                .or()
                .like(CbillAccountingPO.F_AGEN_IDCODE,pageDTO.getKeyword())
                .or()
                .like(CbillAccountingPO.F_BILL_BATCH_ID,pageDTO.getKeyword())
                .or()
                .like(CbillAccountingPO.F_ACCOUNT_TYPE,pageDTO.getKeyword());
        //根据时间排序
        queryWrapper.orderByAsc(CbillAccountingPO.F_CREATE_TIME);
        Page<CbillAccountingPO> poPage = super.page(accountingPOPage,queryWrapper);
        List<CbillAccountingPO> records = poPage.getRecords();
        //转换数据
        List<CbillAccountingDTO> list = MyBeanUtil.copyListProperties(records, CbillAccountingDTO::new);
        pageDTO.setTotal(poPage.getTotal());
        pageDTO.setItems(list);
        PageVO pageVO = MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(SUCCESS, pageVO);
    }

    /**
     * 根据校验码查询入账单据
     *
     * @return 入账数据
     */
    @Override
    @Transactional(rollbackFor = {CustomException.class})
    public ResponseResult selectBySerialId(CbillAccountingDTO cbillAccountingDTO) {
        //检查票据校验码是否存在
        ResultCode resultCode = checkBillSerialIdExist(cbillAccountingDTO.getBillSerialId());
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_BILL_SERIAL_ID,cbillAccountingDTO.getBillSerialId());
        //属性复制
        CbillAccountingDTO accountingDTO = MyBeanUtil.copyProperties(super.getOne(queryWrapper), CbillAccountingDTO.class);
        CbillAccountingVO accountingVO = MyBeanUtil.copyProperties(accountingDTO, CbillAccountingVO.class);
        return new QueryResponseResult<>(SUCCESS,accountingVO);
    }

    /**
     * 通过票据号码查询入账信息
     *
     * @return 入账数据
     */
    @Override
    @Transactional(rollbackFor = {CustomException.class})
    public ResponseResult selectByBillId(CbillAccountingDTO cbillAccountingDTO) {
        //检查票据号码是否存在
        ResultCode resultCode = checkBillNoExist(cbillAccountingDTO.getBillNo());
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_BILL_NO,cbillAccountingDTO.getBillNo());
        CbillAccountingDTO accountingDTO = MyBeanUtil.copyProperties(super.getOne(queryWrapper), CbillAccountingDTO.class);
        //属性复制
        CbillAccountingVO accountingVO = MyBeanUtil.copyProperties(accountingDTO, CbillAccountingVO.class);
        return  new QueryResponseResult<>(SUCCESS,accountingVO);
    }

    /**
     * 通过单位代码查询入账信息
     *
     * @return 入账数据
     */
    @Override
    @Transactional(rollbackFor = {CustomException.class})
    public ResponseResult selectByAgenIdcode(CbillAccountingDTO cbillAccountingDTO) {
        //检测单位代码是否存在
        QueryWrapper<CbillAccountingPO> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_AGEN_IDCODE,cbillAccountingDTO.getAgenIdcode());
        int result = super.count(queryWrapper);
        if(result<=0){
            return new ResponseResult(CbillAccountingCode.AGEN_IDCODE_NOT_EXIST);
        }
        //查询
        List<CbillAccountingDTO> cbillAccountingDTOList = MyBeanUtil.copyListProperties(super.list(queryWrapper), CbillAccountingDTO::new);
        List<CbillAccountingVO> accountingVOList = MyBeanUtil.copyListProperties(cbillAccountingDTOList, CbillAccountingVO.class);
        return new QueryResponseResult<>(SUCCESS,accountingVOList);
    }

    /**
     * 检查票据校验码的存在性
     *
     * @return
     */
    private ResultCode checkBillSerialIdExist(String billSerialId) {
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_serial_id").eq(CbillAccountingPO.F_BILL_SERIAL_ID,billSerialId);
        int result = super.count(queryWrapper);
        if(result==0){
            return BILL_SERIAL_ID_NOT_EXIST;
        }
        return SUCCESS;
    }

    /**
     * 检查票据号是否存在
     *
     * @return
     */
    private ResultCode checkBillNoExist(String billNo) {
        // 检查票据号码是否存在，不存在不能查询
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_no").eq(CbillAccountingPO.F_BILL_NO,billNo);
        int result = count(queryWrapper);
        if(result==0){
            return BILL_NO_NOT_EXIST;
        }
        return SUCCESS;
    }

}
