package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.entity.dto.WriteOffDetailDTO;
import com.bosssoft.ecds.entity.dto.WriteOffReceiveDTO;
import com.bosssoft.ecds.entity.dto.WriteOffResultDTO;
import com.bosssoft.ecds.entity.vo.*;
import com.bosssoft.ecds.service.FinancialWriteOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 财务段的核销服务
 */
@RestController
@RequestMapping("/financial")
@CrossOrigin
public class FinancialWriteOffController {

    @Autowired
    private FinancialWriteOffService financialWriteOffService;

    /**
     * 获取单位端传来的核销信息
     * 接收一段时间的下级单位传来的核销信息
     *
     * @param writeOffReceiveUnitInfoVO
     * @return java.util.List
     */
    @ResponseBody
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public List<WriteOffReceiveVO> receive(@RequestBody WriteOffReceiveUnitInfoVO writeOffReceiveUnitInfoVO) {
        // 这里DTO要修改成VO， 为了方便测试所以没改，以下的一样
        List<WriteOffReceiveVO> list = new ArrayList<>();
        for (WriteOffReceiveDTO writeOffReceiveDTO : financialWriteOffService.receive(writeOffReceiveUnitInfoVO.getfAgenIdCode())){
            WriteOffReceiveVO writeOffReceiveVO = new WriteOffReceiveVO();
            writeOffReceiveVO.setNo(writeOffReceiveDTO.getFNo());
            writeOffReceiveVO.setDate(writeOffReceiveDTO.getFDate());
            writeOffReceiveVO.setReason(writeOffReceiveDTO.getFMemo());
            writeOffReceiveVO.setAuthor(writeOffReceiveDTO.getFAuthor());
            list.add(writeOffReceiveVO);
        }
        return list;
    }

    /**
     * 退回单位端传来的核销信息
     * 需要退回的核销信息根据单位ID回到单位端进行修改
     *
     * @param writeOffReceiveVOList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendBack", method = RequestMethod.POST)
    public boolean sendBack(@RequestBody List<WriteOffReceiveVO> writeOffReceiveVOList) {
        List<WriteOffReceiveDTO> list = new ArrayList<>(writeOffReceiveVOList.size());
        for (WriteOffReceiveVO writeOffReceiveVO : writeOffReceiveVOList){
            WriteOffReceiveDTO writeOffReceiveDTO = new WriteOffReceiveDTO();
            writeOffReceiveDTO.setFNo(writeOffReceiveVO.getNo());
            writeOffReceiveDTO.setFDate(writeOffReceiveVO.getDate());
            writeOffReceiveDTO.setFMemo(writeOffReceiveVO.getReason());
            writeOffReceiveDTO.setFAuthor(writeOffReceiveVO.getAuthor());
            list.add(writeOffReceiveDTO);
        }
        financialWriteOffService.sendBack(list);
        return true;
    }

    /**
     * 获取核销信息详情
     *
     * @param writeOffDetailRequestVO
     * @return java.lang.Object
     */
    @ResponseBody
    @RequestMapping(value = "/getDetails", method = RequestMethod.POST)
    public String getDetails(@RequestBody WriteOffDetailRequestVO writeOffDetailRequestVO) {
        // 根据业务单号获取详细
        return writeOffDetailRequestVO.getfNo();
    }

    /**
     * 获取单位电子档案
     *
     * @param object
     * @return java.lang.Object
     */
    @ResponseBody
    @RequestMapping(value = "/getUnitDetails", method = RequestMethod.POST)
    public Object getUnitDetails(Object object) {
        // 这里直接调用接口 用Service
        return null;
    }

    /**
     * 存入核销结果
     *
     * @param writeOffResultDTO
     * @return java.lang.Object
     */
    @ResponseBody
    @RequestMapping(value = "/setResult", method = RequestMethod.POST)
    public boolean setResult(WriteOffResultDTO writeOffResultDTO) {
        // 修改 f_check_result 值
        // writeOffDetailDTO 有接口直接获得
        WriteOffDetailDTO writeOffDetailDTO = new WriteOffDetailDTO();
        return financialWriteOffService.setResult(writeOffDetailDTO , writeOffResultDTO);
    }

    /**
     * 实现搜索功能
     * 后端搜索完对多条数据进行分页
     *
     * @param searchFromVo
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestBody SearchFromVO searchFromVo){
        System.out.println(searchFromVo.getNumber());
        return "success";
    }

    /**
     * 通过审核
     * 存储数据
     *
     * @param writeOffDetailVO
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    public String pass(@RequestBody WriteOffDetailVO writeOffDetailVO){
        return "success";
    }

    /**
     * 未通过审核
     * 存储数据
     *
     * @param writeOffDetailVO
     * @return java.lang.String
     */
    @ResponseBody
    @RequestMapping(value = "/unPass", method = RequestMethod.POST)
    public String unPass(@RequestBody WriteOffDetailVO writeOffDetailVO){
        return "not success";
    }
}
