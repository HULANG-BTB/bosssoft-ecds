package com.bosssoft.usm.service;


import java.io.Serializable;
import java.util.List;

public interface BaseService<B> {

     B getByPrimaryKey(Serializable key);

     B get(B bean);

     List<B> getAll();

     Class<B> getBeanClass();

     B createBeanObject();

     int insert(B bean);

     void update(B bean);

     boolean delete(Serializable primaryKey);

//     List<B> getsByExample(Example example);
//
//     B getByExample(Example example);
}
