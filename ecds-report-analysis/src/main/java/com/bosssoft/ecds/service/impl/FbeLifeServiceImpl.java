package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.dao.FbeLifeDao;
import com.bosssoft.ecds.domain.po.FbeLifePO;
import com.bosssoft.ecds.domain.search.LifeSearch;
import com.bosssoft.ecds.domain.vo.FbeLifeVO;
import com.bosssoft.ecds.service.FbeLifeService;
import com.bosssoft.ecds.utils.POToVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zouyou
 * @version 1.0
 * @description 生命周期的服务层类，处理业务逻辑和实体类转换
 * @date 2020/8/24 18:33
 */
@Service
public class FbeLifeServiceImpl implements FbeLifeService {

    @Autowired
    private FbeLifeDao fbeLifeDao;

    /**
     * service层将PO对象转换成VO对象，返回VO的list集合
     * @param lifeSearch
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeLifeVO>
     * @date 2020/8/24 23:40
     */
    @Override
    public List<FbeLifeVO> getLifeList(LifeSearch lifeSearch) {
        List<FbeLifePO> fbeLifePOList= fbeLifeDao.getLifeList(lifeSearch);
        List<FbeLifeVO> fbeLifeVOList = new ArrayList<>();
        for (FbeLifePO fbeLifePO : fbeLifePOList) {
            fbeLifeVOList.add(POToVO.fbeLifePOToVO(fbeLifePO));
        }
        return fbeLifeVOList;
    }
}
