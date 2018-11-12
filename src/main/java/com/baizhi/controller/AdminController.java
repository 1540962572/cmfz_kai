package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String login(Admin admin, String enCode, HttpServletRequest request){
        Admin loginService = adminService.findLogin(admin);
        Object code = request.getSession().getAttribute("code");
        System.out.println(code);
        System.out.println(enCode);
        if (code.equals(enCode)){
            if (loginService!=null){
                request.getSession().setAttribute("admin",loginService);
                return "redirect:../back/main/main.jsp";
            }else{
                return "redirect:../back/login.jsp";
            }
        }
        return "redirect:../back/login.jsp";
    }
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(Admin admin,@Param("paw") String paw,HttpServletRequest request){
        Map<String,Object> results=new HashMap<String, Object>();
        Admin byId = adminService.findById(admin);
        try {
            if (byId.getPassword().equals(paw)){
                adminService.motify(admin);
                HttpSession session = request.getSession();
                session.removeAttribute("admin");
                results.put("success",true);
            }else {
                results.put("success",false);
                results.put("messager","原密码输入有误");
                System.out.println("密码输入有误");
            }
        }catch (Exception e){
            results.put("success",false);
            results.put("messager",e.getMessage());
        }
        return results;
    }
    @RequestMapping("/out")
    public String out(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return "redirect:../back/login.jsp";
    }
}
