package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.Sign;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.entity.pojo.BatchPojo;
import com.bosssoft.ecds.entity.vo.*;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.response.ResultCode;
import com.bosssoft.ecds.service.SignService;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.*;
import com.bosssoft.ecds.service.VerifyService;
import com.bosssoft.ecds.util.CommonUtil;
import com.bosssoft.ecds.util.ResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@RestController
@Api(value = "开票模块")
@RequestMapping("/billInvoicing")
public class UneCBillController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private UnitManagerService unitManagerService;

    @Autowired
    private UneCbillService uneCbillService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private MessageService messageService;

    private ReentrantLock lock = new ReentrantLock();

    @Autowired
    private SignatureService signatureService;

    @Autowired
    private SignService signService;

    @Autowired
    private BillService billService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @ApiOperation(value = "查询单位开票记录请求", notes = "根据单位Id，页码，每页数据数量获取该单位开票记录")
    @RequestMapping(path = "/getPage", method = RequestMethod.GET)
    public QueryResponseResult getCbillPage(@ApiParam("当前页")int currentPage, @ApiParam()int pageSize) {
        int total = uneCbillService.billCount();
        Page<UneCbill> page = new Page<>(currentPage, pageSize, total);
        return new QueryResponseResult(CommonCode.SUCCESS, uneCbillService.selectUnecBillPage(page));
    }

    @ApiOperation(value = "查询单张票据信息", notes = "根据票据号码获取票据信息")
    @RequestMapping(value = "/getUneCbillById", method = RequestMethod.GET)
    public String getUneCbillById(String billNo) {
        UneCbillVo uneCbillVo = new UneCbillVo();
        UneCbill uneCbill = uneCbillService.getUneCBillById(billNo);
        if (uneCbill != null) {
            BeanUtil.copyProperties(uneCbill, uneCbillVo);
            return ResponseUtils.getResponse(uneCbillVo, ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(404, "票据不存在");
    }

    @ApiOperation(value = "验票", notes = "根据票据号码和校验码获取票据信息")
    @RequestMapping(value = "/getBillByIdAndCheckCode", method = RequestMethod.GET)
    public String getBillByIdAndCheckCode(String billId, String checkCode) throws ExecutionException, InterruptedException, JsonProcessingException {
        // 查bill
        QueryWrapper<UneCbill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_bill_no", billId)
                .eq("f_check_code", checkCode);
        UneCbill uneCbill = uneCbillService.getBillByIdAndCheckCode(queryWrapper);
        // 查询图片地址，并返回
        if (uneCbill != null) {
            // 获取图片地址
            NontaxBillDTO nontaxBillDTO = getNontax(uneCbill.getFBillId(), uneCbill.getFBillNo());
            String imgUrl = Convert.convert(String.class, (templateService.getImgTemplate(nontaxBillDTO)).data);
            log.info(uneCbill.getFBillId() + " :" + uneCbill.getFBillNo());
            String content = getMsgContent(uneCbill, imgUrl);
            QueryResponseResult<String> res = new QueryResponseResult<>(CommonCode.SUCCESS, content);
            return JSON.toJSONString(res);
        }
        return JSON.toJSONString(new QueryResponseResult<>(CommonCode.FAIL, null));
    }

    @ApiOperation(value = "开票验证", notes = "根据单位名称验证单位是否具备开票条件")
    @RequestMapping(value = "/addVerify", method = RequestMethod.GET)
    public ResponseResult addBillVerify(@RequestParam String unitName) {
        ArrearDTO arrearDTO = unitManagerService.isArrear(unitName);
        if (!arrearDTO.getIsunpaid() || arrearDTO == null) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.SUCCESS, "单位存在欠缴情况");
    }

    @ApiOperation(value = "获取票据", notes = "获取本次开票需要使用的票据")
    @GetMapping("/getAvaBill")
    @ResponseBody
    public QueryResponseResult getAvaBill() throws ExecutionException, InterruptedException {
        QueryResponseResult queryResponseResult = billService.getBill();
        if (queryResponseResult.data != null) {
            UneCbillDto uneCbillDto = new UneCbillDto();
            FabAgenBillDTO fabAgenBillDTO = Convert.convert(FabAgenBillDTO.class, queryResponseResult.data);
            uneCbillDto.setfBillId("0" + fabAgenBillDTO.getFbillId());
            uneCbillDto.setfBillNo(fabAgenBillDTO.getFbillNo());
            uneCbillDto.setfType(fabAgenBillDTO.getFtype());
            uneCbillDto.setCheckCode(commonUtil.getRandNum());
            return new QueryResponseResult(CommonCode.SUCCESS, uneCbillDto);
        }
        return new QueryResponseResult(CommonCode.FAIL, "不存在可用票据");
    }

    @ApiOperation(value = "获取收费项", notes = "获取本单位收费项目")
    @RequestMapping(path = "/getItemInfo", method = RequestMethod.GET)
    public QueryResponseResult getItemInfo(String unitName) {
        QueryResponseResult res = unitManagerService.getItemInfo(unitName);
        if (res.isSuccess()) {
            return new QueryResponseResult(CommonCode.SUCCESS, res.data);
        }
        return new QueryResponseResult(CommonCode.FAIL, "该单位没有可用的收费项");
    }

    @ApiOperation(value = "开票", notes = "新增一张开票")
    @ApiImplicitParam(name = "batchPojo", dataType = "BatchPojo", value = "新增开票的信息")
    @RequestMapping(path = "/addBill", method = RequestMethod.POST)
    public QueryResponseResult addBill(@RequestBody BatchPojo batchPojo) {
        log.info(batchPojo.toString());
        List<UneCbillItemDto> itemDtos = batchPojo.getItemDtos();
        lock.lock();
        try {
            AgenInfoDTO agenInfoDTO = unitManagerService.getDetailByUnitName(batchPojo.getUnitName());
            UneCbill uneCbill = commonUtil.convert(batchPojo.getUnitName(), batchPojo.getPayerDto(),
                    batchPojo.getUneCbillDto(), commonUtil.generateID(),new BigDecimal(batchPojo.getfAmt()), agenInfoDTO);
            uneCbillService.addUneCbill(uneCbill, itemDtos);
            String singMsg = JSON.toJSONString(batchPojo);
            Object o = Convert.toMap(String.class, Object.class, signatureService.sign(singMsg)).get("data");
            SignedDataDto signedDataDto = Convert.convert(SignedDataDto.class, o);
            Sign sign = new Sign();
            BeanUtil.copyProperties(signedDataDto, sign);
            sign.setSignId(uneCbill.getFId());
            signService.addSign(sign);
            uneCbillService.updateState(uneCbill.getFBillId(), uneCbill.getFBillNo(), 2);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return new QueryResponseResult(CommonCode.SUCCESS, "开票成功");
    }

    @ApiOperation(value = "获取模板", notes = "根据开票信息获取票据的电子模板")
    @RequestMapping(path = "/getTemplate", method = RequestMethod.GET)
    public String getTemplate(String billId) throws ExecutionException, InterruptedException {
        UneCbill uneCbill = uneCbillService.getUneCBillById(billId);
        if (uneCbill != null) {
            List<UneCbillItem> uneCbillItems = uneCbillService.getItems(billId);
            NontaxBillDTO nontaxBillDTO = uneCbillService.getNontaxBillDto(uneCbill, uneCbillItems);
            String url = Convert.convert(String.class, ((templateService.getTemplate(nontaxBillDTO)).data));
            return ResponseUtils.getResponse(url, ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(404, "票据不存在");
    }

    @ApiOperation(value = "获取缴款方需要的收费项目")
    @RequestMapping(path = "/getItems", method = RequestMethod.GET)
    public QueryResponseResult getItems(String fPayerTel, String checkCode) {
        QueryWrapper<UneCbill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_payer_tel", fPayerTel)
                .eq("f_check_code", checkCode);
        UneCbill uneCbill = uneCbillService.getBillByIdAndCheckCode(queryWrapper);
        QueryResponseResult<PayDto> responseResult;
        if (uneCbill != null) {
            PayDto payDto = new PayDto();
            payDto.setPayerName(uneCbill.getFPayerName());
            List<UneCbillItem> uneCbillItem = uneCbillService.getItems(String.valueOf(uneCbill.getFId()));
            List<UneCbillItemDto> itemDtos = new ArrayList<>();
            for (UneCbillItem uneCbillItem1 : uneCbillItem) {
                UneCbillItemDto uneCbillItemDto = new UneCbillItemDto();
                BeanUtil.copyProperties(uneCbillItem1, uneCbillItemDto);
                itemDtos.add(uneCbillItemDto);
            }
            payDto.setUneCbillItems(itemDtos);
            return new QueryResponseResult(CommonCode.SUCCESS, payDto);
        }
        return new QueryResponseResult(CommonCode.FAIL, "空");
    }

    @ApiOperation(value = "发送邮件通知")
    @RequestMapping(path = "/sendMail", method = RequestMethod.GET)
    public ResponseResult sendMail(String billNo) throws ExecutionException, InterruptedException, JsonProcessingException {
        UneCbill uneCbill = uneCbillService.getUneCBillById(billNo);
        if (uneCbill != null) {
            NontaxBillDTO nontaxBillDTO = getNontax(uneCbill.getFBillId(), billNo);
            String imgUrl = Convert.convert(String.class, (templateService.getImgTemplate(nontaxBillDTO)).data);
            String content = getMsgContent(uneCbill, imgUrl);
            SendMailVo sendMailVo = new SendMailVo();
            sendMailVo.setContent(content);
            sendMailVo.setMailTo(uneCbill.getFPayerEmail());
            sendMailVo.setSubject("邮件通知");
            sendMailVo.setTemplate("billTemplate.ftl");
            log.info(System.currentTimeMillis() + "");
            ResponseResult res = messageService.sendMail(sendMailVo);
            if (res.isSuccess()) {
                return ResponseResult.SUCCESS();
            }
            return ResponseResult.FAIL();
        }
        return ResponseResult.FAIL();
    }

    @ApiOperation(value = "发送短信通知")
    @RequestMapping(path = "/sendSms", method = RequestMethod.GET)
    public ResponseResult sendSms(String billNo) throws ExecutionException, InterruptedException {
        UneCbill uneCbill = uneCbillService.getUneCBillById(billNo);
        if (uneCbill != null) {
            NontaxBillDTO nontaxBillDTO = getNontax(uneCbill.getFBillId(), billNo);
            String imgUrl = Convert.convert(String.class, (templateService.getImgTemplate(nontaxBillDTO)).data);
            String content = getMsgContent(uneCbill, imgUrl);
            SendSmsVo sendSmsVo = new SendSmsVo();
            sendSmsVo.setSmsFrom("开票单位：boss");
            sendSmsVo.setSmsTo(uneCbill.getFPayerTel());
            sendSmsVo.setContent(content);
            ResponseResult res = messageService.send(sendSmsVo);
            if (res.isSuccess()) {
                return ResponseResult.SUCCESS();
            }
            return ResponseResult.FAIL();
        }
        return ResponseResult.FAIL();
    }

    @ApiOperation(value = "获取核销所需票据信息")
    @GetMapping("/writeOffInfo")
    public ResponseResult writeOffInfo(String start, String end) {
        List<UneCbill> list = uneCbillService.writeOff(start, end);
        List<WriteOffDto> writeOffDtos = new ArrayList<>();
        QueryResponseResult<WriteOffDto> responseResult;
        if (list.size() != 0) {
            for (UneCbill uneCbill : list) {
                WriteOffDto writeOffDto = new WriteOffDto();
                BeanUtil.copyProperties(uneCbill, writeOffDto);
                writeOffDto.setUneCbillItems(uneCbillService.getItems(String.valueOf(uneCbill.getFId())));
                writeOffDtos.add(writeOffDto);
            }
            responseResult = new QueryResponseResult(CommonCode.SUCCESS, writeOffDtos);
            return responseResult;
        }
        return new QueryResponseResult(CommonCode.FAIL, "票据不存在");
    }

    @ApiOperation(value = "修改票据当前状态")
    @GetMapping("/update")
    public QueryResponseResult updateState(String billNo, int state) {
        int res = uneCbillService.updateState("01160201", billNo, state);
        if (res == 1) {
            return new QueryResponseResult(CommonCode.SUCCESS, "状态已更新");
        }
        return new QueryResponseResult(CommonCode.FAIL, "更新出现故障");
    }

    @ApiOperation(value = "获取已经通过单位审核的票据")
    @RequestMapping(path = "/getPassBill", method = RequestMethod.GET)
    public QueryResponseResult getPassBill(int currentPage, int pageSize) {
        int total = uneCbillService.passBillCount();
        Page<UneCbill> page = new Page<>(currentPage, pageSize, total);
        return new QueryResponseResult(CommonCode.SUCCESS, uneCbillService.selectPassBillPage(page));
    }

    @ApiOperation(value = "获取票据模板的图片url")
    @RequestMapping(path = "/getImgUrl", method = RequestMethod.GET)
    public QueryResponseResult getImgUrl(String billId, String billNo) throws ExecutionException, InterruptedException {
        NontaxBillDTO nontaxBillDTO = getNontax(billId, billNo);
        QueryResponseResult queryResponseResult = templateService.getImgTemplate(nontaxBillDTO);
        String url = Convert.convert(String.class, queryResponseResult.data);
        log.info(url);
        if (url != null) {
            return new QueryResponseResult(CommonCode.SUCCESS, url);
        }
        return new QueryResponseResult(CommonCode.FAIL, null);
    }

    /**
     * 获取消息推送内容
     * @param uneCbill
     * @param imgUrl
     * @return
     */
    public String getMsgContent(UneCbill uneCbill,String imgUrl) {
        MessageDto messageDto = new MessageDto();
        BeanUtil.copyProperties(uneCbill, messageDto);
        // 定义开票时间
        messageDto.setFCreateTime(uneCbill.getFDate());
        messageDto.setFBillType(uneCbill.getFType());
        messageDto.setFBillImgUrl(imgUrl);
        return JSON.toJSONStringWithDateFormat(messageDto, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取NontaxBillDTO
     * @param billId
     * @param billNo
     * @return
     */
    public NontaxBillDTO getNontax(String billId, String billNo) {
        UneCbill uneCbill = uneCbillService.getUneCbillByIdAndNo(billId, billNo);
        if(uneCbill == null) {
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        Date date = uneCbill.getFDate();
        log.info(date.toString());
        String crTime = sdf.format(date);
        log.info(crTime);
        List<UneCbillItem> uneCbillItems = uneCbillService.getItems(String.valueOf(uneCbill.getFId()));
        NontaxBillDTO nontaxBillDTO = uneCbillService.getNontaxBillDto(uneCbill, uneCbillItems);
        nontaxBillDTO.setDate(crTime);
        return nontaxBillDTO;
    }
}
