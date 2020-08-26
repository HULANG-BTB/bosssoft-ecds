package com.bosssoft.ecds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.domain.vo.BillDestroyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author syf
 * @Date 2020/8/20 14:19
 */
@Mapper
@Repository
public interface BillDestroyDao extends BaseMapper<BillDestroyVO> {
    @Select("select sum(f_amount) as total,date(f_create_time) as create_date from fbb_stock_destroy where DATEDIFF(CURDATE(),date(f_create_time))<7 and f_logic_delete=0 GROUP BY date(f_create_time)")
    List<BillDestroyVO> selectNumDate();
}
