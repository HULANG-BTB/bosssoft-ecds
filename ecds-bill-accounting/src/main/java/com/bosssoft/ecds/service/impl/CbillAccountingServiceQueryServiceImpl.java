package com.bosssoft.ecds.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.CbillAccountingDao;
import com.bosssoft.ecds.entity.dto.CbillAccountingDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.entity.vo.CbillAccountingVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.enums.CbillAccountingCode;
import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.response.ResultCode;
import com.bosssoft.ecds.service.CbillAccountingQueryService;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VoucherService voucherService;

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
        //若选择了accountType则添加accountType判断条件
        if(pageDTO.getAccountType()!=null){
            queryWrapper.eq(CbillAccountingPO.F_ACCOUNT_TYPE,pageDTO.getAccountType());
        }
        //keyword为空代表查询全部
        if(pageDTO.getKeyword().equals("")||pageDTO.getKeyword()=="null"||pageDTO.getKeyword()==null){
            //不对queryWrapper进行任何修改
        }else{
            //模糊查询
            queryWrapper
                    .like(CbillAccountingPO.F_ACCOUNT_ID,pageDTO.getKeyword())
                    .or()
                    .like(CbillAccountingPO.F_BILL_NO,pageDTO.getKeyword())
                    .or()
                    .like(CbillAccountingPO.F_AGEN_IDCODE,pageDTO.getKeyword())
                    .or()
                    .like(CbillAccountingPO.F_OPERATOR,pageDTO.getKeyword())
                    .or()
                    .like(CbillAccountingPO.F_BILL_SERIAL_ID,pageDTO.getKeyword())
                    .or()
                    .like(CbillAccountingPO.F_AGEN_NAME,pageDTO.getKeyword());
        }
        //降序排序
        if(pageDTO.getSort() == "+id"){
            queryWrapper.orderByAsc(CbillAccountingPO.F_ID);
        }else {
            queryWrapper.orderByDesc(CbillAccountingPO.F_ID);
        }
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
        String lastSql = "limit 1";
        //检查票据校验码是否存在
        ResultCode resultCode = checkBillSerialIdExist(cbillAccountingDTO.getBillSerialId());
        if(!resultCode.success()){
            return new ResponseResult(resultCode);
        }
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CbillAccountingPO.F_BILL_SERIAL_ID,cbillAccountingDTO.getBillSerialId())
                .last(lastSql);
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
        queryWrapper.eq(CbillAccountingPO.F_BILL_NO,cbillAccountingDTO.getBillNo())
                .last("limit 1");
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
     * 更新单条数据(必须新建几组数据，防止影响凭证部分)
     *
     * @param cbillAccountingDTO
     * @return 统一响应
     */
    @Override
    public ResponseResult updateBill(CbillAccountingDTO cbillAccountingDTO) {
        //通过数据校验码查出某条需要插入的数据id
        String serial = cbillAccountingDTO.getBillSerialId();
        String num = cbillAccountingDTO.getBillNo();
        long id = cbillAccountingDTO.getId();
        //检查票据校验码是否重复
        ResultCode resultCode = checkBillSerialIdRepeat(serial,id);
        if(resultCode!=SUCCESS){
            return new ResponseResult(resultCode);
        }
        //检测票据号是否重复
        ResultCode resultCode1 = checkBillSerialIdRepeat(num,id);
        if(resultCode1!=SUCCESS){
            return new ResponseResult(resultCode1);
        }
        //需要同步更新入账凭证库
        VoucherDTO voucherDTO = MyBeanUtil.myCopyProperties(cbillAccountingDTO, VoucherDTO.class);
        //更新入账凭证失败
        if(!voucherService.updateVoucher(voucherDTO).isSuccess()){
            return voucherService.updateVoucher(voucherDTO);
        }
        //更新原入账数据
        CbillAccountingPO cbillAccountingPO = MyBeanUtil.copyProperties(cbillAccountingDTO, CbillAccountingPO.class);
        boolean result = super.updateById(cbillAccountingPO);
        if(!result){
            return new ResponseResult(UPDATE_FAIL);
        }else{
            return new ResponseResult(SUCCESS);
        }
    }

    /**
     * 插入单条数据
     *
     * @param cbillAccountingDTO
     * @return 统一请求
     */
    @Override
    public ResponseResult insertBill(CbillAccountingDTO cbillAccountingDTO) {
        //通过数据校验码查出某条需要插入的数据id
        String serial = cbillAccountingDTO.getBillSerialId();
        String num = cbillAccountingDTO.getBillNo();
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        //生成主键ID与凭证ID
        long id = snowflake.nextId();
        long accountId = snowflake.nextId();
        //检查票据校验码是否重复
        ResultCode resultCode = checkBillSerialIdRepeat(serial,id);
        if(resultCode!=SUCCESS){
            return new ResponseResult(resultCode);
        }
        //检测票据号是否重复
        ResultCode resultCode1 = checkBillNoRepeat(num,id);
        if(resultCode1!=SUCCESS){
            return new ResponseResult(resultCode1);
        }
        CbillAccountingPO cbillAccountingPO = MyBeanUtil.copyProperties(cbillAccountingDTO, CbillAccountingPO.class);
        cbillAccountingPO.setId(id);
        cbillAccountingPO.setAccountId(accountId);
        //拷贝cbillAccountingPO字段给voucherPO
        VoucherDTO voucherDTO = new VoucherDTO();
        MyBeanUtil.copyProperties(cbillAccountingPO,voucherDTO);
        //生成凭证
        if(!voucherService.generateVoucher(voucherDTO)){
            return new ResponseResult(VOUCHER_FAIL);
        }
        boolean result = super.save(cbillAccountingPO);
        if(!result){
            return new ResponseResult(INSERT_FAIL);
        }else{
            return new ResponseResult(SUCCESS);
        }
    }

    /**
     * 批量插入数据
     *
     * @param cbillAccountingDTOList
     * @return 统一请求
     */
    @Override
    public ResponseResult batchInsert(List<CbillAccountingDTO> cbillAccountingDTOList) {
        //暂未检测数据的冲突性
        List<CbillAccountingPO> cbillAccountingPOList = MyBeanUtil.copyListProperties(cbillAccountingDTOList, CbillAccountingPO::new);
        boolean result = super.saveBatch(cbillAccountingPOList);
        if(!result){
            return new ResponseResult(INSERT_FAIL);
        }else{
            return new ResponseResult(SUCCESS);
        }
    }

    /**
     * 检查票据校验码的唯一性
     *
     * @return
     */
    private ResultCode checkBillSerialIdRepeat(String billSerialId, long id) {
        // 检查票据校验码是否唯一
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_serial_id").eq(CbillAccountingPO.F_BILL_SERIAL_ID,billSerialId)
                .ne(CbillAccountingPO.F_ID,id);
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
     * 检查票据号是否存在
     *
     * @return
     */
    private ResultCode checkBillNoExist(String billNo) {
        // 检查票据号码是否存在，不存在不能查询
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_no").eq(CbillAccountingPO.F_BILL_NO,billNo)
                .last("limit 1");
        int result = count(queryWrapper);
        if(result==0){
            return BILL_NO_NOT_EXIST;
        }
        return SUCCESS;
    }

    /**
     * 检查票据号的唯一性
     *
     * @return
     */
    private ResultCode checkBillNoRepeat(String billNo, long id) {
        // 检查票据号码是否已经存在，已经存在不能插入
        QueryWrapper<CbillAccountingPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_bill_no").eq(CbillAccountingPO.F_BILL_NO,billNo)
                .ne(CbillAccountingPO.F_ID,id);
        int result = count(queryWrapper);
        if(result>1){
            return BILL_NO_REPEAT;
        }
        return SUCCESS;
    }


}
