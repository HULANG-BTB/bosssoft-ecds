package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.GroupItemPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzh
 * @since 2020-08-14
 */
@Component
public interface GroupItemDao extends BaseMapper<GroupItemPO> {
    ItemPO getItemInfo(String itemCode);
}
