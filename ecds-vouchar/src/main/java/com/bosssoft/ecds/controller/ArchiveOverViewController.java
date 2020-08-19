package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.ecds.entity.dto.ArchiveOverViewDTO;
import com.bosssoft.ecds.entity.query.ArchiveOverViewQuery;
import com.bosssoft.ecds.entity.vo.ArchiveOverViewVO;
import com.bosssoft.ecds.service.ArchiveOverViewService;
import com.bosssoft.ecds.utils.ResponseUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 根据单位信息查询单位票据归档的信息 单位信息中包括基础信息
     *
     * @param query
     * @return String
     */
    @PostMapping("/unit/info")
    public String queryArchiveInfo(@RequestBody ArchiveOverViewQuery query){
        ArchiveOverViewDTO archiveOverViewDto = service.queryOverViewArchiveInfo(query);
        ArchiveOverViewVO vo = new ArchiveOverViewVO();
        BeanUtil.copyProperties(archiveOverViewDto,vo);
        return ResponseUtils.getResponse(vo, ResponseUtils.ResultType.SUCCESS);
    }

    /**
     * 获取所有单位的归档信息,并且展示给前台
     *
     * @return
     */
    @PostMapping("/fina/allInfo")
    public String queryArchiveAllInfo(@RequestBody ArchiveOverViewQuery query) {
        /**
         * 获取全部的单位信息
         */
        List<ArchiveOverViewDTO> archiveOverViewDTOS = service.queryOverViewArchiveInfos(query);
        List<ArchiveOverViewVO> voList = new ArrayList<>();

        /**
         * 类型转换
         */
        ArchiveOverViewVO vo = null;
        for (ArchiveOverViewDTO dto : archiveOverViewDTOS) {
            vo = new ArchiveOverViewVO();
            BeanUtil.copyProperties(dto, vo);
            voList.add(vo);
        }

        return ResponseUtils.getResponse(voList, ResponseUtils.ResultType.SUCCESS);
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

