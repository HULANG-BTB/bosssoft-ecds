package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.domain.dto.FabAgenDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * 单位基本信息表相关语句
 * @author LiDashan
 * @date 2020/8/24
 */
@Mapper
public interface IFabAgenDao {

    /**
     *
     * @param id
     * @return
     */
    @Select("SELECT f_agen_name from fab_agen where f_agen_code = #{id}")
    @Results(id = "agentDto",value = @Result(property = "agenName",column = "f_agen_name"))
    FabAgenDto selectAgenNameById(String id);
}
