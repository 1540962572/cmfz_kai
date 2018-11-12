package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDao extends BeanDao<Admin>{
    Admin queryId(Admin admin);
}
