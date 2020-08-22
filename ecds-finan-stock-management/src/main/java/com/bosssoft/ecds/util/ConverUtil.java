package com.bosssoft.ecds.util;

import cn.hutool.core.convert.Convert;
import com.bosssoft.ecds.entity.dto.StockOutChangeDto;
import com.bosssoft.ecds.entity.vo.StockOutVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author misheep
 * @Date 2020/8/12 20:14
 * @Description
 */
@Slf4j
public class ConverUtil {
    public static <T, S> List<T> converList(Class<T> targetObjectClass, List<S> sourceList) {
        List<T> targetList = Lists.newArrayList();
        if(sourceList==null || sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        log.info("进入conver方法...data:{}", sourceList);
        if(targetObjectClass==StockOutChangeDto.class &&
                sourceList.stream().findAny().get().getClass() == StockOutVo.class){
            log.info("变动转换...");
            sourceList.forEach(s -> targetList.add((T) outVoToChangeDto((StockOutVo) s)));
            log.info("data:{}",targetList.toString());
            return targetList;
        }
        sourceList.forEach(s -> targetList.add(Convert.convert(targetObjectClass, s)));
        return targetList;
    }

    /**
     * bean转化
     * 将StockOutVo转化为StockOutChangeDto
     *
     * @param outVo outVo
     * @return outChangeDto
     */
    public static StockOutChangeDto outVoToChangeDto(StockOutVo outVo) {
        StockOutChangeDto dto = new StockOutChangeDto();
        dto.setPid(outVo.getId());
        dto.setAltercode(outVo.getAltercode());
        dto.setChangeState(outVo.getChangeState());
        dto.setOperator(outVo.getAuthor());
        dto.setChangeSitu(outVo.getAbstact());
        return dto;
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
