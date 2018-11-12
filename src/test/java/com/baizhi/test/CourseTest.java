package com.baizhi.test;

import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class CourseTest {
    @Autowired
    private CourseService courseService;
    @Test
    public void test01(){
        Course course = new Course();
        course.setStauts("1");
        List<Course> all = courseService.findAll(course);
        for (Course course1 : all) {
            System.out.println(course1);
        }
    }
    @Test
    public void test02(){
        Course course = new Course();
        course.setId("000001");
        course.setTitle("数学");
        course.setMarking("13213");
        courseService.motify(course);
    }
}
