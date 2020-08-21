package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.vo.CurrentBillNumberVO;
import com.bosssoft.ecds.entity.vo.StockInInfo;
import com.bosssoft.ecds.service.StockInService;
import com.bosssoft.ecds.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
@Api(value = "入库接口")
public class StockInController {
    @Autowired
    private StockInService stockInService;
    
    /**
     * 获取业务单号
     *
     * @return 新的业务单号
     */
    @ApiOperation(value = "获取业务单号")
    @RequestMapping("/getId")
    public String getId() {
        CurrentBillNumberVO billNumber = stockInService.getBillNumber();
        return ResponseUtils.getResponse(billNumber, ResponseUtils.ResultType.ACCEPTED);
    }
    
    /**
     * 保存新入库单
     *
     * @param addStockInDTO 新建的入库单信息
     * @return 入库单保存操作结果
     */
    @ApiOperation(value = "保存新入库单")
    @PostMapping("/add")
    public String create(@RequestBody AddStockInDTO addStockInDTO) {
        boolean success = stockInService.save(addStockInDTO);
        return ResponseUtils.getResponse(success, ResponseUtils.ResultType.ACCEPTED);
    }
    
    /**
     * 分页查询入库单
     *
     * @param stockInPageDTO 分页条件
     * @return 包含分页数据的列表
     */
    @ApiOperation(value = "分页查询入库单")
    @PostMapping("/listPage")
    public String listPage(@RequestBody StockInPageDTO stockInPageDTO) {
        log.info(stockInPageDTO.toString());
        PageResult pageResult = stockInService.listVOPage(stockInPageDTO);
        return ResponseUtils.getResponse(pageResult, ResponseUtils.ResultType.ACCEPTED);
    }
    
    /**
     * 审核方法
     *
     * @param stockChangeDTO 审核条件
     * @return 审核操作结果
     */
    @ApiOperation(value = "审核方法")
    @PostMapping("/change")
    public String change(@RequestBody StockChangeDTO stockChangeDTO) {
        boolean success = stockInService.change(stockChangeDTO);
        return ResponseUtils.getResponse(success, ResponseUtils.ResultType.ACCEPTED);
    }
    
    /**
     * 删除入库单
     *
     * @param removeStockInDTO 删除入库单的条件，包含入库单id，操作人
     * @return 删除操作结果
     */
    @ApiOperation(value = "删除入库单")
    @PostMapping("/delete")
    public String delete(@RequestBody RemoveStockInDTO removeStockInDTO) {
        boolean success = stockInService.removeById(removeStockInDTO);
        return ResponseUtils.getResponse(success, ResponseUtils.ResultType.ACCEPTED);
    }
    
    /**
     * 更新入库单
     *
     * @param updateStockInDTO 更新条件，包含入库单id，入库单关联入库明细
     * @return
     */
    @ApiOperation(value = "更新入库单")
    @PostMapping("/update")
    public String update(@RequestBody UpdateStockInDTO updateStockInDTO) {
        boolean success = stockInService.update(updateStockInDTO);
        return ResponseUtils.getResponse(success, ResponseUtils.ResultType.ACCEPTED);
    }
    
    /**
     * 生成库存
     *
     * @param storeDTO 生成库存的信息
     * @return 生成库存操作结果
     */
    @ApiOperation(value = "生成库存")
    @PostMapping("/store")
    public String store(@RequestBody StoreDTO storeDTO) {
        boolean success = stockInService.store(storeDTO);
        return ResponseUtils.getResponse(success, ResponseUtils.ResultType.ACCEPTED);
    }
    
    /**
     * 根据入库单id查询入库单详细信息
     *
     * @param id 入库单id
     * @return 入库单详细信息，包含入库单信息以及关联的入库明细信息
     */
    @ApiOperation(value = "根据入库单id查询入库单详细信息")
    @GetMapping("/getStockInInfo/{id}")
    public String getStockInInfo(@PathVariable("id") Long id) {
        StockInInfo stockInInfo = stockInService.getStockInInfo(id);
        return ResponseUtils.getResponse(stockInInfo, ResponseUtils.ResultType.ACCEPTED);
    }
}

