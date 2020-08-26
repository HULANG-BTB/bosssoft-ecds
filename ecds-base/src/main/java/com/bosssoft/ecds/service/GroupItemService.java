package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.GroupItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.GroupItemPO;
import com.bosssoft.ecds.entity.vo.groupvo.GroupItemVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-14
 */
public interface GroupItemService extends IService<GroupItemPO> {
    ResponseResult save(GroupItemDTO groupItemDTO);
    ResponseResult update(GroupItemDTO groupItemDTO);
    ResponseResult delete(GroupItemDTO groupItemDTO);
    ResponseResult listByPage(PageDTO<GroupItemVO> pageDTO);
    ResponseResult getItemInfo(GroupItemDTO groupItemDTO);
}
