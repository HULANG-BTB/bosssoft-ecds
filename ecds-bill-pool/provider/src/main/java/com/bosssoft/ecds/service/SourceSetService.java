package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.SourceSetDto;

public interface SourceSetService {

    int updateMin(SourceSetDto sourceSetDto);

    int updatePushNumber(SourceSetDto sourceSetDto);
}
