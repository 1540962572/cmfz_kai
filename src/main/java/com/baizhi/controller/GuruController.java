package com.baizhi.controller;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Guru> findAll(){
        List<Guru> all = guruService.findAll();
        return all;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> add(){
        return null;
    }
}
