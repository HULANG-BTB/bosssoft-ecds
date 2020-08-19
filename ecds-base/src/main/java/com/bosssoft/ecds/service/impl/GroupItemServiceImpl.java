package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.dao.GroupItemDao;
import com.bosssoft.ecds.entity.dto.GroupItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.GroupItemPO;
import com.bosssoft.ecds.entity.po.GroupPO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.groupvo.GroupItemVO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.service.GroupItemService;
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
 * @since 2020-08-14
 */
@Service
@DS("slave")
public class GroupItemServiceImpl extends ServiceImpl<GroupItemDao, GroupItemPO> implements GroupItemService {

    @Autowired
    private GroupItemDao groupItemDao;

    @Override
    public ResponseResult save(GroupItemDTO groupItemDTO) {
        // 将dto转化为po
        QueryWrapper<GroupItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GroupItemPO.F_GROUP_CODE,groupItemDTO.getGroupCode())
                .and(wrapper->wrapper.eq(GroupItemPO.F_ITEM_CODE,groupItemDTO.getItemCode()));
        if (groupItemDao.selectOne(queryWrapper) != null){
            return new ResponseResult(ItemResultCode.ITEM_STD_EXISTS);
        }
        GroupItemPO groupItemPO =  MyBeanUtil.copyProperties(groupItemDTO,GroupItemPO.class);
        boolean save = super.save(groupItemPO);
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult update(GroupItemDTO groupItemDTO) {
        // 将dto转化为po
        GroupItemPO groupItemPO = MyBeanUtil.copyProperties(groupItemDTO, GroupItemPO.class);
        // 执行更新操作
        boolean update = super.updateById(groupItemPO);
        // 更新失败返回操作错误
        if (!update) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 更新成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult delete(GroupItemDTO groupItemDTO) {
        QueryWrapper<GroupItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GroupItemPO.F_GROUP_CODE,groupItemDTO.getGroupCode())
                .and(wrapper->wrapper.eq(GroupItemPO.F_ITEM_CODE,groupItemDTO.getItemCode()));
        GroupItemPO groupItemPO = groupItemDao.selectOne(queryWrapper);
        if (groupItemPO == null) {
            return new ResponseResult(ItemResultCode.NOT_EXISTS);
        }
        boolean remove = super.removeById(groupItemPO);
        if (!remove) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功返回操作成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult listByPage(PageDTO<GroupItemVO> pageDTO) {
        Page<GroupItemPO> groupItemPOPage = new Page<>();
        // 设置分页信息
        groupItemPOPage.setCurrent(pageDTO.getPage());
        groupItemPOPage.setSize(pageDTO.getLimit());
        // 构造条件查询器，分页模糊查询
        QueryWrapper<GroupItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(GroupItemPO.F_GROUP_CODE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(GroupPO.F_CREATE_TIME);
        // 读取分页数据
        Page<GroupItemPO> page = super.page(groupItemPOPage, queryWrapper);
        List<GroupItemPO> records = page.getRecords();
        // 转换数据
        List<GroupItemVO> groupVOS = MyBeanUtil.copyListProperties(records, GroupItemVO::new);
        pageDTO.setItems(groupVOS);
        pageDTO.setTotal(page.getTotal());
        PageVO pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    @Override
    public ResponseResult getItemInfo(GroupItemDTO groupItemDTO) {
        QueryWrapper<GroupItemPO> wrapper = new QueryWrapper<>();
        wrapper.eq(GroupItemPO.F_GROUP_CODE,groupItemDTO.getGroupCode());
        List<GroupItemPO> groupItemPOS = groupItemDao.selectList(wrapper);
        List<ItemPO> itemPos = new ArrayList<>();
        for (GroupItemPO groupItemPO : groupItemPOS) {
            ItemPO itemInfo = groupItemDao.getItemInfo(groupItemPO.getItemCode());
            itemPos.add(itemInfo);
        }
        List<ItemVO> itemVOS = MyBeanUtil.copyListProperties(itemPos, ItemVO::new);
        if (!itemVOS.isEmpty()){
            return new QueryResponseResult<>(CommonCode.SUCCESS,itemVOS);
        }
        return new ResponseResult(ItemResultCode.ITEM_NOT_EXISTS);
    }
}
