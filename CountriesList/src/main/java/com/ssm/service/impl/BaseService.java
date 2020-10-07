package com.ssm.service.impl;

import com.ssm.mapper.MyMapper;
import com.ssm.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected MyMapper<T> myMapper;

    public MyMapper<T> getMyMapper() {
        return myMapper;
    }

    @Override
    public T selectByKey(Object key) {
        return myMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<T> select(T t) {
        return myMapper.select(t);
    }

    @Override
    public int save(T entity) {
        return myMapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        return myMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int update(T entity) {
        return myMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return myMapper.selectByExample(example);
    }
}
