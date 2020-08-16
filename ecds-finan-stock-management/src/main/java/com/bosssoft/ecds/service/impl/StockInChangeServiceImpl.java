package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.StockInChangeDao;
import com.bosssoft.ecds.entity.po.StockInChangePO;
import com.bosssoft.ecds.service.StockInChangeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cheng
 * @since 2020-08-10
 */
@Service
public class StockInChangeServiceImpl extends ServiceImpl<StockInChangeDao, StockInChangePO> implements StockInChangeService {
}
