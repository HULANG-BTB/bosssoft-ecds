package com.bosssoft.ecds.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.po.FinanBillPo;
import com.bosssoft.ecds.entity.po.StockReturnPO;
import com.bosssoft.ecds.entity.vo.DateVO;
import com.bosssoft.ecds.entity.vo.StockReturnCheckVO;
import com.bosssoft.ecds.entity.vo.StockReturnItemVO;
import com.bosssoft.ecds.entity.vo.StockReturnVO;
import com.bosssoft.ecds.service.StockReturnItemService;
import com.bosssoft.ecds.service.StockReturnService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhuWen
 * @since 2020-08-13
 */
@RestController
@RequestMapping("/finan-stock-return-po")
@Slf4j
@CrossOrigin("http://localhost:9528")
public class StockReturnController {

    @Autowired
    StockReturnService stockReturnService;

    @Autowired
    StockReturnItemService stockReturnItemService;

    /**
     * 列出所有的退票主表信息
     * @return
     */
    @GetMapping("/getListStockReturn")
    public List<StockReturnVO> getStockReturn(){
        return stockReturnService.stockRetrunVOList();
    }

    /**
     * 根据业务单号查询退票明细信息
     * @param no 业务单号
     * @return
     */
    @GetMapping("/getListStockReturnItem")
    public List<StockReturnItemVO> getStockReturnItem(Long no){
        return stockReturnService.stockReturnItemVOList(no);
    }

    /**
     * 根据业务单号查询单条退票主信息信息
     * @param no 业务单号
     * @return
     */
    @GetMapping("/getListStockReturnByNo")
    public StockReturnVO getStockReturn(Long no){
        return stockReturnService.stockRetrunVOListByNo(no);
    }

    /**
     * 根据业务单号、编制日期区间分页查询
     * @param dateVO
     * @return
     */
    @PostMapping("/getListStockReturnByDate")
    public PageResult getStockReturnListByDate(@RequestBody DateVO dateVO) {
        log.info("dateVo："+dateVO);
        PageResult pageResult = stockReturnService.stockReturnVOListByPage(dateVO);
        return  pageResult;
    }

    /**
     * 根据退票主信息查询退票详细信息
     * @param stockReturnVO1
     * @return
     */
    @PostMapping("/getStockReturnByNo")
    public StockReturnVO stockRetrunVOByNo (@RequestBody StockReturnVO stockReturnVO1) {
        return stockReturnService.stockRetrunVOByNo(stockReturnVO1);
    }

    /**
     * 审核通过操作
     * @param stockReturnCheckVO
     * @return
     */
    @PostMapping("/CheckStatusByNo")
    public String toCheckStatusByNo(@RequestBody StockReturnCheckVO stockReturnCheckVO){
        return stockReturnService.CheckStatusByNo(stockReturnCheckVO);
    }

    /**
     * 审核未通过操作
     * @param no 业务单号
     * @param changeSitu 审核意见
     * @param changeMan 审核人
     * @return
     */
    @PutMapping("/noCheckStatusByNo")
    public String noCheCheckStatusByNo(Long no, String changeSitu, String changeMan){
        StockReturnPO stockReturnPO = new StockReturnPO();
        stockReturnPO.setChangeState(2);
        stockReturnPO.setChangeDate(new Date());
        stockReturnPO.setChangeMan(changeMan);
        stockReturnPO.setChangeSitu(changeSitu);
        boolean status = stockReturnService.update(stockReturnPO,new QueryWrapper<StockReturnPO>().eq("f_no", no));
        if(status == true){
            return "Check no pass!";
        }
        return "check no pass operation failed!";
    }
}

