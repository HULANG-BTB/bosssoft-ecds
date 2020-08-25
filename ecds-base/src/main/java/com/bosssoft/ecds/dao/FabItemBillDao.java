package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.po.FabItemBillPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.vo.itembillvo.ItemBillVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
     *  查询与票据种类有关的项目   项目名称模糊查询
     *
     * @param page      页面信息包括 页面大小限制  请求的页面
     * @param fBillCode 票据种类编码
     * @param itemName  项目名称
     * @return 票据与项目关联信息
     */
    @Select({"<script> " +
            "SELECT  a.f_id as  id ," +
            "a.f_operator as operator, " +
            "a.f_is_enabled as enabled, " +
            " a.f_bill_code as billCode,a.f_create_time as createTime,a.f_update_time as updateTime, " +
            "b.f_item_id as itemId , " +
            "b.f_item_name as itemName,  " +
            "b.f_isenable as  itemIsEnabled " +
            "from ( select * from  fab_item_bill " +
            "<if test = 'fBillCode !=null  and fBillCode !=\"\" '>" +
            " where f_bill_code=#{fBillCode} </if> " +
            ")a left JOIN fab_item b  on  a.f_item_id_code=b.f_item_id    " +
            "<if test = 'itemName !=null  and itemName !=\"\" '>" +
            " where b.f_item_name like #{itemName} </if>  order  by a.f_update_time desc  " +
            "</script>"})
    IPage<ItemBillVO> selectItemWithBillCode(Page<?> page, @Param("fBillCode") String fBillCode, @Param("itemName") String itemName);


    /**
     * 查询与票据无关的项目信息
     *
     * @param page      页面信息
     * @param fBillCode 票据编码
     * @param itemName  项目名称
     * @return
     */
    @Select({"<script> " +
            "select  f_id as id ,f_rgn_id as  rgnId,f_item_id as itemId,f_item_name as itemName,f_incom_sort_code as incomSortCode,\n" +
            "f_subject as subject,f_operator as operator,f_create_time as createTime,f_update_time as updateTime from fab_item where  f_isenable='1' " +
            " and f_item_id not in (select  f_item_id_code from fab_item_bill  " +
            "<if test = 'fBillCode !=null  and fBillCode !=\"\"  '>" +
            "  where f_bill_code =#{fBillCode} </if> " +
            " ) <if test = 'itemName !=null  and itemName !=\"\"  '>" +
            "  and f_item_name like #{itemName} </if>  order  by f_update_time desc " +
            "</script>"})
    IPage<ItemPO> selectNoContactItem(Page<?> page, @Param("fBillCode") String fBillCode, @Param("itemName") String itemName);


}
