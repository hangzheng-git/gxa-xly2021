package com.gxa.gxaxly2021.controller;

import com.gxa.gxaxly2021.dto.LayDTO;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Empl;
import com.gxa.gxaxly2021.param.EmplPageParam;
import com.gxa.gxaxly2021.param.EmplPwdParam;
import com.gxa.gxaxly2021.service.EmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 员工
 * 表(Empl)表控制层
 *
 * @author makejava
 * @since 2021-08-15 21:48:36
 */
@Controller
@RequestMapping("empl")
public class EmplController {
    /**
     * 服务对象
     */
    @Autowired
    private EmplService emplService;
    @Autowired
    private HttpSession httpSession;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Empl selectOne(@RequestParam("emplId")  Integer id) {
        return this.emplService.queryById(id);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param emplId 主键
     * @return 单条数据
     */
    @GetMapping("selectEmplById")
    @ResponseBody
    public ResultDTO SelectEmplById(@RequestParam("emplId") Integer emplId) {
        return emplService.findById(emplId);
    }
    /**
     * 员工的管理页面
     * @return
     */
    @GetMapping("list/page")
    public String emplListPage(){
        return "/empl/empl-list";
    }
    /**
     * 员工的添加页面
     * @return
     */
    @GetMapping("add/page")
    public String emplAddListPage(){
        return "/empl/empl-add";
    }
    @GetMapping("edit/page")
    public String emplEditPage(){
        return "/empl/empl-edit";
    }

    /**
     * 员界面数据的渲染
     * @return
     */
    @PostMapping("list/data")
    @ResponseBody
    public LayDTO empListDate(EmplPageParam param){
        return emplService.listData(param);
    }
    /**
     * 添加的接口
     * @param empl
     * @param roleIds
     * @return
     */
    @PostMapping("add/do")
    @ResponseBody
    public ResultDTO emplAdd(Empl empl,
                             @RequestParam(name = "roleIds[]") String[] roleIds){
        return emplService.insert(empl, roleIds);
    }

    /**
     * 删除数据，逻辑删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public  ResultDTO deleteById(@RequestParam("ids[]") String[] ids){
        return emplService.deleteByIds(ids);
    }
    /**
     * 编辑
     * @param empl
     * @param roleIds
     * @param oldEmplAccount
     * @return
     */
    @PostMapping("edit/do")
    @ResponseBody
    public ResultDTO emplEdit(Empl empl,
                             @RequestParam(name = "roleIds[]") String[] roleIds,
                              @RequestParam(name = "oldEmplAccount") String oldEmplAccount){
        return emplService.edit(empl, roleIds,oldEmplAccount);
    }

    /**
     * 跳转到修改页面
     * @return
     */
    @GetMapping("/changePwd/page")
    public String EmplchangePwdPage(){
        return "/empl/empl-change-pwd";
    }
    /**
     * 完成密码修改并调登录页面
     */
    @PostMapping("/changePwd/do")
    @ResponseBody
    public ResultDTO EmplChangePwd(EmplPwdParam param){

        return emplService.updatePwd(param);
    }

    /**
     * 修改密码
     * @param empl
     * @param httpSession
     * @return
     */
    @PostMapping("/changePwd/status/do")
    @ResponseBody
    public ResultDTO EmplChangeStatus(Empl empl,HttpSession httpSession){
        return emplService.updateStatus(empl,httpSession);
    }

    /**
     * 获取当前用户的
     * @param operatorId
     * @return
     */
    @GetMapping("/getName")
    @ResponseBody
    public String getNameById(Integer operatorId){
        Empl empl = emplService.queryById(operatorId);
        return empl.getEmplName();
    }
    /**
     * 跳转到个人用户的删除
     */
    @GetMapping("personal/info")
    public String emplPersonalInfo(){
        return "person-info";
    }

    /**
     * 通过当前用户的session来获取数据
     * @return
     */
    @PostMapping("personal/info/find")
    @ResponseBody
    public ResultDTO emplFindBySession(){
        return emplService.findBySession();
    }

    /**
     * 用户执行编辑
     * @param empl
     * @return
     */
    @PostMapping("person/edit/do")
    @ResponseBody
    public  ResultDTO personEditDo(Empl empl){
       return emplService.personEdit(empl);
    }
}
