package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.entity.vo.groupvo.GroupPageVO;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.dao.GroupDao;
import com.bosssoft.ecds.dao.GroupItemDao;
import com.bosssoft.ecds.entity.dto.GroupDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.GroupItemPO;
import com.bosssoft.ecds.entity.po.GroupPO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.groupvo.GroupVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.service.GroupService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class GroupServiceImpl extends ServiceImpl<GroupDao, GroupPO> implements GroupService {

    @Autowired
    private GroupItemDao groupItemDao;
    @Autowired
    private GroupDao groupDao;

    @Override
    public ResponseResult save(GroupDTO groupDTO) {
        // 将dto转化为po
        GroupPO groupPO = MyBeanUtil.copyProperties(groupDTO, GroupPO.class);
        Date date = new Date();
        groupPO.setCreateTime(date);
        groupPO.setUpdateTime(date);
        groupPO.setLogicDelete(false);
        groupPO.setVersion(1);
        boolean save = super.save(groupPO);
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult update(GroupDTO groupDTO) {
        // 将dto转化为po
        GroupPO groupPO = MyBeanUtil.myCopyProperties(groupDTO, GroupPO.class);
        // 执行更新操作
        boolean update = super.updateById(groupPO);
        // 更新失败返回操作错误
        if (!update) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 更新成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult delete(GroupDTO groupDTO) {
        // 查询判断要删除的记录是否存在
        GroupPO groupPO = super.getById(groupDTO.getId());
        if (groupPO == null) {
            // 不存在返回记录
            return new ResponseResult(ItemResultCode.NOT_EXISTS);
        }
        // 构造条件查询器，删除项目分组关系表中的数据
        QueryWrapper<GroupItemPO> wrapper = new QueryWrapper<>();
        // 当单位编码等于要删除数据时，删除对应记录
        wrapper.eq(GroupItemPO.F_GROUP_CODE, groupPO.getGroupCode());
        // 查询是否存在要删除分组的外键依赖
        List<GroupItemPO> groupItemPOS = groupItemDao.selectList(wrapper);
        // 存在时先删除分组关系表中的数据
        if (!groupItemPOS.isEmpty()) {
            int delete = groupItemDao.delete(wrapper);
            if (delete != 1) {
                return new ResponseResult(CommonCode.FAIL);
            }
        }
        // 不存在外键依赖之后，删除分组信息
        boolean removeById = super.removeById(groupDTO.getId());
        if (!removeById) {
            // 删除失败
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult listByPage(GroupPageVO<GroupVO> groupPageVO) {
        Page<GroupPO> groupPOPage = new Page<>();
        // 设置分页信息
        groupPOPage.setCurrent(groupPageVO.getPage());
        groupPOPage.setSize(groupPageVO.getLimit());
        // 构造条件查询器，分页模糊查询
        QueryWrapper<GroupPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(GroupPO.F_AGEN_CODE, groupPageVO.getAgenCode())
                .and(wrapper->wrapper.like(GroupPO.F_GROUP_NAME,groupPageVO.getGroupName()));
        queryWrapper.orderByAsc(GroupPO.F_CREATE_TIME);
        // 读取分页数据
        Page<GroupPO> page = super.page(groupPOPage, queryWrapper);
        List<GroupPO> records = page.getRecords();
        // 转换数据
        List<GroupVO> groupVOS = MyBeanUtil.copyListProperties(records, GroupVO::new);
        groupPageVO.setItems(groupVOS);
        groupPageVO.setTotal(page.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS, groupPageVO);
    }

    @Override
    public ResponseResult getGroupByCode(GroupPageVO<Object> groupPageVO) {
        QueryWrapper<GroupPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(GroupPO.F_AGEN_CODE,groupPageVO.getAgenCode());
        List<GroupPO> groupPOS = groupDao.selectList(queryWrapper);
        List<GroupVO> groupVOS = MyBeanUtil.copyListProperties(groupPOS, GroupVO::new);
        return new QueryResponseResult<>(CommonCode.SUCCESS,groupVOS);
    }
}
