package com.baizhi.test;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class AlbumTest {
    @Autowired
    private AlbumService albumService;
    @Test
    public void test01(){
        List<Album> all = albumService.findByPageService(1,10);
        for (Album album : all) {
            System.out.println(album);
        }
    }
    @Test
    public void test02(){
        Long total = albumService.findTotal();
        System.out.println(total);
    }
    @Test
    public void test03(){
        Album album = new Album();
        album.setCoverImg("img/134.jpg");
        album.setAuthor("凯哥");
        album.setBrief("dsadas");
        album.setBroadCast("sadasda");
        album.setCount(10);
        album.setScore(10);
        album.setTitle("我是大师");
        albumService.add(album);
    }
    @Test
    public void test05(){
        List<Album> all = albumService.findAll();
        for (Album album : all) {
            System.out.println(album);
        }
    }
    @Test
    public void test06(){
        Album album = new Album();
        album.setId("a6061c4c-e1a2-4070-bbbe-0bf6d2d30856");
        Album byId = albumService.findById(album);
        System.out.println(byId);
        for (Chapter chapter : byId.getChildren()) {
            System.out.println(chapter.getDownPath());
        }
    }
}
