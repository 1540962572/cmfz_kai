package com.baizhi.service;

import java.util.List;

public interface BeanService<T> {
    void add(T t);
    T findLogin(T t);
    List<T> findAll();
    List<T> findByPageService(Integer page,Integer rows);
    Long findTotal();
    void motify(T t);
    void remove(String id);
}
