package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.GroupDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.GroupPO;
import com.bosssoft.ecds.entity.vo.groupvo.GroupVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-14
 */
public interface GroupService extends IService<GroupPO> {

    ResponseResult save(GroupDTO groupDTO);
    ResponseResult update(GroupDTO groupDTO);
    ResponseResult delete(GroupDTO groupDTO);
    ResponseResult listByPage(PageDTO<GroupVO> pageDTO);
    ResponseResult getGroupName();
}
