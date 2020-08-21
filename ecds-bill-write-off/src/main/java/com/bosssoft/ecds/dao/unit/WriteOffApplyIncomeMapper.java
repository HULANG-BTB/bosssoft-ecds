package com.bosssoft.ecds.dao.unit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.WriteOffApplyIncomePO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-19
 */
public interface WriteOffApplyIncomeMapper extends BaseMapper<WriteOffApplyIncomePO> {
    /**
     * 根据业务单号获取业务的详细信息
     *
     * @param
     * @return
     */
    @Select("SELECT * FROM ube_writeoff_apply_income WHERE f_pid = #{fPid}")
    List<WriteOffApplyIncomePO> getWriteOffApplyIncomeList(@Param("fPid") String fPid);
}
