package com.baizhi.test;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class BannerTest {
    @Autowired
    private BannerService bannerService;
    private HttpServletRequest request;

    @Test
    public void test01(){
        List<Banner> all = bannerService.findByPageService(1,10);
        for (Banner banner : all) {
            System.out.println(banner);
        }
        System.out.println(bannerService.findTotal());
    }
    @Test
    public void test02(){
        Banner banner = new Banner();
        banner.setId("fb74a8af-39c4-4e57-9f4d-c50619718b85");
        banner.setTitle("123456");
        banner.setDesc("这是描述");
        banner.setStatus("1");
        bannerService.motify(banner);
    }
    @Test
    public void test03(){
        File file = new File("");
        if(file.exists()){
            file.delete();
        }
    }
}
