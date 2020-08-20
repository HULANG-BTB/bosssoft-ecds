package com.bosssoft.ecds.service;


import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.vo.billtypevo.AddBillTypeVO;
import com.bosssoft.ecds.entity.vo.billtypevo.BillTypeIdVo;
import com.bosssoft.ecds.entity.vo.billtypevo.QueryTableVO;
import com.bosssoft.ecds.entity.vo.billtypevo.UpdateBillTypeVO;

/**
 * @author :Raiz
 * @date :2020/8/5
 */
public interface BillTypeService {

    /**
     * 添加新的票据种类
     *
     * @param addBillTypeVO 票据种类信息
     * @return 操作结果
     */
    ResponseResult add(AddBillTypeVO addBillTypeVO);

    /**
     * 修改票据种类信息
     *
     * @param updateBillTypeVO 需要更新的票据种类信息
     * @return 操作结果
     */
    ResponseResult update(UpdateBillTypeVO updateBillTypeVO);

    /**
     * 删除票据种类
     *
     * @param billTypeIdVo 票据id
     * @return 操作结果
     */
    ResponseResult delete(BillTypeIdVo billTypeIdVo);

    /**
     * 获取所有票据分类
     *
     * @return 票据分类List
     */
    ResponseResult queryAllBillSort();

    /**
     * 获取所有票据种类
     *
     * @return 票据种类List
     */
    ResponseResult queryAllBillType();

    /**
     * 获取所有票据分类和种类
     *
     * @return 票据种类数
     */
    ResponseResult queryBillTypeTree();

    /**
     * 获取所有票据分类和种类
     *
     * @return 票据种类数
     */
    ResponseResult queryByPage(QueryTableVO queryTableVO);
}
