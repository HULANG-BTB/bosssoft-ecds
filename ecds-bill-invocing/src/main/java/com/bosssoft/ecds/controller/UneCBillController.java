package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.MessageUneCbill;
import com.bosssoft.ecds.entity.po.Template;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.entity.pojo.BatchPojo;
import com.bosssoft.ecds.entity.vo.SendMailVo;
import com.bosssoft.ecds.entity.vo.SendSmsVo;
import com.bosssoft.ecds.entity.vo.UneCbillVo;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.client.MessageService;
import com.bosssoft.ecds.service.client.TemplateService;
import com.bosssoft.ecds.service.client.UnitManagerService;
import com.bosssoft.ecds.service.VerifyService;
import com.bosssoft.ecds.util.CommonUtil;
import com.bosssoft.ecds.util.ResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/billInvoicing")
public class UneCBillController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private UnitManagerService unitManagerService;

    @Autowired
    private UneCbillService uneCbillService;

    //@Autowired
    //private Redisson redisson;

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private MessageService messageService;

    private ReentrantLock lock = new ReentrantLock();

    /**
     * 分页查询当前单位的开票记录
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(path = "/getPage", method = RequestMethod.GET)
    public IPage<UneCbillVo> getCbillPage(int currentPage, int pageSize) {
        int total = uneCbillService.billCount();
        Page<UneCbill> page = new Page<>(currentPage, pageSize, total);
        return uneCbillService.selectUnecBillPage(page);
    }

    /**
     * 查询单张票据
     *
     * @param billId
     * @return
     */
    @RequestMapping(value = "/getUneCbillById", method = RequestMethod.GET)
    public String getUneCbillById(String billId) {
        log.info(billId);
        UneCbillVo uneCbillVo = new UneCbillVo();
        UneCbill uneCbill = uneCbillService.getUneCBillById(billId);
        if (uneCbill != null) {
            BeanUtil.copyProperties(uneCbill, uneCbillVo);
            return ResponseUtils.getResponse(uneCbillVo, ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(404, "票据不存在");
    }

    /**
     * 根据票据ID和校验码查询票据
     *
     * @param billId
     * @param checkCode
     * @return
     */
    @RequestMapping(value = "/getBillByIdAndCheckCode", method = RequestMethod.GET)
    public String getBillByIdAndCheckCode(String billId, String checkCode) throws ExecutionException, InterruptedException, JsonProcessingException {
        QueryWrapper<UneCbill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_bill_id", billId)
                .eq("f_check_code", checkCode);
        UneCbill uneCbill = uneCbillService.getBillByIdAndCheckCode(queryWrapper);
        log.info("success");
        ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
        QueryResponseResult<MessageUneCbill> res;
        if (uneCbill != null) {
            MessageUneCbill messageUneCbill = new MessageUneCbill();
            BeanUtil.copyProperties(uneCbill, messageUneCbill);
            log.info(messageUneCbill.getFBillId() + " :" + messageUneCbill.getFBillNo());
            NontaxBillDTO nontaxBillDTO = new NontaxBillDTO();
            nontaxBillDTO.setBillCode(messageUneCbill.getFBillId());
            nontaxBillDTO.setSerialCode(messageUneCbill.getFBillNo());
            //String url = templateService.getTemplate(nontaxBillDTO);
            messageUneCbill.setImgUrl("url");
            res = new QueryResponseResult<>(CommonCode.SUCCESS, messageUneCbill);
            return writer.writeValueAsString(res);

        }
        res = new QueryResponseResult<>(CommonCode.FAIL, null);
        return writer.writeValueAsString(res);
    }

    /**
     * 开票前验证
     *
     * @param unitName
     * @return
     */
    @RequestMapping(value = "/addVerify", method = RequestMethod.GET)
    public String addBillVerify(@RequestParam String unitName) {
        //TODO 执行开票前验证（单位是否欠缴/开票点开票数是否已经达到上限/当前单位是否有可用票据）--->返回预警信息
        WarnDto warnDto = new WarnDto();
        /*
        int total = uneCbillService.billCount();
        if (unitManagerService.isOutLimit(unitName)) {
            warnDto.setEvtName("单位开票数限制");
            warnDto.setMntCont("单位目前开票数已经达到最大开票限制，禁止开票");
            warnDto.setMntTime(new Date());
            return ResponseUtils.getResponse(失败);
        }else if(unitManagerService.isArrear(batchPojo.getUnitName())){
            warnDto.setEvtName("单位欠缴");
            warnDto.setMntCont("单位目前已欠缴，禁止开票");
            warnDto.setMntTime(new Date());
        } else if(!unitManagerService.hasAvailableBill) {
            warnDto.setEvtName("单位无可用票据");
            warnDto.setMntCont("单位目前已没有可以使用的票据，禁止开票");
            warnDto.setMntTime(new Date());
        }
        */
        return ResponseUtils.getResponse(ResponseUtils.ResultType.OK);
    }

    /**
     * 获取单位信息
     *
     * @param unitName
     * @return
     */
    @RequestMapping(value = "/getUnitInfo", method = RequestMethod.GET)
    public String getUnitInfo(@RequestParam String unitName) {
        return unitManagerService.getDetailByUnitName(unitName);
    }

    /**
     * 获取单位可用票据信息
     *
     * @param unitName
     * @return
     */
    public String getBillInfo(@RequestParam String unitName) {
        //查询到可用票据，生成唯一校验码
        String checkCode = "";
        return unitManagerService.hasAvailableBill(unitName);
    }

    /**
     * 开票选择开票项目列表
     * 项目（通过UnitManagerService调用远程接口查询单位包含项目list）：项目编码，项目名称，收费标准，计量单位
     *
     * @return
     */
    @RequestMapping(path = "getItemList", method = RequestMethod.GET)
    public String getItemLisst(String unitName) {
        String itemList = unitManagerService.getItemList(unitName);
        return "";
    }

    /**
     * 新增开票
     * 所需字段：
     * 单位（通过UnitManagerService调用远程接口查询）：区划id，单位识别码，单位编码，开票点id，开票点编码，开票点名称
     * 缴款人信息 http request body中直接获取
     * 项目（接收项目列表）：项目编码，项目名称，收费标准，计量单位
     * 编制人：当前登录用户(redis中获取)
     * 校验码：commonUtil工具类生成
     *
     * @return
     */
    @RequestMapping(path = "/addBill", method = RequestMethod.POST)
    public String addBill(@RequestBody BatchPojo batchPojo) {
        List<UneCbillItemDto> itemDtos = batchPojo.getItemDtos();
        //2 分布式下，防止多个进程同时开票
        //RLock lock = redisson.getLock(lockKey);
        lock.lock();
        try {
            //TODO 调用服务新增票据
            UneCbill uneCbill = commonUtil.convert(batchPojo.getUnitName(), batchPojo.getPayerDto(),
                    batchPojo.getUneCbillDto(), commonUtil.generateID(), batchPojo.getfAmt());
            uneCbillService.addUneCbill(uneCbill, itemDtos);
        } finally {
            lock.unlock();
        }
        return ResponseUtils.getResponse(ResponseUtils.ResultType.OK);
    }

    /**
     * 获取票据模板
     *
     * @param billId
     * @return
     */
    @RequestMapping(path = "/getTemplate", method = RequestMethod.GET)
    public String getTemplate(String billId) throws ExecutionException, InterruptedException {
        UneCbill uneCbill = uneCbillService.getUneCBillById(billId);
        if (uneCbill != null) {
            NontaxBillDTO nontaxBillDTO = uneCbillService.convert(uneCbill);
            List<BillItemDTO> uneCbillItemDtos = new ArrayList<>();
            List<UneCbillItem> uneCbillItems = uneCbillService.getItems(billId);
            for (UneCbillItem uneCbillItem : uneCbillItems) {
                BillItemDTO billItemDTO = uneCbillService.convertToItem(uneCbillItem);
                uneCbillItemDtos.add(billItemDTO);
            }
            nontaxBillDTO.setItems(uneCbillItemDtos);
            log.info(JSON.toJSONString(nontaxBillDTO));
            String url = templateService.getTemplate(nontaxBillDTO);
            return ResponseUtils.getResponse(url, ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(404, "票据不存在");
    }

    /**
     * 缴款方需要的DTO
     *
     * @param fPayerTel
     * @param checkCode
     * @return
     */
    @RequestMapping(path = "/getItems", method = RequestMethod.GET)
    public String getItems(String fPayerTel, String checkCode) {
        QueryWrapper<UneCbill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_payer_tel", fPayerTel)
                .eq("f_check_code", checkCode);
        UneCbill uneCbill = uneCbillService.getBillByIdAndCheckCode(queryWrapper);
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
            return ResponseUtils.getResponse(JSON.toJSONString(payDto), ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(404, "票据不存在");
    }

    /**
     * 发送邮件
     *
     * @param billId
     * @return
     */
    @RequestMapping(path = "/sendMail", method = RequestMethod.GET)
    public ResponseResult sendMail(String billId) throws ExecutionException, InterruptedException, JsonProcessingException {
        UneCbill uneCbill = uneCbillService.getUneCBillById(billId);
        if (uneCbill != null) {
            MessageDto messageDto = new MessageDto();
            BeanUtil.copyProperties(uneCbill, messageDto);
            messageDto.setFBillType("通用非税票据");
            messageDto.setFBillImgUrl("url");
            SendMailVo sendMailVo = new SendMailVo();
            String str = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(messageDto);
            sendMailVo.setContent(str);
            sendMailVo.setMailTo(uneCbill.getFPayerEmail());
            sendMailVo.setSubject("邮件通知");
            sendMailVo.setTemplate("billTemplate.ftl");
            log.info(System.currentTimeMillis() + "");
            ResponseResult res = messageService.sendMail(sendMailVo);
            //JSONObject jsonObject = JSONObject.parseObject(res);
            if (res.isSuccess()) {
                return ResponseResult.SUCCESS();
            }
            return ResponseResult.FAIL();
        }
        return ResponseResult.FAIL();
    }

    /**
     * 发送短信
     *
     * @param billId
     * @return
     */
    @RequestMapping(path = "/sendSms", method = RequestMethod.GET)
    public ResponseResult sendSms(String billId) throws ExecutionException, InterruptedException {
        UneCbill uneCbill = uneCbillService.getUneCBillById(billId);
        if (uneCbill != null) {
            MessageDto messageDto = new MessageDto();
            BeanUtil.copyProperties(uneCbill, messageDto);
            messageDto.setFBillImgUrl("url");
            SendSmsVo sendSmsVo = new SendSmsVo();
            sendSmsVo.setSmsFrom("开票单位：boss");
            sendSmsVo.setSmsTo(uneCbill.getFPayerTel());
            sendSmsVo.setContent(JSON.toJSONString(messageDto));
            log.info("before");
            ResponseResult res = messageService.send(sendSmsVo);
            //JSONObject jsonObject = JSONObject.parseObject(res);
            if (res.isSuccess()) {
                return ResponseResult.SUCCESS();
            }
            return ResponseResult.FAIL();
        }
        return ResponseResult.FAIL();
    }

    /**
     * 查询票据
     *
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/writeOffInfo")
    public String writeOffInfo(String start, String end) {
        List<UneCbill> list = uneCbillService.writeOff(start, end);
        List<WriteOffDto> writeOffDtos = new ArrayList<>();
        if (list.size() != 0) {
            for (UneCbill uneCbill : list) {
                WriteOffDto writeOffDto = new WriteOffDto();
                BeanUtil.copyProperties(uneCbill, writeOffDto);
                writeOffDto.setUneCbillItems(uneCbillService.getItems(String.valueOf(uneCbill.getFId())));
                writeOffDtos.add(writeOffDto);
            }
            return ResponseUtils.getResponse(writeOffDtos, ResponseUtils.ResultType.OK);
        }
        return ResponseUtils.getResponse(404, "需要核销票据不存在");
    }
}
