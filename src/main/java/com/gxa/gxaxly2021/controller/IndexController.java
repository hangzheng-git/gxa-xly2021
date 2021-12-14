package com.gxa.gxaxly2021.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页控制器
 */
@Controller
public class IndexController {
    /**
     * 主页
     * @return
     */
    @GetMapping({"/index","/"})
    public String index(){

        return "index";
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
