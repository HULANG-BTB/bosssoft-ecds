package com.bosssoft.usm.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 张东海
 * @date 2020/8/12
 * @description
 */
public class EntityUtil {
    public static <P,D> D POToDTO(P po,D dto){
        CopyOptions copyOptions = new CopyOptions();
        List<Field> sourceFields = Arrays.asList(po.getClass().getDeclaredFields());
        Map<String, String> map = sourceFields.stream()
                .parallel()
                .collect(Collectors.toMap(field -> field.getName(),
                        field -> {
                            String sourceFieldName = field.getName();
                            String fieldName = sourceFieldName.substring(1, sourceFieldName.length());
                            fieldName = fieldName.replace(fieldName.charAt(0),fieldName.toLowerCase().charAt(0));
                            return fieldName;
                        }
                ));
        copyOptions.setFieldMapping(map);
        BeanUtil.copyProperties(po,dto,copyOptions);
        return dto;
    }
}
