package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    //添加管理员的业务方法
    @Override
    public void add(Admin admin) {

        adminDao.insert(admin);
    }
    //登录时的查询管理员的业务方法
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin findLogin(Admin admin) {
        return adminDao.queryLogin(admin);
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public List<Admin> findByPageService(Integer page, Integer rows) {
        return null;
    }

    @Override
    public Long findTotal() {
        return null;
    }

    @Override
    public void motify(Admin admin) {
        adminDao.update(admin);
    }

    @Override
    public void remove(String id) {

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin findById(Admin admin) {
        return adminDao.queryId(admin);
    }
}
