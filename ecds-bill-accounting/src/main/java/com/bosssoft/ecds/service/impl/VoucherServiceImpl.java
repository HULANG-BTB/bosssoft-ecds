package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.dao.VoucherDao;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.po.VoucherPO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.VoucherVO;
import com.bosssoft.ecds.enums.VoucherCode;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.bosssoft.ecds.enums.CbillAccountingCode.SUCCESS;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author UoweMe
 * @since 2020-08-10
 */
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherDao, VoucherPO> implements VoucherService {

    /**
     * 生成一条入账凭证
     *
     * @return Boolean
     */
    @Override
    public Boolean generateVoucher(VoucherDTO voucherDTO) {
        VoucherPO voucherPO = new VoucherPO();
        MyBeanUtil.copyProperties(voucherDTO,voucherPO);
        return super.save(voucherPO);
    }

    /**
     * 批量生成入账凭证
     *
     * @return Boolean
     */
    @Override
    public Boolean generateVoucher(List<VoucherDTO> voucherDTOList) {
        List<VoucherPO> voucherPOList = MyBeanUtil.copyListProperties(voucherDTOList, VoucherPO::new);
        return super.saveBatch(voucherPOList);
    }

    /**
     * 查看入账凭证信息列表
     *
     * @return ResponseResult
     */
    @Override
    public ResponseResult listAll() {
        List<VoucherPO> list = super.list();
        List<VoucherDTO> voucherDTOList = MyBeanUtil.copyListProperties(list, VoucherDTO.class);
        List<VoucherVO> voucherVOList = MyBeanUtil.copyListProperties(voucherDTOList, VoucherVO.class);
        return new QueryResponseResult(SUCCESS,voucherVOList);
    }

    /**
     * 通过入账凭证号获取电子凭证
     *
     * @param voucherDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult getByAccountId(VoucherDTO voucherDTO) {
        VoucherPO voucherPO = super.getById(voucherDTO.getAccountId());
        VoucherVO voucherVO = MyBeanUtil.copyProperties(voucherPO, VoucherVO.class);
        return new QueryResponseResult(SUCCESS,voucherVO);
    }

    /**
     * 分页查询入账凭证信息列表
     *
     * @param pageDTO
     * @return List<VoucherDTO>
     */
    @Override
    public QueryResponseResult<PageVO> listByPage(PageDTO<VoucherDTO> pageDTO) {
        Page<VoucherPO> voucherPOPage = new Page<>();
        //设置分页信息
        voucherPOPage.setCurrent(pageDTO.getPage());
        voucherPOPage.setSize(pageDTO.getLimit());
        //读取分页数据
        QueryWrapper<VoucherPO> queryWrapper = new QueryWrapper<>();
        //对单位代码进行模糊查询
        queryWrapper.isNull(pageDTO.getKeyword())
                .or()
                .like(VoucherPO.F_ACCOUNT_ID,pageDTO.getKeyword())
                .or()
                .like(VoucherPO.F_ACCOUNT_TYPE,pageDTO.getKeyword())
                .or()
                .like(VoucherPO.F_AGEN_NAME,pageDTO.getKeyword());
        //根据时间排序
        queryWrapper.orderByAsc(VoucherPO.F_CREATE_TIME);
        Page<VoucherPO> poPage = super.page(voucherPOPage,queryWrapper);
        List<VoucherPO> records = poPage.getRecords();
        //转换数据
        List<VoucherDTO> list = MyBeanUtil.copyListProperties(records, VoucherDTO::new);
        pageDTO.setTotal(poPage.getTotal());
        pageDTO.setItems(list);
        PageVO pageVO = MyBeanUtil.myCopyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(SUCCESS, pageVO);
    }

    /**
     * 删除入账凭证信息
     *
     * @param voucherDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult delete(VoucherDTO voucherDTO) {
        //执行删除操作
        boolean remove = super.removeById(voucherDTO.getAccountId());
        //删除失败返回操作错误
        if(!remove){
            return new ResponseResult(VoucherCode.DELETE_FAIL);
        }
        //删除成功
        return new ResponseResult(VoucherCode.SUCCESS);
    }

    /**
     * 批量删除入账凭证信息
     *
     * @param voucherDTOList
     * @return ResponseResult
     */
    @Override
    public ResponseResult batchDelete(List<VoucherDTO> voucherDTOList) {
        //构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<VoucherDTO> iterator = voucherDTOList.iterator(); iterator.hasNext();) {
            idList.add(iterator.next().getAccountId());
        }
        //执行批量删除
        boolean removeByIds = super.removeByIds(idList);
        // 删除失败返回操作失败
        if (!removeByIds) {
            return new ResponseResult(VoucherCode.DELETE_FAIL);
        }
        // 删除成功返回操作成功
        return new ResponseResult(VoucherCode.SUCCESS);
    }

}
