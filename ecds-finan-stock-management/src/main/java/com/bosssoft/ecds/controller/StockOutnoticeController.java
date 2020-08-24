package com.bosssoft.ecds.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bosssoft.ecds.entity.PageResult;
import com.bosssoft.ecds.entity.constant.StockOutConstant;
import com.bosssoft.ecds.entity.dto.StockOutChangeDto;
import com.bosssoft.ecds.entity.dto.StockOutDto;
import com.bosssoft.ecds.entity.dto.StockOutItemDto;
import com.bosssoft.ecds.entity.po.StockOutnoticeChangePo;
import com.bosssoft.ecds.entity.po.StockOutnoticeItemPo;
import com.bosssoft.ecds.entity.po.StockOutnoticePo;
import com.bosssoft.ecds.entity.vo.StockOutPageVo;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.FinanBillService;
import com.bosssoft.ecds.service.StockOutnoticeChangeService;
import com.bosssoft.ecds.service.StockOutnoticeItemService;
import com.bosssoft.ecds.service.StockOutnoticeService;
import com.bosssoft.ecds.util.ConverUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bosssoft.ecds.entity.constant.StockOutConstant.UN_CHANGE;

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
@Api(tags = "财政出库Controller")
public class StockOutnoticeController extends BaseController {

    @Autowired
    private StockOutnoticeService outService;
    @Autowired
    private StockOutnoticeItemService itemService;
    @Autowired
    private StockOutnoticeChangeService changeService;
    @Autowired
    private FinanBillService billService;


    /**
     * 展示所有已保存等类型的出库请求
     *
     * @return 已保存出库请求的list
     */
    @ApiOperation("展示出库列表")
    @PostMapping("/showAll")
    public QueryResponseResult<PageResult> showAll(@RequestBody StockOutPageVo pageVo) {
        log.info("进入showAll方法...");
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
    log.info("退出方法，data:{}",stockOutDtos.toString());
    return new QueryResponseResult<>(CommonCode.SUCCESS, pageResult);
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
    @ApiOperation("获得出库明细列表")
    @GetMapping("/getItem")
    public QueryResponseResult getItem(@RequestParam Long pid) {
        log.info("进入getItem方法...");
        List<StockOutItemDto> outItemDtos = itemService.queryItemByPid(pid);
        outItemDtos.forEach(itemDto -> {
            itemDto.setMaxNum(billService.getCount(itemDto.getBillPrecode()));
            itemDto.setBillNo1(billService.getStartNo(itemDto.getBillPrecode()).getBillId());
        });
        log.info("退出方法，data:{}", outItemDtos);
        return new QueryResponseResult<>(CommonCode.SUCCESS, outItemDtos);
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
    @ApiOperation("新增出库请求")
    @GetMapping("/add")
    public QueryResponseResult add(@RequestParam String author) {
        return new QueryResponseResult<>(
                CommonCode.SUCCESS,
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
    @ApiOperation("保存编辑")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody StockOutVo outVo) {
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
            return ResponseResult.SUCCESS();
        } else {
            return ResponseResult.FAIL();
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
    @ApiOperation("提交审核")
    @PutMapping("/submit")
    public ResponseResult submit(@RequestParam Long id) {
        StockOutVo outVo = Convert.convert(StockOutVo.class, outService.getById(id));
        outVo.setAltercode(StockOutConstant.ALTER_EDIT);
        outVo.setChangeState(StockOutConstant.UN_CHANGE);
        Boolean result = outService.updateChangeState(id, UN_CHANGE);
        changeService.save(Convert.convert(
                StockOutnoticeChangePo.class,
                ConverUtil.outVoToChangeDto(outVo)));
        return getRes(result);
    }

    /**
     * 提交多选list
     * 更改审核状态为2：待审核
     *
     * @param outVos 出库vo的list
     *
     * @return 是否成功
     */
    @ApiOperation("提交审核批量")
    @PutMapping("/submitAll")
    public ResponseResult submitAll(@RequestBody List<StockOutVo> outVos) {
        outVos.forEach(outVo -> {
            outVo.setChangeState(StockOutConstant.UN_CHANGE);
            outVo.setAltercode(StockOutConstant.ALTER_EDIT);
        });
        List<StockOutDto> outDtos = ConverUtil.converList(StockOutDto.class, outVos);
        Boolean result = outService.updateChangeState(outDtos, UN_CHANGE);
        /*
             记录变动到出库变动表
             通过altercode 确定新增的变动表的altercode变动状态属性
             */
        changeService.saveBatch(ConverUtil.converList(
                StockOutnoticeChangePo.class,
                outVos
        ));
        return getRes(result);
    }

    /**
     * 删除多选list
     *
     * @param outVos 要删除的出库vo的list
     *
     * @return 是否成功
     */
    @ApiOperation("删除出库记录批量")
    @PutMapping("/deleteAll")
    public ResponseResult deleteAll(@RequestBody List<StockOutVo> outVos) {
        log.info("进入deleteAll方法...");
        outVos.forEach(outVo -> outVo.setAltercode(StockOutConstant.ALTER_EDIT));
        List<StockOutnoticePo> pos = ConverUtil.converList(StockOutnoticePo.class, outVos);
        Boolean result = outService.deleteByPos(pos);
        log.info("退出方法，data:{}",outVos.toString());
        changeService.saveBatch(ConverUtil.converList(
                StockOutnoticeChangePo.class,
                ConverUtil.converList(
                        StockOutChangeDto.class,
                        outVos
                )
        ));
        return getRes(result);
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
    @ApiOperation("人工审核")
    @PostMapping("check")
    public ResponseResult check(@RequestBody StockOutVo outVo) {
        log.info("进入check方法...");
        log.info(outVo.toString());
        outVo.setAltercode(StockOutConstant.ALTER_EDIT);
        Boolean result = outService.updateChangeState(outVo.getId(), outVo.getChangeState());
        changeService.save(Convert.convert(
                StockOutnoticeChangePo.class,
                ConverUtil.outVoToChangeDto(outVo)));
        log.info("退出方法，data:{}",result);
        return getRes(result);
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
    @ApiOperation("人工审核批量")
    @PutMapping("/checkAll")
    public ResponseResult checkAll(@RequestBody List<StockOutVo> outVos) {
        if (outVos == null || outVos.isEmpty()) {
            return getRes(false);

        }
        outVos.forEach(outVo -> outVo.setAltercode(StockOutConstant.ALTER_EDIT));
        List<StockOutDto> outDtos = ConverUtil.converList(StockOutDto.class, outVos);
        Integer changeState = outDtos.stream().findAny().get().getChangeState();
        log.info("changeState(use Stream):{}", changeState);
        Boolean result = outService.updateChangeState(outDtos, changeState);
        changeService.saveBatch(ConverUtil.converList(
                StockOutnoticeChangePo.class,
                outVos
        ));
        return getRes(result);
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

