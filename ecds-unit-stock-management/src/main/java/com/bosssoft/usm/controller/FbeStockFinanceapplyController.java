package com.bosssoft.usm.controller;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description
 */

import com.bosssoft.usm.entity.po.FbeStockFinanceapplyPO;
import com.bosssoft.usm.service.impl.FbeStockFinanceapplyServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9528")
public class FbeStockFinanceapplyController {

    @Resource
    FbeStockFinanceapplyServiceImpl fbeStockFinanceapplyService;

    @GetMapping("fbeStockFinanceapply")
    public FbeStockFinanceapplyPO getById(BigInteger id){
        return fbeStockFinanceapplyService.getByPrimaryKey(id);
    }

    @GetMapping("fbeStockFinanceapply/getAll")
    public List<FbeStockFinanceapplyPO> getAll(){
        return fbeStockFinanceapplyService.getAll();
    }

    @PostMapping("fbeStockFinanceapply")
    public int create(@RequestBody FbeStockFinanceapplyPO fbeStockFinanceapply){
        return fbeStockFinanceapplyService.insert(fbeStockFinanceapply);
    }

    @PutMapping("fbeStockFinanceapply")
    public void update(@RequestBody FbeStockFinanceapplyPO fbeStockFinanceapply){
        fbeStockFinanceapplyService.update(fbeStockFinanceapply);
    }

    @DeleteMapping("fbeStockFinanceapply")
    public void delete(BigInteger id){
        fbeStockFinanceapplyService.delete(id);
    }
}
