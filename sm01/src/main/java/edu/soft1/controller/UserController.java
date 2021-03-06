package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("-----hello-----");
        return "hello" +
                "";
    }

    @RequestMapping(value = "upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile image, HttpServletRequest request) throws IOException {
        InputStream is = image.getInputStream();//输入流
        String filename = image.getOriginalFilename();//文件名称
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("上传路径realPath="+realPath);
        FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
        int size = IOUtils.copy(is,os);
        System.out.println("完成上传size="+size+"Byte");
        os.close();is.close();
        return "welcome";
    }

    @RequestMapping(value = "/load.do/{filename}")
    public void load(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) throws IOException  {
        response.setHeader("Conten-Disposition","attachment;filename="+文件名);
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("下载路径="+realPath);
        FileInputStream is = new FileInputStream(new File(realPath,filename));
        OutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        os.close();is.close();
    }
    private String doFilename1(String filename,HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toUpperCase().indexOf("FIREFOX")>0){
            filename = "?UTF-8?B?"+new String();
        }
        return "";
    }

    private String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//获取uuid字符，规避名称重复
        System.out.println("上传文件名="+uuid);
        return uuid+"."+extension;
    }
    @RequestMapping(value = "upload2.do",method = {RequestMethod.POST})
    public String fileUpload2(MultipartFile[] images,HttpServletRequest request) throws IOException {
        InputStream is = null;
        OutputStream os = null;//输入流
        for (MultipartFile image : images) {
            is = image.getInputStream();
            String filename = image.getOriginalFilename();//文件名称
            System.out.println("文件原名称=" + filename);
            if (filename.equals("")) {
                System.out.println("空字符串,进入下一轮循环");
                continue;
            }
            String realPath = request.getServletContext().getRealPath("/images");
            System.out.println("上传路径realPath=" + realPath);
            os = new FileOutputStream(new File(realPath, doFileName(filename)));
            IOUtils.copy(is, os);
        }
            os.close();
            is.close();
            return "welcome";
        }




    @RequestMapping(value = "/login")
    public String login(User user, HttpSession session,HttpServletRequest request){
        System.out.println("-----login()-----");


        int flag = 1;
        if (flag == 1) {
            System.out.println("username="+user.getUsername());
            session.setAttribute("user",user);
            return "welcome";//登录成功
        }
        System.out.println("登录失败,返回首页index");
        request.setAttribute("error","登录失败,请重新尝试");
        return "forward:/index.jsp";//登录失败
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request){
        System.out.println("-----执行delete()成功!-----");
        request.setAttribute("CRUDmsg","删除功能执行完毕!");
        return "hello";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        System.out.println("-----welcome-----");
        return "welcome";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        System.out.println("已登出~");
        return "redirect:/index.jsp";
    }
}
