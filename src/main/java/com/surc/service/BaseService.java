package com.surc.service;

import java.util.List;

public interface BaseService <T,ID>{
    void insert(T t);
    void delete(ID id);
    void update(T t);
    T selectById(ID id);
    List<T> selectAll();
}
