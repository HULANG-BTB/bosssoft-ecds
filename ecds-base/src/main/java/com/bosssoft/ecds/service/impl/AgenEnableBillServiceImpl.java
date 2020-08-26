package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.dao.AgenEnableBillDao;
import com.bosssoft.ecds.dao.BillTypeDao;
import com.bosssoft.ecds.entity.dto.agendto.AgenEnableBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenEnableBillPO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.AgenEnableBillService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wzh
 * @since 2020-08-12
 */
@Service
public class AgenEnableBillServiceImpl extends ServiceImpl<AgenEnableBillDao, AgenEnableBillPO> implements AgenEnableBillService {

    @Autowired
    private BillTypeDao billTypeDao;

    @Autowired
    private AgenEnableBillDao agenBillDao;

    /**
     * 维护单位可用票据关系
     *
     * @param agenBillDTOList
     * @return
     */
    @Override
    public ResponseResult updateBatch(List<AgenEnableBillDTO> agenBillDTOList) {
        QueryWrapper<AgenEnableBillPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenEnableBillPO.F_AGEN_CODE,agenBillDTOList.get(0).getAgenCcode());
        List<AgenEnableBillPO> agenBillPOS = agenBillDao.selectList(queryWrapper);
        boolean remove = true;
        if (!agenBillPOS.isEmpty()) {
            // 将原来的单位可用票据关系删除
            remove = super.remove(queryWrapper);
        }
        if (remove) {
            AgenEnableBillPO agenBillPO = new AgenEnableBillPO();
            // 构建新的AgenBillPO，插入关系
            for (AgenEnableBillDTO agenBillDTO : agenBillDTOList) {
                agenBillPO.setAgenCode(agenBillDTO.getAgenCcode());
                agenBillPO.setTypeCode(agenBillDTO.getTypeCode());
                agenBillPO.setNote(agenBillDTO.getNote());
                boolean save = super.save(agenBillPO);
                if (!save) {
                    return new ResponseResult(CommonCode.FAIL);
                }
            }
        } else {
            return new ResponseResult(CommonCode.FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 分页查询单位票据关系
     * 通过单位编码，查询出所有可用票据
     *
     * @param pageDTO
     * @return
     */
    @Override
    public QueryResponseResult<PageVO> listByPage(PageDTO<BillTypePO> pageDTO) {
        Page<AgenEnableBillPO> agenBillPOPage = new Page<>();
        // 设置分页信息
        agenBillPOPage.setCurrent(pageDTO.getPage());
        agenBillPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<AgenEnableBillPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenEnableBillPO.F_AGEN_CODE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(AgenEnableBillPO.F_CREATE_TIME);
        // 读取分页数据
        agenBillPOPage = super.page(agenBillPOPage, queryWrapper);
        List<AgenEnableBillPO> records = agenBillPOPage.getRecords();
        ArrayList<BillTypePO> billTypePOS = new ArrayList<>();
        // 通过从关系表中查询出的票据编码，查询出票据的相关信息
        for (AgenEnableBillPO record : records) {
            QueryWrapper<BillTypePO> queryBillType = new QueryWrapper<>();
            queryBillType.eq("f_code", record.getTypeCode());
            billTypePOS.add(billTypeDao.selectOne(queryBillType));
        }
        // 转换数据
        pageDTO.setTotal(agenBillPOPage.getTotal());
        pageDTO.setItems(billTypePOS);
        PageVO pageVO = MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    /**
     * 查询单位所有的可用票据，不通过分页显示
     *
     * @param agenBillDTO 输入单位编码
     * @return
     */
    @Override
    public QueryResponseResult<List<BillTypePO>> getBill(AgenEnableBillDTO agenBillDTO) {
        //构造条件查询器，通过单位编码查询单位所有可用票据
        QueryWrapper<AgenEnableBillPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenEnableBillPO.F_AGEN_CODE, agenBillDTO.getAgenCcode());
        List<AgenEnableBillPO> agenBillPOS = agenBillDao.selectList(queryWrapper);
        //用来存储单位所有可用票据的List
        List<BillTypePO> billTypePOS = new ArrayList<>();
        // 通过从关系表中查询出的票据编码，查询出票据的相关信息
        for (AgenEnableBillPO agenBillPO : agenBillPOS) {
            QueryWrapper<BillTypePO> queryBillType = new QueryWrapper<>();
            queryBillType.eq("f_code", agenBillPO.getTypeCode());
            billTypePOS.add(billTypeDao.selectOne(queryBillType));
        }
        return new QueryResponseResult<>(CommonCode.SUCCESS, billTypePOS);
    }
}
