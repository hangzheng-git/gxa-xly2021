package com.gxa.gxaxly2021.controller;

import com.alibaba.fastjson.JSONObject;
import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Dept;
import com.gxa.gxaxly2021.param.EmplPageParam;
import com.gxa.gxaxly2021.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织
 * 结构表(部门表)(Dept)表控制层
 *
 * @author makejava
 * @since 2021-08-14 17:01:45
 */
@Controller
@RequestMapping("dept")
public class DeptController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;

    @ResponseBody
    public String test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","张三");
        jsonObject.put("age","18");
        return jsonObject.toJSONString();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Dept selectOne(Integer id) {
        return this.deptService.queryById(id);
    }

    @PostMapping("/")
    public ResultDTO findAll(){
        ResultDTO resultDTO = deptService.getAllDept();
        return resultDTO;
    }
    /**
     * 获取所有的部门数据
     * @return
     */
    @PostMapping("/all/data")
    @ResponseBody
    public ResultDTO getAllDept(){
        return deptService.getAllDept();
    }
    /**
     * 获取所有的部门数据
     * @return
     */
    @PostMapping("/list/data")
    @ResponseBody
    public LayDTO getAllDeptList(){
        return deptService.listDate();
    }

    /**
     * 获取组织结构页面
     * @return
     */
    @GetMapping("/list/page")
    public String deptListPage(){
        return "/dept/dept-list";
    }
    /**
     * 弹出修改页面
     */
    @GetMapping("/edit/page")
    public String deptEditPage(){
        return "/dept/dept-edit";
    }

    /**
     * 修改提交
     * @param dept
     * @return
     */
    @PostMapping("/edit/do")
    @ResponseBody
    public ResultDTO deptEditDo(Dept dept){
        return deptService.update(dept);
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @GetMapping("/add/page")
    public String deptAddPage(){
        return "/dept/dept-add";
    }

    /**
     * 执行添加
     * @param dept
     * @return
     */
    @PostMapping("add/do")
    @ResponseBody
    public ResultDTO deptAddDo(Dept dept){
        System.out.println("执行添加");

        return deptService.insert(dept);
    }

    /**
     * 执行删除操作
     * @param deptId
     * @return
     */
    @PostMapping("delete/do")
    @ResponseBody
    public ResultDTO deptDeleteDo(@RequestParam("deptId") Integer deptId){
        return deptService.deleteById(deptId);
    }
}
