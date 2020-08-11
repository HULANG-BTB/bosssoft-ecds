package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.entity.dto.DeptDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.vo.DeptVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.DeptService;
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
 * @since 2020-08-09
 */
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService fabDeptService;

    /**
     *
     *
     * @description: 新增部门。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/save")
    public String save(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        fabDeptDTO = fabDeptService.save(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return ResponseUtils.getResponse(fabDeptVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 按部门编码删除部门。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/remove")
    public String remove(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        Boolean result = fabDeptService.remove(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于修改部门信息。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/update")
    public String update(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        Boolean result = fabDeptService.update(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return ResponseUtils.getResponse(result,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据部门编码查询部门。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByDeptCode")
    public String getByDeptCode(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        fabDeptDTO = fabDeptService.getByDeptCode(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return ResponseUtils.getResponse(fabDeptVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 根据部门名查询部门。
     * @param {FabDeptVO} fabDeptVO
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @PostMapping("/getByDeptName")
    public String getByDeptName(@RequestBody DeptVO fabDeptVO){
        DeptDTO fabDeptDTO = new DeptDTO();
        MyBeanUtil.copyProperties(fabDeptVO,fabDeptDTO);
        fabDeptDTO = fabDeptService.getByDeptName(fabDeptDTO);
        MyBeanUtil.copyProperties(fabDeptDTO,fabDeptVO);
        return ResponseUtils.getResponse(fabDeptVO,ResponseUtils.ResultType.OK);
    }

    /**
     *
     *
     * @description: 用于查看部门列表。
     * @return: {String}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    @GetMapping("/listAll")
    public String listAll(){
        List<DeptDTO> fabDeptDTOList = fabDeptService.listAll();
        List<DeptVO> fabDeptVOList = MyBeanUtil.copyListProperties(fabDeptDTOList, DeptVO.class);
        return ResponseUtils.getResponse(fabDeptVOList,ResponseUtils.ResultType.OK);
    }

    /**
     * 通过分页查询
     *
     * @param pageVO
     * @return
     */
    @PostMapping("listByPage")
    public String listByPage(@RequestBody PageVO pageVO) {
        PageDTO pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = fabDeptService.listByPage(pageDTO);

        pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return ResponseUtils.getResponse(pageVO, ResponseUtils.ResultType.OK);
    }

    /**
     * 批量删除
     *
     * @param fabDeptVOList
     * @return
     */
    @PostMapping("removeBatch")
    public String removeBatch(@RequestBody List<DeptVO> fabDeptVOList) {
        List<DeptDTO> fabDeptDTOList = MyBeanUtil.copyListProperties(fabDeptVOList, DeptDTO.class);
        Boolean result = fabDeptService.removeBatch(fabDeptDTOList);
        return ResponseUtils.getResponse(result, ResponseUtils.ResultType.OK);
    }
}

