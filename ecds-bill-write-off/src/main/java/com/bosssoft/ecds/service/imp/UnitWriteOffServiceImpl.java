package com.bosssoft.ecds.service.imp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.unit.WriteOffApplyIncomeMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyItemMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyMapper;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.WriteOffApplyIncomePO;
import com.bosssoft.ecds.entity.po.WriteOffApplyItemPO;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.service.UnitWriteOffService;
import com.bosssoft.ecds.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hjr
 */
@Service
public class UnitWriteOffServiceImpl implements UnitWriteOffService {
    private final WriteOffApplyMapper writeOffApplyMapper;
    private final WriteOffApplyItemMapper writeOffApplyItemMapper;
    private final WriteOffApplyIncomeMapper writeOffApplyIncomeMapper;
    private final BillService billService;

    @Autowired(required = false)
    public UnitWriteOffServiceImpl (WriteOffApplyMapper writeOffApplyMapper,
                                    WriteOffApplyItemMapper writeOffApplyItemMapper,
                                    WriteOffApplyIncomeMapper writeOffApplyIncomeMapper,
                                    BillService billService) {
        this.writeOffApplyMapper = writeOffApplyMapper;
        this.writeOffApplyItemMapper = writeOffApplyItemMapper;
        this.writeOffApplyIncomeMapper = writeOffApplyIncomeMapper;
        this.billService = billService;
    }

    @Override
    public IPage<WriteOffApplyDTO> selectApplyPage(UnitWriteOffApplyQueryInfoDTO queryInfoDTO) {
        Page<WriteOffApplyPO> page = new Page<>(queryInfoDTO.getPageNum(), queryInfoDTO.getPageSize());
        QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WriteOffApplyPO::getFAgenIdCode, queryInfoDTO.getAgenIdCode());
        if (queryInfoDTO.getNo() != null && !"".equals(queryInfoDTO.getNo())) {
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, queryInfoDTO.getNo());
        }
        if (queryInfoDTO.getStartDate() != null && queryInfoDTO.getEndDate()!= null) {
            queryWrapper.lambda().ge(WriteOffApplyPO::getFDate, queryInfoDTO.getStartDate());
            queryWrapper.lambda().le(WriteOffApplyPO::getFDate, queryInfoDTO.getEndDate());
        }
        IPage<WriteOffApplyPO> writeOffApplyPOIPage = writeOffApplyMapper.selectPage(page, queryWrapper);
        IPage<WriteOffApplyDTO> applyDTOIPage = Convert.convert(new TypeReference<IPage<WriteOffApplyDTO>>() {}, writeOffApplyPOIPage);
        applyDTOIPage.setRecords(Convert.toList(WriteOffApplyDTO.class, writeOffApplyPOIPage.getRecords()));
        return applyDTOIPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteApply(String no) {
        QueryWrapper<WriteOffApplyPO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.lambda().eq(WriteOffApplyPO::getFNo, no);
        WriteOffApplyPO applyPO = writeOffApplyMapper.selectOne(queryWrapper1);
        if (applyPO.getFIsUpload() == 2 || applyPO.getFChangeState() == 2) {
            return false;
        }
        int result = writeOffApplyMapper.delete(queryWrapper1);
        QueryWrapper<WriteOffApplyItemPO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().eq(WriteOffApplyItemPO::getFPid, no);
        writeOffApplyItemMapper.delete(queryWrapper2);
        QueryWrapper<WriteOffApplyIncomePO> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.lambda().eq(WriteOffApplyIncomePO::getFPid, no);
        writeOffApplyIncomeMapper.delete(queryWrapper3);
        return result == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadApply(List<String> noList) {
        AtomicInteger result = new AtomicInteger();
        noList.forEach(item -> {
            QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, item);
            WriteOffApplyPO applyPO = writeOffApplyMapper.selectOne(queryWrapper);
            applyPO.setFIsUpload(2);
            applyPO.setFChangeState(1);
            applyPO.setFCheckResult(null);
            result.addAndGet(writeOffApplyMapper.updateById(applyPO));
        });
        return result.get() == noList.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rescindApply(List<String> noList) {
        int result = 0;
        for (String item : noList) {
            QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, item);
            WriteOffApplyPO applyPO = writeOffApplyMapper.selectOne(queryWrapper);
            if (applyPO.getFChangeState() == 2) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
            applyPO.setFIsUpload(1);
            result += writeOffApplyMapper.updateById(applyPO);
        }
        return result == noList.size();
    }

    @Override
    public IPage<WriteOffApplyItemDTO> selectItemPage(UnitWriteOffItemAndIncomeQueryInfoDTO queryInfoDTO) {
        Page<WriteOffApplyItemPO> page = new Page<>(queryInfoDTO.getPageNum(), queryInfoDTO.getPageSize());
        QueryWrapper<WriteOffApplyItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WriteOffApplyItemPO::getFPid, queryInfoDTO.getNo());
        IPage<WriteOffApplyItemPO> writeOffApplyItemPOIPage = writeOffApplyItemMapper.selectPage(page, queryWrapper);
        IPage<WriteOffApplyItemDTO> applyItemDTOIPage = Convert.convert(new TypeReference<IPage<WriteOffApplyItemDTO>>() {}, writeOffApplyItemPOIPage);
        applyItemDTOIPage.setRecords(Convert.toList(WriteOffApplyItemDTO.class, writeOffApplyItemPOIPage.getRecords()));
        return applyItemDTOIPage;
    }

    @Override
    public IPage<WriteOffApplyIncomeDTO> selectIncomePage(UnitWriteOffItemAndIncomeQueryInfoDTO queryInfoDTO) {
        Page<WriteOffApplyIncomePO> page = new Page<>(queryInfoDTO.getPageNum(), queryInfoDTO.getPageSize());
        QueryWrapper<WriteOffApplyIncomePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WriteOffApplyIncomePO::getFPid, queryInfoDTO.getNo());
        IPage<WriteOffApplyIncomePO> writeOffApplyIncomePOIPage = writeOffApplyIncomeMapper.selectPage(page, queryWrapper);
        IPage<WriteOffApplyIncomeDTO> applyIncomeDTOIPage = Convert.convert(new TypeReference<IPage<WriteOffApplyIncomeDTO>>() {}, writeOffApplyIncomePOIPage);
        applyIncomeDTOIPage.setRecords(Convert.toList(WriteOffApplyIncomeDTO.class, writeOffApplyIncomePOIPage.getRecords()));
        return applyIncomeDTOIPage;
    }

    @Override
    public BillInfoDTO getData(BillQueryDTO billQueryDTO) {
        Date startDate = writeOffApplyMapper.getMaxEndDate(billQueryDTO.getAgenIdCode());
        String end = billQueryDTO.getEnd();
        Date endDate = DateUtil.parse(end);
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DAY_OF_MONTH, 1);
        endDate = c.getTime();
        if (startDate.compareTo(endDate) >= 0) {
            return null;
        }
        String start = DateUtil.formatDate(startDate);
        end = DateUtil.formatDate(endDate);
        Object result = billService.getWriteOffInfo(start, end);
        if (result == null) {
            return null;
        }
        Object o = Convert.toMap(String.class, Object.class, result).get("data");
        if (o == null || "票据不存在".equals(o)) {
            return null;
        }
        List<WriteOffDto> writeOffDtos = Convert.toList(WriteOffDto.class, o);
        if (writeOffDtos == null || writeOffDtos.isEmpty()) {
            return null;
        }
        writeOffDtos.sort((o1, o2) -> {
            int diff = Integer.parseInt(o1.getFbillNo()) - Integer.parseInt(o2.getFbillNo());
            if (diff > 0) {
                return 1;
            } else if (diff < 0) {
                return -1;
            }
            return 0;
        });
        Map<String, List<WriteOffApplyItemDTO>> map1 = new HashMap<>();
        Map<String, WriteOffApplyIncomeDTO> map2 = new HashMap<>();
        writeOffDtos.forEach(bill -> {
            // 整合票号
            if (map1.containsKey(bill.getFbillBatchCode())) {
                List<WriteOffApplyItemDTO> list = map1.get(bill.getFbillBatchCode());
                WriteOffApplyItemDTO writeOffApplyItemDTO = list.get(list.size() - 1);
                if (Integer.parseInt(writeOffApplyItemDTO.getFBillNo2()) == Integer.parseInt(bill.getFbillNo()) - 1) {
                    writeOffApplyItemDTO.setFNumber(writeOffApplyItemDTO.getFNumber() + 1);
                    writeOffApplyItemDTO.setFBillNo2(bill.getFbillNo());
                    writeOffApplyItemDTO.setFAmt(writeOffApplyItemDTO.getFAmt().add(BigDecimal.valueOf(bill.getFtotalAmt())));
                } else {
                    addWriteOffItem(bill, list);
                }
            } else {
                List<WriteOffApplyItemDTO> list = new ArrayList<>();
                addWriteOffItem(bill, list);
                map1.put(bill.getFbillBatchCode(), list);
            }
            // 整合项目
            bill.getUneCbillItems().forEach(item -> {
                if (map2.containsKey(item.getFitemCode())) {
                    WriteOffApplyIncomeDTO writeOffApplyIncomeDTO = map2.get(item.getFitemCode());
                    writeOffApplyIncomeDTO.setFAmt(writeOffApplyIncomeDTO.getFAmt().add(BigDecimal.valueOf(item.getFamt())));
                } else {
                    WriteOffApplyIncomeDTO writeOffApplyIncomeDTO = new WriteOffApplyIncomeDTO();
                    writeOffApplyIncomeDTO.setFItemCode(item.getFitemCode());
                    writeOffApplyIncomeDTO.setFItemName(item.getFitemName());
                    writeOffApplyIncomeDTO.setFUnits(item.getFunits());
                    writeOffApplyIncomeDTO.setFAmt(BigDecimal.valueOf(item.getFamt()));
                    map2.put(writeOffApplyIncomeDTO.getFItemCode(), writeOffApplyIncomeDTO);
                }
            });
        });
        List<WriteOffApplyItemDTO> applyItemDTOS = new ArrayList<>();
        List<WriteOffApplyIncomeDTO> applyIncomeDTOS = new ArrayList<>();
        map1.forEach((key, value) -> applyItemDTOS.addAll(value));
        map2.forEach((key, value) -> applyIncomeDTOS.add(value));
        return new BillInfoDTO(applyItemDTOS, applyIncomeDTOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOrUpdateApply(ApplyDTO applyDTO) {
        int result = 0;
        WriteOffApplyDTO writeOffApplyDTO = applyDTO.getApply();
        BillInfoDTO billInfoDTO = applyDTO.getBillInfo();
        int totalNumber = 0;
        BigDecimal totalAmt = BigDecimal.ZERO;
        for (WriteOffApplyItemDTO item : billInfoDTO.getApplyItemDTOS()) {
            totalNumber += item.getFNumber();
            totalAmt = totalAmt.add(item.getFAmt());
        }
        if (writeOffApplyDTO.getFNo() == null || "".equals(writeOffApplyDTO.getFNo())) {
            String no = CommonUtil.generateID() + "";
            WriteOffApplyPO writeOffApplyPO = BeanUtil.copyProperties(writeOffApplyDTO, WriteOffApplyPO.class);
            writeOffApplyPO.setFNo(no);
            writeOffApplyPO.setFRgnCode("1");
            writeOffApplyPO.setFChangeState(1);
            writeOffApplyPO.setFIsUpload(1);
            writeOffApplyPO.setFAgenIdCode("1");
            writeOffApplyPO.setFNumber(totalNumber);
            writeOffApplyPO.setFTotalAmt(totalAmt);
            result += writeOffApplyMapper.insert(writeOffApplyPO);
            addItemAndIncome(no, billInfoDTO);
        } else {
            QueryWrapper<WriteOffApplyPO> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WriteOffApplyPO::getFNo, writeOffApplyDTO.getFNo());
            WriteOffApplyPO writeOffApplyPO = writeOffApplyMapper.selectOne(queryWrapper);
            writeOffApplyPO.setFNumber(totalNumber);
            writeOffApplyPO.setFTotalAmt(totalAmt);
            writeOffApplyPO.setFEndDate(writeOffApplyDTO.getFEndDate());
            writeOffApplyPO.setFMemo(writeOffApplyDTO.getFMemo());
            result += writeOffApplyMapper.updateById(writeOffApplyPO);
            addItemAndIncome(writeOffApplyPO.getFNo(), billInfoDTO);
        }
        return result == 1;
    }

    private void addWriteOffItem(WriteOffDto bill, List<WriteOffApplyItemDTO> list) {
        WriteOffApplyItemDTO dto = new WriteOffApplyItemDTO();
        dto.setFBatchNo(bill.getFbillBatchCode());
        dto.setFBillCode(bill.getFbillId());
        dto.setFBillName(bill.getFtype());
        dto.setFNumber(1);
        dto.setFBillNo1(bill.getFbillNo());
        dto.setFBillNo2(bill.getFbillNo());
        dto.setFAmt(BigDecimal.valueOf(bill.getFtotalAmt()));
        list.add(dto);
    }

    private void addItemAndIncome(String no, BillInfoDTO billInfoDTO) {
        List<WriteOffApplyItemPO> list1 = Convert.toList(WriteOffApplyItemPO.class, billInfoDTO.getApplyItemDTOS());
        List<WriteOffApplyIncomePO> list2 = Convert.toList(WriteOffApplyIncomePO.class, billInfoDTO.getApplyIncomeDTOS());
        QueryWrapper<WriteOffApplyItemPO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.lambda().eq(WriteOffApplyItemPO::getFPid, no);
        writeOffApplyItemMapper.delete(queryWrapper1);
        for (WriteOffApplyItemPO item :list1) {
            item.setFPid(no);
            writeOffApplyItemMapper.insert(item);
        }
        QueryWrapper<WriteOffApplyIncomePO> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().eq(WriteOffApplyIncomePO::getFPid, no);
        writeOffApplyIncomeMapper.delete(queryWrapper2);
        for (WriteOffApplyIncomePO income : list2) {
            income.setFPid(no);
            writeOffApplyIncomeMapper.insert(income);
        }
    }
}
