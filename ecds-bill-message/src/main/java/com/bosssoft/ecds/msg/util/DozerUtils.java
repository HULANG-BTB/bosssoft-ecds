package com.bosssoft.ecds.msg.util;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxiaohui
 */
public class DozerUtils {
    private DozerUtils() {
        //
    }

    private static final Mapper DOZER;

    static {
        DOZER = DozerBeanMapperBuilder.create().build();
        //指定 dozer mapping 的配置文件(放到 resources 类路径下即可)，可添加多个 xml 文件，用逗号隔开
    }

    public static <T, S> List<T> mapList(List<S> sourceList, Class<T> targetObjectClass) {
        List<T> targetList = Lists.newArrayList();
        sourceList.forEach(s -> targetList.add(DOZER.map(s, targetObjectClass)));
        return targetList;

    }

    /**
     * 将javabean实体类转为map类型，然后返回一个map类型的值
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
    public static <T> T map(Object o, Class<T> aClass) {
        return DOZER.map(o, aClass);
    }

    public static void map(Object o, Object o1) {
        DOZER.map(o, o1);
    }

    public static <T> T map(Object o, Class<T> aClass, String s) {
        return DOZER.map(o, aClass, s);
    }

    public static void map(Object o, Object o1, String s) {
        DOZER.map(o, o1, s);
    }
}
