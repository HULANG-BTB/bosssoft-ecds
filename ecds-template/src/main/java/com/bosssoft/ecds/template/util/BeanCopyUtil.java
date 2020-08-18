package com.bosssoft.ecds.template.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 对象拷贝工具类
 */
public class BeanCopyUtil {

    /**
     * 将一个对象List拷贝到另一个对象List
     * from: https://www.cnblogs.com/Johnson-lin/p/12123012.html
     *
     * @param sources 源对象列表
     * @param target 目标类::new (eg: AdminVo::new)
     * @param <S> 源对象类型
     * @param <T> 目标对象类型
     * @return 目标对象列表
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            BeanUtils.copyProperties(source, t);
            list.add(t);
        }
        return list;
    }
}
