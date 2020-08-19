package com.bosssoft.ecds.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.itemdto.ItemStdDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.po.ItemStdPO;
import com.bosssoft.ecds.dao.ItemStdDao;
import com.bosssoft.ecds.entity.vo.itemvo.ItemStdVO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.service.ItemStdService;
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
public class ItemStdServiceImpl extends ServiceImpl<ItemStdDao, ItemStdPO> implements ItemStdService {

    /**
     * 插入项目标准，输入项目信息
     *
     * @param itemStdDTO
     * @return
     */
    @Override
    public ResponseResult save(ItemStdDTO itemStdDTO) {
        QueryWrapper<ItemStdPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ItemStdPO.F_ITEM_CODE, itemStdDTO.getItemCode());
        ItemStdPO itemStdPO = super.getOne(queryWrapper);
        if (itemStdPO != null){
            return new ResponseResult(ItemResultCode.ITEM_STD_EXISTS);
        }
        // 将dto转化为po
        itemStdPO = MyBeanUtil.myCopyProperties(itemStdDTO, ItemStdPO.class);
        // 执行插入操作
        boolean save = super.save(itemStdPO);
        // 插入失败返回操作错误
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 更新项目标准
     *
     * @param itemStdDTO
     * @return
     */
    @Override
    public ResponseResult update(ItemStdDTO itemStdDTO) {
        // 将dto转化为po
        ItemStdPO itemStdPO = MyBeanUtil.myCopyProperties(itemStdDTO, ItemStdPO.class);
        // 执行更新操作
        boolean update = super.updateById(itemStdPO);
        // 更新失败返回操作错误
        if (!update) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 更新成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 删除项目标准
     *
     * @param itemStdDTO
     * @return
     */
    @Override
    public ResponseResult delete(ItemStdDTO itemStdDTO) {
        // 判断传入id是否存在
        if (itemStdDTO.getId() == null) {
            return new ResponseResult(ItemResultCode.ITEM_STD_NOT_EXISTS);
        }
        // 执行删除操作
        boolean remove = super.removeById(itemStdDTO.getId());
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
     * @param pageDTO
     * @return
     */
    @Override
    public QueryResponseResult<PageVO> listByPage(PageDTO<ItemStdDTO> pageDTO) {
        Page<ItemStdPO> itemStdDTOPage = new Page<>();
        // 设置分页信息
        itemStdDTOPage.setCurrent(pageDTO.getPage());
        itemStdDTOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<ItemStdPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ItemStdPO.F_ITEMSTD_NAME, pageDTO.getKeyword())
                .or()
                .like(ItemStdPO.F_ISENABLE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(ItemPO.F_CREATE_TIME);
        // 读取分页数据
        Page<ItemStdPO> itemPOPage = super.page(itemStdDTOPage, queryWrapper);
        List<ItemStdPO> records = itemPOPage.getRecords();
        // 转换数据
        List<ItemStdDTO> itemPOS = MyBeanUtil.copyListProperties(records, ItemStdDTO::new);
        pageDTO.setTotal(itemPOPage.getTotal());
        pageDTO.setItems(itemPOS);
        PageVO pageVO = MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    /**
     * 批量删除
     *
     * @param itemStdDTOS
     * @return
     */
    @Override
    public ResponseResult batchDelete(List<ItemStdDTO> itemStdDTOS) {
        // 构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<ItemStdDTO> iterator = itemStdDTOS.iterator(); iterator.hasNext(); ) {
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
    public ResponseResult batchVerify(List<ItemStdDTO> itemStdDTOS) {
        for (ItemStdDTO itemStdDTO : itemStdDTOS) {
            // 查询出审核成功的标准信息
            ItemStdPO itemStdPO = super.getById(itemStdDTO.getId());
            // 修改审核状态
            itemStdPO.setIsenable(1);
            // 更新
            boolean updateById = super.updateById(itemStdPO);
            // 修改失败返回操作失败
            if (!updateById) {
                return new ResponseResult(CommonCode.FAIL);
            }
        }
        // 修改成功返回操作程序
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 通过项目编码查询项目标准
     *
     * @param itemStdDTO
     * @return
     */
    @Override
    public QueryResponseResult<ItemStdVO> getByItemCode(ItemStdDTO itemStdDTO) {
        QueryWrapper<ItemStdPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ItemStdPO.F_ITEM_CODE, itemStdDTO.getItemCode());
        ItemStdPO itemStdPO = super.getOne(queryWrapper);
        ItemStdVO itemStdVO = MyBeanUtil.copyProperties(itemStdPO, ItemStdVO.class);
        if (itemStdVO != null) {
            return new QueryResponseResult<>(CommonCode.SUCCESS, itemStdVO);
        } else {
            return new QueryResponseResult<>(ItemResultCode.ITEM_STD_NOT_EXISTS, itemStdVO);
        }

    }
}
