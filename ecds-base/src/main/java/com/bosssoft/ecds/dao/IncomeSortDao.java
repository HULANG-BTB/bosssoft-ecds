package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.dto.IncomeSortDTO;
import com.bosssoft.ecds.entity.dto.IncomeSortShowDTO;
import com.bosssoft.ecds.entity.dto.IncomeSubjectDTO;
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
 * Mapper 接口
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
    @Select("SELECT f_id,f_code,f_name\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_logic_delete=0 and f_parent_id=0")
    @Results(id = "incomeTree", value = {
            @Result(property = "id", column = "f_id"),
            @Result(property = "code", column = "f_code"),
            @Result(property = "name", column = "f_name"),
            @Result(property = "incomeSubjectDTOList", column = "f_id",
                    many = @Many(select = "com.bosssoft.ecds.dao.IncomeSortDao.getTreeByParentId"))
    })
    List<IncomeSubjectDTO> getIncomeSortTreeList();

    /**
     * 递归查询的子查询部分
     *
     * @param parentId
     * @return IncomeSortDTO
     */
    @Select("SELECT f_id,f_code,f_name\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_logic_delete=0 and f_parent_id=#{parentId}")
    @Results(id = "income", value = {
            @Result(property = "id", column = "f_id"),
            @Result(property = "code", column = "f_code"),
            @Result(property = "name", column = "f_name"),
            @Result(property = "incomeSubjectDTOList", column = "f_id",
                    many = @Many(select = "com.bosssoft.ecds.dao.SubjectDao.getIncomeSubject"))

    })
    List<IncomeSubjectDTO> getTreeByParentId(String parentId);

    /**
     * 根据收入种类id分页获取该种类的子类信息
     *
     * @param id
     * @return
     */
    @Select("SELECT f_id,f_code,f_name,f_is_leaf,f_level,f_parent_id\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_logic_delete=0 and f_parent_id=#{id}\n"
    )
    List<IncomeSortDTO> getById(@Param("id") String id);

    /**
     * 获取全部收入种类
     *
     * @return
     */
    @Select("select f_id as id,f_code as code,f_name as name,f_is_leaf as leaf,f_level as level,f_parent_id as parentId\n" +
            "FROM fab_income_sort\n" +
            "where f_logic_delete=0")
    List<IncomeSortDTO> getAll();

    /**
     * 获取所有非底级的收入类别
     *
     * @return
     */
    @Select("SELECT f_id AS id,f_code AS CODE,f_name AS NAME\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_logic_delete=0 and f_is_leaf=0")
    List<IncomeSortDTO> getByLeaf();

    /**
     * 通过id查询收入类别的层级
     *
     * @param id
     * @return
     */
    @Select("SELECT f_level FROM fab_income_sort \n" +
            "WHERE f_logic_delete=0 and f_id=#{id}")
    Integer getLevel(Long id);

    /**
     * 通过id获取指定收入类别的编码
     *
     * @param id
     * @return
     */
    @Select("SELECT f_code FROM fab_income_sort \n" +
            "WHERE f_logic_delete=0 and f_id=#{id}")
    String getCode(Long id);

    /**
     * 获取所有层级为1的收入类别
     *
     * @return
     */
    @Select("SELECT f_id AS id,f_code AS CODE,f_name AS NAME\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_logic_delete=0 and f_level=1")
    List<IncomeSortDTO> getFirst();

    /**
     * 获取最大层级
     *
     * @return
     */
    @Select("select max(f_level) from fab_income_sort")
    int getMaxLevelFromIncome();

    /**
     * 根据收入种类id获取该种类的信息
     *
     * @param id
     * @return
     */
    @Select("SELECT f_id as id,f_code as code,f_name as name,f_is_leaf as leaf,f_level as level,f_parent_id as parentId\n" +
            "FROM fab_income_sort\n" +
            "WHERE f_logic_delete=0 and f_id=#{id}\n"
    )
    IncomeSortDTO getOneById(Long id);

    /**
     * 获取全部收入种类
     *
     * @return
     */
    @Select("select f_code as code,f_name as name\n" +
            "FROM fab_income_sort\n" +
            "where f_logic_delete=0")
    List<IncomeSortShowDTO> selectAll();

    /**
     * 对外提供收入类别信息
     *
     * @param subjectId
     * @return
     */
    @Select("SELECT fis.f_code AS CODE,fis.f_name AS NAME\n" +
            "FROM fab_income_sort fis,fab_income_sort_subject fiss\n" +
            "WHERE fis.f_id=fiss.f_income_sort_id AND \n" +
            "fis.f_logic_delete=0 AND fiss.f_logic_delete=0 AND\n" +
            " f_subject_id=#{subjectId}")
    IncomeSortShowDTO getBySubjectId(Long subjectId);

}
