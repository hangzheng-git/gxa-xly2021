package com.gxa.gxaxly2021.controller;


import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Menu;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Menu;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 获取菜单的控制器
 * @author 一路向北
 */
@RestController
public class MenuController {


    /**
     * 获取菜单列表
     * @param session
     * @return
     */
    @PostMapping("/menu/list")
    public ResultDTO getMenu(HttpSession session){
        // 获取菜单数据
        List<Menu> menus = (List<Menu>) session.getAttribute("menus");
        return new ResultDTO(200, "success", menus);
    }

}
