package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bosssoft.ecds.entity.vo.ItemBillVO;

public interface FabItemBillService {
    /**
     * 根据票据种类查询与之关联的项目,分页查询
     *
     * @param pagenum
     * @param pagesize
     * @param billCode 票据种类编码
     * @return 返回存了一页的ItemBillVO
     */
    IPage<ItemBillVO> selectItemByBillCode(Integer pagenum, Integer pagesize, String billCode);

    /**
     * 将项目与票据种类进行关联
     *
     * @param billCode 票据种类编码
     * @param itemId   项目id
     * @return 操作是否成功
     */
    boolean insertIntoItemBill(String billCode, String itemId);


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
     * @param billCode 票据种类编码
     * @param itemId   项目id
     * @return true 有关联  false 无关联
     */

    boolean checkItemBill(String billCode, String itemId);
}
