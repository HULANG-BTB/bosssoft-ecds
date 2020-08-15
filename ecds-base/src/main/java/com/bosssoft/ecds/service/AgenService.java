package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.AgenDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenPO;
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
public interface AgenService extends IService<AgenPO> {

    /**
     *
     *
     * @description: 新增单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public AgenDTO save(AgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 按单位编码删除单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean remove(AgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 用于修改单位信息。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean update(AgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 根据单位编码查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {FabAgenDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public AgenDTO getByAgenCode(AgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 根据单位名查询单位。
     * @param {FabDeptDTO} fabDeptDTO
     * @return: {FabDeptDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public AgenDTO getByAgenName(AgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 根据部门编码查询单位。
     * @param {FabAgenDTO} fabAgenDTO
     * @return: {List<FabAgenDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<AgenDTO> getByDeptCode(AgenDTO fabAgenDTO);

    /**
     *
     *
     * @description: 用于查看单位列表。
     * @return: {List<FabAgenDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<AgenDTO> listAll();

    /**
     * 分页读取
     *
     * @param pageDTO
     * @return
     */
    public PageDTO listByPage(PageDTO pageDTO);

    /**
     * 未审核分页读取
     *
     * @param pageDTO
     * @return
     */
    public PageDTO checkListByPage(PageDTO pageDTO);

    /**
     * 批量删除单位
     *
     * @param fabAgenDTOList
     * @return
     */
    public Boolean removeBatch(List<AgenDTO> fabAgenDTOList);

    /**
     * 批量审核单位
     *
     * @param fabAgenDTOList
     * @return
     */
    public Boolean checkBatch(List<AgenDTO> fabAgenDTOList);
}
