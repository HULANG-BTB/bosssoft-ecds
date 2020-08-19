package com.bosssoft.ecds.service.impl;

import cn.hutool.core.convert.ConverterRegistry;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.dao.ItemMapper;
import com.bosssoft.ecds.entity.dto.ApplyDto;
import com.bosssoft.ecds.entity.po.ApplyPo;
import com.bosssoft.ecds.dao.ApplyMapper;
import com.bosssoft.ecds.entity.po.ItemPo;
import com.bosssoft.ecds.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.util.CommonUtil;
import com.bosssoft.ecds.util.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qiuheng
 * @since 2020-08-12
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, ApplyPo> implements ApplyService {

    @Autowired(required = false)
    private ApplyMapper applyMapper;

    @Override
    public ApplyPo selectById(Long id) {
        return applyMapper.selectById(id);
    }

    /**
     * @description: 插入票据销毁或申请主表信息，此时需将dto转po
     * @param: [applyDto]
     * @return: boolean
     * @date: 2020/8/13
     */
    @Override
    public boolean insertApplyInfo(ApplyDto applyDto) {
        ApplyPo applyPo = new ApplyPo();
        BeanUtils.copyProperties(applyDto,applyPo);
        //业务单号
        //applyPo.setfDestroyNo(applyDto.getfDestroyNo());
        //applyPo.setfAgenIdCode(applyDto.getfAgenIdCode());
        //applyPo.setfUnitName(applyDto.getfUnitName());
        //applyPo.setfRgnCode(applyDto.getfRgnCode());
        //applyPo.setfDestroyType(applyDto.getfDestroyType());
        //applyPo.setfDestroyMemo(applyDto.getfDestroyMemo());
        //applyPo.setfApplyMan(applyDto.getfApplyMan());
        //applyPo.setfStatus(applyDto.getfStatus());
        //applyPo.setfOperatorId(applyDto.getfOperatorId());
        //applyPo.setfOperatorName(applyDto.getfOperatorName());
        //主键
        applyPo.setfId(CommonUtil.generateID());
        applyPo.setfCreateTime(LocalDateTime.now());
        applyPo.setfUpdateTime(LocalDateTime.now());
        //申请时间
        //applyPo.setfApplyDate(applyDto.getfApplyDate());
        //乐观锁
        applyPo.setfVersion(0);
        applyMapper.insert(applyPo);
        return false;
    }

    @Override
    public List<ApplyPo> getApplyList() {
        QueryWrapper<ApplyPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_destroy_no", "f_unit_name","f_apply_man","f_apply_date","f_destroy_type","f_status");
        List<ApplyPo> applyPos = applyMapper.selectList(queryWrapper);
        return applyPos;
    }

    @Override
    public List<ApplyPo> getApplyListByAgenIdCode(String agenIdCode) {
        QueryWrapper<ApplyPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("f_destroy_no", "f_unit_name","f_apply_man","f_apply_date",
                "f_destroy_type","f_status").like("f_agen_id_code",agenIdCode);
        List<ApplyPo> applyPos = applyMapper.selectList(queryWrapper);
        return applyPos;
    }

    @Override
    public ApplyPo getApplyInfoByDestroyNo(String fDestroyNo) {
        QueryWrapper<ApplyPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("f_destroy_no", fDestroyNo);
        ApplyPo applyPo = applyMapper.selectOne(queryWrapper);
        return applyPo;
    }

    @Override
    public int updateApplyInfo(ApplyDto applyDto) {
        ApplyPo applyPo = new ApplyPo();
        BeanUtils.copyProperties(applyDto,applyPo);
        return applyMapper.updateById(applyPo);
    }

    @Override
    public int deleteApplyInfoByDestroyNo(String fDestroyNo) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("f_destroy_no",fDestroyNo);
        return applyMapper.deleteByMap(map);
    }


}
