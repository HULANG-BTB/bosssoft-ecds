package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.dao.AgenBillDao;
import com.bosssoft.ecds.dao.BillTypeDao;
import com.bosssoft.ecds.entity.dto.AgenBillDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenBillPO;
import com.bosssoft.ecds.entity.po.BillTypePO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.service.AgenBillService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
public class AgenBillServiceImpl extends ServiceImpl<AgenBillDao, AgenBillPO> implements AgenBillService {

    @Autowired
    private BillTypeDao billTypeDao;

    /**
     * 添加单位票据关系
     *
     * @param agenBillDTO
     * @return
     */
    @Override
    public ResponseResult save(AgenBillDTO agenBillDTO) {
        // 将dto转化为po
        AgenBillPO agenBillPO = MyBeanUtil.myCopyProperties(agenBillDTO, AgenBillPO.class);
        // 执行插入操作
        boolean save = super.save(agenBillPO);
        // 插入失败返回操作错误
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 删除单位票据关系
     *
     * @param agenBillDTO
     * @return
     */
    @Override
    public ResponseResult delete(AgenBillDTO agenBillDTO) {
        // 判断传入id是否存在
        if (agenBillDTO.getId() == null) {
            return new ResponseResult(ItemResultCode.AGEN_BILL_NOT_EXISTS);
        }
        // 执行删除操作
        boolean remove = super.removeById(agenBillDTO.getId());
        // 删除失败返回操作错误
        if (!remove) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 分页查询单位项目关系
     * 通过单位编码，查询出所有可用票据
     *
     * @param pageDTO
     * @return
     */
    @Override
    public QueryResponseResult<PageVO> listByPage(PageDTO<BillTypePO> pageDTO) {
        Page<AgenBillPO> agenBillPOPage = new Page<>();
        // 设置分页信息
        agenBillPOPage.setCurrent(pageDTO.getPage());
        agenBillPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<AgenBillPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenBillPO.F_AGEN_IDCODE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(AgenBillPO.F_CREATE_TIME);
        // 读取分页数据
        agenBillPOPage = super.page(agenBillPOPage, queryWrapper);
        List<AgenBillPO> records = agenBillPOPage.getRecords();
        ArrayList<BillTypePO> billTypePOS = new ArrayList<>();
        // 通过从关系表中查询出的票据编码，查询出票据的相关信息
        for (AgenBillPO record : records) {
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
     * 批量删除单位可用票据
     *
     * @param agenBillDTOList
     * @return
     */
    @Override
    public ResponseResult batchDelete(List<AgenBillDTO> agenBillDTOList) {
        // 构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<AgenBillDTO> iterator = agenBillDTOList.iterator(); iterator.hasNext(); ) {
            idList.add(iterator.next().getId());
        }
        // 执行批量删除
        boolean removeByIds = super.removeByIds(idList);
        // 删除失败返回操作失败
        if (!removeByIds) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功返回操作成功
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
