package com.boss.msg.service;

import com.boss.msg.entity.dto.CheckRecordDto;
import com.boss.msg.entity.po.CheckRecordPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boss.msg.entity.vo.CheckRecordQueryVo;

import java.util.List;

/**
 * @author zhangxiaohui
 * @create 2020/8/14 11:34
 */
public interface CheckRecordService extends IService<CheckRecordPo>{

    /**
     * 分页查询
     * @param recordQuery 查询对象
     * @param page 当前页面
     * @param limit 当前页面大小
     * @return 查询到匹配的结果集
     */
    List<CheckRecordDto> listPage(CheckRecordQueryVo recordQuery, Long page, Long limit);

    /**
     * 获取数据库中匹配的总记录数
     * @param recordQuery 查询对象
     * @return 总记录数
     */
    Long getTotal(CheckRecordQueryVo recordQuery);

    /**
     * 保存查验记录
     * @param billId 票据id
     * @param res 查验结果
     */
    void saveRecord(String billId, int res);
}
