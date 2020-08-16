package com.bosssoft.ecds.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.entity.dto.*;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.entity.po.UneCbillItem;
import com.bosssoft.ecds.entity.pojo.BatchPojo;
import com.bosssoft.ecds.entity.vo.UneCbillVo;
import com.bosssoft.ecds.service.UneCbillService;
import com.bosssoft.ecds.service.UnitManagerService;
import com.bosssoft.ecds.util.CommonUtil;
import com.bosssoft.ecds.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/billInvoicing")
public class UneCBillController {

    @Autowired
    private UnitManagerService unitManagerService;

    @Autowired
    private UneCbillService uneCbillService;

/*    @Autowired
    private Redisson redisson;*/

    @Autowired
    private CommonUtil commonUtil;

    private ReentrantLock lock = new ReentrantLock();

    //String lockKey = "lockKey";

    /**
     * 分页查询当前单位的开票记录
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
     * @param billId
     * @return
     */
    @RequestMapping(value = "/getUneCbillById", method = RequestMethod.GET)
    public String getUneCbillById(String billId) {
        log.info(billId);
        return ResponseUtils.getResponse(uneCbillService.getUneCBillById(billId), ResponseUtils.ResultType.OK);
    }

    /**
     * 根据票据ID和校验码查询票据
     * @param billId
     * @param checkCode
     * @return
     */
    @RequestMapping(value = "/getBillByIdAndCheckCode", method = RequestMethod.GET)
    public String getBillByIdAndCheckCode(String billId, String checkCode) {
        QueryWrapper<UneCbill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_bill_id", billId)
                .eq("f_check_code", checkCode);
        UneCbill uneCbill = uneCbillService.getBillByIdAndCheckCode(queryWrapper);
        return ResponseUtils.getResponse(uneCbill, ResponseUtils.ResultType.OK);
    }

    /**
     * 开票前验证
     * @param unitName
     * @return
     */
    @RequestMapping(value = "/addVerify", method = RequestMethod.GET)
    public String addBillVerify(@RequestParam String unitName) {
        //TODO 执行开票前验证（单位是否欠缴/开票点开票数是否已经达到上限/当前单位是否有可用票据）--->返回预警信息
        WarnDto warnDto = new WarnDto();
        int total = uneCbillService.billCount();
        if (unitManagerService.isOutLimit(unitName, total)) {
            warnDto.setEvtName("单位开票数限制");
            warnDto.setMntCont("单位目前开票数已经达到最大开票限制，禁止开票");
            warnDto.setMntTime(new Date());
        }
//        if (unitManagerService.)
        return ResponseUtils.getResponse(ResponseUtils.ResultType.OK);
    }

    /**
     * 获取单位信息
     * @param unitName
     * @return
     */
    @RequestMapping(value = "/getUnitInfo", method = RequestMethod.GET)
    public String getUnitInfo (@RequestParam String unitName) {
        return unitManagerService.getDetailByUnitName(unitName);
    }

    /**
     * 获取单位可用票据信息
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
     * @return
     */
    @RequestMapping(path = "getItemList", method = RequestMethod.GET)
    public String getItemLisst(String unitName) {
        String itemList = unitManagerService.getItemList(unitName);
        //转换成json字符串传到前端
        return "";
    }

    /**
     * 新增开票
     * 所需字段：
     *单位（通过UnitManagerService调用远程接口查询）：区划id，单位识别码，单位编码，开票点id，开票点编码，开票点名称
     *缴款人信息 http request body中直接获取
     *项目（接收项目列表）：项目编码，项目名称，收费标准，计量单位
     *编制人：当前登录用户(redis中获取)
     * 校验码：commonUtil工具类生成
     * @return
     */
    @RequestMapping(path = "/addBill", method = RequestMethod.POST)
    public String addBill(@RequestBody BatchPojo batchPojo) {
        //1 验证数据完整性/金额小于收费标准上限
        log.info(batchPojo.getPayerDto().getfPayerName() + ":姓名");
        List<UneCbillItemDto> itemDtos = batchPojo.getItemDtos();
        log.info(itemDtos.get(0).toString() + "金额:" + batchPojo.getAmt());
        //2 分布式下，防止多个进程同时开票拿到同一张票据
        //RLock lock = redisson.getLock(lockKey);
        lock.lock();
        try {
            //TODO 调用服务新增票据
            UneCbill uneCbill = commonUtil.convert(batchPojo.getUnitName(), batchPojo.getPayerDto(),
                    batchPojo.getUneCbillDto(), commonUtil.generateID(), batchPojo.getAmt());
            uneCbillService.addUneCbill(uneCbill);

            //插入成功后移除仓库中相对应的票号
        } finally {
            lock.unlock();
        }
        return ResponseUtils.getResponse(ResponseUtils.ResultType.OK);
    }
}
