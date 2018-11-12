package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String, Object> findAll(Integer page, Integer rows){
        Map<String,Object> results=new HashMap<String,Object>();
        List<Album> byPageService = albumService.findByPageService(page, rows);
        results.put("total",albumService.findTotal());//将总条数传递过去
        results.put("rows",byPageService);//查出总数据传递过去
        return results;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> add(Album album , @RequestParam("file") CommonsMultipartFile file, HttpServletRequest request)throws IOException {
        Map<String,Object> results=new HashMap<String, Object>();
        try {

            //获取当前项目路径
            String images = request.getSession().getServletContext().getRealPath("back");
            String path = images + "/album/img/" + file.getOriginalFilename();
            //上传
            File newFile = new File(path);
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(newFile);
            //修改album里的图片路径
            album.setCoverImg("/img/" + file.getOriginalFilename());
            //调用添加方法，将banner存储到数据库
            albumService.add(album);
            results.put("success", true);
        }catch (Exception e){
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }
    @RequestMapping("/findAlbum")
    @ResponseBody
    public List<Album> findAlbum(){
        List<Album> all = albumService.findAll();
        return all;
    }
    @RequestMapping("/download")
    @ResponseBody
    public void downLoad(Album album, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String images = request.getSession().getServletContext().getRealPath("back");
        String path = images + "/album/mp4/";
        Album byId = albumService.findById(album);
        System.out.println(byId);
        for (Chapter chapter : byId.getChildren()) {
            String[] split=chapter.getDownPath().split("/mp4/");
            System.out.println(split[1]);
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
    @RequestMapping("/findById")
    public @ResponseBody Album findById(Album album,HttpServletRequest request){
        Album byId = albumService.findById(album);
        System.out.println(byId.getCoverImg());
        HttpSession session = request.getSession();
        session.setAttribute("img",byId.getCoverImg());
        return byId;
    }
}
