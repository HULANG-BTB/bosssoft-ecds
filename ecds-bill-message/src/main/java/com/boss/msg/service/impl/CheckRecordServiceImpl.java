package com.boss.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boss.msg.entity.dto.CheckRecordDto;
import com.boss.msg.entity.po.MailPo;
import com.boss.msg.entity.vo.CheckRecordQueryVo;
import com.boss.msg.entity.vo.MailQueryVo;
import com.boss.msg.util.DozerUtils;
import com.boss.msg.util.SnowflakeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boss.msg.entity.po.CheckRecordPo;
import com.boss.msg.mapper.CheckRecordMapper;
import com.boss.msg.service.CheckRecordService;

import java.util.Date;
import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
@Service
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
        if (recordQuery.getId() != null) {
            query.eq("f_id", recordQuery.getId());
        }
        if (recordQuery.getResult() != null) {
            query.eq("f_result", recordQuery.getResult());
        }
        if (recordQuery.getBillCode() != null) {
            query.eq("f_bill_code", recordQuery.getBillCode());
        }
        if (recordQuery.getPeriod() != null) {
            Date startDate = recordQuery.getPeriod().get(0);
            Date endDate = recordQuery.getPeriod().get(1);
            if (startDate != null && endDate != null && endDate.compareTo(startDate) > 0) {
                query.le("f_create_time", endDate).ge("f_create_time", startDate);
            }
        }
        query.eq("f_logic_delete", 0);
        return query;
    }
}
