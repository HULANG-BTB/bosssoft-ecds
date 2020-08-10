package com.bosssoft.usm.controller;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description
 */

import com.bosssoft.usm.entity.po.FabAgenBillPO;
import com.bosssoft.usm.service.impl.FabAgenBillServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FabAgenBillController {

    @Resource
    FabAgenBillServiceImpl fabAgenBillService;

    @GetMapping("fabAgenBill")
    public FabAgenBillPO getById(Integer id){
        return fabAgenBillService.getByPrimaryKey(id);
    }

    @GetMapping("fabAgenBill/getAll")
    public List<FabAgenBillPO> getAll(){
        return fabAgenBillService.getAll();
    }

    @PostMapping("fabAgenBill")
    public int create(@RequestBody FabAgenBillPO fabAgenBill){
        return fabAgenBillService.insert(fabAgenBill);
    }

    @PutMapping("fabAgenBill")
    public void update(@RequestBody FabAgenBillPO fabAgenBill){
        fabAgenBillService.update(fabAgenBill);
    }

    @DeleteMapping("fabAgenBill")
    public void delete(Integer id){
        fabAgenBillService.delete(id);
    }
}
