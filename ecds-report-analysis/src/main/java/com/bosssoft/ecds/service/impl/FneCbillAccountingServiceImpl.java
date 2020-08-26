package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.dao.FneCbillAccountingDao;
import com.bosssoft.ecds.domain.po.FneCbillAccountingPO;
import com.bosssoft.ecds.domain.search.FneCbillAccountingSearch;
import com.bosssoft.ecds.domain.vo.FneCbillAccountingVO;
import com.bosssoft.ecds.domain.vo.FneCbillAccountingWeekVO;
import com.bosssoft.ecds.service.FneCbillAccountingService;
import com.bosssoft.ecds.utils.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FneCbillAccountingServiceImpl
 * @Description FneCbillAccountingServiceImpl
 * @auther wangpeng
 * @Date 2020/8/19 16:04
 * @Version 1.0
 **/
@Service
public class FneCbillAccountingServiceImpl implements FneCbillAccountingService {

    @Autowired
    FneCbillAccountingDao fneCbillAccountingDao;

    @Override
    public List<FneCbillAccountingVO> searchTime(FneCbillAccountingSearch fneCbillAccountingSearch) {

        List<FneCbillAccountingVO> fneCbillAccountingVOList = new ArrayList<FneCbillAccountingVO>();
        List<Date> fTimeList = fneCbillAccountingSearch.getFTimeList();
        if(fTimeList.size() == 0){
            return null;
        } else {
            QueryWrapper<FneCbillAccountingPO> wrapper = new QueryWrapper<>();
            wrapper.between("f_time", fTimeList.get(0), fTimeList.get(1));
            wrapper.eq("f_account_status",1);
            List<FneCbillAccountingPO> fneCbillAccountingPOList = fneCbillAccountingDao.selectList(wrapper);
            ListUtils<FneCbillAccountingVO> utils = BeanUtils.instantiate(ListUtils.class);
            utils.copyList(fneCbillAccountingPOList,fneCbillAccountingVOList, FneCbillAccountingVO.class);
            return fneCbillAccountingVOList;
        }
    }

    @Override
    public List<FneCbillAccountingVO> searchAll(FneCbillAccountingSearch fneCbillAccountingSearch) {

        List<FneCbillAccountingVO> fneCbillAccountingVOList = new ArrayList<FneCbillAccountingVO>();
        List<Date> fTimeList = fneCbillAccountingSearch.getFTimeList();
        QueryWrapper<FneCbillAccountingPO> wrapper = new QueryWrapper<>();
        if(fTimeList.size() == 0){
        } else {
            wrapper.between("f_time", fTimeList.get(0), fTimeList.get(1));
        }
        if(fneCbillAccountingSearch.getFAgenName() == "") {

        }else{
            wrapper.eq("f_agen_name",fneCbillAccountingSearch.getFAgenName());
        }
        if(fneCbillAccountingSearch.getFPayerTel() == ""){

        } else {
            wrapper.eq("f_payer_tel",fneCbillAccountingSearch.getFPayerTel());
        }
        if(fneCbillAccountingSearch.getFType() == null){

        } else {
           wrapper.eq("f_type", fneCbillAccountingSearch.getFType());
        }
        wrapper.eq("f_account_status",1);
        List<FneCbillAccountingPO> fneCbillAccountingPOList = fneCbillAccountingDao.selectList(wrapper);
        ListUtils<FneCbillAccountingVO> utils = BeanUtils.instantiate(ListUtils.class);
        utils.copyList(fneCbillAccountingPOList,fneCbillAccountingVOList, FneCbillAccountingVO.class);
        return fneCbillAccountingVOList;
    }
}
