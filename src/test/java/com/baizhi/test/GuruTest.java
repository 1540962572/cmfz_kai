package com.baizhi.test;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GuruTest {
    @Autowired
    private GuruService guruService;
    @Test
    public void test01(){
        List<Guru> all = guruService.findAll();
        for (Guru guru : all) {
            System.out.println(guru);
        }
    }
}
