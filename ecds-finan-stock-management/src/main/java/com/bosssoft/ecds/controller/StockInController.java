package com.bosssoft.ecds.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.vo.CurrentBillNumberVO;
import com.bosssoft.ecds.entity.vo.StockInForChangeVO;
import com.bosssoft.ecds.service.StockInService;
import com.bosssoft.ecds.utils.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cheng
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/stockIn")
@Slf4j
public class StockInController {
    @Autowired
    private StockInService stockInService;
    
    @RequestMapping("/getId")
    public R getId() {
        CurrentBillNumberVO billNumber = stockInService.getBillNumber();
        return RUtils.responseForNull(billNumber, "生成失败");
    }
    
    @PostMapping("/add")
    public R create(@RequestBody AddStockInDTO addStockInDTO) {
        boolean success = stockInService.save(addStockInDTO);
        return RUtils.responseForBoolean(success, null, "保存失败");
    }
    
    @GetMapping("/unChangeList")
    public R<List<StockInForChangeVO>> unChangeList() {
        List<StockInForChangeVO> stockInForChangeVOS = stockInService.listUnChange();
        log.info(stockInForChangeVOS.toString());
        return R.ok(stockInForChangeVOS);
    }
    
    @PostMapping("/change")
    public R change(@RequestBody StockChangeDTO stockChangeDTO) {
        boolean success = stockInService.change(stockChangeDTO);
        return RUtils.responseForBoolean(success, null, "审核提交失败");
    }
    
    @PostMapping("/delete")
    public R delete(@RequestBody RemoveStockInDTO removeStockInDTO) {
        boolean success = stockInService.removeById(removeStockInDTO);
        return RUtils.responseForBoolean(success, null, "删除失败");
    }
    
    @PostMapping("/update")
    public R update(@RequestBody UpdateStockInDTO updateStockInDTO) {
        boolean success = stockInService.update(updateStockInDTO);
        return RUtils.responseForBoolean(success, null, "修改失败");
    }
    
    @PostMapping("/store")
    public R store(@RequestBody StoreDTO storeDTO) {
        boolean success = stockInService.store(storeDTO);
        return RUtils.responseForBoolean(success, null, "入库成功");
    }
    
    @PostMapping("/checkStore")
    public String checkStore(@RequestBody CheckStoreDTO checkStoreDTO) {
        boolean available = stockInService.checkStore(checkStoreDTO);
        return RUtils.responseBoolean(available, null, "可用", "不可用");
    }
}

