package com.bosssoft.usm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.usm.config.StatusCode;
import com.bosssoft.usm.entity.po.StockReturnItemPO;
import com.bosssoft.usm.entity.po.StockReturnPO;
import com.bosssoft.usm.entity.vo.PageVO;
import com.bosssoft.usm.service.StockReturnItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 类功能描述：退票明细
 * @author ZhuWen
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/stock-return-item-po")
public class StockReturnItemController {

    @Autowired
    private StockReturnItemService stockReturnItemService;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/getInfo")
    public StockReturnItemPO getSrockReturnItemPOById(Long id) {
        return stockReturnItemService.getById(id);
    }

    /**
     * 根据序号查询
     * @param stockReturnItemPO
     * @return
     */
    @GetMapping("/getListByWrap")
    public List<StockReturnItemPO> getListByWrap(@RequestParam StockReturnItemPO stockReturnItemPO) {
        QueryWrapper<StockReturnItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_no",stockReturnItemPO.getNo());
        List<StockReturnItemPO> list = stockReturnItemService.list(queryWrapper);
        return list;
    }

    /**
     * 添加
     * @param stockReturnItemPO
     * @return
     */
    @PostMapping("/addInfo")
    public String addStockReturnItemPO(@RequestBody StockReturnItemPO stockReturnItemPO){
        boolean status = stockReturnItemService.save(stockReturnItemPO);
        if(status == true){
            return StatusCode.ADD_SUCCESS;
        } else {
            return StatusCode.ADD_FAILED;
        }
    }

    /**
     * 查询所有退票明细信息
     * @return
     */
    @GetMapping("/getList")
    public List<StockReturnItemPO> getStockReturnList(){
        List<StockReturnItemPO> list = stockReturnItemService.list();
        return list;
    }

    /**
     * 分页查询表中的所有信息
     * @param pageVO
     * @return
     */
    @GetMapping("/getPageList")
    public IPage<StockReturnItemPO> getPageStockReturnList(@RequestBody PageVO pageVO){
        IPage<StockReturnItemPO> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        page = stockReturnItemService.page(page);
        return page;
    }

    /**
     * 批量新增退票主表信息
     * @param listStockReturnItemPO
     */
    @PostMapping("/addBatchInfo")
    public String addBatchStockReturnPO(@RequestBody List<StockReturnItemPO> listStockReturnItemPO) {
        boolean status = stockReturnItemService.saveBatch(listStockReturnItemPO);
        if(status == true){
            return StatusCode.ADD_SUCCESS;
        } else {
            return StatusCode.ADD_FAILED;
        }
    }

    /**
     * 根据id更新退票主表信息
     * @param stockReturnItemPO
     * @return
     */
    @PutMapping("/updateInfo")
    public String updateStockReturnPO(@RequestBody StockReturnItemPO stockReturnItemPO) {
        boolean status = stockReturnItemService.updateById(stockReturnItemPO);
        if(status == true){
            return StatusCode.UPDATE_SUCCESS;
        } else {
            return StatusCode.UPDATE_FAILED;
        }
    }

    /**
     * 根据id删除信息
     * @param stockReturnItemPO
     * @return
     */
    @PostMapping("/deleteInfo")
    public String deleteById(@RequestBody StockReturnPO stockReturnItemPO) {
        boolean status = stockReturnItemService.removeById(stockReturnItemPO.getId());
        if(status == true){
            return StatusCode.DELETE_SUCCESS;
        } else {
            return StatusCode.DELETE_FAILED;
        }
    }

    /**
     * 根据id批量删除信息
     * @param stockReturnPOS
     * @return
     */
    @PostMapping("/deleteInfos")
    public String deleteByIds(@RequestBody List<StockReturnPO> stockReturnPOS) {
        List<Long> ids = new ArrayList<>();
        for(int i=0; i<stockReturnPOS.size(); i++) {
            ids.add(stockReturnPOS.get(i).getId());
        }
        boolean status = stockReturnItemService.removeByIds(ids);
        if(status == true){
            return StatusCode.DELETE_SUCCESS;
        } else {
            return StatusCode.DELETE_FAILED;
        }
    }

    /**
     * 根据序号删除信息
     * @param no
     * @return
     */
    @DeleteMapping("/deleteInfoWrap")
    public String deleteByProperty(@RequestParam String no){
        QueryWrapper<StockReturnItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_no",no);
        boolean status = stockReturnItemService.remove(queryWrapper);
        if(status == true){
            return StatusCode.DELETE_SUCCESS;
        } else {
            return StatusCode.DELETE_FAILED;
        }
    }



}

