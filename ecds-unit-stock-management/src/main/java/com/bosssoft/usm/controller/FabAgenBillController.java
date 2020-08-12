package com.bosssoft.usm.controller;

/**
 * @author 张东海
 * @date 2020/8/10
 * @description
 */

import com.bosssoft.usm.entity.dto.BillApplyDTO;
import com.bosssoft.usm.entity.po.FabAgenBillPO;
import com.bosssoft.usm.entity.po.FbeStockFinanceapplyPO;
import com.bosssoft.usm.service.impl.FabAgenBillServiceImpl;
import com.bosssoft.usm.util.EntityUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:9528")
public class FabAgenBillController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    FabAgenBillServiceImpl fabAgenBillService;

    @GetMapping("fabAgenBill")
    public FabAgenBillPO getById(BigInteger id){
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
    public void delete(BigInteger id){
        fabAgenBillService.delete(id);
    }

    @GetMapping("copy")
    public BillApplyDTO copy(@RequestBody FbeStockFinanceapplyPO fbeStockFinanceapply){
        BillApplyDTO billApplyDto = new BillApplyDTO();
        return EntityUtil.POToDTO(fbeStockFinanceapply,billApplyDto);
    }

    @GetMapping("test")
    public void test(){
//        rabbitTemplate.convertAndSend("amq.direct","cao","666");
        Object o = rabbitTemplate.receiveAndConvert("dfQ");
        System.out.println(o);
    }

//    @PutMapping("rollback")
//    @Transactional(rollbackFor = Exception.class)
//    public void testRollback(Integer isRollBack,@RequestBody FabAgenBillPO fabAgenBill) throws Exception {
//        fabAgenBill.setFBillName("炸之前的订单名字");
//        fabAgenBillService.update(fabAgenBill);
//        if (isRollBack == 1){
//            throw new Exception("炸啦！");
//        }
//        fabAgenBill.setFBillName("炸之后的订单名字");
//        fabAgenBillService.update(fabAgenBill);
//    }
}
