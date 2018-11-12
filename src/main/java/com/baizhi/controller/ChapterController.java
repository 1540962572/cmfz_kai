package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/add")
    @ResponseBody
    /**
     * size:音频的大小
     * duration音频的时长
     * downPath下载路径
     * */
    public Map<String, Object> add(Chapter chapter, @RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, Object> results = new HashMap<String, Object>();

        long ls = 0;
        try {
            //获取当前项目路径
            String images = request.getSession().getServletContext().getRealPath("back");
            String path = images + "/album/mp4/" + file.getOriginalFilename();
            //上传
            File newFile = new File(path);
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(newFile);
            //修改album里的图片路径
            chapter.setDownPath("/mp4/" + file.getOriginalFilename());
            DecimalFormat decimalFormat = new DecimalFormat("#####0.00");
            chapter.setSize(decimalFormat.format(file.getSize() / 1024.00 / 1024.00) + "M");
            MP3File f = (MP3File) AudioFileIO.read(newFile);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            String trackLength = audioHeader.getTrackLength() / 360 + ":" + audioHeader.getTrackLength() / 60 + ":" + audioHeader.getTrackLength() % 60;
            chapter.setDuration(trackLength);
            System.out.println(chapter);
            //调用添加方法，将banner存储到数据库
            chapterService.add(chapter);
            results.put("success", true);
        } catch (Exception e) {
            results.put("success", false);
            results.put("message", e.getMessage());
        }
        return results;
    }

    @RequestMapping("/download")
    @ResponseBody
    public void downLoad(String downPath, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取当前项目路径
        String images = request.getSession().getServletContext().getRealPath("back");
        String path = images + "/album/mp4/";
        String[] split=downPath.split("/mp4/");

        System.out.println(path+"!!!!!!!!!!");
        System.out.println(split[1]+"!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        FileInputStream is = new FileInputStream(new File(path,split[1]));
        response.setHeader("Content-Disposition","attachment;filename="+new String(split[1].getBytes("UTF-8"),"ISO8859-1"));
        ServletOutputStream os=response.getOutputStream();
        byte[] b=new byte[1024];
        int len;
        while (true){
            len=is.read(b);
            if(len==-1) break;
            os.write(b,0,len);
        }
        if(is!=null) is.close();
        if(os!=null) os.close();
    }
}