package com.bosssoft.ecds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.agendto.AgenItemDTO;
import com.bosssoft.ecds.entity.dto.ItemDTO;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.po.AgenItemPO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.entity.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wzh
 * @since 2020-08-12
 */
public interface AgenItemService extends IService<AgenItemPO> {

    /**
     * 分页查询单位的可用票据
     *
     * @param pageDTO 输入分页信息,limit、page、keyword
     *                keyword为空时普通查询，keyword不为空时模糊查询
     * @return limit、page、total、items
     */
    QueryResponseResult<PageVO> listByPage(PageDTO<ItemDTO> pageDTO);

    /**
     * 查询单位所有的可用项目，不通过分页显示
     *
     * @param agenItemDTO 输入单位编码
     * @return 返回出单位所有的可用项目
     */
    QueryResponseResult<List<ItemVO>> getItemAll(AgenItemDTO agenItemDTO);


    /**
     * 批量维护单位项目关系数据
     *
     * @param agenItemDTOList 输入单位编码和项目编码
     * @return 返回成功或者失败的code和msg
     */
    ResponseResult updateBatch(List<AgenItemDTO> agenItemDTOList);

}
