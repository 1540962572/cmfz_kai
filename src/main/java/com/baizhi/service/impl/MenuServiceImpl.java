package com.baizhi.service.impl;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Override
    public void add(Menu menu) { }
    @Override
    public Menu findLogin(Menu menu) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Menu> findAll() {
        return menuDao.queryAll();
    }

    @Override
    public List<Menu> findByPageService(Integer page, Integer rows) {
        return null;
    }

    @Override
    public Long findTotal() {
        return null;
    }

    @Override
    public void motify(Menu menu) {

    }

    @Override
    public void remove(String id) {

    }
}
