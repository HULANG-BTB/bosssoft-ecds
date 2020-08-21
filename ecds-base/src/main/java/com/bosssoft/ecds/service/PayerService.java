package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PayerDTO;
import com.bosssoft.ecds.entity.po.PayerPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AloneH
 * @since 2020-08-10
 */
public interface PayerService extends IService<PayerPO> {

    /**
     * 保存接口
     *
     * @param payerDTO
     * @return
     */
    PayerDTO save(PayerDTO payerDTO);

    /**
     * 删除数据
     *
     * @param payerDTO
     * @return
     */
    Boolean remove(PayerDTO payerDTO);

    /**
     * 修改数据
     *
     * @param payerDTO
     * @return
     */
    Boolean update(PayerDTO payerDTO);

    /**
     * 通过ID查询
     *
     * @param payerDTO
     * @return
     */
    PayerDTO getById(PayerDTO payerDTO);

    /**
     * 分页查询
     *
     * @param pageDTO
     * @return
     */
    PageDTO<PayerDTO> listByPage(PageDTO<PayerDTO> pageDTO);

    /**
     * 批量查询
     *
     * @param payerDTOList
     * @return
     */
    Boolean removeBatch(List<PayerDTO> payerDTOList);

}
