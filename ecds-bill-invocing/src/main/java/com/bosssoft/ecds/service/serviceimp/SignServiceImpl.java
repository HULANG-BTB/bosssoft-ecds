package com.bosssoft.ecds.service.serviceimp;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.dao.SignMapper;
import com.bosssoft.ecds.dao.UneCbillMapper;
import com.bosssoft.ecds.entity.dto.SignedDataDto;
import com.bosssoft.ecds.entity.po.Sign;
import com.bosssoft.ecds.entity.po.UneCbill;
import com.bosssoft.ecds.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;

    @Autowired
    private UneCbillMapper uneCbillMapper;

    /**
     * 获取票据的签名信息
     * @param billId
     * @return
     */
    @Override
    public SignedDataDto getSignData(String billId, String billNo) {
        QueryWrapper<UneCbill> queryWrapper = new QueryWrapper();
        queryWrapper.eq("f_bill_id", billId)
                .eq("f_bill_no", billNo);
        SignedDataDto signedDataDto = new SignedDataDto();
        UneCbill uneCbill = uneCbillMapper.selectOne(queryWrapper);
        log.info(String.valueOf(uneCbill.getFId()));
        Sign sign = signMapper.selectById(uneCbill.getFId());
        log.info(sign.toString());
        BeanUtil.copyProperties(sign, signedDataDto);
        return signedDataDto;
    }

    /**
     * 插入票据的签名信息
     * @param sign
     * @return
     */
    @Override
    public int addSign(Sign sign) {
        return signMapper.insert(sign);
    }
}
