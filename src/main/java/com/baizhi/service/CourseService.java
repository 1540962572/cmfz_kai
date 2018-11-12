package com.baizhi.service;

import com.baizhi.entity.Course;

import java.util.List;

public interface CourseService extends BeanService<Course> {
    List<Course> findAll(Course course);
}
