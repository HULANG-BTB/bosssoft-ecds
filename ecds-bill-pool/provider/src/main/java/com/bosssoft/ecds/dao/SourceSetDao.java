package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.po.SourceMessagePo;
import com.bosssoft.ecds.entity.po.SourceSetPo;

import java.util.List;

public interface SourceSetDao {

    int updateMin(SourceSetPo sourceSetPo);

    int updatePushNumber(SourceSetPo sourceSetPo);

    int updateSet(SourceSetPo sourceSetPo);

    int createTable(SourceSetPo sourceSetPo);

    int createTypeToPool(SourceSetPo sourceSetPo);

    int deleteSourceTable(SourceSetPo sourceSetPo);

    int createSet(SourceSetPo sourceSetPo);

    List<SourceMessagePo> retrieveSourceMessageList();

    SourceMessagePo retrieveSourceMessageByCode(String billTypeCode);

    List<SourceSetPo> retrieveSetList();

    SourceSetPo retrieveSetByCode(String billTypeCode);
}
