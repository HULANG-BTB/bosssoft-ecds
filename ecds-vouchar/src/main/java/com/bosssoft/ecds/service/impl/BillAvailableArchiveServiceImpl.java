package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillAvailableArchiveDao;
import com.bosssoft.ecds.entity.dto.BillAvailableInfoDTO;
import com.bosssoft.ecds.entity.po.BillAvailableArchivePO;
import com.bosssoft.ecds.service.BillAvailableArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuke
 * @since 2020-08-13
 */
@Service
@Slf4j
public class BillAvailableArchiveServiceImpl extends ServiceImpl<BillAvailableArchiveDao, BillAvailableArchivePO> implements BillAvailableArchiveService {

    @Autowired
    private BillAvailableArchiveDao billAvailableArchiveDao;

    @Override
    public List<BillAvailableInfoDTO> getBillApplyInfos(String agenCode) {
        LambdaQueryWrapper<BillAvailableArchivePO> lambdaQuery = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<BillAvailableArchivePO> eq = lambdaQuery.eq(BillAvailableArchivePO::getAgenCode, agenCode);
        List<BillAvailableArchivePO> list = list(eq);
        List<BillAvailableInfoDTO> res = new ArrayList<>();
        list.forEach(
                po -> {
                    BillAvailableInfoDTO dto = BeanUtil.toBean(po, BillAvailableInfoDTO.class);
                    res.add(dto);
                }
        );
        return res;
    }

    @Override
    public void finaBillApplyArchive() {
        /*获取所有单位一天之内的票据申领信息*/
        List<BillAvailableInfoDTO> billAvailableInfoDTOS = billAvailableArchiveDao.collectBillAvailableInfo();
        List<BillAvailableArchivePO> billAvailableArchivePOS = new ArrayList<>();

        billAvailableInfoDTOS.forEach(
                dto -> {
                    BillAvailableArchivePO po = BeanUtil.toBean(dto, BillAvailableArchivePO.class);
                    /*
                     * 初始化数据
                     */
                    po.setVersion(0);
                    po.setLogicDelete(false);
                    billAvailableArchivePOS.add(po);
                }
        );
        /*批量插入*/
        saveBatch(billAvailableArchivePOS);
    }

}
