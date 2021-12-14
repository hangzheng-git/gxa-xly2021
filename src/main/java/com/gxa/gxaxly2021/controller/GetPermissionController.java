package com.gxa.gxaxly2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class GetPermissionController {
    @Autowired
    private HttpSession session;
    @RequestMapping("/getSession")
    @ResponseBody
    public String getPermission(){
        return (String) session.getAttribute("permissionName");
    }
}
