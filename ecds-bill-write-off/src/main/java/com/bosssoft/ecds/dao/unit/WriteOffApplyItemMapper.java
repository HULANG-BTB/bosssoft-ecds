package com.bosssoft.ecds.dao.unit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.WriteOffApplyItemPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-14
 */
public interface WriteOffApplyItemMapper extends BaseMapper<WriteOffApplyItemPO> {

    /**
     * 根据业务单号获取业务的详细信息
     *
     * @param
     * @return
     */
    @Select("SELECT * FROM ube_writeoff_apply_item WHERE f_pid = #{fPid}")
    List<WriteOffApplyItemPO> getWriteOffApplyItemList(@Param("fPid") String fPid);
}
