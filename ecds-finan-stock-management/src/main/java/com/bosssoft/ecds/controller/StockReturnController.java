package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.vo.DateVO;
import com.bosssoft.ecds.entity.vo.StockReturnItemVO;
import com.bosssoft.ecds.entity.vo.StockReturnVO;
import com.bosssoft.ecds.service.StockReturnItemService;
import com.bosssoft.ecds.service.StockReturnService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
     * 根据编制日期区间查询
     * @param dateVO
     * @return
     */
    @GetMapping("/getListStockReturnByDate")
    public List<StockReturnVO> getStockReturnListByDate(@RequestBody DateVO dateVO) {
        log.info("时间："+dateVO.getStartTime().toString());
        return stockReturnService.stockReturnVOlistByDate(dateVO);
    }

}

