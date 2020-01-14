package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

public abstract class BaseServiceImpl<T,ID> implements BaseService<T,ID> {
    @Autowired
    ApplicationContext applicationContext;

    public abstract BaseMapper getMapper();

    @Override
    public void insert(T t) {
        getMapper().insert(t);
    }

    @Override
    public void delete(ID id) {
        getMapper().delete(id);
    }

    @Override
    public void update(T t) {
        getMapper().update(t);
    }

    @Override
    public T selectById(ID id) {
        return (T) getMapper().selectById(id);
    }

    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }
}
