package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.bosssoft.ecds.entity.dto.BillSortDTO;
import com.bosssoft.ecds.entity.dto.BillTypeDTO;
import com.bosssoft.ecds.entity.dto.BillTypeShowDTO;
import com.bosssoft.ecds.entity.dto.BillTypeTableDTO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.billtypevo.QueryTableVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :Raiz
 * @date :2020/8/5
 */
@Repository
public interface BillTypeDao extends BaseMapper<BillTypePO> {

    /**
     * 获取所有票据种类
     *
     * @return 票据种类List
     */
    @Select("SELECT f_id as id,f_code as code,f_name as name,f_type_code as typeCode " +
            "FROM fab_bill " +
            "WHERE f_check_sort = 0 AND f_logic_delete = 0 " +
            "ORDER BY f_code")
    List<BillTypeDTO> getBillType();

    /**
     * 获取所有票据分类
     *
     * @return 票据分类List
     */
    @Select("SELECT f_id as id,f_code as code,f_name as name " +
            "FROM fab_bill " +
            "WHERE f_check_sort = 1 AND f_logic_delete = 0 " +
            "ORDER BY f_code")
    List<BillSortDTO> getBillSort();

    /**
     * 获取所有票据种类及分类
     *
     * @return 票据种类及分类List
     */
    @Select("SELECT f_id as id,f_code as code,f_name as name," +
            "f_bill_nature as billNature, f_memory_code as memoryCode," +
            "f_eff_date as effDate,f_exp_date as expDate,f_check_sort as checkSort," +
            "f_check_quota as checkQuota,f_quota_amount as quotaAmount," +
            "f_safe_year as safeYear,f_remark as remark," +
            "f_level as level,f_pid as pid,f_check_leaf as checkLeaf,f_nature_code as natureCode," +
            "f_type_code as typeCode,f_parent_code as parentCode " +
            "FROM fab_bill " +
            "WHERE f_logic_delete = 0 " +
            "ORDER BY f_code ")
    List<BillTypeShowDTO> getAllBillType();

    /**
     * 按条件查询票据种类及分类
     *
     * @param param 查询的参数
     * @return 查询的票据种类及分类List
     */
    @Select("<script>" +
            "SELECT f_id as id,f_code as code,f_name as name," +
            "f_bill_nature as billNature, f_memory_code as memoryCode," +
            "f_eff_date as effDate,f_exp_date as expDate,f_check_sort as checkSort," +
            "f_check_quota as checkQuota,f_quota_amount as quotaAmount," +
            "f_safe_year as safeYear,f_remark as remark," +
            "f_level as level,f_pid as pid,f_check_leaf as checkLeaf,f_nature_code as natureCode," +
            "f_type_code as typeCode,f_parent_code as parentCode " +
            "FROM fab_bill " +
            "WHERE f_logic_delete = 0" +
            "<if test=\"param.id != null and param.id != \'\' \">" +
            " AND (f_id = #{param.id} or f_pid = #{param.id})" +
            " </if>" +
            "<if test=\"param.billNature != null and param.billNature != \'\' \">" +
            " AND f_bill_nature = #{param.billNature}" +
            " </if>" +
            "<if test=\"param.memoryCode != null and param.memoryCode != \'\' \">" +
            " AND f_memory_code like concat('%', #{param.memoryCode}, '%')" +
            " </if>" +
            "<if test=\"param.name != null and param.name != \'\' \">" +
            " AND f_name like concat('%', #{param.name}, '%')" +
            " </if>" +
            "<if test=\"param.checkSort != null \">" +
            " AND f_check_sort = #{param.checkSort}" +
            " </if>" +
            "ORDER BY f_code" +
            "</script>")
    List<BillTypeTableDTO> queryTable(@Param("param") QueryTableVO param);
}
