package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
public interface ItemService extends IService<ItemPO> {
    /**
     * 插入项目
     * @param itemDTO
     * @return
     */
    int save(ItemDTO itemDTO);
    int update(ItemDTO itemDTO);
    int delete(ItemDTO itemDTO);
    PageVO listByPage(PageDTO<ItemDTO> pageDTO);

}
