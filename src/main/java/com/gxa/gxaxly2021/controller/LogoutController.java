package com.gxa.gxaxly2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @Autowired
    HttpSession session;
    @RequestMapping("/logout")
    public String logout(){
        //将emplName的值设为null
        session.removeAttribute("emplName");
        //session.setAttribute("emplName",null);
        //回到登录页面
        return "login";
    }
}
