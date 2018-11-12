package com.baizhi.service.impl;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;
    @Override
    public void add(Guru guru) {
        guru.setId(UUID.randomUUID().toString());
        guruDao.insert(guru);
    }

    @Override
    public Guru findLogin(Guru guru) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> findAll() {
        return guruDao.queryAll();
    }

    @Override
    public List<Guru> findByPageService(Integer page, Integer rows) {
        return null;
    }

    @Override
    public Long findTotal() {
        return null;
    }

    @Override
    public void motify(Guru guru) {

    }

    @Override
    public void remove(String id) {

    }
}
