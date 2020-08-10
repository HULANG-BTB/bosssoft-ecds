package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.FabAgenDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.FabAgenVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.FabAgenService;
import com.bosssoft.ecds.utils.BeanUtil;
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
 * @since 2020-08-09
 */
@Slf4j
@RestController
@RequestMapping("/fabAgen")
public class FabAgenController {

    @Autowired
    FabAgenService fabAgenService;

    /**
     *
     *
     * @description: 新增单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/save")
    public String save(@RequestBody FabAgenVO fabAgenVO){
        FabAgenDTO fabAgenDTO = new FabAgenDTO();
        BeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        fabAgenDTO = fabAgenService.save(fabAgenDTO);
        BeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(fabAgenVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 按单位编码删除单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/remove")
    public String remove(@RequestBody FabAgenVO fabAgenVO){
        FabAgenDTO fabAgenDTO = new FabAgenDTO();
        BeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        Boolean result = fabAgenService.remove(fabAgenDTO);
        BeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于修改单位信息。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/update")
    public String update(@RequestBody FabAgenVO fabAgenVO){
        FabAgenDTO fabAgenDTO = new FabAgenDTO();
        BeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        Boolean result = fabAgenService.update(fabAgenDTO);
        BeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据单位编码查询单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByAgenCode")
    public String getByAgenCode(@RequestBody FabAgenVO fabAgenVO){
        FabAgenDTO fabAgenDTO = new FabAgenDTO();
        BeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        fabAgenDTO = fabAgenService.getByAgenCode(fabAgenDTO);
        BeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(fabAgenVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据单位名查询单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByAgenName")
    public String getByAgenName(@RequestBody FabAgenVO fabAgenVO){
        FabAgenDTO fabAgenDTO = new FabAgenDTO();
        BeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        fabAgenDTO = fabAgenService.getByAgenName(fabAgenDTO);
        BeanUtil.copyProperties(fabAgenDTO,fabAgenVO);
        return ResponseUtils.getResponse(fabAgenVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于查看单位列表。
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @GetMapping("/listAll")
    public String listAll(){
        List<FabAgenDTO> fabAgenDTOList = fabAgenService.listAll();
        List<FabAgenVO> fabAgenVOList = BeanUtil.copyListProperties(fabAgenDTOList, FabAgenVO.class);
        return ResponseUtils.getResponse(fabAgenVOList,ResponseUtils.ResultType.OK);
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
        PageDTO pageDTO = BeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = fabAgenService.listByPage(pageDTO);

        pageVO = BeanUtil.copyProperties(pageDTO, PageVO.class);
        return ResponseUtils.getResponse(pageVO, ResponseUtils.ResultType.OK);
    }

    /**
     * 批量删除单位
     *
     * @param fabAgenVOList
     * @return
     */
    @PostMapping("removeBatch")
    public String removeBatch(@RequestBody List<FabAgenVO> fabAgenVOList) {
        List<FabAgenDTO> fabAgenDTOList = BeanUtil.copyListProperties(fabAgenVOList, FabAgenDTO.class);
        Boolean result = fabAgenService.removeBatch(fabAgenDTOList);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据部门编码查询单位。
     * @param {FabAgenVO} fabAgenVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByDeptCode")
    public String getByDeptCode(@RequestBody FabAgenVO fabAgenVO){
        FabAgenDTO fabAgenDTO = new FabAgenDTO();
        BeanUtil.copyProperties(fabAgenVO,fabAgenDTO);
        List<FabAgenDTO> fabAgenDTOList = fabAgenService.getByDeptCode(fabAgenDTO);
        List<FabAgenVO> fabAgenVOList= BeanUtil.copyListProperties(fabAgenDTOList,FabAgenVO.class);
        return ResponseUtils.getResponse(fabAgenVOList,ResponseUtils.ResultType.OK);
    }
}

