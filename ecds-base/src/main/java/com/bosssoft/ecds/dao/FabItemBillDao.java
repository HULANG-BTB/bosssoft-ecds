package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.FabItemBillPo;
import com.bosssoft.ecds.entity.vo.ItemBillVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuliming
 * @since 2020-08-06
 */
@Repository
@Mapper
public interface FabItemBillDao extends BaseMapper<FabItemBillPo> {

    /**
     *   查询和票据种类有关系的项目
     * @param fBillCode
     * @return
     */
    @Select("SELECT  \n" +
            "a.f_operator as f_operator,\n" +
            "a.f_is_enabled as f_is_enable,\n" +
            "b.f_item_id as f_item_id ,\n" +
            "b.f_item_name as f_item_name, \n" +
            "b.f_isenable as  f_item_is_enabled\n" +
            "from  (SELECT * from fab_item_bill WHERE f_bill_code=#{fBillCode})a\n" +
            "LEFT JOIN fab_item b  on  a.f_item_id_code=b.f_item_id")
    public List<ItemBillVo> selectItemByBillCode(@Param("fBillCode") String fBillCode);

}
