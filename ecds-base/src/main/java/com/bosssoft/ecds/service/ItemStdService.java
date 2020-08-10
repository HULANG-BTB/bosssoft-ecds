package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.ItemStdDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemStdPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
public interface ItemStdService extends IService<ItemStdPO> {
    /**
     * 插入项目标准
     *
     * @param itemStdDTO
     * @return boolean
     */
    boolean save(ItemStdDTO itemStdDTO);

    /**
     * 修改项目标准信息
     *
     * @param itemStdDTO
     * @return boolean
     */
    boolean update(ItemStdDTO itemStdDTO);

    /**
     * 删除项目标准信息
     *
     * @param itemStdDTO
     * @return boolean
     */
    boolean delete(ItemStdDTO itemStdDTO);

    /**
     * 分页查询项目标准信息
     *
     * @param  pageDTO
     * @return PageVO
     */
    PageVO listByPage(PageDTO<ItemStdDTO> pageDTO);

    /**
     * 批量删除项目标准信息
     *
     * @param itemStdDTOS
     * @return boolean
     */
    boolean batchdelete(List<ItemStdDTO> itemStdDTOS);

}
