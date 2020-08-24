package com.bosssoft.usm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.usm.config.StatusCode;
import com.bosssoft.usm.entity.po.FabAgenBillPO;
import com.bosssoft.usm.entity.po.StockReturnItemPO;
import com.bosssoft.usm.entity.po.StockReturnPO;
import com.bosssoft.usm.dao.StockReturnDao;
import com.bosssoft.usm.entity.vo.DateVO;
import com.bosssoft.usm.entity.vo.StockReturnItemVO;
import com.bosssoft.usm.entity.vo.StockReturnVO;
import com.bosssoft.usm.service.FabAgenBillService;
import com.bosssoft.usm.service.StockReturnItemService;
import com.bosssoft.usm.service.StockReturnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.usm.util.EntityPage;
import com.bosssoft.usm.util.PageResult;
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

    @Autowired
    private FabAgenBillService fabAgenBillService;

    /**
     * 添加退票信息
     * @param stockReturnVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addStockReturnPO(StockReturnVO stockReturnVO) {
        log.info("*********stockReturnVO************",stockReturnVO.getNo()+stockReturnVO.getAgenIdCode());
        StockReturnPO stockReturnPO = new StockReturnPO();
        BeanUtils.copyProperties(stockReturnVO,stockReturnPO);
        // stockReturnPO.setNo(123456789L);
        stockReturnPO.setRgnCode("11");
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
            StockReturnVO stockReturnVO = new StockReturnVO();
            BeanUtils.copyProperties(stockReturnPO,stockReturnVO);
            stockReturnVOS.add(stockReturnVO);
            // stockReturnVO.setStockReturnItemVOList(null);
        }
        return stockReturnVOS;
    }

    /**
     * 分页查询
     * @param dateVO
     * @return
     */
    @Override
    public PageResult stockReturnVOListByPage(DateVO dateVO) {
        PageResult pageResult= new PageResult();
        Page<StockReturnPO> page1 = new Page<>(dateVO.getPage(), dateVO.getLimit());
        QueryWrapper<StockReturnPO> queryWrapper = new  QueryWrapper<StockReturnPO>();
        if(dateVO.getNo() != null || (dateVO.getStartTime() !=null && dateVO.getEndTime() != null )) {
            queryWrapper.eq("f_no", dateVO.getNo());
            queryWrapper.or();
            queryWrapper.lambda().gt(StockReturnPO::getDate, dateVO.getStartTime());
            queryWrapper.lambda().le(StockReturnPO::getDate, dateVO.getEndTime());
            page1 = page(page1, queryWrapper);
        } else {
            page1 = page(page1);
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
     * 根据业务单号或者编制日期查询退票主信息
     * @param no
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockReturnVO> stockReturnVOlistByDate(Long no, Date startTime, Date endTime) {
        QueryWrapper<StockReturnPO> queryWrapper = new  QueryWrapper<StockReturnPO>();
        queryWrapper.eq("f_no",no);
        queryWrapper.or();
        queryWrapper.lambda().gt(StockReturnPO::getDate,startTime);
        queryWrapper.lambda().le(StockReturnPO::getDate,endTime);
        log.info("编制起始时间"+startTime);
        log.info("编制结束时间"+endTime);
        List<StockReturnPO> stockReturnPOS = list(queryWrapper);
        List<StockReturnVO> stockReturnVOS = new ArrayList<>();
        for(StockReturnPO stockReturnPO :stockReturnPOS) {
            StockReturnVO stockReturnVO = new StockReturnVO();
            BeanUtils.copyProperties(stockReturnPO,stockReturnVO);
            stockReturnVOS.add(stockReturnVO);
            // stockReturnVO.setStockReturnItemVOList(null);
        }
        return stockReturnVOS;
    }

    /**
     *根据业务单号或者编制日期查询退票主信息（正在使用）
     * @param dateVO
     * @return
     */
    @Override
    public List<StockReturnVO> stockReturnVOlistByDate1(DateVO dateVO) {
        QueryWrapper<StockReturnPO> queryWrapper = new  QueryWrapper<StockReturnPO>();
        queryWrapper.eq("f_no",dateVO.getNo());
        queryWrapper.or();
        queryWrapper.lambda().gt(StockReturnPO::getDate,dateVO.getStartTime());
        queryWrapper.lambda().le(StockReturnPO::getDate,dateVO.getEndTime());
        log.info("编制起始时间"+dateVO.getStartTime());
        log.info("编制结束时间"+dateVO.getEndTime());

        List<StockReturnPO> stockReturnPOS = list(queryWrapper);
        List<StockReturnVO> stockReturnVOS = new ArrayList<>();
        for(StockReturnPO stockReturnPO :stockReturnPOS) {
            StockReturnVO stockReturnVO = new StockReturnVO();
            BeanUtils.copyProperties(stockReturnPO,stockReturnVO);
            stockReturnVOS.add(stockReturnVO);
            // stockReturnVO.setStockReturnItemVOList(null);
        }
        return stockReturnVOS;
    }

    /**
     * 修改退票主信息和退票明細
     * @param stockReturnVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateStockReturn(StockReturnVO stockReturnVO) {
        boolean status1 = remove(new QueryWrapper<StockReturnPO>().eq("f_no",stockReturnVO.getNo()));
        boolean status2 = stockReturnItemService.remove(new QueryWrapper<StockReturnItemPO>().eq("f_pid",stockReturnVO.getNo()));
        if(status1 == true && status2 == true){
           String status3 = addStockReturnPO(stockReturnVO);
           if(status3.equals("102")) {
               return StatusCode.UPDATE_SUCCESS;
           }
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

    /**
     * 根据业务单号修改单位票据表的退票状态
     * @param no 业务单号
     * @return
     */
    @Override
    public String submitApply(Long no) {
       /* StockReturnPO stockReturnPO = new StockReturnPO();
        stockReturnPO.setSubmitStatus(1);
        boolean status1 = update(stockReturnPO,new QueryWrapper<StockReturnPO>().eq("f_no",no));*/
        List<StockReturnItemPO> stockReturnItemPOList = stockReturnItemService.list(new QueryWrapper<StockReturnItemPO>().eq("f_pid",no));
        for(StockReturnItemPO stockReturnItemPO : stockReturnItemPOList) {
            FabAgenBillPO fabAgenBillPO = new FabAgenBillPO();
            fabAgenBillPO.setFIsReturn(1);
            QueryWrapper<FabAgenBillPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("f_bill_precode",stockReturnItemPO.getBillCode());
            queryWrapper.between("f_bill_id",stockReturnItemPO.getBillNo1(),stockReturnItemPO.getBillNo2());
            int status1 = fabAgenBillService.updateByWrapper(fabAgenBillPO,queryWrapper);
            if(status1 == 0) {
                return StatusCode.UPDATE_FAILED;
            }
        }
        return StatusCode.UPDATE_FAILED;
    }


}
