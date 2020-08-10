package com.boss.msg.util;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import java.util.List;

/**
 * @author zhangxiaohui
 */
public class DozerUtils {
    private DozerUtils() {
        //
    }

    private static final Mapper dozer;

    static {

        dozer = DozerBeanMapperBuilder.create().build();
        //指定 dozer mapping 的配置文件(放到 resources 类路径下即可)，可添加多个 xml 文件，用逗号隔开
    }

    public static <T, S> List<T> mapList(List<S> sourceList, Class<T> targetObjectClass) {
        List<T> targetList = Lists.newArrayList();
        sourceList.forEach(s -> targetList.add(dozer.map(s, targetObjectClass)));
        return targetList;

    }

    public static <T> T map(Object o, Class<T> aClass) {
        return dozer.map(o, aClass);
    }

    public static void map(Object o, Object o1) {
        dozer.map(o, o1);
    }

    public static <T> T map(Object o, Class<T> aClass, String s) {
        return dozer.map(o, aClass, s);
    }

    public static void map(Object o, Object o1, String s) {
        dozer.map(o, o1, s);
    }
}
