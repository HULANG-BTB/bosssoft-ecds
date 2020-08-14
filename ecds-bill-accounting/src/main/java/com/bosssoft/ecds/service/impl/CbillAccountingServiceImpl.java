package com.bosssoft.ecds.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.common.response.ResultCode;
import com.bosssoft.ecds.dao.CbillAccountingDao;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.entity.vo.CbillAccountingVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.CbillAccountingService;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.bosssoft.ecds.enums.CbillAccountingCode.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */
@Service
public class CbillAccountingServiceImpl extends ServiceImpl<CbillAccountingDao, CbillAccountingPO> implements CbillAccountingService {

    @Autowired
    private CbillAccountingDao cbillAccountingDao;

    @Autowired
    private VoucherService voucherService;

    /**
     * 查询入账单据列表
     *
     * @return 入账数据
     */
    @Override
    public ResponseResult listAll() {
        List<CbillAccountingDTO> accountingDTOList = MyBeanUtil.copyListProperties(super.list(), CbillAccountingDTO.class);
        List<CbillAccountingVO> accountingVOList = MyBeanUtil.copyListProperties(accountingDTOList, CbillAccountingVO.class);
        return new QueryResponseResult<>(SUCCESS,accountingVOList);
    }

    /**
     * 分页查询
     *
     * @param pageDTO
     * @return
     */
    @Override
    public QueryResponseResult<PageVO> listByPage(PageDTO<CbillAccountingPO> pageDTO) {
        return null;
    }

    /**
     * 根据校验码查询入账单据
     *
     * @return 入账数据
     */
    @Override
    public ResponseResult selectBySerialId(CbillAccountingDTO cbillAccountingDTO) {
        //检查票据校验码是否存在
        ResultCode resultCode = checkBillSerialIdExist(cbillAccountingDTO.getBillSerialId());
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        CbillAccountingDTO accountingDTO = cbillAccountingDao.selectBySerialId(cbillAccountingDTO.getBillSerialId());
        CbillAccountingVO accountingVO = MyBeanUtil.copyProperties(accountingDTO, CbillAccountingVO.class);
        return new QueryResponseResult<>(SUCCESS,accountingVO);
    }

    /**
     * 通过票据号码查询入账信息
     *
     * @return 入账数据
     */
    @Override
    public ResponseResult selectByBillId(CbillAccountingDTO cbillAccountingDTO) {
        //检查票据号码是否存在
        ResultCode resultCode = checkBillNoExist(cbillAccountingDTO.getBillNo());
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        CbillAccountingDTO accountingDTO = cbillAccountingDao.selectByBillId(cbillAccountingDTO.getBillNo());
        CbillAccountingVO accountingVO = MyBeanUtil.copyProperties(accountingDTO, CbillAccountingVO.class);
        return  new QueryResponseResult<>(SUCCESS,accountingVO);
    }

    /**
     * 通过单位代码查询入账信息
     *
     * @return 入账数据
     */
    @Override
    public ResponseResult selectByAgenIdcode(CbillAccountingDTO cbillAccountingDTO) {
        List<CbillAccountingDTO> cbillAccountingDTOList = cbillAccountingDao.selectByAgenIdcode(cbillAccountingDTO.getAgenIdcode());
        List<CbillAccountingVO> accountingVOList = MyBeanUtil.copyListProperties(cbillAccountingDTOList, CbillAccountingVO.class);
        return new QueryResponseResult<>(SUCCESS,accountingVOList);
    }

    /**
     * 缴费阶段查询应缴金额
     *
     * @return
     */
    @Override
    public ResponseResult selectAccount(CbillAccountingDTO cbillAccountingDTO){
        //检查票据校验码是否存在
        ResultCode resultCode = checkBillSerialIdExist(cbillAccountingDTO.getBillSerialId());
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        BigDecimal waitAccount = cbillAccountingDao.selectAccount(cbillAccountingDTO.getBillSerialId());
        //单独列出待缴金额方便以后进行判断
        //cbillAccountingDTO.setWaitAccount(waitAccount);
        //CbillAccountingVO cbillAccountingVO = MyBeanUtil.copyProperties(cbillAccountingDTO,CbillAccountingVO.class);
        return new QueryResponseResult<>(SUCCESS,waitAccount);
    }

    /**
     * 开票阶段插入基础信息
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult insertAccBaseInfo(AccBaseInfoDTO accBaseInfoDto) {
        //检查票据校验码是否重复
        ResultCode resultCode = checkBillSerialIdRepeat(accBaseInfoDto.getCheckCode());
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        //更新信息
        CbillAccountingPO cbillAccountingPO = new CbillAccountingPO();
        MyBeanUtil.copyProperties(accBaseInfoDto,cbillAccountingPO);
        cbillAccountingPO.setWaitAccount(accBaseInfoDto.getTotalAmt());
        cbillAccountingPO.setBillSerialId(accBaseInfoDto.getCheckCode());
        if(accBaseInfoDto.getPayerTel()==null){
            //缴款人电话数目为空，代表汇缴
            cbillAccountingPO.setType(1);
        }
        //插入信息
        boolean result = cbillAccountingDao.insert(cbillAccountingPO) == 1;
        if(result){
            return ResponseResult.SUCCESS();
        }else {
            return new ResponseResult(INSERT_FAIL);
        }
    }

    /**
     * 缴费阶段插入入账信息
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult insertAccount(AccIntoInfoDTO accIntoInfoDTO) {
        //通过数据校验码查出某条需要插入的数据id
        String serial = accIntoInfoDTO.getBillSerialId();
        //检查票据校验码唯一性
        ResultCode resultCode = checkBillSerialIdExist(serial);
        if(resultCode!=SUCCESS){
            return new ResponseResult(resultCode);
        }
        //判断金额及联系方式是否正确
        CbillAccountingDTO cbillAccountingDTO = cbillAccountingDao.selectBySerialId(serial);
        if(!cbillAccountingDTO.getPayerTel().equals(accIntoInfoDTO.getPayerTel())){
            return new ResponseResult(PAYER_TEL_ILLEGAL);
        }
        if(cbillAccountingDTO.getWaitAccount().compareTo(accIntoInfoDTO.getAccount())!=0){
            return new ResponseResult(ACCOUNT_ILLEGAL);
        }
        //赋值
        CbillAccountingPO cbillAccountingPO = new CbillAccountingPO();
        MyBeanUtil.copyProperties(accIntoInfoDTO,cbillAccountingDTO);
        //判断时间
        if(!checkDate(cbillAccountingDTO.getTime(),cbillAccountingDTO.getCreateTime())){
            return new ResponseResult(ACCOUNT_TIME_ILLEGAL);
        }
        MyBeanUtil.copyProperties(cbillAccountingDTO,cbillAccountingPO);
        cbillAccountingPO.setAccountStatus(true);
        //更新数据
        if(cbillAccountingDao.updateById(cbillAccountingPO)==1){
            return new ResponseResult(SUCCESS);
        }else {
            return new ResponseResult(UPDATE_FAIL);
        }
    }

    /**
     * 开票后插入票据信息
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult insertBillInfo(AccBillDTO accBillDto) {
        //通过数据校验码查出某条需要插入的数据
        String serial = accBillDto.getCheckCode();
        //校验入账完成状态
        ResultCode status = AccountStatus(serial);
        if(status==UNFINISHED){
            return new ResponseResult(status);
        }
        //校验票据号是否重复
        ResultCode resultCode = checkBillNoRepeat(accBillDto.getBillNo());
        if(resultCode!=SUCCESS){
            return new ResponseResult(resultCode);
        }
        //已经入账完成
        CbillAccountingDTO cbillAccountingDTO = cbillAccountingDao.selectBySerialId(serial);
        //赋值
        cbillAccountingDTO.setBillBatchId(accBillDto.getBillBatchId());
        cbillAccountingDTO.setBillNo(accBillDto.getBillNo());
        //校验时间合理性
        if(checkDate(cbillAccountingDTO.getTime(),accBillDto.getDate())){
            return new ResponseResult(ACCOUNT_TIME_ILLEGAL);
        }
        cbillAccountingDTO.setAgenTime(accBillDto.getDate());
        //生成凭证ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        cbillAccountingDTO.setAccountId(id);
        //拷贝cbillAccountingPO字段给voucherPO
        VoucherDTO voucherDTO = new VoucherDTO();
        MyBeanUtil.copyProperties(cbillAccountingDTO,voucherDTO);
        //生成凭证
        if(!voucherService.generateVoucher(voucherDTO)){
            return new ResponseResult(VOUCHER_FAIL);
        }
        CbillAccountingPO cbillAccountingPO = MyBeanUtil.copyProperties(cbillAccountingDTO, CbillAccountingPO.class);
        Boolean result = cbillAccountingDao.updateById(cbillAccountingPO)==1;

        if(result){
            return new ResponseResult(SUCCESS);
        }else {
            return new ResponseResult(UPDATE_FAIL);
        }

    }

    /**
     * 开票前查询入账完成状态
     *
     * @return
     */
    @Override
    public ResponseResult selectStatus(AccBaseInfoDTO accBaseInfoDto) {
        ResultCode status = AccountStatus(accBaseInfoDto.getCheckCode());
        return new ResponseResult(status);
    }

    /**
     * 检查时间合理性
     *
     * @return
     */
    private Boolean checkDate(Date effDate, Date expDate) {
        // 检查生效日期和失效日期的大小关系
        return effDate.compareTo(expDate) >= 0;
    }

    /**
     * 检查票据校验码的唯一性
     *
     * @return
     */
    private ResultCode checkBillSerialIdRepeat(String BillSerialId) {
        // 检查票据校验码是否已经存在
        Integer result = cbillAccountingDao.selectCheckCodeNumber(BillSerialId);
        if(result>=1){
            return BILL_SERIAL_ID_REPEAT;
        }
        return SUCCESS;
    }
    /**
     * 检查票据校验码的存在性
     *
     * @return
     */
    private ResultCode checkBillSerialIdExist(String BillSerialId) {
        Integer result = cbillAccountingDao.selectCheckCodeNumber(BillSerialId);
        if(result==0){
            return BILL_SERIAL_ID_NOT_EXIST;
        }
        return SUCCESS;
    }

    /**
     * 检查票据号的唯一性
     *
     * @return
     */
    private ResultCode checkBillNoRepeat(String BillNo) {
        // 检查票据号码是否已经存在，已经存在不能插入
        Integer result = cbillAccountingDao.selectBillNoNumberId(BillNo);
        if(result>=1){
            return BILL_NO_REPEAT;
        }
        return SUCCESS;
    }

    /**
     * 检查票据号是否存在
     *
     * @return
     */
    private ResultCode checkBillNoExist(String BillNo) {
        // 检查票据号码是否存在，不存在不能查询
        Integer result = cbillAccountingDao.selectBillNoNumberId(BillNo);
        if(result==0){
            return BILL_NO_NOT_EXIST;
        }
        return SUCCESS;
    }

    /**
     * 检查入账完成状态
     *
     * @return
     */
    private ResultCode AccountStatus(String BillSerialId) {
        //检查票据序列号是否存在
        ResultCode resultCode = checkBillSerialIdExist(BillSerialId);
        if(resultCode!=SUCCESS){
            return resultCode;
        }
        //查询状态
        boolean result = cbillAccountingDao.selectStatus(BillSerialId);
        if(result){
            return FINISHED;
        }
        return UNFINISHED;
    }
}
