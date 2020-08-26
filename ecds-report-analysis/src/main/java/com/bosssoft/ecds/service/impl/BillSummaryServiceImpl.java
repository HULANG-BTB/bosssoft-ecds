package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.dao.IFabAgenDao;
import com.bosssoft.ecds.dao.IFbeTodayBillKindDao;
import com.bosssoft.ecds.dao.IFbeWeekBillSummaryDao;
import com.bosssoft.ecds.dao.ITodayBillSummaryDao;
import com.bosssoft.ecds.domain.dto.FabAgenDto;
import com.bosssoft.ecds.domain.dto.TodayBillKindDto;
import com.bosssoft.ecds.domain.dto.TodayBillSummaryDto;
import com.bosssoft.ecds.domain.dto.WeekBillSummaryDto;
import com.bosssoft.ecds.domain.vo.WeekBillSummaryVO;
import com.bosssoft.ecds.service.IBillSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 开票汇总service实现类
 * @author LiDaShan
 */
@Service
@Slf4j
public class BillSummaryServiceImpl implements IBillSummaryService {

    @Resource
    private IFbeTodayBillKindDao todayBillKindDao;
    @Resource
    private IFabAgenDao fabAgenDao;
    @Resource
    private IFbeWeekBillSummaryDao weekBillSummaryDao;
    @Resource
    private ITodayBillSummaryDao todayBillSummaryDao;

    @Override
    public List<TodayBillSummaryDto> selectTodayBillSummary() {
        List<TodayBillSummaryDto> todayBillSummaryDtos = todayBillSummaryDao.selectHourBillInfo();
        return todayBillSummaryDtos;
    }

    @Override
    public List<TodayBillKindDto> selectTodayBillKind() {
        return todayBillKindDao.selectTodayBillKindInfo();
    }

    @Override
    public List<WeekBillSummaryVO> selectWeekBillSummary() {
        List<WeekBillSummaryVO> vos = new ArrayList<>();
        // 存放单位名称和其在 vos 中的索引
        HashMap<String,Integer> indexOfKey = new HashMap<String, Integer>();
        for (int i = 0; i<=6; i++) {
            // 查询今日的开票单位和开票数量
            List<WeekBillSummaryDto> weekBillSummaryDtos = weekBillSummaryDao.selectTodayBillKindInfo(i);
            // 遍历开票单位
            for (int j = 0; j < weekBillSummaryDtos.size(); j++){
                // 判断此单位一周内是否已经开过票
                if (indexOfKey.containsKey(weekBillSummaryDtos.get(j).getAgenid())) {
                    // 如果 vos 中已存有该单位
                    Integer index = indexOfKey.get(weekBillSummaryDtos.get(j).getAgenid());
                    WeekBillSummaryVO weekBillSummaryVO = vos.get(index);
                    int[] billnum = weekBillSummaryVO.getBillnum();
                    // 设置对应单位的对应天数的开票数
                    billnum[i] = weekBillSummaryDtos.get(j).getBillnum();
                } else {
                    // 如果七天内第一次开票
                    WeekBillSummaryVO vo  = new WeekBillSummaryVO();
                    // 获取单位名
                    FabAgenDto fabAgenDto = fabAgenDao.selectAgenNameById(weekBillSummaryDtos.get(j).getAgenid());
                    vo.setAgenname(fabAgenDto.getAgenName());
                    // 存入此单位第 i 的开票数
                    int[] billnum = new int[]{0,0,0,0,0,0,0};
                    billnum[i] = weekBillSummaryDtos.get(j).getBillnum();
                    vo.setBillnum(billnum);
                    // 存入list，并记录此单位的 index
                    vos.add(vo);
                    indexOfKey.put(weekBillSummaryDtos.get(j).getAgenid(),vos.size()-1);
                }
            }
        }
        return vos;
    }


}
