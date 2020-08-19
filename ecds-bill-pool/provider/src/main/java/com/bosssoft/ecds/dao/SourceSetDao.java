package com.bosssoft.ecds.dao;

import com.bosssoft.ecds.entity.po.SourceMessagePo;
import com.bosssoft.ecds.entity.po.SourceSetPo;

import java.util.List;

/**
 * @author lixin
 * @version 1.0
 * @date 2020/8/18 10:43
 */
public interface SourceSetDao {

    /**
     * 更新票据池设置
     * @param sourceSetPo 需要修改的票据池编码、最小推送数、修改类型
     * @return int
     */
    int updateMin(SourceSetPo sourceSetPo);

    /**
     * 更新票据池设置
     * @param sourceSetPo 需要修改的票据池编码、阈值、修改类型
     * @return int
     */
    int updatePushNumber(SourceSetPo sourceSetPo);

    /**
     * 更新票据池设置
     * @param sourceSetPo 票据池修改的全部信息包括推送数量、阈值、修改类型等
     * @return int
     */
    int updateSet(SourceSetPo sourceSetPo);

    /**
     * 创建数据表
     * @param sourceSetPo 新数据表需要的票据编码，根据票据编码创建新数据表
     * @return int
     */
    int createTable(SourceSetPo sourceSetPo);

    /**
     * 插入票据池与数据表的对应
     * @param sourceSetPo 票据编码
     * @return int
     */
    int createTypeToPool(SourceSetPo sourceSetPo);

    /**
     * 删除数据表
     * @param sourceSetPo 通过票据编码找到数据表名
     * @return int
     */
    int deleteSourceTable(SourceSetPo sourceSetPo);

    /**
     * 插入新的票据池信息
     * @param sourceSetPo 票据池设置的各种信息
     * @return
     */
    int createSet(SourceSetPo sourceSetPo);

    /**
     * 取出票据池的各种信息，票据编码、数据表表名、阈值
     * @return List
     */
    List<SourceMessagePo> retrieveSourceMessageList();

    /**
     * 根据票据编码获取对应票据池信息，票据编码、数据表表名、阈值
     * @param billTypeCode 票据编码
     * @return SourceMessagePo
     */
    SourceMessagePo retrieveSourceMessageByCode(String billTypeCode);

    /**
     * 取出全部票据池设置信息
     * @return List<SourceSetPo>
     */
    List<SourceSetPo> retrieveSetList();

    /**
     * 根据票据编码取出对应票据池设置信息
     * @param billTypeCode 票据编码
     * @return
     */
    SourceSetPo retrieveSetByCode(String billTypeCode);
}
