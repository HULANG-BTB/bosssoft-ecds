package com.bosssoft.ecds.service;

import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.entity.dto.SubjectDTO;
import com.bosssoft.ecds.entity.po.SubjectPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.entity.vo.subjectvo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     */
    QueryResponseResult copy(Long id);

    /**
     * 返回导出文件名
     */
    String getFileName(Long id);

    /**
     * 根据subjectQueryVO返回要导出的数据
     */
    List<SubjectExcelData> selectExcel(SubjectQueryVO subjectQueryVO);


    /**
     * 导入数据
     */
    QueryResponseResult upload(List<SubjectImportData> list, Long id);

    /**
     * 导入数据
     */
    QueryResponseResult upload(MultipartFile file, Long id) throws IOException;

    /**
     * 返回前两级预算科目树
     *
     * @param year
     * @return
     */
    List<SubjectVO> getSecondTree(String year);
}
