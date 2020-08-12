package com.bosssoft.ecds.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.common.response.CommonCode;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PlaceDTO;
import com.bosssoft.ecds.entity.po.ItemPO;
import com.bosssoft.ecds.entity.po.PlacePO;
import com.bosssoft.ecds.dao.PlaceDao;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.enums.PlaceResultCode;
import com.bosssoft.ecds.service.PlaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@Service
@DS("slave")
public class PlaceServiceImpl extends ServiceImpl<PlaceDao, PlacePO> implements PlaceService {

    /**
     * 插入开票点信息
     *
     * @param placeDTO
     * @return
     */
    @Override
    public ResponseResult save(PlaceDTO placeDTO) {
        // 将dto转化为po
        PlacePO placePO = MyBeanUtil.myCopyProperties(placeDTO, PlacePO.class);
        // 执行插入操作
        boolean save = super.save(placePO);
        // 插入失败返回操作错误
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 更新开票点
     *
     * @param placeDTO
     * @return
     */
    @Override
    public ResponseResult update(PlaceDTO placeDTO) {
        // 将dto转化为po
        PlacePO placePO = MyBeanUtil.myCopyProperties(placeDTO, PlacePO.class);
        // 执行更新操作
        boolean update = super.updateById(placePO);
        // 更新失败返回操作错误
        if (!update) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 更新成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 删除开票点
     *
     * @param placeDTO
     * @return
     */
    @Override
    public ResponseResult delete(PlaceDTO placeDTO) {
        // 判断传入id是否存在
        if (placeDTO.getId() == null) {
            return new ResponseResult(PlaceResultCode.PLACE_NOT_EXISTS);
        }
        // 执行删除操作
        boolean remove = super.removeById(placeDTO.getId());
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
    public QueryResponseResult<PageVO> listByPage(PageDTO<PlaceDTO> pageDTO) {
        Page<PlacePO> placePOPage = new Page<>();
        // 设置分页信息
        placePOPage.setCurrent(pageDTO.getPage());
        placePOPage.setSize(pageDTO.getLimit());
        // 读取分页数据
        QueryWrapper<PlacePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(PlacePO.F_AGEN_ID, pageDTO.getKeyword())
                .or()
                .like(PlacePO.F_PLACE_NAME, pageDTO.getKeyword())
                .or()
                .like(ItemPO.F_ISENABLE, pageDTO.getKeyword());
        queryWrapper.orderByAsc(PlacePO.F_CREATE_TIME);
        // 读取分页数据
        Page<PlacePO> itemPOPage = super.page(placePOPage, queryWrapper);
        List<PlacePO> records = itemPOPage.getRecords();
        // 转换数据
        List<PlaceDTO> placeDTOS = MyBeanUtil.copyListProperties(records, PlaceDTO::new);
        pageDTO.setTotal(itemPOPage.getTotal());
        pageDTO.setItems(placeDTOS);
        PageVO pageVO = MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    /**
     * 批量删除
     *
     * @param placeDTOList
     * @return
     */
    @Override
    public ResponseResult batchDelete(List<PlaceDTO> placeDTOList) {
        // 构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<PlaceDTO> iterator = placeDTOList.iterator(); iterator.hasNext(); ) {
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
    public ResponseResult batchVerify(List<PlaceDTO> placeDTOList) {
        return null;
    }
}
