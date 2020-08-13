package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDto;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.entity.vo.ArchiveOverViewVo;
import com.bosssoft.ecds.service.ArchiveOverViewService;
import com.bosssoft.ecds.util.ResponseUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuke
 * @since 2020-08-11
 */
@Api(value = "归档")
@RestController
@RequestMapping("/archive")
public class ArchiveOverViewController {

    @Autowired
    private ArchiveOverViewService service;

    /**
     * 根据单位信息查询单位票据归档的信息
     * @param query
     * @return String
     */
    @PostMapping("/unit/info")
    public String queryArchiveInfo(@RequestBody ArchiveOverViewQuery query){
        ArchiveOverViewDto archiveOverViewDto = service.queryOverViewArchiveInfo(query);
        ArchiveOverViewVo vo = new ArchiveOverViewVo();
        BeanUtil.copyProperties(archiveOverViewDto,vo);
        return ResponseUtils.getResponse(vo,ResponseUtils.ResultType.OK);
    }

    /**
     * 获取所有单位的归档信息,并且展示给前台
     * @return
     */
    @GetMapping("/fina/allInfo")
    public String queryArchiveAllInfo(){
        /**
         * 获取全部的单位信息
         */
        List<ArchiveOverViewDto> archiveOverViewDtos = service.queryOverViewArchiveAllInfo();
        List<ArchiveOverViewVo> voList = new ArrayList<>();

        /**
         * 类型转换
         */
        ArchiveOverViewVo vo = null;
        for (ArchiveOverViewDto dto:archiveOverViewDtos) {
            vo = new ArchiveOverViewVo();
            BeanUtil.copyProperties(dto, vo);
            voList.add(vo);
        }

        return ResponseUtils.getResponse(voList,ResponseUtils.ResultType.OK);
    }

    /**
     * 用户自定义设置归档周期
     * 暂时 系统规定归档周期
     * @param cycleTime
     * @return
     */
    public String setArchiveCycle(int cycleTime){

        return "";
    }
}

