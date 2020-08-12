package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.DeptDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.DeptPO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vihenne
 * @since 2020-08-09
 */
public interface DeptService extends IService<DeptPO> {

    /**
     *
     *
     * @description: 新增部门。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public DeptDTO save(DeptDTO fabDeptDTO);

    /**
     *
     *
     * @description: 按部门编码删除部门。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean remove(DeptDTO fabDeptDTO);

    /**
     *
     *
     * @description: 用于修改部门信息。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean update(DeptDTO fabDeptDTO);

    /**
     *
     *
     * @description: 根据部门编码查询部门。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public DeptDTO getByDeptCode(DeptDTO fabDeptDTO);

    /**
     *
     *
     * @description: 根据部门名查询部门。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public DeptDTO getByDeptName(DeptDTO fabDeptDTO);

    /**
     *
     *
     * @description: 用于查看部门列表。
     * @return: {List<FabDeptDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<DeptDTO> listAll();

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    public PageDTO listByPage(PageDTO pageDTO);

    /**
     * 批量删除角色
     *
     * @param fabDeptDTOList
     * @return
     */
    public Boolean removeBatch(List<DeptDTO> fabDeptDTOList);
}
