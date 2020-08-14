package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.po.StockReturnItemPO;
import com.bosssoft.ecds.entity.po.StockReturnPO;
import com.bosssoft.ecds.dao.StockReturnDao;
import com.bosssoft.ecds.entity.vo.DateVO;
import com.bosssoft.ecds.entity.vo.StockReturnItemVO;
import com.bosssoft.ecds.entity.vo.StockReturnVO;
import com.bosssoft.ecds.service.StockReturnItemService;
import com.bosssoft.ecds.service.StockReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    /**
     * 查询退票主表信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockReturnVO> stockRetrunVOList() {
        List<StockReturnPO> stockReturnPOS = list();
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
     * 根据编制日期查询退票主信息
     * @param dateVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockReturnVO> stockReturnVOlistByDate(DateVO dateVO) {
        QueryWrapper<StockReturnPO> queryWrapper = new  QueryWrapper<StockReturnPO>();
        queryWrapper.lambda().gt(StockReturnPO::getDate,dateVO.getStartTime());
        queryWrapper.lambda().le(StockReturnPO::getDate,dateVO.getEndTime());
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

    @Override
    public String CheckStatusByNo(Long no) {
        return null;
    }
}
