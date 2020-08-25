package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.domain.po.FneCbillAccountingPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName FneCbillAccountingDao
 * @Description FneCbillAccountingDa
 * @auther wangpeng
 * @Date 2020/8/19 15:58
 * @Version 1.0
 **/
@Repository
@Mapper
public interface FneCbillAccountingDao extends BaseMapper<FneCbillAccountingPO> {

    @Select(" select ifnull(b.f_account,0) as f_account_sum" +
            " from (" +
            " SELECT curdate() as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 1 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 2 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 3 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 4 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 5 day) as click_date" +
            " union all" +
            " SELECT date_sub(curdate(), interval 6 day) as click_date" +
            " ) a left join (" +
            " select date(f_time) as datetime, SUM(f_account) as f_account from fne_cbill_accounting" +
            " group by date(f_time)" +
            " ) b on a.click_date = b.datetime ORDER BY click_date;")
    public List<BigDecimal> searchWeek();
}
