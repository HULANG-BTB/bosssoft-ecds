package com.bosssoft.ecds.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.entity.vo.StockOutItemVo;
import com.bosssoft.ecds.entity.vo.StockOutPageVo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.service.StockOutnoticeItemService;
import com.bosssoft.ecds.service.StockOutnoticeService;
import com.bosssoft.ecds.util.ConverUtil;
import com.bosssoft.ecds.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author misheep
 * @since 2020-08-11
 */
@Slf4j
@RestController
@RequestMapping("/stock-out")
@CrossOrigin
public class StockOutnoticeController {

    @Autowired
    private StockOutnoticeService outService;
    @Autowired
    private StockOutnoticeItemService itemService;

    private static final String SUCCESS = "success";

    /**
     * 展示所有已保存等类型的出库请求
     *
     * @return 已保存出库请求的list
     */
    @PostMapping("/showAll")
    public String showAll(@RequestBody StockOutPageVo pageVo) {

        Long total = outService.getCount(pageVo.getChangeState());
        log.info("--------------------{}", pageVo.toString());
        List<StockOutDto> stockOutDtos = outService.queryByChangeState(
                pageVo.getChangeState(),
                pageVo.getPage(),
                pageVo.getLimit());

        List<StockOutVo> stockOutVos = ConverUtil.converList(StockOutVo.class, stockOutDtos);
        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(
                total,
                pageVo.getLimit(),
                pageVo.getPage(),
                stockOutVos);

        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                pageResult);
    }
    //Convert.convert(StockOutVo.class, stockOutDtos);
//        List<StockOutD>

//        Convert.toList()

    /**
     * 1.获取 ActionForm 表单数据
     * UserActionForm uForm = (UserActionForm) form;
     * 2.构造一个User对象
     * User user = new User();
     * 3.赋值
     * BeanUtils.copyProperties(user, uForm);
     */

        /*
        MailDto mailDto = DozerUtils.map(mailVo, MailDto.class);
        // 获取匹配记录数
        Long total = mailService.getTotal(mailDto);
        // 查询匹配记录
        List<MailDto> mails = mailService.listPage(mailDto, mailVo.getPage(), mailVo.getLimit());
        // 封装结果集，携带页面参数
        PageResult pageResult = new PageResult(total, mailVo.getLimit(), mailVo.getPage(), mails);

        return ResponseUtils.getResponse(
                ResponseUtils.ResultType.OK.getCode(),
                ResponseUtils.ResultType.OK.getMsg(),
                pageResult);
        */

    /**
     * 通过pid（即：出库表id）获取明细表
     *
     * @param pid 父id
     *
     * @return 明细list
     */
    @GetMapping("/getItem")
    public String getItem(@RequestParam Long pid) {
        log.info("-----------------------pid:{}", pid);
        List<StockOutItemDto> outItemDtos = itemService.queryItemByPid(pid);
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                outItemDtos);
    }

    /**
     * 新增出库请求
     * 用户在前端点击“新增”，
     * 后台返回给用户一个单号。
     * 用户同时要做的还包括：获取全部仓库，获取全部可用票据。
     *
     * @param author 编制人
     *
     * @return 出库通知单号（主键id）
     */
    @PostMapping("/add")
    public String add(@RequestParam String author) {
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                outService.addNewBuss(author));
    }

    /**
     * 保存
     * 前端传入要保存的stockOutVo
     * 后端写入保存数据。
     * 在此过程中，后台会进行一次自动审核，若数据没问题，则允许保存。
     *
     * \@RequestBody StockOutVo outVo,
     * \@RequestBody List<StockOutItemVo> outItemVos
     * Map<StockOutVo, List<StockOutItemVo>>
     *
     * @param outMap key=出库表数据，value=出库明细表数据list
     *
     * @return 结果
     */
    @PostMapping("/save")
    public String save(@RequestBody Map<String, Object> outMap) {
        /**
         * 提取并转换成vo实体
         */
        JSONObject obj = new JSONObject(outMap);
        StockOutVo outVo = Convert.convert(
                StockOutVo.class,
                obj.getJSONObject("outVo"));
        List<StockOutItemVo> outItemVos = ConverUtil.converList(
                StockOutItemVo.class,
                obj.getJSONArray("outItemVos"));
        /**
         * 转换为Dto
         */
        StockOutDto outDto = Convert.convert(StockOutDto.class, outVo);
        List<StockOutItemDto> outItemDtos = ConverUtil.converList(StockOutItemDto.class, outItemVos);
        outItemDtos.forEach(dto -> dto.setPid(outDto.getId()));
        /**
         * 自动审核，
         * 需要出库表和出库明细表数据正确
         */
        if (outService.checkSave(outDto, outItemDtos)) {
            /**
             * 通过主键id，新增or更新出库表数据
             */
            outService.saveOrUpdate(
                    Convert.convert(StockOutnoticePo.class, outDto),
                    Wrappers.<StockOutnoticePo>lambdaQuery().eq(StockOutnoticePo::getId, outDto.getId()));
            /**
             * 通过altercode 确定新增的变动表的altercode变动状态属性
             */


            //变动表代码


            /**
             * 通过pid，新增or更新出库明细表
             */
            itemService.saveOrUpdateBatch(
                    ConverUtil.converList(StockOutnoticeItemPo.class, outItemDtos));

        }
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg());
    }

    @PostMapping("/testmap")
    public String testMap() {
        Map<String, Object> outMap = new HashMap<>();
        StockOutVo outVo = Convert.convert(StockOutVo.class, outService.getById(1));
        List<StockOutItemVo> outItemVos = ConverUtil.converList(
                StockOutItemVo.class,
                itemService.lambdaQuery()
                        .eq(StockOutnoticeItemPo::getPid, 1)
                        .list());
        outMap.put("outVo", outVo);
        outMap.put("outItemVos", outItemVos);
        String result = JSONUtil.toJsonStr(outMap);
        log.info("-------------{}", result);
        return result;
    }


    /**
     * 提交审核
     * 用户将保存的出库请求提交到审核人处。
     *
     * @return 提交是否成功
     */
    @RequestMapping("/sentToCheck")
    public String sentToCheck() {

        return SUCCESS;
    }

    /**
     * 人工审核
     * 人工进行判断出库请求是否通过审核。
     * 参数中的changeState确定是3通过or4退回
     *
     * @param stockOutVo 出库请求vo
     *
     * @return 是否通过
     */
    @RequestMapping("check")
    public String check(StockOutVo stockOutVo) {
        StockOutnoticePo outnoticePo = Convert.convert(StockOutnoticePo.class, stockOutVo);
        if(outnoticePo.updateById()) {
            return ResponseUtil.getResponse(
                    ResponseUtil.ResultType.OK.getCode(),
                    ResponseUtil.ResultType.OK.getMsg());
        } else {
            return ResponseUtil.getResponse(
                    ResponseUtil.ResultType.NOT_MODIFIED.getCode(),
                    ResponseUtil.ResultType.NOT_MODIFIED.getMsg(),
                    false);
        }
    }


    /**
     * 出库，也许没用？
     *
     * @return 结果
     */
    @RequestMapping("/stockOut")
    public String stockOut() {

        return SUCCESS;
    }


    /**
     * \@DeleteMapping("/pollution/delete/{id}")
     * public ResponseData deletePollutionById(@PathVariable("id")String id, @RequestBody PollutionData data){
     *
     *     System.out.println(id);
     *     System.out.println(data);
     *     return new ResponseData(CodeEnum.SUCCESS.getCode(),MsgEnum.SUCCESS.getMsg(),null);
     * }
     */
}

