package com.bosssoft.ecds.service;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectQueryVO;
import com.bosssoft.ecds.entity.vo.subjectvo.SubjectVO;
import com.bosssoft.ecds.entity.vo.subjectvo.UpdateSubjectVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wy
 * @since 2020-08-10
 */
public interface SubjectService extends IService<SubjectPO> {

    /**
     * 分页条件查询接口
     *
     * @param subjectQueryVO
     * @return
     */
    QueryResponseResult<Map<String, SubjectVO>> listPage(SubjectQueryVO subjectQueryVO);

    /**
     * 添加预算科目
     *
     * @param subjectDTO
     * @return
     */
    QueryResponseResult add(SubjectDTO subjectDTO);

    /**
     * 修改预算科目
     *
     * @param updateSubjectVO
     * @return
     */

    QueryResponseResult update(UpdateSubjectVO updateSubjectVO);

    /**
     * 删除预算科目
     */
    QueryResponseResult delete(Long id);

    /**
     * 复制预算科目
     *
     * @param
     * @return
     */
    QueryResponseResult copy(Long id);

    /**
     * 返回预算科目树
     *
     * @param year
     * @return
     */
    List<SubjectVO> getAll(String year);

    /**
     * 返回出二级预算科目树
     * @param year
     * @return
     */
    List<SubjectVO> getSecondTree(String year);
}
