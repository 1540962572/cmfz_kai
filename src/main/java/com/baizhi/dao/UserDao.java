package com.baizhi.dao;

import com.baizhi.entity.User;

public interface UserDao extends BeanDao<User>{
    User querySalt(User user);
    User queryId(User user);
}
