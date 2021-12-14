package com.gxa.gxaxly2021.controller;

import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Role;
import com.gxa.gxaxly2021.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色
 * 表(Role)表控制层
 *
 * @author makejava
 * @since 2021-08-16 15:17:11
 */
@Controller
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Role selectOne(Integer id) {
        return this.roleService.queryById(id);
    }
    /**
     * 查询所有的角色
     * @return
     */
    @PostMapping("all/data")
    @ResponseBody
    public ResultDTO getAllRoles(){
        return roleService.getAllRoles();
    }
    /**
     * 跳转到显示角色页面
     */
    @GetMapping("list/page")
    public String roleListPage(){
        return "/role/role-list";
    }

    /**
     * 显示所有数据
     * @return
     */
    @PostMapping("list/data")
    @ResponseBody
    public LayDTO roleListData(){
        return roleService.listAllRoles();
    }

}
