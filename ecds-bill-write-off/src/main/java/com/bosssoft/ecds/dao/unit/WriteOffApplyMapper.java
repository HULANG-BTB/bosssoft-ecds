package com.bosssoft.ecds.dao.unit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.ecds.entity.po.WriteOffApplyPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author hujierong
 * @date 2020-8-14
 */
@Mapper
public interface WriteOffApplyMapper extends BaseMapper<WriteOffApplyPO> {

    /**
     * 根据fAgenIdCode单位识别码
     * 获取单位上报的核销申请
     * f_is_upload 表示已上传
     *
     * @param fAgenIdCode
     * @return java.util.List
     */
    @Select("SELECT * FROM ube_writeoff_apply WHERE f_agen_id_code = #{fAgenIdCode} AND f_is_upload = 2")
    List<WriteOffApplyPO> getWriteOffApplyPOByAgenIdCode(@Param("fAgenIdCode") String fAgenIdCode);

    /**
     * 处理退回的核销申请
     * 根据fNo业务单号进行修改
     * 更新 f_check_result以及 f_is_upload
     * f_check_result "审验结果：1 良好 2 合格 3 问题 4 整改通过" -- > 3
     * f_is_upload "是否上报：1 未上报 2 已上报"  -- > 1
     * f_change_state "是否审验：1 未审验 2 已审验"  -- > 2
     * @param fNo
     * @return
     */
    @Update("UPDATE ube_writeoff_apply SET f_change_state = 2, f_check_result = 3, f_is_upload = 1 WHERE f_no = #{fNo}")
    boolean updateWriteOffApply(@Param("fNo") String fNo);
}
