package com.baizhi.test;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserTest {
    @Autowired
    private UserService userService;
    @Test
    public void test01(){
        User user = new User();
        user.setUsername("李凯");
        user.setPassword("1540962572");
        user.setPhoneNum("15035445179");
        user.setNickName("达摩");
        user.setProvince("山西省");
        user.setCity("运城市");
        user.setGender("男");
        user.setSign("标志");
        user.setHeadPic("/img/a.ipg");
        user.setDate(new Date());
        userService.add(user);
    }
    @Test
    public void test02(){
        User user=new User();
        user.setPhoneNum("15035445179");
        user.setPassword("1540962572");
        User login = userService.findLogin(user);
        System.out.println(login);
    }
}
