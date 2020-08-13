package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.dao.ItemDao;
import com.bosssoft.ecds.entity.vo.itemvo.ItemPageVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
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
 * @since 2020-08-09
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, ItemPO> implements ItemService {

    /**
     * 插入项目，输入项目信息
     *
     * @param itemDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult save(ItemDTO itemDTO) {
        // 将dto转化为po
        ItemPO itemPO = MyBeanUtil.myCopyProperties(itemDTO, ItemPO.class);
        // 执行插入操作
        boolean save = super.save(itemPO);
        // 插入失败返回操作错误
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 更新项目信息
     *
     * @param itemDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult update(ItemDTO itemDTO) {
        // 将dto转化为po
        ItemPO itemPO = MyBeanUtil.myCopyProperties(itemDTO, ItemPO.class);
        // 执行更新操作
        boolean update = super.updateById(itemPO);
        // 更新失败返回操作错误
        if (!update) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 更新成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 删除项目
     *
     * @param itemDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult delete(ItemDTO itemDTO) {
        // 判断传入id是否存在
        if (itemDTO.getId() == null) {
            return new ResponseResult(ItemResultCode.ITEM_NOT_EXISTS);
        }
        // 执行删除操作
        boolean remove = super.removeById(itemDTO.getId());
        // 删除失败返回操作错误
        if (!remove) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * pageDTO.getKeyword() 无数据输入时实现查询全部数据，有数据输入时进行模糊查询
     * 分页展示查询结果
     *
     * @param itemPageVO
     * @return
     */
    @Override
    public QueryResponseResult<ItemPageVO> listByPage(ItemPageVO<ItemDTO> itemPageVO) {
        Page<ItemPO> itemDTOPage = new Page<>();
        // 设置分页信息
        itemDTOPage.setCurrent(itemPageVO.getPage());
        itemDTOPage.setSize(itemPageVO.getLimit());
        // 读取分页数据
        QueryWrapper<ItemPO> queryWrapper = new QueryWrapper<>();
        if (itemPageVO.getIsenable() == null) {
            queryWrapper.like(ItemPO.F_ITEM_NAME, itemPageVO.getKeyword());
        } else {
            queryWrapper.eq(ItemPO.F_ISENABLE, itemPageVO.getIsenable())
                    .and(wrapper -> wrapper.like(ItemPO.F_ITEM_NAME, itemPageVO.getKeyword()));
        }
        queryWrapper.orderByAsc(ItemPO.F_CREATE_TIME);
        // 读取分页数据
        Page<ItemPO> itemPOPage = super.page(itemDTOPage, queryWrapper);
        List<ItemPO> records = itemPOPage.getRecords();
        // 转换数据
        List<ItemDTO> itemPOS = MyBeanUtil.copyListProperties(records, ItemDTO::new);
        itemPageVO.setTotal(itemPOPage.getTotal());
        itemPageVO.setItems(itemPOS);
        return new QueryResponseResult<>(CommonCode.SUCCESS, itemPageVO);
    }

    /**
     * 批量删除
     *
     * @param itemDTOS
     * @return
     */
    @Override
    public ResponseResult batchDelete(List<ItemDTO> itemDTOS) {
        // 构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<ItemDTO> iterator = itemDTOS.iterator(); iterator.hasNext(); ) {
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

    /**
     * 批量审核
     * 遍历itemDTOS，每次查询出审核成功的项目信息，修改审核状态，执行更新
     *
     * @param itemDTOS
     * @return
     */
    @Override
    public ResponseResult batchVerify(List<ItemDTO> itemDTOS) {
        for (ItemDTO itemDTO : itemDTOS) {
            // 查询出审核成功的项目信息
            ItemPO itemPO = super.getById(itemDTO.getId());
            // 修改审核状态
            itemPO.setIsenable(1);
            // 更新
            boolean updateById = super.updateById(itemPO);
            // 修改失败返回操作失败
            if (!updateById) {
                return new ResponseResult(CommonCode.FAIL);
            }
        }
        // 修改成功返回操作程序
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
