package com.ssm.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<T> {
    T selectByKey(Object key);

    List<T> select(T t);

    int save (T entity);

    int delete(Object key);

    int update(T entity);

    List<T> selectByExample(Object example);

    //TODO 其他...
}
