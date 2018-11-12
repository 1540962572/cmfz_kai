package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Override
    public void add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setUploadTime(new Date());
        chapterDao.insert(chapter);
    }

    @Override
    public Chapter findLogin(Chapter chapter) {
        return null;
    }

    @Override
    public List<Chapter> findAll() {
        return null;
    }

    @Override
    public List<Chapter> findByPageService(Integer page, Integer rows) {
        return null;
    }

    @Override
    public Long findTotal() {
        return null;
    }

    @Override
    public void motify(Chapter chapter) {

    }

    @Override
    public void remove(String id) {

    }
}
