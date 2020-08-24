package com.bosssoft.ecds.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.msg.entity.dto.CheckRecordDto;
import com.bosssoft.ecds.msg.entity.vo.CheckRecordQueryVo;
import com.bosssoft.ecds.msg.util.DozerUtils;
import com.bosssoft.ecds.msg.util.SnowflakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.msg.entity.po.CheckRecordPo;
import com.bosssoft.ecds.msg.mapper.CheckRecordMapper;
import com.bosssoft.ecds.msg.service.CheckRecordService;

import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
@Service
@Slf4j
public class CheckRecordServiceImpl extends ServiceImpl<CheckRecordMapper, CheckRecordPo> implements CheckRecordService {


    @Override
    public List<CheckRecordDto> listPage(CheckRecordQueryVo recordQuery, Long page, Long limit) {
        Page<CheckRecordPo> pageQuery = new Page<>(page, limit);
        QueryWrapper<CheckRecordPo> query = getQuery(recordQuery);
        Page<CheckRecordPo> recordPage = baseMapper.selectPage(pageQuery, query);
        List<CheckRecordPo> records = recordPage.getRecords();
        return DozerUtils.mapList(records, CheckRecordDto.class);
    }

    @Override
    public Long getTotal(CheckRecordQueryVo recordQuery) {

        return baseMapper.selectCount(getQuery(recordQuery)).longValue();
    }

    @Override
    public void saveRecord(String billId, int res) {
        CheckRecordPo record = new CheckRecordPo();
        record.setResult(res);
        record.setBillCode(billId);
        record.setCheckType("COMMON");
        record.setId(SnowflakeUtil.genId());
        baseMapper.insert(record);
    }


    /**
     * 获取queryWrapper
     *
     * @param recordQuery 查询对象
     * @return QueryWrapper
     */
    public QueryWrapper<CheckRecordPo> getQuery(CheckRecordQueryVo recordQuery) {
        QueryWrapper<CheckRecordPo> query = new QueryWrapper<>();
        query.orderByDesc(CheckRecordPo.COL_F_CREATE_TIME);
        if (recordQuery.getId() != null) {
            query.eq(CheckRecordPo.COL_F_ID, recordQuery.getId());
        }
        if (recordQuery.getResult() != null) {
            query.eq(CheckRecordPo.COL_F_RESULT, recordQuery.getResult());
        }
        if (recordQuery.getBillCode() != null) {
            query.eq(CheckRecordPo.COL_F_BILL_CODE, recordQuery.getBillCode());
        }
        if (recordQuery.getPeriod() != null) {
            Date startDate = recordQuery.getPeriod().get(0);
            Date endDate = recordQuery.getPeriod().get(1);
            if (startDate != null && endDate != null && endDate.compareTo(startDate) > 0) {
                query.le(CheckRecordPo.COL_F_CREATE_TIME, endDate).ge(CheckRecordPo.COL_F_CREATE_TIME, startDate);
            }
        }
        query.eq(CheckRecordPo.COL_F_LOGIC_DELETE, 0);
        return query;
    }
}
