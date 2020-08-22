package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.FabFinDept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lin.wanning
 * @since 2020-08-05
 */
@Repository
public interface FabFinDeptDao extends BaseMapper<FabFinDept> {

    /**
     * 名称模糊查询
     */
    @Select({"<script> " +
            "SELECT  f_id as id,f_rgn_code as rgnCode,f_findept_code as findeptCode,f_findept_name as findeptName,f_is_enable as isEnable,f_operator as operator, " +
            "f_operator_id as operatorId,f_create_time as createTime,f_linkman as linkman,f_link_tel as linkTel, " +
            "f_addr as addr " +
            "from fab_fin_dept " +
            "where 1=1 " +
            "<if test = 'inEnable !=null  and inEnable !=\"\" '>" +
            " AND  f_is_enable=#{inEnable} </if> " +
            "<if test = 'findeptName !=null  and findeptName !=\"\" '>" +
            " AND f_findept_name like  concat('%', #{findeptName}, '%')  </if> " +
            "</script>"})
    IPage<FabFinDept> selectByCondition(Page<?> page, @Param("inEnable") Boolean inEnable, @Param("findeptName") String findeptName);

}
