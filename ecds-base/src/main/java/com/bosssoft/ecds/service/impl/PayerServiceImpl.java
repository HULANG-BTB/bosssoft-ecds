package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PayerDTO;
import com.bosssoft.ecds.entity.po.PayerPO;
import com.bosssoft.ecds.dao.PayerDao;
import com.bosssoft.ecds.service.PayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AloneH
 * @since 2020-08-10
 */
@Service
public class PayerServiceImpl extends ServiceImpl<PayerDao, PayerPO> implements PayerService {

    /**
     * 保存接口
     *
     * @param payerDTO
     * @return
     */
    @Override
    public PayerDTO save(PayerDTO payerDTO) {
        PayerPO payerPO = new PayerPO();
        MyBeanUtil.copyProperties(payerDTO, payerPO);
        boolean saveResult = super.save(payerPO);
        if (saveResult) {
            payerDTO.setId(payerPO.getId());
        }
        return payerDTO;
    }

    /**
     * 删除数据
     *
     * @param payerDTO
     * @return
     */
    @Override
    public Boolean remove(PayerDTO payerDTO) {
        PayerPO payerPO = MyBeanUtil.copyProperties(payerDTO, PayerPO.class);
        return super.removeById(payerPO.getId());
    }

    /**
     * 修改数据
     *
     * @param payerDTO
     * @return
     */
    @Override
    public Boolean update(PayerDTO payerDTO) {
        PayerPO payerPO = MyBeanUtil.copyProperties(payerDTO, PayerPO.class);
        return super.updateById(payerPO);
    }

    /**
     * 通过ID查询
     *
     * @param payerDTO
     * @return
     */
    @Override
    public PayerDTO getById(PayerDTO payerDTO) {
        PayerPO payerPO = MyBeanUtil.copyProperties(payerDTO, PayerPO.class);
        payerPO = super.getById(payerPO.getId());
        payerDTO = MyBeanUtil.copyProperties(payerPO, PayerDTO.class);
        return payerDTO;
    }

    /**
     * 分页查询
     *
     * @param pageDTO
     * @return
     */
    @Override
    public PageDTO<PayerDTO> listByPage(PageDTO<PayerDTO> pageDTO) {
        Page<PayerPO> payerPOPage = new Page<>();
        // 设置分页信息
        payerPOPage.setCurrent(pageDTO.getPage());
        payerPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<PayerPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(PayerPO.F_PAYER_NAME, pageDTO.getKeyword())
                .or()
                .like(PayerPO.F_TEL, pageDTO.getKeyword())
                .or()
                .like(PayerPO.F_EMAIL, pageDTO.getKeyword())
                .or()
                .like(PayerPO.F_PAYER_ACCTNAME, pageDTO.getKeyword())
                .or()
                .like(PayerPO.F_PAYER_BANKNAME, pageDTO.getKeyword());

        queryWrapper.orderByAsc(PayerPO.F_CREATE_TIME);
        // 读取分页数据
        payerPOPage = super.page(payerPOPage, queryWrapper);
        List<PayerPO> records = payerPOPage.getRecords();
        // 转换数据
        List<PayerDTO> payerDTOList = MyBeanUtil.copyListProperties(records, PayerDTO::new);
        pageDTO.setTotal(payerPOPage.getTotal());
        pageDTO.setItems(payerDTOList);
        return pageDTO;
    }

    /**
     * 批量查询
     *
     * @param payerDTOList
     * @return
     */
    @Override
    public Boolean removeBatch(List<PayerDTO> payerDTOList) {
        List<Long> ids = new ArrayList<>();
        for (PayerDTO payerDTO : payerDTOList) {
            if (!payerDTO.getId().equals(0L)) {
                ids.add(payerDTO.getId());
            }
        }
        return super.removeByIds(ids);
    }

}
