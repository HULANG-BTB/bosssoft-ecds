package com.bosssoft.ecds.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bosssoft.ecds.entity.dto.FinanBillDto;
import com.bosssoft.ecds.entity.dto.ReceiveFinanceapplyDto;
import com.bosssoft.ecds.entity.dto.SentBillDto;
import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.mapper.FinanBillMapper;
import com.bosssoft.ecds.service.FinanBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@Service
public class FinanBillServiceImpl extends ServiceImpl<FinanBillMapper, FinanBillPo> implements FinanBillService {

    @Autowired
    private FinanBillMapper finanBillMapper;

    /**
     *              发放出库
     * ******************************************************************************
     */

    /**
     *              主体业务外界调用
     * *********************************************
     */

    /**
     * 发放票据出库
     *
     * @param receiveDtos 接收的发票请求list，主要包含票据代码和数量
     *
     * @return SentBillDto发送的票据段list
     */
    @Override
    public List<SentBillDto> outBills(List<ReceiveFinanceapplyDto> receiveDtos) {
        log.info("开始outBills方法...");
        List<SentBillDto> sentBillDtos = new ArrayList<>();
        if (receiveDtos == null || receiveDtos.isEmpty()) {
            return null;
        } else {
            /*
            1. 确定票据库中数量是否足够
             */
            for (ReceiveFinanceapplyDto dto : receiveDtos) {
                if (!enoughBill(dto)) {
                    return null;
                }
            }
            log.info("outBills方法：数量足够，开始发送...");
            /*
            2. 发送票据
             */
            receiveDtos.forEach(dto -> {
                FinanBillDto finanBillDto = getStartNo(dto);
                Integer endNo = Integer.parseInt(finanBillDto.getBillId()) + dto.getNumber() - 1;
                SentBillDto sentBillDto = new SentBillDto();
                sentBillDto.setBillPrecode(dto.getBillPrecode());
                sentBillDto.setNumber(dto.getNumber());
                sentBillDto.setBillNo1(finanBillDto.getBillId());
                sentBillDto.setBillNo2(String.format("%010d", endNo));
                // 更新表的是否发放字段为已发放（1）
                updateIsPutOut(sentBillDto);
                sentBillDtos.add(sentBillDto);
            });
            log.info("退出outBills方法，发送数据：{}", sentBillDtos);
            return sentBillDtos;
        }
    }

    /**
     * 发放票据出库
     *
     * @param receiveDto 接收的发票请求，主要包含票据代码和数量
     *
     * @return FinanBillDto 发送的票据段
     */
    @Override
    public FinanBillDto getStartNo(ReceiveFinanceapplyDto receiveDto) {
        log.info("开始获取起始号...");
        // 获得起始票据（号）
        FinanBillDto billDto = Convert.convert(
                FinanBillDto.class,
                this.getOne(getWrapperValid()
                        .eq(FinanBillPo::getBillPrecode, receiveDto.getBillPrecode())
                        .orderByAsc(FinanBillPo::getBillId), false));
        log.info("起始号代码：{},号码：{}", billDto.getBillPrecode(), billDto.getBillId());
        return billDto;
    }

    /**
     * 判断票据发放请求数量是否合理
     * 即：判断该票据代码下，是否有足够票据用来发放
     *
     * @param receiveDto 发放请求
     *
     * @return 是否足够
     */
    @Override
    public Boolean enoughBill(ReceiveFinanceapplyDto receiveDto) {
        log.info("判断代码：{}的数量是否足够", receiveDto.getBillPrecode());
        LambdaQueryWrapper<FinanBillPo> wrapper = getWrapperValid();
        wrapper.eq(FinanBillPo::getBillPrecode, receiveDto.getBillPrecode());
        // 获得数量
        int count = this.count(wrapper);
        log.info("需求数量为：{}，获取的数量为：{}", receiveDto.getNumber(), count);
        // 数量足够，返回true
        if (receiveDto.getNumber() <= count) {
            return true;
        }
        return false;
    }

    /**
     * 根据发送票据dto，更新状态为已发送
     *
     * @param sentBillDto 发送票据dto
     *
     * @return 是否成功
     */
    @Override
    public Boolean updateIsPutOut(SentBillDto sentBillDto) {
        LambdaUpdateWrapper<FinanBillPo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(FinanBillPo::getBillPrecode, sentBillDto.getBillPrecode())
                .ge(FinanBillPo::getBillId, sentBillDto.getBillNo1())
                .le(FinanBillPo::getBillId, sentBillDto.getBillNo2())
                .set(FinanBillPo::getIsPutout, 1);
        return this.update(wrapper);
    }

    /**
     * 条件获取：可发放票（未发放，未作废，未失效（在生效范围内），未核销）
     *
     * @return wrapper
     */
    public LambdaQueryWrapper<FinanBillPo> getWrapperValid() {
        LambdaQueryWrapper<FinanBillPo> wrapper = Wrappers.<FinanBillPo>lambdaQuery();
        wrapper
                .eq(FinanBillPo::getIsPutout, 0)
                .eq(FinanBillPo::getIsInvalid, 0)
                .le(FinanBillPo::getEffDate, DateTime.now())
                .ge(FinanBillPo::getExpDate, DateTime.now())
                .eq(FinanBillPo::getHxStatus, 0)
        ;
        return wrapper;
    }

    /**
     *              查 get query
     * *********************************************
     */

    /**
     * 根据票据编码获取全部票据
     *
     * @param billCode 票据编码
     */
    @Override
    public FinanBillPo getOneByBillCode(Long billCode) {
        return getOneByBillCode(billCode, null);
    }

    /**
     * 根据票据编码获取票据，true可用false不可用
     *
     * @param billCode 票据编码
     * @param valid    是否可用
     */
    @Override
    public FinanBillPo getOneByBillCode(Long billCode, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper.eq("f_bill_code", billCode);
        return finanBillMapper.selectOne(queryWrapper);
    }

    /**
     * 根据仓库id获取全部票据
     *
     * @param warehouseId 仓库id
     */
    @Override
    public List<FinanBillPo> queryByWarehouseId(Long warehouseId) {
        return queryByWarehouseId(warehouseId, null);
    }

    /**
     * 根据仓库id获取票据，true可用false不可用
     *
     * @param warehouseId 仓库id
     * @param valid       是否可用
     */
    @Override
    public List<FinanBillPo> queryByWarehouseId(Long warehouseId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_warehouse_id", warehouseId)
                .groupBy("f_bill_precode");
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 根据票据代码获取全部票据
     *
     * @param billPrecode 票据代码
     */
    @Override
    public List<FinanBillPo> queryByBillPrecode(Long billPrecode) {
        return queryByBillPrecode(billPrecode, null);
    }

    /**
     * 根据票据代码获取票据，true可用false不可用
     *
     * @param billPrecode 票据代码
     * @param valid       是否可用
     */
    @Override
    public List<FinanBillPo> queryByBillPrecode(Long billPrecode, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper.eq("f_bill_precode", billPrecode);
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 根据创建年份获取该年的全部票据
     *
     * @param year 年份
     */
    @Override
    public List<FinanBillPo> queryByYear(String year) {
        return queryByYear(year, null);
    }

    /**
     * 根据创建年份获取该年的票据，true可用false不可用
     *
     * @param year  年份
     * @param valid 是否可用
     */
    @Override
    public List<FinanBillPo> queryByYear(String year, Boolean valid) {
        DateTime startTime = DateUtil.parse(year + "-01-01"); //年初
        DateTime endTime = DateUtil.endOfYear(startTime); //年底
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper.between("f_create_time", startTime, endTime);
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 根据票据编码，查找多条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param startId     开始号
     * @param endId       结束号
     */
    @Override
    public List<FinanBillPo> queryBills(String billPrecode, String startId, String endId) {
        return queryBills(billPrecode, startId, endId, null);
    }

    /**
     * 根据票据编码，查找多条财政票据记录，true可用false不可用
     *
     * @param billPrecode 票据代码
     * @param startId     开始号
     * @param endId       结束号
     * @param valid       是否可用
     */
    @Override
    public List<FinanBillPo> queryBills(String billPrecode, String startId, String endId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_bill_precode", billPrecode)
                .between("f_bill_id", startId, endId)
        ;
        return finanBillMapper.selectList(queryWrapper);
    }

    /**
     * 条件获取：可发放票（未发放，未作废，未失效（在生效范围内），未核销）
     */
    @Override
    public QueryWrapper<FinanBillPo> conditionPutoutValid() {
        QueryWrapper<FinanBillPo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("f_is_putout", 0)
                .eq("f_is_invalid", 0)
                .le("f_eff_date", DateTime.now())
                .ge("f_exp_date", DateTime.now())
                .eq("f_hx_status", 0)
        ;
        return queryWrapper;
    }

    /**
     * 条件获取：不可发放票（已发放，已作废，已失效（不在生效范围内），已核销）
     */
    @Override
    public QueryWrapper<FinanBillPo> conditionPutoutInvalid() {
        QueryWrapper<FinanBillPo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("f_is_putout", 1)
                .or()
                .eq("f_is_invalid", 1)
                .or()
                .ge("f_eff_date", DateTime.now())
                .or()
                .le("f_exp_date", DateTime.now())
                .or()
                .eq("f_hx_status", 1)
        ;
        return queryWrapper;
    }

    /**
     * 条件获取：获取全部（null）or可用（true）or不可用（false）票
     *
     * @param valid 是否可用
     */
    @Override
    public QueryWrapper<FinanBillPo> conditionValid(Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper;
        //获取全部
        if (valid == null) {
            queryWrapper = new QueryWrapper<>();
        }
        if (valid) {
            //获取可用
            queryWrapper = conditionPutoutValid();
        } else {
            //获取不可用
            queryWrapper = conditionPutoutInvalid();
        }
        return queryWrapper;
    }

    /**
     *              增 insert
     * ***************************************************
     */


    /**
     *              删 delete
     * ***************************************************
     */

    /**
     * 根据票据编码，删除单条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param billId      票据号
     */
    @Override
    public Integer deleteOneBill(String billPrecode, String billId) {
        return deleteOneBill(billPrecode, billId, null);
    }

    /**
     * 根据票据编码，删除单条财政票据记录，true可用，false不可用
     *
     * @param billPrecode 票据代码
     * @param billId      票据号
     * @param valid       是否可用
     */
    @Override
    public Integer deleteOneBill(String billPrecode, String billId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_bill_precode", billPrecode)
                .eq("f_bill_id", billId)
        ;
        return finanBillMapper.delete(queryWrapper);
    }

    /**
     * 根据票据编码，删除多条财政票据记录
     *
     * @param billPrecode 票据代码
     * @param startId     起始号
     * @param endId       终止号
     */
    @Override
    public Integer deleteBills(String billPrecode, String startId, String endId) {
        return deleteBills(billPrecode, startId, endId, null);
    }

    /**
     * 根据票据编码，删除多条财政票据记录，true可用，false不可用
     *
     * @param billPrecode 票据代码
     * @param startId     起始号
     * @param endId       终止号
     * @param valid       是否可用
     */
    @Override
    public Integer deleteBills(String billPrecode, String startId, String endId, Boolean valid) {
        QueryWrapper<FinanBillPo> queryWrapper = conditionValid(valid);
        queryWrapper
                .eq("f_bill_precode", billPrecode)
                .between("f_bill_id", startId, endId)
        ;
        return finanBillMapper.delete(queryWrapper);
    }

    /**
     *              改 update
     * **************************************************
     */

}
