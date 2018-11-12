package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void add(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setStatus("1");
        String replace = UUID.randomUUID().toString().replace("-", "");
        String salt=MD5Util.getPwd(replace);
        user.setSalt(salt);
        String pwd = MD5Util.getPwd(user.getPassword());
        user.setPassword(pwd+salt);
        userDao.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findLogin(User user) {
        User user1 = userDao.querySalt(user);
        String pwd = MD5Util.getPwd(user.getPassword());
        user.setPassword(pwd+user1.getSalt());
        return userDao.queryLogin(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findByPageService(Integer page, Integer rows) {
        return null;
    }

    @Override
    public Long findTotal() {
        return null;
    }

    @Override
    public void motify(User user) {
        String pwd = MD5Util.getPwd(user.getPassword());
        String replace = UUID.randomUUID().toString().replace("-", "");
        String salt=MD5Util.getPwd(replace);
        user.setSalt(salt);
        user.setPassword(pwd+salt);
        userDao.update(user);
    }

    @Override
    public void remove(String id) {

    }
}
