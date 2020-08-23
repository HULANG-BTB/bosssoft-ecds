package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.StockInPO;
import com.bosssoft.ecds.entity.vo.CurrentBillNumberVO;
import com.bosssoft.ecds.entity.vo.StockInInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cheng
 * @since 2020-08-10
 */
public interface StockInService extends IService<StockInPO> {
    /**
     * 获取当前业务号
     *
     * @return 最新的业务号
     */
    CurrentBillNumberVO getBillNumber();
    
    /**
     * 创建票据入库单
     *
     * @param addStockInDTO 票据入库单
     * @return 操作结果
     */
    boolean save(AddStockInDTO addStockInDTO);
    
    /**
     * 修改审核状态
     *
     * @param stockChangeDTO 审核数据
     * @return 修改审核状态结果
     */
    boolean change(StockChangeDTO stockChangeDTO);
    
    /**
     * 删除入库单
     *
     * @param removeStockInDTO 传入的删除数据
     * @return 删除操作结果
     */
    boolean removeById(RemoveStockInDTO removeStockInDTO);
    
    /**
     * 修改入库单
     *
     * @param updateStockInDTO 传入的修改数据
     * @return 修改操作结果
     */
    boolean update(UpdateStockInDTO updateStockInDTO);
    
    /**
     * 入库方法
     *
     * @param storeDTO 入库DTO
     * @return 操作结果
     */
    boolean store(StoreDTO storeDTO);
    
    /**
     * 分页查询入库单
     *
     * @param stockInPageDTO 查询条件
     * @return 包含分页信息的入库单列表对象
     */
    PageResult listVOPage(StockInPageDTO stockInPageDTO);
    
    /**
     * 根据id查询入库单详细信息
     *
     * @param id
     * @return
     */
    StockInInfo getStockInInfo(Long id);
}
