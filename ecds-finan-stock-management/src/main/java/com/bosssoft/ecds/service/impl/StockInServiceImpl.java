package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.StockInDao;
import com.bosssoft.ecds.entity.constant.StockInChangeConstant;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.StockInChangePO;
import com.bosssoft.ecds.entity.po.StockInItemPO;
import com.bosssoft.ecds.entity.po.StockInPO;
import com.bosssoft.ecds.entity.vo.CurrentBillNumberVO;
import com.bosssoft.ecds.entity.vo.StockInForChangeVO;
import com.bosssoft.ecds.service.StockInChangeService;
import com.bosssoft.ecds.service.StockInItemService;
import com.bosssoft.ecds.service.StockInService;
import com.bosssoft.ecds.utils.SnowFlakeUtils;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cheng
 * @since 2020-08-10
 */
@Service
@Slf4j
public class StockInServiceImpl extends ServiceImpl<StockInDao, StockInPO> implements StockInService {
    @Autowired
    private StockInChangeService stockInChangeService;
    
    @Autowired
    private StockInItemService stockInItemService;
    
    /**
     * 获取当前业务号
     *
     * @return 最新的业务号
     */
    @Override
    public CurrentBillNumberVO getBillNumber() {
        CurrentBillNumberVO currentBillNumberVO = new CurrentBillNumberVO();
        Long id = SnowFlakeUtils.genId();
        currentBillNumberVO.setNo(id);
        currentBillNumberVO.setNoString("NO." + id);
        return currentBillNumberVO;
    }
    
    /**
     * 获取未审核列表
     *
     * @return 未审核列表
     */
    @Override
    public List<StockInForChangeVO> listUnChange() {
        List<StockInForChangeVO> stockInForChangeVOS = new ArrayList<>();
        StockInPO stockInPO = new StockInPO();
        
        // 设置查询条件为未审核
        stockInPO.setChangeState(StockInChangeConstant.UN_CHANGE);
        List<StockInPO> stockInPOList = list(new QueryWrapper<>(stockInPO));
        
        // 如果未审核的列表不为空，封装为对应的VO列表
        if (stockInPOList.size() > 0) {
            stockInPOList.forEach(item -> {
                StockInForChangeVO stockInForChangeVO = new StockInForChangeVO();
                BeanUtils.copyProperties(item, stockInForChangeVO);
                stockInForChangeVOS.add(stockInForChangeVO);
            });
        }
        return stockInForChangeVOS;
    }
    
    /**
     * 创建票据入库单
     *
     * @param addStockInDTO 票据入库单
     * @return 操作结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(AddStockInDTO addStockInDTO) {
        if (addStockInDTO != null) {
            AddStockInItemDTO[] addStockInItemDTOArray = addStockInDTO.getAddStockInItemDTOArray();
            // 1.写入入库明细表
            if (addStockInItemDTOArray != null) {
                List<StockInItemPO> stockInItemPOS = new ArrayList<>();
                for (AddStockInItemDTO addStockInItemDTO : addStockInItemDTOArray) {
                    StockInItemPO stockInItemPO = new StockInItemPO();
                    BeanUtils.copyProperties(addStockInItemDTO, stockInItemPO);
                    stockInItemPO.setPid(addStockInDTO.getNo());
                    stockInItemPOS.add(stockInItemPO);
                }
                stockInItemService.saveBatch(stockInItemPOS);
                
                // 2.入库单写入数据库
                StockInPO stockInPO = new StockInPO();
                BeanUtils.copyProperties(addStockInDTO, stockInPO);
                
                // 入库单ID与业务号相同
                stockInPO.setId(addStockInDTO.getNo());
                stockInPO.setDate(new Date());
                stockInPO.setChangeState(StockInChangeConstant.UN_CHANGE);
                log.debug(stockInPO.toString());
                save(stockInPO);
                
                // 3.新增入库变动
                StockInChangePO stockInChangePO = new StockInChangePO();
                stockInChangePO.setChangeDate(new Date());
                stockInChangePO.setBussId(addStockInDTO.getNo());
                // 变动类型为增加
                stockInChangePO.setAltercode(StockInChangeConstant.ADD);
                // 新增入库变动同入库单编制人
                stockInChangePO.setChangeMan(addStockInDTO.getAuthor());
                stockInChangeService.save(stockInChangePO);
                return true;
            }
            
        }
        return false;
    }
    
    /**
     * 修改审核状态
     *
     * @param stockChangeDTO 审核数据
     * @return 修改审核状态结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean change(StockChangeDTO stockChangeDTO) {
        if (stockChangeDTO != null && stockChangeDTO.getId() != null) {
            // 如果是审核退回则审核意见必填
            boolean unPassCheck = stockChangeDTO.getChangeState().equals(StockInChangeConstant.UN_PASS) &&
                    Strings.isNullOrEmpty(stockChangeDTO.getChangeSitu());
            if (unPassCheck) {
                return false;
            }
            StockInPO stockInPO = getById(stockChangeDTO.getId());
            BeanUtils.copyProperties(stockChangeDTO, stockInPO);
            // 返回更新操作的结果
            return updateById(stockInPO);
        }
        return false;
    }
    
    /**
     * 删除入库单
     *
     * @param removeStockInDTO 传入的DTO
     * @return 操作结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(RemoveStockInDTO removeStockInDTO) {
        if (removeStockInDTO == null) {
            return false;
        }
        
        // 1.删除入库单
        boolean remove = removeById(removeStockInDTO.getId());
        
        // 2.删除入库明细
        removeStockInItemByPid(removeStockInDTO.getId());
        
        // 3.新增入库单删除变动
        StockInChangePO stockInChangePO = new StockInChangePO();
        stockInChangePO.setChangeMan(removeStockInDTO.getChangeMan());
        stockInChangePO.setAltercode(StockInChangeConstant.DELETE);
        stockInChangePO.setBussId(removeStockInDTO.getId());
        stockInChangePO.setChangeDate(new Date());
        boolean save = stockInChangeService.save(stockInChangePO);
        return remove && save;
    }
    
    /**
     * 修改入库单
     *
     * @param updateStockInDTO 传入的修改数据
     * @return 修改操作结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateStockInDTO updateStockInDTO) {
        boolean isLegal = (updateStockInDTO != null)
                && (updateStockInDTO.getNo() != null)
                && (updateStockInDTO.getAddStockInItemDTOArray() != null);
        if (isLegal) {
            // 1.删除原入库单的入库明细
            removeStockInItemByPid(updateStockInDTO.getNo());
            
            // 2.写入新的入库明细
            AddStockInItemDTO[] addStockInItemDTOArray = updateStockInDTO.getAddStockInItemDTOArray();
            saveAddStockInItemDTO(addStockInItemDTOArray, updateStockInDTO.getNo());
            
            // 3.新增入库变动
            StockInChangePO changePO = convert(updateStockInDTO, updateStockInDTO.getNo(), StockInChangeConstant.UPDATE);
            stockInChangeService.save(changePO);
            return true;
        }
        return false;
    }
    
    /**
     * 入库方法
     *
     * @param storeDTO 入库DTO
     * @return 操作结果
     */
    @Override
    public boolean store(StoreDTO storeDTO) {
        // TODO
        Long[][] longArray = convert2LongArray(storeDTO.getNo());
        
        return false;
    }
    
    /**
     * 转化为Long数组
     *
     * @param pid
     * @return
     */
    public Long[][] convert2LongArray(Long pid) {
        StockInItemPO stockInItemPO = new StockInItemPO();
        stockInItemPO.setPid(pid);
        List<StockInItemPO> stockInItemPOS = stockInItemService.list(new QueryWrapper<>(stockInItemPO));
        List<Long[]> longPairs = new ArrayList<>();
        stockInItemPOS.forEach(stockInItemPO1 -> {
            Long[] longPair = new Long[2];
            longPair[0] = Long.parseLong(stockInItemPO1.getBillNo1());
            longPair[1] = Long.parseLong(stockInItemPO1.getBillNo2());
            longPairs.add(longPair);
        });
        Long[][] result = new Long[longPairs.size()][2];
        return longPairs.toArray(result);
    }
    
    
    /**
     * 合并票据区间
     *
     * @param intervals
     * @return
     */
    public Long[][] merge(Long[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> (int) (v1[0] - v2[0]));
        // 遍历区间
        Long[][] res = new Long[intervals.length][2];
        int idx = -1;
        for (Long[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
    
    /**
     * 通过入库单ID逻辑删除相关的入库明细
     *
     * @param id 入库单ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean removeStockInItemByPid(Long id) {
        StockInItemPO stockInItemPO = new StockInItemPO();
        stockInItemPO.setPid(id);
        stockInItemService.remove(new QueryWrapper<>(stockInItemPO));
        return true;
    }
    
    /**
     * 使用DTO对象写入入库明细
     *
     * @param addStockInItemDTOArray DTO数组
     * @param pid                    入库单ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean saveAddStockInItemDTO(AddStockInItemDTO[] addStockInItemDTOArray, Long pid) {
        boolean isLegal = addStockInItemDTOArray != null && pid != null && addStockInItemDTOArray.length != 0;
        if (isLegal) {
            List<StockInItemPO> stockInItemPOS = new ArrayList<>();
            for (AddStockInItemDTO addStockInItemDTO : addStockInItemDTOArray) {
                StockInItemPO stockInItem = new StockInItemPO();
                BeanUtils.copyProperties(addStockInItemDTO, stockInItem);
                stockInItem.setPid(pid);
                stockInItemPOS.add(stockInItem);
            }
            return stockInItemService.saveBatch(stockInItemPOS);
        }
        return false;
    }
    
    /**
     * 转化为StockInChangePO对象
     *
     * @param stockInDTO 传入的DTO
     * @param id         操作业务号
     * @param alterCode  操作代码
     * @return 封装的StockInChangePO对象
     */
    private StockInChangePO convert(StockInDTO stockInDTO, Long id, int alterCode) {
        StockInChangePO stockInChangePO = new StockInChangePO();
        BeanUtils.copyProperties(stockInDTO, stockInChangePO);
        stockInChangePO.setChangeDate(new Date());
        stockInChangePO.setAltercode(alterCode);
        stockInChangePO.setBussId(id);
        return stockInChangePO;
    }

    
}
