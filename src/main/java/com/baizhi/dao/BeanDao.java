package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BeanDao<T> {
    void insert(T t);
    T queryLogin(T t);
    List<T> queryAll();
    List<T> queryByPage(@Param("start") Integer starts,@Param("rows") Integer rows);
    Long queryTotal();
    void update(T t);
    void delete(@Param("id") String id);
}
