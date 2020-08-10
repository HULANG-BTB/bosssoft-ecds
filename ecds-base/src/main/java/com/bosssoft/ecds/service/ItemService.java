package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
public interface ItemService extends IService<ItemPO> {
    /**
     * 插入项目
     *
     * @param itemDTO
     * @return boolean
     */
    boolean save(ItemDTO itemDTO);

    /**
     * 修改项目信息
     *
     * @param itemDTO
     * @return boolean
     */
    boolean update(ItemDTO itemDTO);

    /**
     * 删除项目信息
     *
     * @param itemDTO
     * @return boolean
     */
    boolean delete(ItemDTO itemDTO);

    /**
     * 分页查询项目信息
     *
     * @param  pageDTO
     * @return PageVO
     */
    PageVO listByPage(PageDTO<ItemDTO> pageDTO);

    /**
     * 批量删除项目信息
     *
     * @param itemDTOS
     * @return boolean
     */
    boolean batchdelete(List<ItemDTO> itemDTOS);

}
