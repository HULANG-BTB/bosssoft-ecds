package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.dao.AgenDao;
import com.bosssoft.ecds.dao.ItemStdDao;
import com.bosssoft.ecds.entity.dto.itemdto.ItemInfoDTO;
import com.bosssoft.ecds.entity.po.AgenPO;
import com.bosssoft.ecds.entity.po.ItemStdPO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.dao.AgenItemDao;
import com.bosssoft.ecds.dao.ItemDao;
import com.bosssoft.ecds.entity.dto.agendto.AgenItemDTO;
import com.bosssoft.ecds.entity.dto.itemdto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenItemPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.service.AgenItemService;
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
public class AgenItemServiceImpl extends ServiceImpl<AgenItemDao, AgenItemPO> implements AgenItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private AgenItemDao agenItemDao;
    @Autowired
    private AgenDao agenDao;
    @Autowired
    private ItemStdDao itemStdDao;

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
        //构造条件查询器，通过单位编码查询单位所有可用项目
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

    @Override
    public ResponseResult updateBatch(List<AgenItemDTO> agenItemDTOList) {
        QueryWrapper<AgenItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenItemPO.F_AGEN_IDCODE,agenItemDTOList.get(0).getAgenIdcode());
        List<AgenItemPO> agenItemPOS = agenItemDao.selectList(queryWrapper);
        boolean remove = true;
        if (!agenItemPOS.isEmpty()) {
            // 将原来的单位可用项目关系删除
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

    @Override
    public List<ItemInfoDTO> getItemInfo(String agenName) {
        // 通过单位名字，查询出单位信息，为了获得单位编码
        QueryWrapper<AgenPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AgenPO.F_AGEN_NAME, agenName);
        AgenPO agenPO = agenDao.selectOne(queryWrapper);
        //通过单位编码查询出单位的可用项目
        QueryWrapper<AgenItemPO> wrapper = new QueryWrapper<>();
        wrapper.eq(AgenItemPO.F_AGEN_IDCODE, agenPO.getAgenCode());
        List<AgenItemPO> agenItemPOS = agenItemDao.selectList(wrapper);
        //通过项目编码，查询出项目的收费标准
        List<ItemInfoDTO> itemInfoDTOS = new ArrayList<>();
        for (AgenItemPO agenItemPO : agenItemPOS) {
            ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
            //通过项目id查询出项目名称
            QueryWrapper<ItemPO> itemPOWrapper = new QueryWrapper<>();
            itemPOWrapper.eq(ItemPO.F_ITEM_ID,agenItemPO.getItemCode());
            ItemPO itemPO = itemDao.selectOne(itemPOWrapper);
            MyBeanUtil.copyProperties(itemPO,itemInfoDTO);
            //根据项目编码，查询出项目标准
            QueryWrapper<ItemStdPO> itemStdWrapper = new QueryWrapper<>();
            itemStdWrapper.eq(ItemStdPO.F_ITEM_CODE,agenItemPO.getItemCode());
            ItemStdPO itemStdPO = itemStdDao.selectOne(itemStdWrapper);
            MyBeanUtil.copyProperties(itemStdPO,itemInfoDTO);
            itemInfoDTOS.add(itemInfoDTO);
        }
        return itemInfoDTOS;
    }
}
