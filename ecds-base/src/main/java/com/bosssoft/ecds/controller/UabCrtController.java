package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.UabCrtDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.UabCrtVO;
import com.bosssoft.ecds.service.UabCrtService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import com.bosssoft.ecds.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vihenne
 * @since 2020-08-10
 */
@Slf4j
@RestController
@RequestMapping("/uabCrt")
public class UabCrtController {

    @Autowired
    UabCrtService uabCrtService;

    /**
     *
     *
     * @description: 新增领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/save")
    public String save(@RequestBody UabCrtVO uabCrtVO){
        UabCrtDTO uabCrtDTO = new UabCrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        uabCrtDTO = uabCrtService.save(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return ResponseUtils.getResponse(uabCrtVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 按领购证ID删除领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/remove")
    public String remove(@RequestBody UabCrtVO uabCrtVO){
        UabCrtDTO uabCrtDTO = new UabCrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        Boolean result = uabCrtService.remove(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于修改领购证信息。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/update")
    public String update(@RequestBody UabCrtVO uabCrtVO){
        UabCrtDTO uabCrtDTO = new UabCrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        Boolean result = uabCrtService.update(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据领购证编码查询领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByCrtCode")
    public String getByCrtCode(@RequestBody UabCrtVO uabCrtVO){
        UabCrtDTO uabCrtDTO = new UabCrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        uabCrtDTO = uabCrtService.getByCrtCode(uabCrtDTO);
        MyBeanUtil.copyProperties(uabCrtDTO,uabCrtVO);
        return ResponseUtils.getResponse(uabCrtVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据单位编码查询领购证。
     * @param {UabCrtVO} uabCrtVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByAgenCode")
    public String getByAgenCode(@RequestBody UabCrtVO uabCrtVO){
        UabCrtDTO uabCrtDTO = new UabCrtDTO();
        MyBeanUtil.copyProperties(uabCrtVO,uabCrtDTO);
        List<UabCrtDTO> uabCrtDTOList = uabCrtService.getByAgenCode(uabCrtDTO);
        MyBeanUtil.copyListProperties(uabCrtDTOList,UabCrtVO.class);
        return ResponseUtils.getResponse(uabCrtDTOList,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于查看领购证列表。
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @GetMapping("/listAll")
    public String listAll(){
        List<UabCrtDTO> uabCrtDTOList = uabCrtService.listAll();
        List<UabCrtVO> uabCrtVOList = MyBeanUtil.copyListProperties(uabCrtDTOList, UabCrtVO.class);
        return ResponseUtils.getResponse(uabCrtVOList,ResponseUtils.ResultType.OK);
    }

    /**
     * 通过分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("listByPage")
    public String listByPage(@RequestParam("page") Long page, @RequestParam("limit") Long limit, @RequestParam("keyword") String keyword) {
        PageVO pageVO = new PageVO();
        pageVO.setLimit(limit);
        pageVO.setPage(page);
        pageVO.setKeyword(keyword);
        PageDTO pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = uabCrtService.listByPage(pageDTO);

        pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return ResponseUtils.getResponse(pageVO, ResponseUtils.ResultType.OK);
    }

    /**
     * 批量删除领购证
     *
     * @param uabCrtVOList
     * @return
     */
    @PostMapping("removeBatch")
    public String removeBatch(@RequestBody List<UabCrtVO> uabCrtVOList) {
        List<UabCrtDTO> uabCrtDTOList = MyBeanUtil.copyListProperties(uabCrtVOList, UabCrtDTO.class);
        Boolean result = uabCrtService.removeBatch(uabCrtDTOList);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

}

