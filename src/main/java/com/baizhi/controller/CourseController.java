package com.baizhi.controller;

import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Course> findAll(){
        //1代表是必修课
        Course course = new Course();
        course.setStauts("1");
        List<Course> all = courseService.findAll(course);
        return all;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> add(Course course){
        Map<String,Object> results=new HashMap<String,Object>();
        try {
            course.setStauts("1");
            courseService.add(course);
        }catch (Exception e){
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }
    @RequestMapping("/findId")
    @ResponseBody
    public Course findId(Course course){
        Course login = courseService.findLogin(course);
        return login;
    }
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Course course){
        Map<String,Object> results=new HashMap<String,Object>();
        try {
            System.out.println(course);
            courseService.motify(course);
            results.put("success",true);
        }catch (Exception e){
            results.put("success",true);
            results.put("message",e.getMessage());
        }
        return results;
    }
    @RequestMapping("/deleteAll")
    @ResponseBody
    public Map<String,Object> deleteAll(String[] id){
        Map<String,Object> results=new HashMap<String, Object>();
        try {
            for (int i=0;i<id.length;i++){
                courseService.remove(id[i]);
            }
            results.put("success",true);
        }catch (Exception e){
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }
}
