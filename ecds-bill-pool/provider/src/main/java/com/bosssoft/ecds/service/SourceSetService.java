package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.SourceSetDto;

import java.util.List;

public interface SourceSetService {

    int updateMin(SourceSetDto sourceSetDto);

    int updatePushNumber(SourceSetDto sourceSetDto);

    List<SourceSetDto> retrieveSetList();
}
