package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.FabAgenDTO;
import com.bosssoft.ecds.entity.dto.FabDeptDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.FabAgenPO;
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
public interface FabAgenService extends IService<FabAgenPO> {

    /**
     *
     *
     * @description: 新增单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public FabAgenDTO save(FabAgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 按单位编码删除单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean remove(FabAgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 用于修改单位信息。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean update(FabAgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 根据单位编码查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public FabAgenDTO getByAgenCode(FabAgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 根据单位名查询单位。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public FabAgenDTO getByAgenName(FabAgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 根据部门编码查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {List<FabAgenDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<FabAgenDTO> getByDeptCode(FabAgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 用于查看单位列表。
     * @return: {List<FabAgenDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<FabAgenDTO> listAll();

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
     * @param fabAgenDTOList
     * @return
     */
    public Boolean removeBatch(List<FabAgenDTO> fabAgenDTOList);

}
