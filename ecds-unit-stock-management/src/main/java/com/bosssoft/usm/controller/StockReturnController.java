package com.bosssoft.usm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.usm.config.StatusCode;
import com.bosssoft.usm.entity.po.StockReturnPO;
import com.bosssoft.usm.entity.vo.DateVO;
import com.bosssoft.usm.entity.vo.PageVO;
import com.bosssoft.usm.service.StockReturnService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 类功能描述：退票主表
 * @author ZhuWen
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/stock-return-po")
public class StockReturnController {

    @Autowired
    StockReturnService stockReturnService;

    /**
     * 根据id查询主表记录
     * @param id
     * @return
     */
    @GetMapping("/getInfo")
    public StockReturnPO getSrockReturnPOById(@RequestBody String id) {
        return stockReturnService.getById(id);
    }

    /**
     * 根据业务单号查询退票信息
     * @param no 业务单位
     * @return
     */
    @GetMapping("/getListMap")
    public Collection<StockReturnPO> getListMap(@RequestParam String no) {
        Map<String,Object> map = new HashMap<>();
        //kay是字段名 value是字段值
        map.put("f_no",no);
        Collection<StockReturnPO> stockReturnPOList = stockReturnService.listByMap(map);
        return stockReturnPOList;
    }

    /**
     * 用条件构造器根据编制日期查询退票信息
     * @param stockReturnPO
     * @return
     */
    @GetMapping("/getListByNoWrap")
    public List<StockReturnPO> getListByWrap(@RequestBody StockReturnPO stockReturnPO) {
        QueryWrapper<StockReturnPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_no",stockReturnPO.getNo());
        List<StockReturnPO> list = stockReturnService.list(queryWrapper);
        return list;
    }

    /**
     * 根据编制日期查询退票信息
     * @param dateVO 日期VO
     * @return
     */
    @GetMapping("/getByDate")
    public List<StockReturnPO> getStockReturnPOByDate(@RequestBody DateVO dateVO) {
        QueryWrapper<StockReturnPO> queryWrapper = new  QueryWrapper<StockReturnPO>();
        queryWrapper.lambda().gt(StockReturnPO::getDate,dateVO.getStartTime());
        queryWrapper.lambda().le(StockReturnPO::getDate,dateVO.getEndTime());
        List<StockReturnPO> listStockReturnPO = stockReturnService.list(queryWrapper);
        return listStockReturnPO;
    }


    /**
     * 查询所有退票主信息
     * @return
     */
    @GetMapping("/getList")
    public List<StockReturnPO> getStockReturnList(){
        List<StockReturnPO> list = stockReturnService.list();
        return list;
    }

    /**
     * 分页查询表中的所有信息
     * @param pageVO
     * @return
     */
    @GetMapping("/getPageList")
    public IPage<StockReturnPO> getPageStockReturnList(@RequestBody PageVO pageVO){
        IPage<StockReturnPO> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        page = stockReturnService.page(page);
        return page;
    }

    /**
     *新增退票主表信息
     * @param stockReturnPO
     */
    @PostMapping("/addInfo")
    public String addStockReturnPO(@RequestBody StockReturnPO stockReturnPO){
        boolean status = stockReturnService.save(stockReturnPO);
        if(status == true){
            return StatusCode.ADD_SUCCESS;
        } else {
            return StatusCode.ADD_FAILED;
        }
    }

    /**
     * 批量新增退票主表信息
     * @param listStockReturnPO
     */
    @PostMapping("/addBatchInfo")
    public String addBatchStockReturnPO(@RequestBody List<StockReturnPO> listStockReturnPO) {
        boolean status = stockReturnService.saveBatch(listStockReturnPO);
        if(status == true){
            return StatusCode.ADD_SUCCESS;
        } else {
            return StatusCode.ADD_FAILED;
        }
    }

    /**
     * 根据id更新退票主表信息
     * @param stockReturnPO
     * @return
     */
    @PutMapping("/updateInfo")
    public String updateStockReturnPO(@RequestBody StockReturnPO stockReturnPO) {
        boolean status = stockReturnService.updateById(stockReturnPO);
        if(status == true){
            return StatusCode.UPDATE_SUCCESS;
        } else {
            return StatusCode.UPDATE_FAILED;
        }
    }

    /**
     * 根据id删除信息
     * @param stockReturnPO
     * @return
     */
    @PostMapping("/deleteInfo")
    public String deleteById(@RequestBody StockReturnPO stockReturnPO) {
        boolean status = stockReturnService.removeById(stockReturnPO.getId());
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
       boolean status = stockReturnService.removeByIds(ids);
       if(status == true){
           return StatusCode.DELETE_SUCCESS;
       } else {
           return StatusCode.DELETE_FAILED;
       }
    }

    /**
     * 根据业务单号删除信息
     * @param no
     * @return
     */
    @PostMapping("/deleteInfoWrap")
    public String deleteByProperty(@RequestParam String no){
        QueryWrapper<StockReturnPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_no",no);
        boolean status = stockReturnService.remove(queryWrapper);
        if(status == true){
            return StatusCode.DELETE_SUCCESS;
        } else {
            return StatusCode.DELETE_FAILED;
        }
    }

}

