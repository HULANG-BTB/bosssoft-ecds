package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.FabItemBillPO;
import com.bosssoft.ecds.entity.vo.ItemBillVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wuliming
 * @since 2020-08-06
 */
@Repository
@Mapper
public interface FabItemBillDao extends BaseMapper<FabItemBillPO> {

    /**
     * 查询和票据种类有关系的项目
     *
     * @param fBillCode
     * @return
     */
    @Select("SELECT  a.f_id as  id ," +
            "a.f_operator as operator, " +
            "a.f_is_enabled as enabled, " +
            "b.f_item_id as itemId , " +
            "b.f_item_name as itemName,  " +
            "b.f_isenable as  itemIsEnabled " +
            "from  (SELECT * from fab_item_bill WHERE f_bill_code=#{fBillCode})a " +
            "LEFT JOIN fab_item b  on  a.f_item_id_code=b.f_item_id where  b.f_isenable='1' ")
    IPage<ItemBillVO> selectItemByBillCode(Page<?> page, @Param("fBillCode") String fBillCode);



}
