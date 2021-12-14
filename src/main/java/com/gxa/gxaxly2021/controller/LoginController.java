package com.gxa.gxaxly2021.controller;

import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Empl;
import com.gxa.gxaxly2021.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
    @RequestMapping("/page")
    public String login(){
        return "login";
    }


    @Autowired
    private LoginService service;
    @PostMapping("do")
    @ResponseBody
    /**
     * 登录操作
     */
    public ResultDTO loginDo(Empl empl){
        return service.login(empl);
    }
}
