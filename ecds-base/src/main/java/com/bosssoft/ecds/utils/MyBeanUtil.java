package com.bosssoft.ecds.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @ClassName BeanUtil
 * @Author AloneH
 * @Date 2020/7/31 22:07
 * @Description TODO
 **/
public class MyBeanUtil {

    public static void copyProperties(Object source, Object target) {
        return cn.hutool.core.bean.BeanUtil.copyProperties(source,target);
    }



    /**
     * @author Johnson
     * 使用场景：Entity、Bo、Vo层数据的复制，因为BeanUtils.copyProperties只能给目标对象的属性赋值，却不能在List集合下循环赋值，因此添加该方法
     * 如：List<AdminEntity> 赋值到 List<AdminVo> ，List<AdminVo>中的 AdminVo 属性都会被赋予到值
     * S: 数据源类 ，T: 目标类::new(eg: AdminVo::new)
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        if (null == sources) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
         }
        return list;
    }

    public static <S, T> List<T> copyListProperties(List<S> sources, Class<T> elementType) {
        JSONArray objects = JSONUtil.parseArray(sources);
        return JSONUtil.toList(objects, elementType);
    }

}
