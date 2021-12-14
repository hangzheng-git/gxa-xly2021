package com.gxa.gxaxly2021;

import com.gxa.gxaxly2021.dao.DeptDao;
import com.gxa.gxaxly2021.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GxaXly2021ApplicationTests {
    @Autowired
    private DeptDao deptDao;
    @Test
    void contextLoads() {
        List<Dept> deptList = deptDao.findAll();
        for (Dept dept:deptList){
            System.out.println(dept);
        }
    }

}
