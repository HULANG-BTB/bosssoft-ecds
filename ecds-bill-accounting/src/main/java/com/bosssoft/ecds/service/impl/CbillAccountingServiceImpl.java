package com.bosssoft.ecds.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.CbillAccountingDao;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.entity.vo.AccountInfoVO;
import com.bosssoft.ecds.enums.CbillAccountingCode;
import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.response.ResultCode;
import com.bosssoft.ecds.service.CbillAccountingService;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
     * 缴费阶段查询应缴金额
     *
     * @return
     */
    @Transactional(rollbackFor = {CustomException.class})
    @Override
    public ResponseResult selectAccount(CbillAccountingDTO cbillAccountingDTO){
        String serial = cbillAccountingDTO.getBillSerialId();
        //检查票据校验码是否存在
        ResultCode resultCode = checkBillSerialIdExist(serial);
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        AccountInfoVO accountInfoVO = new AccountInfoVO();
        //校验入账完成状态(防止重复入账)
        ResultCode status = accountStatus(serial);
        if(status==FINISHED){
            accountInfoVO.setWaitAccount(BigDecimal.valueOf(0.00));
            return new QueryResponseResult<>(CbillAccountingCode.FINISHED,accountInfoVO);
        }
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_BILL_SERIAL_ID,cbillAccountingDTO.getBillSerialId())
                .eq(CbillAccountingPO.F_PAYER_TEL,cbillAccountingDTO.getPayerTel());

        MyBeanUtil.copyProperties(super.getOne(queryWrapper),accountInfoVO);
        //单独列出待缴金额方便以后进行判断
        return new QueryResponseResult<>(UNFINISHED,accountInfoVO);
    }

    /**
     * 开票阶段插入基础信息
     *
     * @return
     */
    @Transactional(rollbackFor = {CustomException.class, MethodArgumentNotValidException.class})
    @Override
    public ResponseResult insert(AccBaseInfoDTO accBaseInfoDto) {
        //检查票据校验码是否已经存在，若存在重复则不能插入
        String serial = accBaseInfoDto.getCheckCode();
        ResultCode resultCode = checkBillSerialIdRepeat(serial);
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        //更新信息
        CbillAccountingPO cbillAccountingPO = new CbillAccountingPO();
        MyBeanUtil.copyProperties(accBaseInfoDto,cbillAccountingPO);
        cbillAccountingPO.setWaitAccount(accBaseInfoDto.getTotalAmt());
        cbillAccountingPO.setBillSerialId(serial);
        //插入信息
        boolean result = super.save(cbillAccountingPO);
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
    @Transactional(rollbackFor = {CustomException.class, MethodArgumentNotValidException.class})
    @Override
    public ResponseResult insertAccount(AccIntoInfoDTO accIntoInfoDTO) {
        //通过数据校验码查出某条需要插入的数据id
        String serial = accIntoInfoDTO.getBillSerialId();
        //检查票据校验码存在性
        ResultCode resultCode = checkBillSerialIdExist(serial);
        if(resultCode!=SUCCESS){
            return new ResponseResult(resultCode);
        }
        //校验入账完成状态(防止重复入账)
        ResultCode status = accountStatus(serial);
        if(status==FINISHED){
            return new ResponseResult(CbillAccountingCode.FINISHED);
        }
        //判断金额及联系方式是否正确
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_BILL_SERIAL_ID,serial);
        CbillAccountingDTO cbillAccountingDTO = MyBeanUtil.copyProperties(super.getOne(queryWrapper), CbillAccountingDTO.class);
        if(!cbillAccountingDTO.getPayerTel().equals(accIntoInfoDTO.getPayerTel())){
            return new ResponseResult(CbillAccountingCode.PAYER_TEL_ILLEGAL);
        }
        if(cbillAccountingDTO.getWaitAccount().compareTo(accIntoInfoDTO.getAccount())!=0){
            return new ResponseResult(CbillAccountingCode.ACCOUNT_ILLEGAL);
        }
        //赋值
        CbillAccountingPO cbillAccountingPO = new CbillAccountingPO();
        MyBeanUtil.copyProperties(accIntoInfoDTO,cbillAccountingDTO);
        //判断时间
        if(!checkDate(cbillAccountingDTO.getTime(),cbillAccountingDTO.getCreateTime())){
            return new ResponseResult(CbillAccountingCode.ACCOUNT_TIME_ILLEGAL);
        }
        MyBeanUtil.copyProperties(cbillAccountingDTO,cbillAccountingPO);
        cbillAccountingPO.setAccountStatus(true);
        //更新数据
        if(super.updateById(cbillAccountingPO)){
            return new ResponseResult(CbillAccountingCode.SUCCESS);
        }else {
            return new ResponseResult(CbillAccountingCode.FINISHED);
        }
    }

    /**
     * 开票后插入票据信息
     *
     * @return
     */
    @Transactional(rollbackFor = {CustomException.class, MethodArgumentNotValidException.class})
    @Override
    public ResponseResult insertBillInfo(AccBillDTO accBillDto) {
        //通过数据校验码查出某条需要插入的数据
        String serial = accBillDto.getCheckCode();
        //检查票据校验码存在性
        ResultCode resultCode = checkBillSerialIdExist(serial);
        if(resultCode!=SUCCESS){
            return new ResponseResult(resultCode);
        }
        //校验入账完成状态
        ResultCode status = accountStatus(serial);
        if(status==UNFINISHED){
            return new ResponseResult(status);
        }
        //校验票据号是否重复
        ResultCode resultCode1 = checkBillNoRepeat(accBillDto.getBillNo());
        if(resultCode1!=SUCCESS){
            return new ResponseResult(resultCode1);
        }
        //已经入账完成
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_BILL_SERIAL_ID,serial);
        CbillAccountingDTO cbillAccountingDTO = MyBeanUtil.copyProperties(super.getOne(queryWrapper), CbillAccountingDTO.class);
        //赋值
        cbillAccountingDTO.setBillBatchId(accBillDto.getBillBatchId());
        cbillAccountingDTO.setBillNo(accBillDto.getBillNo());
        //校验时间合理性
        if(checkDate(cbillAccountingDTO.getTime(),accBillDto.getDate())){
            return new ResponseResult(TIME_ILLEGAL);
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
        if(super.updateById(cbillAccountingPO)){
            return new ResponseResult(SUCCESS);
        }else {
            return new ResponseResult(UPDATE_FAIL);
        }

    }

    /**
     * 删除入账信息
     *
     * @param cbillAccountingDTO
     * @return
     */
    @Transactional(rollbackFor = {CustomException.class})
    @Override
    public ResponseResult delete(CbillAccountingDTO cbillAccountingDTO) {
        //执行删除操作
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_ID,cbillAccountingDTO.getId())
                .or()
                .eq(CbillAccountingPO.F_ACCOUNT_ID,cbillAccountingDTO.getAccountId())
                .or()
                .eq(CbillAccountingPO.F_BILL_NO,cbillAccountingDTO.getBillNo())
                .or()
                .eq(CbillAccountingPO.F_BILL_SERIAL_ID,cbillAccountingDTO.getBillSerialId()
                );
        boolean remove = super.remove(queryWrapper);
        //删除失败返回操作错误
        if(!remove){
            return new ResponseResult(CbillAccountingCode.DELETE_FAIL);
        }
        //删除成功
        return new ResponseResult(CbillAccountingCode.SUCCESS);
    }

    /**
     * 批量删除入账信息
     *
     * @param cbillAccountingDTOList
     * @return
     */
    @Override
    @Transactional(rollbackFor = {CustomException.class})
    public ResponseResult batchDelete(List<CbillAccountingDTO> cbillAccountingDTOList) {
        //构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<CbillAccountingDTO> iterator = cbillAccountingDTOList.iterator(); iterator.hasNext();) {
            idList.add(iterator.next().getId());
        }
        //执行批量删除
        boolean removeByIds = super.removeByIds(idList);
        //或者使用map检测其它字段进行删除

        // 删除失败返回操作失败
        if (!removeByIds) {
            return new ResponseResult(CbillAccountingCode.DELETE_FAIL);
        }
        // 删除成功返回操作成功
        return new ResponseResult(CbillAccountingCode.SUCCESS);
    }

    /**
     * 修改入账状态(单纯为练前端功能，不必要)
     *
     * @param cbillAccountingDTO
     * @return
     */
    @Override
    public ResponseResult updateStatus(CbillAccountingDTO cbillAccountingDTO) {
        //获取id
        Long id = cbillAccountingDTO.getId();
        CbillAccountingPO cbillAccountingPO = super.getById(id);
        cbillAccountingPO.setAccountStatus(!cbillAccountingPO.getAccountStatus());
        boolean result = super.updateById(cbillAccountingPO);
        if(result) {
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
        ResultCode status = accountStatus(accBaseInfoDto.getCheckCode());
        return new ResponseResult(status);
    }

    /**
     * 检查入账完成状态
     *
     * @return
     */
    private ResultCode accountStatus(String billSerialId) {
        //检查票据序列号是否存在
        ResultCode resultCode = checkBillSerialIdExist(billSerialId);
        if(resultCode!=SUCCESS){
            return resultCode;
        }
        //查询状态
        boolean result = cbillAccountingDao.selectStatus(billSerialId);
        if(result){
            return FINISHED;
        }
        return UNFINISHED;
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
    private ResultCode checkBillSerialIdRepeat(String billSerialId) {
        // 检查票据校验码是否唯一，用于插入前检测重复
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_serial_id").eq(CbillAccountingPO.F_BILL_SERIAL_ID,billSerialId);
        int result = super.count(queryWrapper);
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
    private ResultCode checkBillSerialIdExist(String billSerialId) {
        //用于查询语句前校验
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_serial_id").eq(CbillAccountingPO.F_BILL_SERIAL_ID,billSerialId)
                .last("limit 1");
        int result = super.count(queryWrapper);
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
    private ResultCode checkBillNoRepeat(String billNo) {
        // 检查票据号码是否已经存在，已经存在不能插入
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_no").eq(CbillAccountingPO.F_BILL_NO,billNo);
        int result = count(queryWrapper);
        if(result>=1){
            return BILL_NO_REPEAT;
        }
        return SUCCESS;
    }
}
