package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.IncomeSubjectDTO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
@Mapper
public interface SubjectDao extends BaseMapper<SubjectPO> {

    /**
     * 递归查询的子查询部分
     *
     * @param parentId
     * @return IncomeSortDTO
     */
    @Select("SELECT s.f_id ,s.f_sub_code , s.f_sub_name \n" +
            "FROM fab_income_sort_subject iss,fab_subject s\n" +
            "WHERE s.f_id=iss.f_subject_id AND iss.f_income_sort_id = #{parentId}")
    @Results(id = "income", value = {
            @Result(property = "id", column = "f_id"),
            @Result(property = "code", column = "f_sub_code"),
            @Result(property = "name", column = "f_sub_name"),
    })
    List<IncomeSubjectDTO> getIncomeSubject(String parentId);


}
