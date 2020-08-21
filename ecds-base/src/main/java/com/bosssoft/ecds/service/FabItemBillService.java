package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.vo.*;

public interface FabItemBillService {

    /**
     * 根据票据种类查询与之关联的项目,分页查询,项目名称模糊查询
     *
     * @param selectItemVO pagenum 请求的页数 pagesize 页面大小 billCode 票据种类编码 itemName 项目名称
     * @return 返回存了一页的ItemBillVO
     */
    IPage<ItemBillVO> selectItemWithBillCode(SelectItemVO selectItemVO);

    /**
     * 将项目与票据种类进行关联
     *
     * @param itemsInsertVO 包含 billCode 票据种类编码  itemId   项目id
     * @return 操作是否成功
     */
    boolean insertIntoItemBill(BillItemsInsertVO itemsInsertVO);

    /**
     * 批量将项目与票据种类进行关联
     *
     * @param billItemsVO 票据种类编码 存储项目id的List
     * @return 操作是否成功
     */
    boolean insertBatchItemBill(BillItemsVO billItemsVO);


    /**
     * 取消项目与票据种类之间的关联
     *
     * @param id 关联表主键
     * @return 操作是否成功
     */
    boolean deleteFromItemBill(Long id);

    /**
     * 调整票据种类与项目之间关联是否启用的状态
     *
     * @param id 关联表主键
     * @return 返回最后的是否启用的状态
     */
    boolean turnEnabled(Long id);

    /**
     * 项目与票据种类是否有关联
     *
     * @param itemsInsertVO 包含 billCode 票据种类编码 itemId   项目id
     * @return true 有关联  false 无关联
     */
    boolean checkItemBill(BillItemsInsertVO itemsInsertVO);

    /**
     * 查询与项目种类无关的项目
     *
     * @param selectItemVO
     * @return
     */
    IPage selectNoContactItem(SelectItemVO selectItemVO);
}
