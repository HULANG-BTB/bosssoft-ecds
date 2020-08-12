package com.bosssoft.ecds.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BeanUtils {

    private static final ConcurrentMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();

    public static <T> T convertObject(Object source, Class<T> target) {
        T ret = null;
        if (source != null) {
            try {
                ret = target.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("create class[" + target.getName()
                        + "] instance error", e);
            }
            BeanCopier beanCopier = getBeanCopier(source.getClass(), target);
            beanCopier.copy(source, ret, null);
        }
        return ret;
    }

    public static <T> List<T> convertList(List<?> list, Class<T> target) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("被复制的队列为空!");
        }
        List<T> resultList = new LinkedList<>();
        for (Object object : list) {
            resultList.add(convertObject(object, target));
        }
        return resultList;
    }

    public static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String beanCopierKey = generateBeanKey(source, target);
        if (beanCopierMap.containsKey(beanCopierKey)) {
            return beanCopierMap.get(beanCopierKey);
        } else {
            BeanCopier beanCopier = BeanCopier.create(source, target, false);
            beanCopierMap.putIfAbsent(beanCopierKey, beanCopier);
        }
        return beanCopierMap.get(beanCopierKey);
    }

    public static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "@" + target.getName();
    }
}
