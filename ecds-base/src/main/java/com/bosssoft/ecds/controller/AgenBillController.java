package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.AgenBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.AgenBillVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.AgenBillService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzh
 * @since 2020-08-12
 */
@RestController
@RequestMapping("/agenBill")
@Api(value = "单位可用票据接口")
public class AgenBillController {

    @Autowired
    private AgenBillService agenBillService;

    /**
     * 插入单位可用项目相关信息
     *
     * @param agenBillVO 可用项目相关信息
     * @return
     */
    @ApiOperation(value = "添加单位可用票据关系")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody AgenBillVO agenBillVO) {
        AgenBillDTO agenBillDTO = MyBeanUtil.myCopyProperties(agenBillVO, AgenBillDTO.class);
        return agenBillService.save(agenBillDTO);
    }

    /**
     * 删除项目
     *
     * @param agenBillVO
     * @return
     */
    @ApiOperation(value = "删除单位可用票据关系")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody AgenBillVO agenBillVO) {
        AgenBillDTO agenBillDTO = MyBeanUtil.myCopyProperties(agenBillVO, AgenBillDTO.class);
        return agenBillService.delete(agenBillDTO);
    }

    /**
     * 批量删除
     *
     * @param agenBillVOList
     * @return
     */
    @ApiOperation(value = "批量删除单位可用票据关系")
    @PostMapping("/batchDelete")
    public ResponseResult batchDelete(@RequestBody List<AgenBillVO> agenBillVOList) {
        List<AgenBillDTO> agenBillDTOList = MyBeanUtil.copyListProperties(agenBillVOList, AgenBillDTO::new);
        return agenBillService.batchDelete(agenBillDTOList);
    }

    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("/listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestBody PageVO pageVO) {
        PageDTO<BillTypePO> pageDTO = MyBeanUtil.myCopyProperties(pageVO, PageDTO.class);
        return agenBillService.listByPage(pageDTO);
    }

}

