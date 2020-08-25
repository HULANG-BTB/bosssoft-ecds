package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.domain.po.FbeLifePO;
import com.bosssoft.ecds.domain.search.LifeSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zouyou
 * @version 1.0
 * @description 生命周期模块的dao层
 * @date 2020/8/24 18:38
 */
@Mapper
@Repository
public interface FbeLifeDao {

    /**
     * 根据code查询对应票据的生命周期
     * @param lifeSearch
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.po.FbeLifePO>
     * @date 2020/8/24 22:50
     */
    @Select("select * from fbe_life where f_bill_code = #{code}")
    List<FbeLifePO> getLifeList(LifeSearch lifeSearch);
}
