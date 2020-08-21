package com.bosssoft.ecds.service.imp;

import com.bosssoft.ecds.service.BillService;
import org.springframework.stereotype.Component;

/**
 * @author hujierong
 * @date 2020-8-21
 */
@Component
public class BillServiceHystrix implements BillService {
    @Override
    public Object getWriteOffInfo(String start, String end) {
        return null;
    }
}
