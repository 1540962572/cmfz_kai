package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService extends BeanService<Admin> {
    Admin findById(Admin admin);
}
