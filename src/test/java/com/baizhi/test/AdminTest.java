package com.baizhi.test;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class AdminTest {
    @Autowired
    private AdminService adminService;
    @Test
    public void test01(){
        String paw="0123456";
        Admin admin = new Admin();
        admin.setId("47ce95f7-7844-4fcc-a3f4-16c80e3df696");
        Admin byId = adminService.findById(admin);
        if (byId.getPassword().equals(paw)){
            System.out.println("123456");
        }else {
            System.out.println("密码错误");
        }
        System.out.println(byId);
    }
}
