package com.bosssoft.ecds.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Bean工具类
 * 参考 https://www.huangyunkun.com/2014/11/23/java-cloning-library/
 * https://juejin.im/entry/5bc3db04f265da0aaa053baa
 *
 * @Date: 2018/12/20 17:12
 */
public class MyBeanUtil {

    private static final Cloner cloner = new Cloner();

    private MyBeanUtil() {

    }

    /**
     * 复制对象（深度拷贝）
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T clone(final T object) {
        if (object == null) {
            return null;
        }
        return cloner.deepClone(object);
    }

    /**
     * 复制集合（深度拷贝）
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> List<T> cloneList(final List<T> object) {
        if (object == null) {
            return null;
        }
        return cloner.deepClone(object);
    }

    /**
     * 复制对象到指定类（深度拷贝）
     *
     * @param object
     * @param destclas 指定类
     * @param <T>
     * @return
     */
    public static <T> T clone(final Object object, Class<T> destclas) {
        if (object == null) {
            return null;
        }
        String json = JSON.toJSONString(object);
        return JSON.parseObject(json, destclas);
    }

    /**
     * 复制集合到指定类（深度拷贝）
     *
     * @param object
     * @param destclas 指定类
     * @param <T>
     * @return
     */
    public static <T> List<T> cloneList(List<?> object, Class<T> destclas) {
        if (object == null) {
            return new ArrayList<T>();
        }
        String json = JSON.toJSONString(object);
        return JSON.parseArray(json, destclas);
    }

    public static <T> T copyProperties(Object source, Class<T> tClass, String... ignoreProperties) {
        T target = ReflectUtil.newInstanceIfPossible(tClass);
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
        return target;
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
    }

    public static <T> T myCopyProperties(Object source, Class<T> tClass, String... ignoreProperties) {
        T target = ReflectUtil.newInstanceIfPossible(tClass);
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
        return target;
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
