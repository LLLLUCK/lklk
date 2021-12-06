package edu.soft1.controller;

import edu.soft1.pojo.User;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.protocol.HttpProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/param1")
public class MyController {

//    @RequestMapping("/hello.do")
//    public String hello(){
//        System.out.println("---hello()---");
//        return "hello";
//    }

//    @RequestMapping("/hello.do")
//    public ModelAndView hello(){
//        System.out.println("---hello()---");//进入方法标志
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("hello");
//        mav.addObject("msg","I'm Peter!");
//        return mav;
//    }

//    @RequestMapping("/hello")
//    public String hello(String username,Model model){
//        System.out.println("---hello()---");//进入方法标志
//        //将传入的参数添加到Model对象（存入了request作用域）
//        model.addAttribute("username",username);
////        model.addAttribute(username);
//        return "hello";
//    }

    @RequestMapping("/hello")
    public String hello(String username, Map<String ,String >map){
        System.out.println("---hello()---");//进入方法标志
        map.put("username",username);
        return "hello";
    }

    @RequestMapping(value = "/param1",method = {RequestMethod.GET})
    public String param1(HttpServletRequest request){
        //接收client发来的数据
        String name = request.getParameter("name");
        System.out.println("name="+name);
        request.setAttribute("name",name);
        //调用业务层的方法
        //页面跳转
        return "hello";
    }


    @RequestMapping(value = "/param2",method = {RequestMethod.GET,RequestMethod.POST})
    public String param2(HttpServletRequest request, HttpSession session){
        //接收client发来的数据
        String name = request.getParameter("username");//获取数据
        String age = request.getParameter("age");//获取数据
        System.out.println("name="+name+",age="+age);//打印数据
        session.setAttribute("age",age);//将数据存入session
        request.setAttribute("name",name);//将数据存入request
        return "hello";//跳转至jsp/hello.jsp页面
    }

    @RequestMapping(value = "/param3",method = {RequestMethod.POST})
    public String param3(String username,int age){
        System.out.println("----param3()----");
        System.out.println("username="+username);//打印数据
        System.out.println("age="+age);
        return "hello";//跳转至jsp/hello.jsp页面
    }

    @RequestMapping(value = "/param4",method = {RequestMethod.POST})//数据名与方法参数不同
    public String param4(@RequestParam(value = "username",required = false) String u,
                         @RequestParam(value = "age",defaultValue = "18") int  a, HttpServletRequest session) {
        System.out.println("----param4()----");
        System.out.println("u="+u);
        System.out.println("a="+a);
        session.setAttribute("name",u);
        return "redirect:test";
    }


    @RequestMapping(value = "/param5",method = {RequestMethod.POST})//数据名与方法参数不同
    public String param5(User user, HttpSession session) {
        System.out.println("----param5()----");
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        session.setAttribute("name",user.getUsername());
        return "redirect:test";

    }


    @RequestMapping("test")
    public String test(){
        System.out.println("----test()----");
        return "hello";
    }

    @RequestMapping("/reg")
    public String reg(User user){
        System.out.println("username="+user.getUsername());
        System.out.println("pwd="+user.getAge());
        System.out.println("birthday="+user.getBirthday());
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        return "hello";
    }

}
