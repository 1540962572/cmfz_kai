package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("findAll")
    public @ResponseBody
    Map<String, Object> queryAll(Integer page, Integer rows){
        Map<String,Object> results=new HashMap<String,Object>();
        List<Banner> byPageService = bannerService.findByPageService(page, rows);
       /* for (Banner banner : byPageService) {
            System.out.println(banner+"!!!!!!!!!!!!!");
        }*/
        results.put("total",bannerService.findTotal());//将总条数传递过去
        results.put("rows",byPageService);//查出总数据传递过去
        return results;
    }
    /**
     * @RequestParam("file")将name=file控件得到的文件封装成CommonsMultipartFile
     * */
    @RequestMapping("/addbanner")
    public @ResponseBody Map<String, Object> addBanner(Banner banner,HttpServletRequest request) throws IllegalStateException, IOException{
        Map<String,Object> results=new HashMap<String,Object>();
        try {
            //获取当前项目路径
            String images = request.getSession().getServletContext().getRealPath("back");
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request))
            {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
                //获取multiRequest 中所有的文件名
                Iterator iter=multiRequest.getFileNames();
                while(iter.hasNext())
                {
                    //一次遍历所有文件
                    MultipartFile file=multiRequest.getFile(iter.next().toString());
                    if(file!=null)
                    {
                        String path=images+"/img/"+file.getOriginalFilename();
                        //上传
                        /*String path="/back/img/"+file.getOriginalFilename();*/
                        file.transferTo(new File(path));
                        //修改banner里的图片路径
                        banner.setImgPath("/img/"+file.getOriginalFilename());
                    }
                }
            }
            //调用添加方法，将banner存储到数据库
            bannerService.add(banner);
            results.put("success",true);
        }catch (Exception e){
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }
    @RequestMapping("/findId")
    public @ResponseBody Banner findID(Banner banner){
        Banner login = bannerService.findLogin(banner);
        return login;
    }
    @RequestMapping("/updatebanner")
    public @ResponseBody Map<String,Object> updateBanner(Banner banner,@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request) throws IOException{
        Map<String,Object> results=new HashMap<String,Object>();
        try {
            //获取当前项目路径
            String images = request.getSession().getServletContext().getRealPath("back");
            String path = images + "/img/" + file.getOriginalFilename();
            //上传
            File newFile = new File(path);
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(newFile);
            //修改banner里的图片路径
            banner.setImgPath("/img/" + file.getOriginalFilename());
            //调用添加方法，将banner存储到数据库
            System.out.println(banner + "!!!!!!!!!!!!!!!");
            bannerService.motify(banner);
            results.put("success", true);
        } catch (Exception e) {
            results.put("success",false);
            results.put("message",e.getMessage());
        }
        return results;
    }
    @RequestMapping("/delete")
    public @ResponseBody String delete(String[] id,HttpServletRequest request,String[] imgPath){

        //获取当前项目路径
        String images = request.getSession().getServletContext().getRealPath("back");
        for (int i=0;i<id.length;i++){
            System.out.println("!!!!!!!!"+id[i]);
            bannerService.remove(id[i]);
            File file = new File(images+imgPath[i]);
            if (file.exists()){
                file.delete();
            }
        }
        return null;
    }
}
