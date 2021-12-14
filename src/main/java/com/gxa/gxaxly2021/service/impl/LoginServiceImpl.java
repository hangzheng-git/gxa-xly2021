package com.gxa.gxaxly2021.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxa.gxaxly2021.dao.EmplDao;
import com.gxa.gxaxly2021.dao.PermissionDao;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.*;
import com.gxa.gxaxly2021.service.LoginService;
import com.gxa.gxaxly2021.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Service

public class LoginServiceImpl implements LoginService {
    @Autowired
    private EmplDao dao;
    @Autowired
    private PermissionDao permissionDao;
    //num用来记录密码可用次数
    @Autowired
    private HttpSession session;
    @Override
    public ResultDTO login(Empl empl) {
        Empl dbEmpl = dao.findByAccount(empl.getEmplAccount());
        if (dbEmpl == null) {
            return new ResultDTO(1001, "该用户不存在！");
        }
        Integer num = dbEmpl.getCanErrorNum();
    // 判断密码
        if (!dbEmpl.getEmplPwd().equals(MD5Util.MD55(empl.getEmplPwd())) && num >=0) {
            //每错一次num减一，减到零时锁定账户，将emplStatus设为0

            if (num == 0){
                dbEmpl.setEmplStatus(0);
                dbEmpl.setCanErrorNum(num);
                dao.update(dbEmpl);
                return new ResultDTO(1002, "错误次数过多,请联系管理员！");
            }
            num = num -1;
            dao.update(new Empl(dbEmpl.getEmplId(),num));
            //通过拼接字符串来显示剩余密码次数
            return new ResultDTO(1002, "密码不正确！,还剩"+num+"次机会");
        }
    // 判断状态
        if (dbEmpl.getEmplStatus().equals(0)) {
            return new ResultDTO(1003, "你已经被禁用了！请联系超级管理员");
        }
        //判断是否为初始密码
        if(dbEmpl.getEmplPwd().equals(MD5Util.MD55("111111"))){
            return new ResultDTO(1004,"请勿使用初始密码，请先修改密码再登录,即将跳转到修改页面",dbEmpl.getEmplId());
        }

        dao.update(new Empl(dbEmpl.getEmplId(),new Timestamp(System.currentTimeMillis())));
        //记录用户登录名
        session.setAttribute("emplName",dbEmpl.getEmplName());
        session.setAttribute("deptName",dbEmpl.getDept().getDeptFullName());
        session.setAttribute("roleName",this.getRolesStr(dbEmpl));
        //
        session.setAttribute("permissionName",getPermissionsStr(dbEmpl));
        //保存是否是超级管理员的状态
        session.setAttribute("isSuper",isSuper(dbEmpl));
        // 将登录用户的菜单数据存放到session中
        session.setAttribute("menus", getMenus(dbEmpl));
        //将当前用户的id传入
        session.setAttribute("emplId",dbEmpl.getEmplId());
        //密码正确后重置数据
        num = 5;
        dao.update(new Empl(dbEmpl.getEmplId(),num));
        return new ResultDTO(200, "登录成功了！");
    }
    private String getRolesStr(Empl empl){
        List<Role> roleList = empl.getRoles();
        if(roleList.size() == 0){
            return "未设置角色";
        }
        String str = "";
        for (Role role : roleList) {
            str += role.getRoleName()+" , ";
        }
        return str.substring(0,str.length()-3);
    }


    /**
     * 获取菜单的方法
     * @param empl
     * @return
     */
    public List<Menu> getMenus(Empl empl){
        List<Permission> permissions = new ArrayList<>();
        // 先判断是否是超级管理员
        if (isSuper(empl)) {
            // 权限数据从数据库中来
            permissions = permissionDao.findAll();
        }else{
            // 从员工对象中来
            for (Role role : empl.getRoles()) {
                if (role.getPermissions() == null) {
                    continue;
                }
                permissions.addAll(role.getPermissions());
            }
        }

        return  transMenu(permissions);

    }
    /**
     * 转换菜单的过程
     * @param permissions
     * @return
     */
    public List<Menu> transMenu(List<Permission> permissions){
        // 装菜单的容器
        List<Menu> menus = new ArrayList<>();
        Set<Menu> menuSet = new TreeSet<>();
        // 先处理一级菜单
        for (Permission permission : permissions) {
            // 只处理一级菜单
            if (permission.getParentId().equals(0) && permission.getPermissionLevel().equals(1)) {
                menuSet.add(new Menu(
                        permission.getPermissionId(),
                        permission.getPermissionName(),
                        permission.getPermissionUrl(),
                        permission.getPermissionLevel(),
                        permission.getOrderId()
                        ));
            }
        }
        // 将set转换成List
        menus.addAll(menuSet);

        // 处理二级菜单
        for (Menu menu : menus) {
            // 子菜单的集合
            Set<Menu> subMenus = new TreeSet<>();
            for (Permission permission : permissions) {
                // 判断权限是否是菜单子菜单
                if (permission.getPermissionLevel().equals(2) && permission.getParentId() != 0) {
                    // 是二级菜单
                    if (menu.getMenuId().equals(permission.getParentId())) {
                        // 说明当前的permission是menu的子菜单
                        subMenus.add(new Menu(
                                permission.getPermissionId(),
                                permission.getPermissionName(),
                                permission.getPermissionUrl(),
                                permission.getPermissionLevel(),
                                permission.getOrderId()
                        ));
                    }
                }
            }

            // 将产生的子菜单添加到父级菜单中
            menu.getSubMenus().addAll(subMenus);
        }
        return menus;//调用排序方法，将菜单按照需要的顺序排序
    }

    /**
     * ！！！！该方法已经弃用，本人留作纪念而已！！！！
     * 给list重新排序
     * @param menus
     * @return
     */
    public List<Menu> sort(List<Menu> menus){
        List<Menu> result = new ArrayList<>();
        //指定菜单顺序放入一个int数组，后期可以改为传入int数组参数来指定顺序
        int[] ints = {1,2,3,4,5,6,7};
        for (int i: ints){
            for (Menu menu : menus) {
                //当菜单id也就是permissionId与遍历的i相同时就将这条数据插入新的集合中
                if (menu.getMenuId() == i ){
                    result.add(menu);
                }
            }
        }
        //返回排序后的list
        return result;
    }
    /**
     * 判断是否是超级管理员
     * @param empl
     * @return
     */
    private Boolean isSuper(Empl empl){
        List<Role> roles = empl.getRoles();
        for (Role role : roles) {
            if (role.getIsSuper().equals(1)){
                return true;
            }
        }
        return false;
    }

    private String getPermissionsStr(Empl empl)  {
        String str = "";

        Map<String,Integer> map = new HashMap<>();
        for (Role role : empl.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                String s = permission.getPermissionName();
                Integer i = permission.getParentId();
                map.put(s,i);
            }

        }
        ObjectMapper om = new ObjectMapper();
        String json = null;
        try {
            json = om.writeValueAsString(map);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
