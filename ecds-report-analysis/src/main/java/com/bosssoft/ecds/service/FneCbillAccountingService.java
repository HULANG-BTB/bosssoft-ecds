package com.bosssoft.ecds.service;

import com.bosssoft.ecds.domain.search.FneCbillAccountingSearch;
import com.bosssoft.ecds.domain.vo.FneCbillAccountingVO;
import com.bosssoft.ecds.domain.vo.FneCbillAccountingWeekVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FneCbillAccountingService
 * @Description FneCbillAccountingService
 * @auther wangpeng
 * @Date 2020/8/19 16:00
 * @Version 1.0
 **/
@Service
public interface FneCbillAccountingService {

    public List<FneCbillAccountingVO> searchTime(FneCbillAccountingSearch fneCbillAccountingSearch);

    public List<FneCbillAccountingVO> searchAll(FneCbillAccountingSearch fneCbillAccountingSearch);
    
}
