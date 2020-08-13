package com.bosssoft.usm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.bosssoft.usm.config.StatusCode;
import com.bosssoft.usm.entity.po.StockReturnItemPO;
import com.bosssoft.usm.entity.po.StockReturnPO;
import com.bosssoft.usm.dao.StockReturnDao;
import com.bosssoft.usm.entity.vo.DateVO;
import com.bosssoft.usm.entity.vo.StockReturnItemVO;
import com.bosssoft.usm.entity.vo.StockReturnVO;
import com.bosssoft.usm.service.StockReturnItemService;
import com.bosssoft.usm.service.StockReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhuWen
 * @since 2020-08-11
 */
@Service
@Slf4j
public class StockReturnServiceImpl extends ServiceImpl<StockReturnDao, StockReturnPO> implements StockReturnService {

    @Autowired
    private StockReturnItemService stockReturnItemService;

    /**
     * 添加退票信息
     * @param stockReturnVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addStockReturnPO(StockReturnVO stockReturnVO) {
        StockReturnPO stockReturnPO = new StockReturnPO();
        BeanUtils.copyProperties(stockReturnVO,stockReturnPO);
        stockReturnPO.setRgnCode("11");
       /* stockReturnPO.setChangeDate(new Date());
        stockReturnPO.setChangeMan("老刘");
        stockReturnPO.setChangeSitu("同意提交");*/
        // stockReturnPO.setVersion(null);
        boolean status1 = save(stockReturnPO);
        List<StockReturnItemVO> stockReturnItemVOS = stockReturnVO.getStockReturnItemVOList();
        List<StockReturnItemPO> stockReturnItemPOS = new ArrayList<>();
        for(StockReturnItemVO stockReturnItemVO : stockReturnItemVOS) {
            StockReturnItemPO stockReturnItemPO = new StockReturnItemPO();
            BeanUtils.copyProperties(stockReturnItemVO,stockReturnItemPO);
            stockReturnItemPO.setPid(stockReturnPO.getNo());
            // stockReturnItemPO.setVersion(null);
            stockReturnItemPOS.add(stockReturnItemPO);
        }
        boolean status2 = stockReturnItemService.saveBatch(stockReturnItemPOS);
        if(status1 == true && status2 == true) {
            return StatusCode.ADD_SUCCESS;
        }
        return StatusCode.ADD_FAILED;

    }

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

    /**
     * 修改退票原因
     * @param stockReturnVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateStockReturnPO(StockReturnVO stockReturnVO) {
        QueryWrapper<StockReturnPO> queryWrapper = new QueryWrapper<StockReturnPO>();
        StockReturnPO stockReturnPO = new StockReturnPO();
        BeanUtils.copyProperties(stockReturnVO,stockReturnPO);
        queryWrapper.eq("f_no",stockReturnPO.getNo());
        // stockReturnPO = getOne(queryWrapper);
        boolean status = update(stockReturnPO,queryWrapper);
        if(status == true) {
            return StatusCode.UPDATE_SUCCESS;
        }
        return StatusCode.UPDATE_FAILED;
    }

    /**
     * 修改退票明细表信息
     * @param stockReturnItemVOS 退票明细列表项
     * @param no 业务单号
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateStockReturnItemPO(List<StockReturnItemVO> stockReturnItemVOS,Long no) {
        List<StockReturnItemPO> stockReturnItemPOList = new ArrayList<>();
        for(StockReturnItemVO stockReturnItemVO : stockReturnItemVOS) {
            StockReturnItemPO stockReturnItemPO = new StockReturnItemPO();
            BeanUtils.copyProperties(stockReturnItemVO,stockReturnItemPO);
            //stockReturnItemPO.setPid(no);
            QueryWrapper<StockReturnItemPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("f_no",stockReturnItemPO.getNo());
            queryWrapper.eq("f_pid",no);
            boolean status = stockReturnItemService.saveOrUpdate(stockReturnItemPO, queryWrapper);
            if(status == false) {
                return StatusCode.UPDATE_FAILED;
            }
        }

        return StatusCode.UPDATE_SUCCESS;
    }


    /**
     * 根据业务单号删除退票信息
     * @param no
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteStockReturn(Long no) {
        QueryWrapper<StockReturnPO> queryWrapper = new QueryWrapper<>();
        QueryWrapper<StockReturnItemPO> queryWrapperItem = new QueryWrapper<>();
        queryWrapper.eq("f_no",no);
        queryWrapperItem.eq("f_pid",no);
        boolean status2 = stockReturnItemService.remove(queryWrapperItem);
        boolean status1 = remove(queryWrapper);
        if(status1 == true && status2 == true) {
            return StatusCode.DELETE_SUCCESS;
        }
        return StatusCode.DELETE_FAILED;

    }

}
