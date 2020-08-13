package com.bosssoft.usm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.usm.config.StatusCode;
import com.bosssoft.usm.entity.po.StockReturnItemPO;
import com.bosssoft.usm.entity.po.StockReturnPO;
import com.bosssoft.usm.entity.vo.DateVO;
import com.bosssoft.usm.entity.vo.PageVO;
import com.bosssoft.usm.entity.vo.StockReturnItemVO;
import com.bosssoft.usm.entity.vo.StockReturnVO;
import com.bosssoft.usm.service.StockReturnItemService;
import com.bosssoft.usm.service.StockReturnService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@Slf4j
public class StockReturnController {

    @Autowired
    StockReturnService stockReturnService;

    @Autowired
    StockReturnItemService stockReturnItemService;

    /**
     * 根据id查询主表记录
     * @param id
     * @return
     */
    @GetMapping("/getInfo")
    public StockReturnPO getSrockReturnPOById(Long id) {
        StockReturnPO stockReturnPO = stockReturnService.getById(id);
        return stockReturnPO;
    }

    /**
     * 根据业务单号查询退票信息
     * @param no 业务单位
     * @return
     */
    @GetMapping("/getListMap")
    public Collection<StockReturnPO> getListMap(@RequestParam String no) {
        Map<String,Object> map = new HashMap<>();
        // kay是字段名 value是字段值
        map.put("f_no",no);
        Collection<StockReturnPO> stockReturnPOList = stockReturnService.listByMap(map);
        return stockReturnPOList;
    }

    /**
     * 用条件构造器根据业务单号查询退票信息
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
    @DeleteMapping("/deleteInfo")
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
    @DeleteMapping("/deleteInfos")
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
     * @param no 业务单号
     * @return
     */
    @DeleteMapping("/deleteInfoWrap")
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

    /**************************退票主表和退票明细表进行多表联合操作****************************/

    /**
     * 添加退票申请
     * @param stockReturnVO 退票VO
     * @return
     */
    @PostMapping("/addStockReturn")
    public String addStockRetrun(@RequestBody StockReturnVO stockReturnVO){
        String status = stockReturnService.addStockReturnPO(stockReturnVO);
        return status;
    }

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
    public List<StockReturnVO> getStockReturnListByDate(@RequestBody  DateVO dateVO) {
        log.info("时间："+dateVO.getStartTime().toString());
        return stockReturnService.stockReturnVOlistByDate(dateVO);
    }

    /**
     * 根据No修改退票主信息
     * @param stockReturnVO 退票信息VO
     * @return
     */
    @PutMapping("/updateByNo")
    public String updateByNo(@RequestBody StockReturnVO stockReturnVO) {
        return stockReturnService.updateStockReturnPO(stockReturnVO);
    }

    /**
     * 修改退票明细信息
     * @param stockReturnItemVOList 退票明细VO集合
     * @return
     */
    @PutMapping("/updateItemByNo")
    public String updateItemByNo(@RequestBody List<StockReturnItemVO> stockReturnItemVOList, Long no) {
        return stockReturnService.updateStockReturnItemPO(stockReturnItemVOList, no);
    }

    /**
     * 删除退票申请
     * @param no 业务单号
     * @return
     */
    @DeleteMapping("/deleteByNo")
    public String deleteByNo(Long no) {
        return stockReturnService.deleteStockReturn(no);
    }

    /**
     * 删除退票明细
     * @param no
     * @return
     */
    @DeleteMapping("/deleteItemByNo")
    public String deleteItemByNo(Long no) {
        QueryWrapper<StockReturnItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_pid",no);
        boolean status = stockReturnItemService.remove(queryWrapper);
        if(status == true){
            return StatusCode.DELETE_SUCCESS;
        } else {
            return StatusCode.DELETE_FAILED;
        }
    }

    /**
     * 确定是否提交 0未提交，1已经提交
     * @param no 业务单号
     * @return
     */
    @GetMapping("/getSubmitStatus")
    public Integer getSubmitStatus(Long no) {
       StockReturnPO stockReturnPO = stockReturnService.getOne(new QueryWrapper<StockReturnPO>().eq("f_no",no));
       return stockReturnPO.getSubmitStatus();
    }

    /**
     * 查询审核状态 0未审核，1已审核通过，2审核未通过，3无需审核
     * @param no 业务单号
     * @return
     */
    @PostMapping("/getCheckStatus")
    public Integer saveInfo(Long no) {
        StockReturnPO stockReturnPO = stockReturnService.getOne(new QueryWrapper<StockReturnPO>().eq("f_no",no));
        return stockReturnPO.getChangeState();
    }
}

