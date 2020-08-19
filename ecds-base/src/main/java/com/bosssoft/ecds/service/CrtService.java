package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.CrtDTO;
import com.bosssoft.ecds.entity.po.CrtPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vihenne
 * @since 2020-08-10
 */
public interface CrtService extends IService<CrtPO> {

    /**
     *
     *
     * @description: 新增领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public CrtDTO save(CrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 按领购证编码删除领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean remove(CrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 用于修改领购证信息。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean update(CrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 根据准购证号查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public CrtDTO getByCrtCode(CrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 根据id查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public CrtDTO getById(CrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 根据单位编码查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<CrtDTO> getByAgenCode(CrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 用于查看领购证列表。
     * @return: {List<UabCrtDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<CrtDTO> listAll();

    /**
     * 分页读取领购证
     *
     * @param pageDTO
     * @return
     */
    public PageDTO listByPage(PageDTO pageDTO);

    /**
     * 准购证审核分页读取领购证
     *
     * @param pageDTO
     * @return
     */
    public PageDTO checkListByPage(PageDTO pageDTO);

    /**
     * 批量删除领购证
     *
     * @param uabCrtDTODTOList
     * @return
     */
    public Boolean removeBatch(List<CrtDTO> uabCrtDTODTOList);

    /**
     * 批量审核领购证
     *
     * @param uabCrtDTODTOList
     * @return
     */
    public Boolean checkBatch(List<CrtDTO> uabCrtDTODTOList);
}
