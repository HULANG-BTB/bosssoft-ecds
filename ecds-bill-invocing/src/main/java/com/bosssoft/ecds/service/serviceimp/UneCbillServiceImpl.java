package com.bosssoft.ecds.service.serviceimp;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.UneCbillItemMapper;
import com.bosssoft.ecds.dao.UneCbillMapper;
import com.bosssoft.ecds.entity.dto.BillItemDTO;
import com.bosssoft.ecds.entity.dto.NontaxBillDTO;
import com.bosssoft.ecds.entity.dto.UneCbillItemDto;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.entity.vo.UneCbillVo;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UneCbillServiceImpl implements UneCbillService {

    @Autowired
    private UneCbillMapper uneCbillMapper;

    @Autowired
    private UneCbillItemMapper uneCbillItemMapper;

    /**
     * 根据ID查找某张具体的票据信息
     * @param id
     * @return
     */
    @Override
    public UneCbill getUneCBillById(String id) {
        UneCbill uneCbill = uneCbillMapper.selectById(id);
        return uneCbill;
    }

    /**
     * 分页查询开票记录
     * 将查询到的PO
     * @param page
     * @return
     */
    @Override
    public IPage<UneCbillVo> selectUnecBillPage(Page<UneCbill> page) {
        List<UneCbill> cbillList = uneCbillMapper.selectPageVO(page);
        List<UneCbillVo> cbillVos = new ArrayList<>();
        /**
         * 对象类型转换
         */
        for (UneCbill uneCbill : cbillList) {
            UneCbillVo uneCbillVo = new UneCbillVo();
            BeanUtil.copyProperties(uneCbill, uneCbillVo);
            cbillVos.add(uneCbillVo);
        }
        long currentPage = page.getCurrent();
        long pageSize = page.getSize();
        long total = page.getTotal();
        Page<UneCbillVo> page1 = new Page<>(currentPage, pageSize, total);
        return page1.setRecords(cbillVos);
    }

    /**
     * 保存开票主表以及开票明细
     * @param uneCbill
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int addUneCbill(UneCbill uneCbill, List<UneCbillItemDto> itemDtos) {
        int i = 0;
        for (UneCbillItemDto itemDto : itemDtos) {
            UneCbillItem uneCbillItem = new UneCbillItem();
            BeanUtil.copyProperties(itemDto, uneCbillItem);
            uneCbillItem.setFPid(uneCbill.getFId());
            uneCbillItem.setFCreateTime(new Date());
            uneCbillItem.setFId(CommonUtil.generateID());
            uneCbillItem.setFSortNo(i++);
            uneCbillItem.setFStd(8888.00);
            uneCbillItem.setFVersion(0);
            uneCbillItem.setFUpdateTime(new Date());
            uneCbillItem.setFOperator("samuel");
            uneCbillItemMapper.insert(uneCbillItem);
        }
        return uneCbillMapper.insert(uneCbill);
    }

    @Override
    public int billCount() {
        return uneCbillMapper.billCount();
    }

    /**
     *根据票据ID和校验码查询一张数据
     * @param queryWrapper
     * @return
     */
    @Override
    public UneCbill getBillByIdAndCheckCode(QueryWrapper<UneCbill> queryWrapper) {
        return uneCbillMapper.selectOne(queryWrapper);
    }

    @Override
    public List<UneCbillItem> getItems(String billId) {
        QueryWrapper<UneCbillItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_pid", billId);
        return uneCbillItemMapper.selectList(queryWrapper);
    }

    /**
     * 根据电话和票据校验码查询bill
     * @param queryWrapper
     * @return
     */
    @Override
    public UneCbill getBillByTelAndCheckCode(QueryWrapper<UneCbill> queryWrapper) {
        return uneCbillMapper.selectOne(queryWrapper);
    }

    /**
     * 转换DTO
     * @param uneCbill
     * @return
     */
    @Override
    public NontaxBillDTO convert(UneCbill uneCbill) {
        NontaxBillDTO nontaxBillDTO = new NontaxBillDTO();
        nontaxBillDTO.setAddition("ok");
        nontaxBillDTO.setAgenName(uneCbill.getFPlaceName());
        nontaxBillDTO.setBillCode(uneCbill.getFBillId());
        nontaxBillDTO.setCheckCode(uneCbill.getFCheckCode());
        nontaxBillDTO.setDate(uneCbill.getFCreateTime().toString());
        nontaxBillDTO.setTotalAmount(String.valueOf(uneCbill.getFTotalAmt()));
        nontaxBillDTO.setTotalAmountCapital(CommonUtil.toChinese(String.valueOf(uneCbill.getFTotalAmt())));
        nontaxBillDTO.setChecker("xxxxluu");
        nontaxBillDTO.setPayee(String.valueOf(uneCbill.getFPayerType()));
        nontaxBillDTO.setPayerName(uneCbill.getFPayerName());
        nontaxBillDTO.setRemark(uneCbill.getFMemo());
        nontaxBillDTO.setSerialCode("100");
        return nontaxBillDTO;
    }

    /**
     * 转换DTO
     * @param uneCbillItem
     * @return
     */
    @Override
    public BillItemDTO convertToItem(UneCbillItem uneCbillItem) {
        BillItemDTO billItemDTO = new BillItemDTO();
        billItemDTO.setAmount(String.valueOf(uneCbillItem.getFAmt()));
        billItemDTO.setItemCode(uneCbillItem.getFItemCode());
        billItemDTO.setQuantity(String.valueOf(uneCbillItem.getFNumber()));
        billItemDTO.setItemName(uneCbillItem.getFItemName());
        billItemDTO.setUnits(uneCbillItem.getFUnits());
        billItemDTO.setStandardName("100");
        return billItemDTO;
    }
}
