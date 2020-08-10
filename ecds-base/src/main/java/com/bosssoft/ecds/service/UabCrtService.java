package com.bosssoft.ecds.service;

import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.UabCrtDTO;
import com.bosssoft.ecds.entity.po.UabCrtPO;
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
public interface UabCrtService extends IService<UabCrtPO> {

    /**
     *
     *
     * @description: 新增领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public UabCrtDTO save(UabCrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 按领购证编码删除领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean remove(UabCrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 用于修改领购证信息。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {boolean}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public boolean update(UabCrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 根据准购证号查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public UabCrtDTO getByCrtCode(UabCrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 根据单位编码查询领购证。
     * @param {UabCrtDTO} uabCrtDTO
     * @return: {UabCrtDTO}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<UabCrtDTO> getByAgenCode(UabCrtDTO uabCrtDTO);

    /**
     *
     *
     * @description: 用于查看领购证列表。
     * @return: {List<UabCrtDTO>}
     * @author: YuHangChen
     * @time: 09/08/2020 上午10:17
     */
    public List<UabCrtDTO> listAll();

    /**
     * 分页读取领购证
     *
     * @param pageDTO
     * @return
     */
    public PageDTO listByPage(PageDTO pageDTO);

    /**
     * 批量删除领购证
     *
     * @param uabCrtDTODTOList
     * @return
     */
    public Boolean removeBatch(List<UabCrtDTO> uabCrtDTODTOList);

}
