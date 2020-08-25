package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.domain.vo.BillNumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author syf
 * @Date 2020/8/19 18:23
 */
@Mapper
@Repository
public interface BillNumDao extends BaseMapper<BillNumVO> {

    @Select("select sum(f_amount) as total,date(f_create_time) as create_date from fbb_stock where DATEDIFF(CURDATE(),date(f_create_time))<7 and f_logic_delete=0 GROUP BY date(f_create_time)")
    List<BillNumVO> selectNumDate();
}
