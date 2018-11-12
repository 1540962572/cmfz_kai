package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public void add(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        banner.setDate(new Date());
        bannerDao.insert(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Banner findLogin(Banner banner) {
        return bannerDao.queryLogin(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findAll() {
        return bannerDao.queryAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findByPageService(Integer page, Integer rows) {
        int start=(page-1)*rows;
        return bannerDao.queryByPage(start,rows);
    }

    @Override
    public Long findTotal() {
        return bannerDao.queryTotal();
    }

    @Override
    public void motify(Banner banner) {
        if (banner.getImgPath()==null){
            bannerDao.updatetwo(banner);
        }else{
            bannerDao.update(banner);
        }
    }

    @Override
    public void remove(String id) {
        bannerDao.delete(id);
    }
}
