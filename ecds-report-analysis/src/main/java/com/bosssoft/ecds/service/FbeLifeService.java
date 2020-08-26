package com.bosssoft.ecds.service;

import com.bosssoft.ecds.domain.search.LifeSearch;
import com.bosssoft.ecds.domain.vo.FbeLifeVO;
import java.util.List;

/**
 * 获取生命周期所有信息的接口
 * @author zouyou
 * @date 2020/8/25 9:10
 */
public interface FbeLifeService {
    /**
     * 根据传入条件查询生命周期所有信息，返回VO的list集合
     * @param lifeSearch
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeLifeVO>
     * @date 2020/8/25 9:23
     */
    List<FbeLifeVO> getLifeList(LifeSearch lifeSearch);
}
