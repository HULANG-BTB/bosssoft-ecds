package com.bosssoft.ecds.service.imp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.unit.WriteOffApplyItemMapper;
import com.bosssoft.ecds.dao.unit.WriteOffApplyMapper;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.WriteOffApplyItemPO;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;
import com.bosssoft.ecds.service.BillService;
import com.bosssoft.ecds.service.UnitWriteOffService;
import com.bosssoft.ecds.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UnitWriteOffServiceImpl implements UnitWriteOffService {
    private final WriteOffApplyMapper writeOffApplyMapper;
    private final WriteOffApplyItemMapper writeOffApplyItemMapper;
    private final BillService billService;

    @Autowired(required = false)
    public UnitWriteOffServiceImpl (WriteOffApplyMapper writeOffApplyMapper,
                                    WriteOffApplyItemMapper writeOffApplyItemMapper,
                                    BillService billService) {
        this.writeOffApplyMapper = writeOffApplyMapper;
        this.writeOffApplyItemMapper = writeOffApplyItemMapper;
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
    public IPage<WriteOffApplyItemDTO> selectItemPage(UnitWriteOffItemQueryInfoDTO queryInfoDTO) {
        Page<WriteOffApplyItemPO> page = new Page<>(queryInfoDTO.getPageNum(), queryInfoDTO.getPageSize());
        QueryWrapper<WriteOffApplyItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WriteOffApplyItemPO::getFPid, queryInfoDTO.getNo());
        IPage<WriteOffApplyItemPO> writeOffApplyItemPOIPage = writeOffApplyItemMapper.selectPage(page, queryWrapper);
        IPage<WriteOffApplyItemDTO> applyItemDTOIPage = Convert.convert(new TypeReference<IPage<WriteOffApplyItemDTO>>() {}, writeOffApplyItemPOIPage);
        applyItemDTOIPage.setRecords(Convert.toList(WriteOffApplyItemDTO.class, writeOffApplyItemPOIPage.getRecords()));
        return applyItemDTOIPage;
    }

    @Override
    public BillInfoDTO getData(BillQueryDTO billQueryDTO) {
        ResponseUtils.ResponseBody responseBody = JSONUtil.toBean(billService.getWriteOffInfo(billQueryDTO.getStart(),
                billQueryDTO.getEnd()), ResponseUtils.ResponseBody.class);
        List<WriteOffDto> writeOffDtos = Convert.toList(WriteOffDto.class, responseBody.getData());
        writeOffDtos.sort((o1, o2) -> {
            int diff = Integer.parseInt(o1.getFBillNo()) - Integer.parseInt(o2.getFBillNo());
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
            if (map1.containsKey(bill.getFBillBatchCode())) {
                List<WriteOffApplyItemDTO> list = map1.get(bill.getFBillBatchCode());
                WriteOffApplyItemDTO writeOffApplyItemDTO = list.get(list.size() - 1);
                if (Integer.parseInt(writeOffApplyItemDTO.getFBillNo2()) == Integer.parseInt(bill.getFBillNo()) - 1) {
                    writeOffApplyItemDTO.setFNumber(writeOffApplyItemDTO.getFNumber() + 1);
                    writeOffApplyItemDTO.setFBillNo2(bill.getFBillNo());
                } else {
                    WriteOffApplyItemDTO dto = new WriteOffApplyItemDTO();
                    dto.setFBatchNo(bill.getFBillBatchCode());
                    dto.setFBillCode(bill.getFBillId());
                    dto.setFNumber(1);
                    dto.setFBillNo1(bill.getFBillNo());
                    dto.setFBillNo2(bill.getFBillNo());
                    dto.setFAmt(BigDecimal.valueOf(bill.getFTotalAmt()));
                    list.add(dto);
                }
            } else {
                List<WriteOffApplyItemDTO> list = new ArrayList<>();
                WriteOffApplyItemDTO writeOffApplyItemDTO = new WriteOffApplyItemDTO();
                writeOffApplyItemDTO.setFBatchNo(bill.getFBillBatchCode());
                writeOffApplyItemDTO.setFBillCode(bill.getFBillId());
                // 票据名称
                writeOffApplyItemDTO.setFNumber(1);
                writeOffApplyItemDTO.setFBillNo1(bill.getFBillNo());
                writeOffApplyItemDTO.setFBillNo2(bill.getFBillNo());
                writeOffApplyItemDTO.setFAmt(BigDecimal.valueOf(bill.getFTotalAmt()));
                list.add(writeOffApplyItemDTO);
                map1.put(bill.getFBillBatchCode(), list);
            }
            // 整合项目
            bill.getUneCbillItems().forEach(item -> {
                if (map2.containsKey(item.getFItemCode())) {
                    WriteOffApplyIncomeDTO writeOffApplyIncomeDTO = map2.get(item.getFItemCode());
                    writeOffApplyIncomeDTO.setFAmt(writeOffApplyIncomeDTO.getFAmt().add(BigDecimal.valueOf(item.getFAmt())));
                } else {
                    WriteOffApplyIncomeDTO writeOffApplyIncomeDTO = new WriteOffApplyIncomeDTO();
                    writeOffApplyIncomeDTO.setFItemCode(item.getFItemCode());
                    writeOffApplyIncomeDTO.setFItemName(item.getFItemName());
                    writeOffApplyIncomeDTO.setFUnits(item.getFUnits());
                    writeOffApplyIncomeDTO.setFAmt(BigDecimal.valueOf(item.getFAmt()));
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
}
