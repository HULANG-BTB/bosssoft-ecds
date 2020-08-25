package com.bosssoft.usm.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了若干单表基本增删改查操作的抽象服务
 * @param <B> 实体类PO
 * @param <M> 实体类PO对应Mapper接口
 */
public abstract class AbstractService<B, M extends BaseMapper<B>> {

    @Autowired
    protected M mapper;

    public B getByPrimaryKey(Serializable key) {
        B bean = mapper.selectById(key);
        if (bean == null){
            return null;
        }
        return bean;
    }

    public B get(B bean) {
        bean = mapper.selectOne(null);
        return bean;
    }

    public List<B> getAll() {
        List<B> beans = mapper.selectList(null);
        if (beans == null) {
            return new ArrayList<>();
        }
        return beans;
    }

    public Class<B> getBeanClass() {
        try {
            String name = this.getClass().getName();
            String className = name.substring(name.lastIndexOf('.'), name.lastIndexOf("Service"));
            String packageName = name.substring(0, name.lastIndexOf('.'));
            packageName = packageName.substring(0, packageName.lastIndexOf('.'));
            packageName += ".bean";
            className = packageName + className;
            return (Class<B>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public B createBeanObject() {
        try {
            return getBeanClass().newInstance();
        } catch (InstantiationException|IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(B bean) {
        return mapper.insert(bean);
    }

    public void update(B bean) {
        mapper.updateById(bean);
    }

    public int updateByWrapper(B bean, Wrapper<B> updateWrapper) {
       return mapper.update(bean,updateWrapper);
    }

    public boolean delete(Serializable primaryKey) {
        mapper.deleteById(primaryKey);
        return true;
    }

}
