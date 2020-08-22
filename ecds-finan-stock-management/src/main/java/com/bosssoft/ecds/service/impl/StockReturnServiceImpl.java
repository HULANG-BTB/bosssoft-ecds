package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.po.AgenBillPO;
import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.bosssoft.ecds.entity.po.StockReturnItemPO;
import com.bosssoft.ecds.entity.po.StockReturnPO;
import com.bosssoft.ecds.dao.StockReturnDao;
import com.bosssoft.ecds.entity.vo.DateVO;
import com.bosssoft.ecds.entity.vo.StockReturnCheckVO;
import com.bosssoft.ecds.entity.vo.StockReturnItemVO;
import com.bosssoft.ecds.entity.vo.StockReturnVO;
import com.bosssoft.ecds.service.AgenBillService;
import com.bosssoft.ecds.service.FinanBillService;
import com.bosssoft.ecds.service.StockReturnItemService;
import com.bosssoft.ecds.service.StockReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.ConstantChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhuWen
 * @since 2020-08-13
 */
@Service
@Slf4j
public class StockReturnServiceImpl extends ServiceImpl<StockReturnDao, StockReturnPO> implements StockReturnService {

    @Autowired
    private StockReturnItemService stockReturnItemService;

    @Autowired
    private FinanBillService finanBillService;

    @Autowired
    private AgenBillService agenBillService;

    /**
     * 查询退票主表信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockReturnVO> stockRetrunVOList() {
        List<StockReturnPO> stockReturnPOS = list(new QueryWrapper<StockReturnPO>().eq("f_submit_status", 1));
        List<StockReturnVO> stockReturnVOS = new ArrayList<>();
        for(StockReturnPO stockReturnPO :stockReturnPOS) {
            int i = 0;
            StockReturnVO stockReturnVO = new StockReturnVO();
            BeanUtils.copyProperties(stockReturnPO,stockReturnVO);
            stockReturnVO.setNum(i++);
            stockReturnVOS.add(stockReturnVO);
            // stockReturnVO.setStockReturnItemVOList(null);
        }
        return stockReturnVOS;
    }

    /**
     * 根据业务单号来查询退票明细信息
     * @param no 业务单号
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockReturnItemVO> stockReturnItemVOList(Long no) {
        List<StockReturnItemPO> stockReturnItemPOS = stockReturnItemService.list(new QueryWrapper<StockReturnItemPO>().eq("f_pid",no));
        List<StockReturnItemVO> stockReturnItemVOS = new ArrayList<>();
        for(StockReturnItemPO stockReturnItemPO : stockReturnItemPOS) {
            StockReturnItemVO stockReturnItemVO = new StockReturnItemVO();
            BeanUtils.copyProperties(stockReturnItemPO,stockReturnItemVO);
            stockReturnItemVOS.add(stockReturnItemVO);
        }
        return stockReturnItemVOS;
    }

    /**
     * 根据业务单号来查询单个退票
     * @param no 业务单号
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public StockReturnVO stockRetrunVOListByNo(Long no) {
        StockReturnVO stockReturnVO = new StockReturnVO();
        StockReturnPO stockReturnPO = getOne(new QueryWrapper<StockReturnPO>().eq("f_no",no));
        BeanUtils.copyProperties(stockReturnPO, stockReturnVO);
        stockReturnVO.setNum(1);
        return stockReturnVO;
    }

    /**
     * 根据编制日期和业务单号查询退票主信息
     * @param dateVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockReturnVO> stockReturnVOlistByDateOrNo(DateVO dateVO) {
        QueryWrapper<StockReturnPO> queryWrapper = new  QueryWrapper<StockReturnPO>();
        queryWrapper.lambda().gt(StockReturnPO::getDate,dateVO.getStartTime());
        queryWrapper.lambda().le(StockReturnPO::getDate,dateVO.getEndTime());
        queryWrapper.eq("f_submit_status",1);
        log.info("编制起始时间"+dateVO.getStartTime());
        log.info("编制结束时间"+dateVO.getEndTime());
        List<StockReturnPO> stockReturnPOS = list(queryWrapper);
        List<StockReturnVO> stockReturnVOS = new ArrayList<>();
        for(StockReturnPO stockReturnPO :stockReturnPOS) {
            int i = 0;
            StockReturnVO stockReturnVO = new StockReturnVO();
            BeanUtils.copyProperties(stockReturnPO,stockReturnVO);
            stockReturnVO.setNum(i++);
            stockReturnVOS.add(stockReturnVO);
            // stockReturnVO.setStockReturnItemVOList(null);
        }
        return stockReturnVOS;
    }

    /**
     * 分页查询以及根据业务单号和编制日期查询主表信息
     * @param dateVO
     * @return
     */
    @Override
    public PageResult stockReturnVOListByPage(DateVO dateVO) {
        PageResult pageResult= new PageResult();
        Page<StockReturnPO> page1 = new Page<>(dateVO.getPage(), dateVO.getLimit());
        QueryWrapper<StockReturnPO> queryWrapper = new  QueryWrapper<StockReturnPO>();
        queryWrapper.eq("f_submit_status",1);
        if(dateVO.getNo() != null || (dateVO.getStartTime() !=null && dateVO.getEndTime() != null )) {
            queryWrapper.lambda().gt(StockReturnPO::getDate, dateVO.getStartTime());
            queryWrapper.lambda().le(StockReturnPO::getDate, dateVO.getEndTime());
            queryWrapper.or();
            queryWrapper.eq("f_no", dateVO.getNo());
            page1 = page(page1, queryWrapper);
        } else {
            page1 = page(page1, queryWrapper);
        }
        pageResult.setLimit(dateVO.getLimit());
        pageResult.setPage(dateVO.getPage());
        pageResult.setTotal(page1.getTotal());
        List<StockReturnVO> stockReturnVOS = new ArrayList<StockReturnVO>();
        for(StockReturnPO stockReturnPO : page1.getRecords()) {
            StockReturnVO stockReturnVO = new StockReturnVO();
            BeanUtils.copyProperties(stockReturnPO, stockReturnVO);
            stockReturnVOS.add(stockReturnVO);
            log.info("stockReturnVO: ",stockReturnVO);
        }
        pageResult.setRow(stockReturnVOS);
        return pageResult;
    }

    /**
     * 根据业务单号来查询单个退票以及附属明细信息
     * @param stockReturnVO1 业务单号
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public StockReturnVO stockRetrunVOByNo (StockReturnVO stockReturnVO1) {
        StockReturnVO stockReturnVO = new StockReturnVO();
        StockReturnPO stockReturnPO = getOne(new QueryWrapper<StockReturnPO>().eq("f_no",stockReturnVO1.getNo()));
        BeanUtils.copyProperties(stockReturnPO, stockReturnVO);
        List<StockReturnItemPO> stockReturnItemPOS = stockReturnItemService.list(new QueryWrapper<StockReturnItemPO>().eq("f_pid",stockReturnVO1.getNo()));
        List<StockReturnItemVO> stockReturnItemVOS = new ArrayList<>();
        for (StockReturnItemPO stockReturnItemPO : stockReturnItemPOS) {
            StockReturnItemVO stockReturnItemVO = new StockReturnItemVO();
            BeanUtils.copyProperties(stockReturnItemPO, stockReturnItemVO);
            stockReturnItemVOS.add(stockReturnItemVO);
        }
        stockReturnVO.setStockReturnItemVOList(stockReturnItemVOS);
        return stockReturnVO;
    }

    /**
     * 审核 创建时间，起始号和终止号是否正常,正常更改退票主表中的审核状态字段，
     * 并将财政票据表和单位票据表退票属性更改
     * @param stockReturnCheckVO
     * @return
     */
    @Override
    public String CheckStatusByNo(StockReturnCheckVO stockReturnCheckVO) {
        StockReturnPO stockReturnPO = new StockReturnPO();
        stockReturnPO = getOne(new QueryWrapper<StockReturnPO>().eq("f_no",stockReturnCheckVO.getNo()));
        if(stockReturnPO != null) {
            if(stockReturnPO.getSubmitStatus() == ConstantChange.SUBMIT_STATUS_YES) {
                if(/*new Date().after(stockReturnPO.getDate()) && */stockReturnCheckVO.getChangeState() ==1 ) {
                    stockReturnPO.setChangeState(ConstantChange.CHECK_STATUS_YES);
                    stockReturnPO.setChangeMan(stockReturnCheckVO.getChangeMan());
                    stockReturnPO.setChangeSitu(stockReturnCheckVO.getChangeSitu());
                    stockReturnPO.setChangeDate(new Date());
                    update(stockReturnPO,new QueryWrapper<StockReturnPO>().eq("f_no", stockReturnCheckVO.getNo()));
                    List<StockReturnItemPO> stockReturnItemPOList = stockReturnItemService.list(new QueryWrapper<StockReturnItemPO>().eq("f_pid", stockReturnCheckVO.getNo()));
                    for (StockReturnItemPO stockReturnItemPO : stockReturnItemPOList) {
                        FinanBillPo finanBillPo = new FinanBillPo();
                        AgenBillPO agenBillPO = new AgenBillPO();
                        QueryWrapper<FinanBillPo> queryWrapper = new QueryWrapper<>();
                        QueryWrapper<AgenBillPO> queryWrapper1 = new QueryWrapper<>();
                        queryWrapper.between("f_bill_id", stockReturnItemPO.getBillNo1(), stockReturnItemPO.getBillNo2());
                        queryWrapper.eq("f_bill_precode", stockReturnItemPO.getBillCode());
                        queryWrapper1.between("f_bill_id", stockReturnItemPO.getBillNo1(), stockReturnItemPO.getBillNo2());
                        queryWrapper1.eq("f_bill_precode", stockReturnItemPO.getBillCode());
                        agenBillPO.setIsReturn(1);
                        finanBillPo.setIsReturn(1);
                        boolean status1 = finanBillService.update(finanBillPo, queryWrapper);
                        boolean status2 = agenBillService.update(agenBillPO, queryWrapper1);
                        if(status1 == true && status2 == true) {
                            return "Check Pass Success!";
                        }
                    }

                } else {
                    stockReturnPO.setChangeState(ConstantChange.CHECK_STATUS_FAILED);
                    stockReturnPO.setChangeMan(stockReturnCheckVO.getChangeMan());
                    stockReturnPO.setChangeSitu(stockReturnCheckVO.getChangeSitu());
                    stockReturnPO.setChangeDate(new Date());
                    update(stockReturnPO, new QueryWrapper<StockReturnPO>().eq("f_no", stockReturnCheckVO.getNo()));
                    return "Check NOPass Success!";
                }
            }
        }
        return "Check Pass or NOPass Failed!";
    }
}
