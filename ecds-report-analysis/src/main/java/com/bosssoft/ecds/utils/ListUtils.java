package com.bosssoft.ecds.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName ListUtils
 * @Description 复制对象列表
 * @auther wangpeng
 * @Date 2020/8/23 17:00
 * @Version 1.0
 **/
public class ListUtils<T> {
    public void copyList(Object obj, List<T> list2, Class<T> classObj) {
        if ((!Objects.isNull(obj)) && (!Objects.isNull(list2))) {
            List list1 = (List) obj;
            list1.forEach(item -> {
                try {
                    T data = classObj.newInstance();
                    BeanUtils.copyProperties(item, data);
                    list2.add(data);
                } catch (InstantiationException e) {
                } catch (IllegalAccessException e) {
                }
            });
        }
    }
}
