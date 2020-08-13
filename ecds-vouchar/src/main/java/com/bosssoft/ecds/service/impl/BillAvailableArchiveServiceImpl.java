package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillAvailableArchiveDao;
import com.bosssoft.ecds.entity.dto.BillApplyDto;
import com.bosssoft.ecds.entity.dto.BillAvailableInfoDto;
import com.bosssoft.ecds.entity.po.AgenBillPO;
import com.bosssoft.ecds.entity.po.BillAvailableArchivePO;
import com.bosssoft.ecds.entity.po.WriteoffBillsummaryPO;
import com.bosssoft.ecds.service.AgenBillService;
import com.bosssoft.ecds.service.BillAvailableArchiveService;
import com.bosssoft.ecds.service.WriteoffBillsummaryService;
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
    private AgenBillService agenBillService;
    /**
     * 审验单服务
     */
    @Autowired
    private WriteoffBillsummaryService writeoffBillsummaryService;

    @Autowired
    private BillAvailableArchiveDao billAvailableArchiveDao;

    /**
     * 财政端
     */

    @Override
    public List<BillApplyDto> getBillApplyInfos() {
        return null;
    }

    @Override
    public void finaBillApplyArchive() {
        List<BillAvailableInfoDto> res = new ArrayList<>();

        /**
         * 获取所有单位的票据申领信息
         */
        List<AgenBillPO> list = agenBillService.list();

        /**
         * 信息初步转换
         */
        list.forEach(
                temp -> {
                    BillAvailableInfoDto dto = new BillAvailableInfoDto();
                    dto.setAgenCode(temp.getAgenIdcode());
                    dto.setBillCode(temp.getTypeCode());
                    res.add(dto);
                }
        );

        /**
         * 信息处理
         */
        for (int i = 0; i < res.size(); i++) {
            BillAvailableInfoDto tempDto = res.get(i);
            LambdaQueryWrapper<WriteoffBillsummaryPO> lqw = new LambdaQueryWrapper<>();
            LambdaQueryWrapper<WriteoffBillsummaryPO> eq = lqw.eq(WriteoffBillsummaryPO::getBillCode, tempDto.getBillCode());
            WriteoffBillsummaryPO one = writeoffBillsummaryService.getOne(eq);
            BeanUtil.copyProperties(one, tempDto);
            res.set(i, tempDto);
        }

        /**
         * 批量插入 申领信息归档
         * 如果直接使用po  利用hutool复制值会出现时间的覆盖
         * //使用po自动注入时间  不能使用xml文件写入时间
         * /*boolean b = billAvailableArchiveDao.insertBatch(billAvailableArchivePOS);
         *         log.info("result: "+b);
         */
        List<BillAvailableArchivePO> billAvailableArchivePOS = new ArrayList<>();
        res.forEach(
                temp -> {
                    BillAvailableArchivePO po = new BillAvailableArchivePO();
                    BeanUtil.copyProperties(temp, po);
                    /**
                     * 初始化数据
                     */
                    po.setVersion(1);
                    po.setLogicDelete(false);
                    billAvailableArchivePOS.add(po);
                }
        );
        billAvailableArchivePOS.forEach(
                item -> billAvailableArchiveDao.insert(item)
        );
    }

    /**
     * 单位端
     */
    @Override
    public BillApplyDto getBillApplyInfo(String agenCode) {
        return null;
    }

    @Override
    public void unitBillApplyArchive() {

    }
}
