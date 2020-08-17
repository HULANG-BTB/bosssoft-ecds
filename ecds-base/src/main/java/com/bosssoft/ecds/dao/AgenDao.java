package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.agendto.AgenInfoDTO;
import com.bosssoft.ecds.entity.po.AgenPO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author vihenne
 * @since 2020-08-09
 */
@Repository
@Component
public interface AgenDao extends BaseMapper<AgenPO> {
    AgenInfoDTO getDetailByUnitName(String agenName);
}
