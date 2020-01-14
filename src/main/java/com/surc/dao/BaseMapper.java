package com.surc.dao;

import java.util.List;

public interface BaseMapper<T,ID> {
    void insert(T t);
    void delete(ID id);
    void update(T t);
    T selectById(ID id);
    List<T> selectAll();
}
