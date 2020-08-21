package com.bosssoft.ecds.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeChangePo;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.entity.vo.StockOutPageVo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
//import com.bosssoft.ecds.service.StockOutnoticeChangeService;
import com.bosssoft.ecds.service.StockOutnoticeChangeService;
import com.bosssoft.ecds.service.StockOutnoticeItemService;
import com.bosssoft.ecds.service.StockOutnoticeService;
import com.bosssoft.ecds.util.ConverUtil;
import com.bosssoft.ecds.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bosssoft.ecds.entity.constant.StockOutChangeConstant.UN_CHANGE;

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
@Api(tags = "StockOutnoticeController1233333333333333333333333333")
public class StockOutnoticeController {

    @Autowired
    private StockOutnoticeService outService;
    @Autowired
    private StockOutnoticeItemService itemService;
    @Autowired
    private StockOutnoticeChangeService changeService;

    private static final String SUCCESS = "success";

    /**
     * 展示所有已保存等类型的出库请求
     *
     * @return 已保存出库请求的list
     */
    @ApiOperation("showAll")
    @PostMapping("/showAll")
    public String showAll(@RequestBody StockOutPageVo pageVo) {
        Long total = outService.getCount(pageVo);
        List<StockOutDto> stockOutDtos = outService.queryByPageVo(
                pageVo,
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

    /**
     * 1.获取 ActionForm 表单数据
     * UserActionForm uForm = (UserActionForm) form;
     * 2.构造一个User对象
     * User user = new User();
     * 3.赋值
     * BeanUtils.copyProperties(user, uForm);
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
    @GetMapping("/add")
    public String add(@RequestParam String author) {
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                Convert.convert(StockOutDto.class, outService.addNewBuss(author)));
    }

    /**
     * 保存
     * 前端传入要保存的stockOutVo
     * 后端写入保存数据。
     * 在此过程中，后台会进行一次自动审核，若数据没问题，则允许保存。
     *
     * @param outVo 出库vo，其中也包含了itemvo内容
     *
     * @return 结果
     */
    @PostMapping("/save")
    public String save(@RequestBody StockOutVo outVo) {
        /*
         转换为Dto
         */
        StockOutDto outDto = Convert.convert(StockOutDto.class, outVo);
        List<StockOutItemDto> outItemDtos = ConverUtil.converList(StockOutItemDto.class, outVo.getOutItemVos());
        outItemDtos.forEach(dto -> dto.setPid(outDto.getId()));
        /*
         自动审核，
         需要出库表和出库明细表数据正确
         */
        if (outService.checkSave(outDto) && itemService.checkSave(outItemDtos)) {
            /*
             通过主键id，新增or更新出库表数据
             */
            outService.saveOrUpdate(
                    Convert.convert(StockOutnoticePo.class, outDto),
                    Wrappers.<StockOutnoticePo>lambdaQuery().eq(StockOutnoticePo::getId, outDto.getId()));

            /*
             记录变动到出库变动表
             通过altercode 确定新增的变动表的altercode变动状态属性
             */
            changeService.save(Convert.convert(
                    StockOutnoticeChangePo.class,
                    ConverUtil.outVoToChangeDto(outVo)));

            /*
             通过pid，新增or更新出库明细表
             */
            log.info(ConverUtil.converList(StockOutnoticeItemPo.class, outItemDtos).toString());
            itemService.saveChange(ConverUtil.converList(StockOutnoticeItemPo.class, outItemDtos), outDto.getId());
            /*
             返回正确通知
             */
            return ResponseUtil.getResponse(
                    ResponseUtil.ResultType.OK.getCode(),
                    ResponseUtil.ResultType.OK.getMsg(),
                    true);
        } else {
            return ResponseUtil.getResponse(
                    ResponseUtil.ResultType.NOT_MODIFIED.getCode(),
                    ResponseUtil.ResultType.NOT_MODIFIED.getMsg(),
                    false);
        }
    }

    /**
     * 提交审核
     * 用户将保存的出库请求提交到审核人处。
     * 更改审核状态为2：待审核
     *
     * @param id 出库主键
     *
     * @return 提交是否成功
     */
    @PutMapping("/submit")
    public String submit(@RequestParam Long id) {
        Boolean result = outService.updateChangeState(id, UN_CHANGE);
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                result);
    }

    /**
     * 提交多选list
     * 更改审核状态为2：待审核
     *
     * @param outVos 出库vo的list
     *
     * @return 是否成功
     */
    @PutMapping("/submitAll")
    public String submitAll(@RequestBody List<StockOutVo> outVos) {
        List<StockOutDto> outDtos = ConverUtil.converList(StockOutDto.class, outVos);
        Boolean result = outService.updateChangeState(outDtos, UN_CHANGE);
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                result);
    }

    /**
     * 删除多选list
     *
     * @param outVos 要删除的出库vo的list
     *
     * @return 是否成功
     */
    @PutMapping("/deleteAll")
    public String deleteAll(@RequestBody List<StockOutVo> outVos) {
        List<StockOutnoticePo> pos = ConverUtil.converList(StockOutnoticePo.class, outVos);
        Boolean result = outService.deleteByPos(pos);
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                result);
    }

    /**
     * 人工审核
     * 人工进行判断出库请求是否通过审核。
     * 参数中的changeState是:3通过,4退回
     *
     * @param outVo 出库vo
     *
     * @return 是否通过
     */
    @PostMapping("check")
    public String check(StockOutVo outVo) {
        Boolean result = outService.updateChangeState(outVo.getId(), outVo.getChangeState());
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                result);
    }

    /**
     * 审核多选list
     * 更改审核状态为：
     * 3：通过；
     * 4：退回。
     *
     * @param outVos 出库vo的list
     *
     * @return 是否成功
     */
    @PutMapping("/checkAll")
    public String checkAll(@RequestBody List<StockOutVo> outVos) {
        if (outVos == null || outVos.isEmpty()) {
            return ResponseUtil.getResponse(
                    ResponseUtil.ResultType.BAD_REQUEST.getCode(),
                    ResponseUtil.ResultType.BAD_REQUEST.getMsg(),
                    false);

        }
        List<StockOutDto> outDtos = ConverUtil.converList(StockOutDto.class, outVos);
        Integer changeState = outDtos.stream().findAny().get().getChangeState();
        log.info("changeState(use Stream):{}", changeState);
        Boolean result = outService.updateChangeState(outDtos, changeState);
        return ResponseUtil.getResponse(
                ResponseUtil.ResultType.OK.getCode(),
                ResponseUtil.ResultType.OK.getMsg(),
                result);
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

