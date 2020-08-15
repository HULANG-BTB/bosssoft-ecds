package com.bosssoft.ecds.util;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author misheep
 * @Date 2020/8/12 20:14
 * @Description
 */
public class ConverUtil {

//    public List<com.bosssoft.ecds.entity.vo.StockOutVo> toVo(List<StockOutnoticePo> stockOutnoticePos){
//        List<com.bosssoft.ecds.entity.vo.StockOutVo> stockOutVos = new ArrayList<>();
//        stockOutnoticePos.forEach(stockOutnoticePo -> {
//            stockOutVos.add(Convert.convert(com.bosssoft.ecds.entity.vo.StockOutVo.class,stockOutnoticePo));
//        });
//        return null;
//    }

//    /**
//     * 出库明细表dto转换
//     *
//     * @param stockOutnoticeItemPos 出库明细表Po
//     *
//     * @return dto
//     */
//    public static List<StockOutItemDto> toDtoList(List<StockOutnoticeItemPo> stockOutnoticeItemPos) {
//        List<StockOutItemDto> stockOutItemDtos = new ArrayList<>();
//        stockOutnoticeItemPos.forEach(stockOutnoticeItemPo -> {
//            stockOutItemDtos.add(Convert.convert(StockOutItemDto.class, stockOutnoticeItemPo));
//        });
//        return stockOutItemDtos;
//    }

//    public static StockOutVo toVo(StockOutnoticeItemPo stockOutnoticeItemPo, List<StockOutnoticeItemPo> stockOutnoticeItemPos) {
//        StockOutVo stockOutVo = new StockOutVo();
//        BeanUtil.copyProperties(stockOutnoticeItemPo, stockOutVo);
//        BeanUtil.copyProperties();
//    }

    //    /**
//     *
//     * @param stockOutVo
//     * @return
//     */
//    public static StockOutDto toDto(StockOutVo stockOutVo) {
//        return Convert.convert(StockOutDto.class, stockOutVo);
//    }
    public static <T, S> List<T> converList(Class<T> targetObjectClass, List<S> sourceList) {
        List<T> targetList = Lists.newArrayList();
        sourceList.forEach(s -> targetList.add(Convert.convert(targetObjectClass, s)));
        return targetList;
    }

    /**
     * 将Object类型转换成对应类型的List
     *
     * @param <T> 泛
     * @param targetObjectClass 目标class类型
     * @param obj 待转换的object
     * @return 结果list
     */
    public static <T> List<T> castList(Class<T> targetObjectClass, Object obj) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(targetObjectClass.cast(o));
            }
            return result;
        }
        return null;
    }

}
