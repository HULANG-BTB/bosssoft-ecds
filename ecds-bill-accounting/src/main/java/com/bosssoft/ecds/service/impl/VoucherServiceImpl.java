package com.bosssoft.ecds.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.common.response.QueryResponseResult;
import com.bosssoft.ecds.common.response.ResponseResult;
import com.bosssoft.ecds.dao.VoucherDao;
import com.bosssoft.ecds.entity.dto.VoucherDTO;
import com.bosssoft.ecds.entity.po.VoucherPO;
import com.bosssoft.ecds.entity.vo.VoucherVO;
import com.bosssoft.ecds.service.VoucherService;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private VoucherDao voucherDao;

    @Override
    public Boolean generateVoucher(VoucherDTO voucherDTO) {
        //初始化部分数据,主键，版本号，删除状态，创建时间，修改时间；
        VoucherPO voucherPO = new VoucherPO();
        MyBeanUtil.copyProperties(voucherDTO,voucherPO);
        return voucherDao.insert(voucherPO)==1;
    }

    /**
     * 查看入账凭证信息列表
     *
     * @return 空
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
     * @return VoucherDTO
     */
    @Override
    public ResponseResult getByAccountId(VoucherDTO voucherDTO) {
        VoucherPO voucherPO = super.getById(voucherDTO.getAccountId());
        VoucherVO voucherVO = MyBeanUtil.copyProperties(voucherPO, VoucherVO.class);
        return new QueryResponseResult(SUCCESS,voucherVO);
    }

}
