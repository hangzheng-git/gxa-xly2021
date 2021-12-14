package com.gxa.gxaxly2021;

import com.gxa.gxaxly2021.dao.DeptDao;
import com.gxa.gxaxly2021.dao.EmplDao;
import com.gxa.gxaxly2021.dao.PermissionDao;
import com.gxa.gxaxly2021.dao.RoleDao;
import com.gxa.gxaxly2021.dto.ResultDTO;
import com.gxa.gxaxly2021.entity.Dept;
import com.gxa.gxaxly2021.entity.Empl;
import com.gxa.gxaxly2021.entity.Permission;
import com.gxa.gxaxly2021.entity.Role;
import com.gxa.gxaxly2021.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class TestDao {
    @Autowired
    private LoginService loginService;
    @Autowired
    private EmplDao emplDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private DeptDao deptDao;
    @Test
    public void TestFindAll(){

    }
}
