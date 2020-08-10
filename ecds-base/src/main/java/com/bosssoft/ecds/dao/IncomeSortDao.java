package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.IncomeSortDTO;
import com.bosssoft.ecds.entity.po.IncomeSortPO;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AloneH
 * @since 2020-08-10
 */
@Repository
public interface IncomeSortDao extends BaseMapper<IncomeSortPO> {
    /**
     * 递归查询收入类别
     *
     * @return IncomeSortDTO
     */
    @Select("SELECT f_id,f_code,f_name,f_is_leaf,f_level,f_parent_id\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_parent_id=0")
    @Results(id = "incomeTree", value = {
            @Result(property = "id", column = "f_id"),
            @Result(property = "code", column = "f_code"),
            @Result(property = "name", column = "f_name"),
            @Result(property = "leaf", column = "f_is_leaf"),
            @Result(property = "level", column = "f_level"),
            @Result(property = "parentId", column = "f_parent_id"),
            @Result(property = "incomeSortDTOList", column = "f_id",
                    many = @Many(select = "com.boss.trainee.incomedemo.dao.IncomeSortDAO.getTreeByParentId"))
    })
    List<IncomeSortDTO> getIncomeSortTreeList();

    /**
     * 递归查询的子查询部分
     *
     * @param parentId
     * @return IncomeSortDTO
     */
    @Select("SELECT f_id,f_code,f_name,f_is_leaf,f_level,f_parent_id\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_parent_id=#{parentId}")
    @Results(id = "income", value = {
            @Result(property = "id", column = "f_id"),
            @Result(property = "code", column = "f_code"),
            @Result(property = "name", column = "f_name"),
            @Result(property = "leaf", column = "f_is_leaf"),
            @Result(property = "level", column = "f_level"),
            @Result(property = "parentId", column = "f_parent_id")
    })
    List<IncomeSortDTO> getTreeByParentId(String parentId);

    /**
     * 根据收入种类id分页获取该种类的子类信息
     *
     * @param id
     * @return
     */
    @Select("SELECT f_id,f_code,f_name,f_is_leaf,f_level,f_parent_id\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_parent_id=#{id}\n" +
            "limit ")
    List<IncomeSortDTO> getById(@Param("id") String id);

    /**
     * 获取全部收入种类
     *
     * @return
     */
    @Select("select f_id as id,f_code as code,f_name as name,f_is_leaf as leaf,f_level as level,f_parent_id as parentId\n" +
            "FROM fab_income_sort\n")
    List<IncomeSortDTO> getAll();

    /**
     * 获取所有非底级的收入类别
     *
     * @return
     */
    @Select("SELECT f_id AS id,f_code AS CODE,f_name AS NAME\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_is_leaf=0")
    List<IncomeSortDTO> getByLeaf();

    /**
     * 通过id查询收入类别的层级
     *
     * @param id
     * @return
     */
    @Select("SELECT f_level FROM fab_income_sort \n" +
            "WHERE f_id=#{id}")
    Integer getLevel(Long id);

    /**
     * 通过id获取指定收入类别的编码
     *
     * @param id
     * @return
     */
    @Select("SELECT f_code FROM fab_income_sort \n" +
            "WHERE f_id=#{id}")
    String getCode(Long id);

}
