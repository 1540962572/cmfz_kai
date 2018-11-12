package com.baizhi.service.impl;

import com.baizhi.dao.CourseDao;
import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findAll(Course course) {
        return courseDao.queryAll(course);
    }

    @Override
    public void add(Course course) {
        course.setId(UUID.randomUUID().toString().replace("-",""));
        course.setCreatTime(new Date());
        courseDao.insert(course);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Course findLogin(Course course) {
        return courseDao.queryLogin(course);
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public List<Course> findByPageService(Integer page, Integer rows) {
        return null;
    }

    @Override
    public Long findTotal() {
        return null;
    }

    @Override
    public void motify(Course course) {
        courseDao.update(course);
    }

    @Override
    public void remove(String id) {
        courseDao.delete(id);
    }
}
