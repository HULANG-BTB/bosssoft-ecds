package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.BillAvailableArchiveDao;
import com.bosssoft.ecds.entity.dto.BillApplyDTO;
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
    public List<BillApplyDTO> getBillApplyInfos() {
        return null;
    }

    @Override
    public void finaBillApplyArchive() {
        List<BillAvailableInfoDto> res = new ArrayList<>();

        /*
         * 获取所有单位一天之内的票据申领信息
         */
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<>();
        wrapper.apply("date_format(f_create_time, '%Y-%m-%d') = date_format(date_sub(SYSDATE(), interval 1 day), '%Y-%m-%d')");
        List<AgenBillPO> list = agenBillService.list(wrapper);

        /*
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

        /*
         * 信息处理
         */
        for (int i = 0; i < res.size(); i++) {
            BillAvailableInfoDto tempDto = res.get(i);
            LambdaQueryWrapper<WriteoffBillsummaryPO> lqw = new LambdaQueryWrapper<>();
            LambdaQueryWrapper<WriteoffBillsummaryPO> eq = lqw
                    .eq(WriteoffBillsummaryPO::getBillCode, tempDto.getBillCode())
                    .apply("date_format(f_create_time, '%Y-%m-%d') = date_format(date_sub(SYSDATE(), interval 1 day), '%Y-%m-%d')");
            WriteoffBillsummaryPO one = writeoffBillsummaryService.getOne(eq);
            BeanUtil.copyProperties(one, tempDto);
            res.set(i, tempDto);
        }

        /*
         * 批量插入 申领信息归档
         * 如果直接使用po  利用hutool复制值会出现时间的覆盖
         * //使用po自动注入时间  不能使用xml文件写入时间
         */
        res.forEach(
                temp -> {
                    BillAvailableArchivePO po = new BillAvailableArchivePO();
                    BeanUtil.copyProperties(temp, po);
                    /*
                     * 初始化数据
                     */
                    po.setVersion(1);
                    po.setLogicDelete(false);
                    /*
                        插入数据
                     */
                    billAvailableArchiveDao.insert(po);
                }
        );
    }

    /**
     * 单位端
     */
    @Override
    public BillApplyDTO getBillApplyInfo(String agenCode) {
        return null;
    }

    @Override
    public void unitBillApplyArchive() {

    }
}
