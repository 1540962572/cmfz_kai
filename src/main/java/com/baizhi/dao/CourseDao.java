package com.baizhi.dao;

import com.baizhi.entity.Course;

import java.util.List;

public interface CourseDao extends BeanDao<Course> {
    List<Course> queryAll(Course course);
}
