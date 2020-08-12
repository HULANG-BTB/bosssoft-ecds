package com.bosssoft.ecds.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.CbillAccountingDao;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.CbillAccountingPO;
import com.bosssoft.ecds.service.CbillAccountingService;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CbillAccountingDTO> listAll() {
        List<CbillAccountingPO> list = super.list();
        List<CbillAccountingDTO> accountingDTOList = MyBeanUtil.copyListProperties(list, CbillAccountingDTO.class);
        return accountingDTOList;
    }

    /**
     * 根据校验码查询入账单据
     *
     * @return 入账数据
     */
    @Override
    public CbillAccountingDTO selectBySerialId(CbillAccountingDTO cbillAccountingDTO) {
        CbillAccountingPO cbillAccountingPO = cbillAccountingDao.selectBySerialId(cbillAccountingDTO.getBillSerialId());
        MyBeanUtil.copyProperties(cbillAccountingPO,cbillAccountingDTO);
        return cbillAccountingDTO;
    }

    /**
     * 通过票据号码查询入账信息
     *
     * @return 入账数据
     */
    @Override
    public CbillAccountingDTO selectByBillId(CbillAccountingDTO cbillAccountingDTO) {
        CbillAccountingPO cbillAccountingPO = cbillAccountingDao.selectByBillId(cbillAccountingDTO.getBillNo());
        MyBeanUtil.copyProperties(cbillAccountingPO,cbillAccountingDTO);
        return cbillAccountingDTO;
    }

    /**
     * 通过单位代码查询入账信息
     *
     * @return 入账数据
     */
    @Override
    public List<CbillAccountingDTO> selectByAgenIdcode(CbillAccountingDTO cbillAccountingDTO) {
        List<CbillAccountingPO> cbillAccountingPOList = cbillAccountingDao.selectByAgenIdcode(cbillAccountingDTO.getAgenIdcode());
        return MyBeanUtil.copyListProperties(cbillAccountingPOList, CbillAccountingDTO.class);
    }

    /**
     * 缴费阶段查询应缴金额
     *
     * @return
     */
    @Override
    public CbillAccountingDTO selectAccount(CbillAccountingDTO cbillAccountingDTO){
        CbillAccountingPO cbillAccountingPO = new CbillAccountingPO();
        cbillAccountingPO.setWaitAccount(cbillAccountingDao.selectAccount(cbillAccountingDTO.getBillSerialId()));
        MyBeanUtil.copyProperties(cbillAccountingPO,cbillAccountingDTO);
        return cbillAccountingDTO;
    }

    /**
     * 开票阶段插入基础信息
     *
     * @return
     */
    @Override
    public Boolean insertAccBaseInfo(AccBaseInfoDTO accBaseInfoDto) {
        CbillAccountingPO cbillAccountingPO = new CbillAccountingPO();
        MyBeanUtil.copyProperties(accBaseInfoDto,cbillAccountingPO);
        cbillAccountingPO.setWaitAccount(accBaseInfoDto.getTotalAmt());
        cbillAccountingPO.setBillSerialId(accBaseInfoDto.getCheckCode());
        if(accBaseInfoDto.getPayerTel()==null){
            //缴款人电话数目为空，代表汇缴
            cbillAccountingPO.setType(1);
        }
        //插入信息
        return cbillAccountingDao.insert(cbillAccountingPO) == 1;
    }

    /**
     * 缴费阶段插入入账信息
     *
     * @return
     */
    @Override
    public Boolean insertAccount(AccIntoInfoDTO accIntoInfoDTO) {
        //通过数据校验码查出某条需要插入的数据id
        String serial = accIntoInfoDTO.getBillSerialId();
        CbillAccountingPO cbillAccountingPO = cbillAccountingDao.selectBySerialId(serial);
        if(cbillAccountingPO==null){
            return false;
        }
        if(!cbillAccountingPO.getPayerTel().equals(accIntoInfoDTO.getPayerTel())){
            return false;
        }
        if(cbillAccountingPO.getWaitAccount().compareTo(accIntoInfoDTO.getAccount())!=0){
            return false;
        }
        //赋值
        MyBeanUtil.copyProperties(accIntoInfoDTO,cbillAccountingPO);
        cbillAccountingPO.setAccountStatus(true);
        //更新数据
        return cbillAccountingDao.updateById(cbillAccountingPO)==1;
    }

    /**
     * 开票后插入票据信息
     *
     * @return
     */
    @Override
    public Boolean insertBillInfo(AccBillDTO accBillDto) {
        //通过数据校验码查出某条需要插入的数据
        String serial = accBillDto.getCheckCode();
        CbillAccountingPO cbillAccountingPO = cbillAccountingDao.selectBySerialId(serial);
        if(cbillAccountingPO==null){
            return false;
        }
        VoucherDTO voucherDTO = new VoucherDTO();
        //赋值
        cbillAccountingPO.setBillBatchId(accBillDto.getBillBatchId());
        cbillAccountingPO.setBillNo(accBillDto.getBillNo());
        cbillAccountingPO.setAgenTime(accBillDto.getDate());
        //生成凭证ID
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        cbillAccountingPO.setAccountId(id);
        //拷贝cbillAccountingPO字段给voucherPO
        MyBeanUtil.copyProperties(cbillAccountingPO,voucherDTO);
        //生成凭证
        if(!voucherService.generateVoucher(voucherDTO)){
            return false;
        }
        return cbillAccountingDao.updateById(cbillAccountingPO)==1;

    }

    /**
     * 开票前查询入账完成状态
     *
     * @return
     */
    @Override
    public Boolean selectStatus(AccBaseInfoDTO accBaseInfoDto) {
        return cbillAccountingDao.selectStatus(accBaseInfoDto.getCheckCode());
    }
}
