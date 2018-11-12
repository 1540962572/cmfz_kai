package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserConterller {
    @Autowired
    private UserService userService;
    @RequestMapping("/regist")
    @ResponseBody
    public Map<String,Object> regist(User user){
        Map<String,Object> results=new HashMap<String, Object>();
        try {
            userService.add(user);

            results.put("success",true);
        }catch (Exception e){
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(User user){
        Map<String ,Object> results=new HashMap<String,Object>();
        try {
            User login = userService.findLogin(user);
            if (login==null){
                results.put("success",101);
            }else {
                results.put("success",login);
            }
        }catch (Exception e){
            results.put("success",103);
            results.put("message",e.getMessage());
        }
        return results;
    }
}
