package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.dao.BillDestroyDao;
import com.bosssoft.ecds.dao.BillNumDao;
import com.bosssoft.ecds.dao.FabFinanBillDao;
import com.bosssoft.ecds.domain.po.FabFinanBillPO;
import com.bosssoft.ecds.domain.search.FabFinanBillSearch;
import com.bosssoft.ecds.domain.vo.BillDestroyVO;
import com.bosssoft.ecds.domain.vo.BillNumVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author syf
 * @Date 2020/8/15 22:44
 */
@RestController
@RequestMapping("/report")
@Slf4j
@Api(value = "票据信息")
public class FabFinanBillController {

    @Autowired
    private FabFinanBillDao fabFinanBillDao;

    @Autowired
    private BillNumDao billNumDao;

    @Autowired
    private BillDestroyDao billDestroyDao;

    @ApiOperation("查询所有票据信息")
    @GetMapping("/bill")
    public List<FabFinanBillPO> all(){
        return fabFinanBillDao.selectList(null);
    }

    @ApiOperation("根据条件查询票据信息")
    @PostMapping("/search")
    public List<FabFinanBillPO> selectSearch(@RequestBody FabFinanBillSearch fabFinanBillSearch){
        log.info(String.valueOf(fabFinanBillSearch));
        return fabFinanBillDao.selectSearch(fabFinanBillSearch);
    }
    @ApiOperation("获取最近七天库存数量")
    @GetMapping("/num")
    public QueryResponseResult selectNumDate(){
        List<BillNumVO> list=billNumDao.selectNumDate();
        List<BillNumVO> list1=new ArrayList<>(7);
        ArrayList<Date> week= DateUtils.getDays(7);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : week) {
            int index = 0;
            boolean exist = false;
            for (int j = 0; j < list.size(); j++) {
                //week.size() - i - 1
                if (sdf.format(list.get(j).getCreateDate()).equals(sdf.format(date))) {
                    index = j;
                    exist = true;
                    break;
                }
            }
            if (exist) {
                list1.add(list.get(index));
            } else {
                list1.add(new BillNumVO(0, date));
            }
        }
        List<Integer> num=new ArrayList<>();
        for (BillNumVO billNumVO : list1) {
            num.add(billNumVO.getTotal());
        }
        return new QueryResponseResult(CommonCode.SUCCESS,num);
    }

    @ApiOperation("获取最近七天库存销毁数量")
    @GetMapping("/desnum")
    public QueryResponseResult selectDestroy(){
        List<BillDestroyVO> list=billDestroyDao.selectNumDate();
        List<BillDestroyVO> list1=new ArrayList<>(7);
        ArrayList<Date> week = DateUtils.getDays(7);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : week) {
            int index = 0;
            boolean exist = false;
            for (int j = 0; j < list.size(); j++) {
                //week.size() - i - 1
                if (sdf.format(list.get(j).getCreateDate()).equals(sdf.format(date))) {
                    index = j;
                    exist = true;
                    break;
                }
            }
            if (exist) {
                list1.add(list.get(index));
            } else {
                list1.add(new BillDestroyVO(0, date));
            }
        }
        List<Integer> num=new ArrayList<>();
        for (BillDestroyVO billDestroyVO : list1) {
            num.add(billDestroyVO.getTotal());
        }
        return new QueryResponseResult(CommonCode.SUCCESS,num);
    }
}
