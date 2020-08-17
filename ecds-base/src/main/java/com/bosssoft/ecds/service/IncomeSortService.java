package com.bosssoft.ecds.service;


import com.bosssoft.ecds.entity.dto.IncomeSortDTO;
import com.bosssoft.ecds.entity.vo.incomesortvo.AddIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.DeleteIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.FuzzyQueryIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.PageIncomeSortVO;
import com.bosssoft.ecds.entity.vo.incomesortvo.UpdateIncomeSortVO;
import com.bosssoft.ecds.response.QueryResponseResult;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/5 11:27
 */
public interface IncomeSortService {

    /**
     * 获取全部收入种类信息
     *
     * @return
     */
    QueryResponseResult getAll();

    /**
     * 根据名称、编码分页获取收入信息
     *
     * @param fuzzyQueryIncomeSortVO
     * @return
     */
    QueryResponseResult<IncomeSortDTO> pageQueryByName(FuzzyQueryIncomeSortVO fuzzyQueryIncomeSortVO);

    /**
     * 根据收入类别id分页获取该种类的子类信息
     *
     * @param pageIncomeSortVO
     * @return
     */
    QueryResponseResult<IncomeSortDTO> pageQueryById(PageIncomeSortVO pageIncomeSortVO);

    /**
     * 判断收入名称是否符合入库规则
     *
     * @param name
     * @return
     */
    Boolean checkByName(String name);

    /**
     * 判断编码和父级id是否符合入库规则
     *
     * @param code
     * @param parentId
     * @return
     */
    Boolean checkByCode(String code, Long parentId);

    /**
     * 更新收入信息
     *
     * @param updateIncomeSortVO
     * @return
     */
    Boolean update(UpdateIncomeSortVO updateIncomeSortVO);


    /**
     * 新增收入类别
     *
     * @param addIncomeSortVO
     * @return
     */
    Boolean add(AddIncomeSortVO addIncomeSortVO);


    /**
     * 查询第一级收入类别
     *
     * @return
     */
    QueryResponseResult getFirstIncomeSort();

    /**
     * 删除指定的收入类别
     *
     * @param deleteIncomeSortVO
     * @return
     */
    Boolean delete(DeleteIncomeSortVO deleteIncomeSortVO);

    /**
     * 对外提供收入所有类别信息
     *
     * @return
     */
    QueryResponseResult selectAll();

}
