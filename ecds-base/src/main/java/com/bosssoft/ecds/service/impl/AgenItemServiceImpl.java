package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.dao.AgenItemDao;
import com.bosssoft.ecds.dao.ItemDao;
import com.bosssoft.ecds.entity.dto.AgenBillDTO;
import com.bosssoft.ecds.entity.dto.AgenItemDTO;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenBillPO;
import com.bosssoft.ecds.entity.po.AgenItemPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.service.AgenItemService;
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
public class AgenItemServiceImpl extends ServiceImpl<AgenItemDao, AgenItemPO> implements AgenItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private AgenItemDao agenItemDao;

    /**
     * 查询单位可用项目
     *
     * @param agenItemDTO
     * @return
     */
    @Override
    public ResponseResult save(AgenItemDTO agenItemDTO) {
        // 将dto转化为po
        AgenItemPO agenItemPO = MyBeanUtil.myCopyProperties(agenItemDTO, AgenItemPO.class);
        // 执行插入操作
        boolean save = super.save(agenItemPO);
        // 插入失败返回操作错误
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 删除单位可用项目
     *
     * @param agenItemDTO
     * @return
     */
    @Override
    public ResponseResult delete(AgenItemDTO agenItemDTO) {
        // 判断传入id是否存在
        if (agenItemDTO.getId() == null) {
            return new ResponseResult(ItemResultCode.AGEN_BILL_NOT_EXISTS);
        }
        // 执行删除操作
        boolean remove = super.removeById(agenItemDTO.getId());
        // 删除失败返回操作错误
        if (!remove) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 分页查询单位可用项目
     *
     * @param pageDTO
     * @return
     */
    @Override
    public QueryResponseResult<PageVO> listByPage(PageDTO<ItemDTO> pageDTO) {
        Page<AgenItemPO> agenBillPOPage = new Page<>();
        // 设置分页信息
        agenBillPOPage.setCurrent(pageDTO.getPage());
        agenBillPOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<AgenItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenItemPO.F_AGEN_IDCODE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(AgenItemPO.F_CREATE_TIME);
        // 读取分页数据
        agenBillPOPage = super.page(agenBillPOPage, queryWrapper);
        List<AgenItemPO> records = agenBillPOPage.getRecords();
        ArrayList<ItemPO> itemPOS = new ArrayList<>();
        // 通过从关系表中查询出的项目编码，查询出项目的相关信息
        for (AgenItemPO record : records) {
            QueryWrapper<ItemPO> queryBillType = new QueryWrapper<>();
            queryBillType.eq("f_item_id", record.getItemCode());
            itemPOS.add(itemDao.selectOne(queryBillType));
        }
        // 将po转为DTO
        List<ItemDTO> itemDTOS = MyBeanUtil.copyListProperties(itemPOS, ItemDTO::new);
        // 转换数据
        pageDTO.setTotal(agenBillPOPage.getTotal());
        pageDTO.setItems(itemDTOS);
        PageVO pageVO = MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    @Override
    public QueryResponseResult<List<ItemVO>> getItemAll(AgenItemDTO agenItemDTO) {
        //构造条件查询器，通过单位编码查询单位所有可用票据
        QueryWrapper<AgenItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenItemPO.F_AGEN_IDCODE, agenItemDTO.getAgenIdcode());
        List<AgenItemPO> agenItemPOS = agenItemDao.selectList(queryWrapper);
        //用来存储单位所有可用票据的List
        List<ItemPO> itemPOS = new ArrayList<>();
        // 通过从关系表中查询出的票据编码，查询出票据的相关信息
        for (AgenItemPO agenItemPO : agenItemPOS) {
            QueryWrapper<ItemPO> queryItem = new QueryWrapper<>();
            queryItem.eq("f_item_id", agenItemPO.getItemCode());
            itemPOS.add(itemDao.selectOne(queryItem));
        }
        List<ItemVO> itemVOS = MyBeanUtil.copyListProperties(itemPOS, ItemVO::new);
        return new QueryResponseResult<>(CommonCode.SUCCESS,itemVOS);
    }

    /**
     * 批量删除单位可用项目
     *
     * @param agenBillDTOList
     * @return
     */
    @Override
    public ResponseResult batchDelete(List<AgenItemDTO> agenBillDTOList) {
        // 构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<AgenItemDTO> iterator = agenBillDTOList.iterator(); iterator.hasNext(); ) {
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

    @Override
    public ResponseResult updateBatch(List<AgenItemDTO> agenItemDTOList) {
        QueryWrapper<AgenItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenBillPO.F_AGEN_IDCODE,agenItemDTOList.get(0).getAgenIdcode());
        List<AgenItemPO> agenItemPOS = agenItemDao.selectList(queryWrapper);
        boolean remove = true;
        if (!agenItemPOS.isEmpty()) {
            // 将原来的单位可用票据关系删除
            remove = super.remove(queryWrapper);
        }
        if (remove) {
            AgenItemPO agenItemPO = new AgenItemPO();
            // 构建新的AgenBillPO，插入关系
            for (AgenItemDTO agenItemDTO : agenItemDTOList) {
                agenItemPO.setAgenIdcode(agenItemDTO.getAgenIdcode());
                agenItemPO.setItemCode(agenItemDTO.getItemCode());
                agenItemPO.setNote(agenItemDTO.getNote());
                boolean save = super.save(agenItemPO);
                if (!save) {
                    return new ResponseResult(CommonCode.FAIL);
                }
            }
        } else {
            return new ResponseResult(CommonCode.FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
