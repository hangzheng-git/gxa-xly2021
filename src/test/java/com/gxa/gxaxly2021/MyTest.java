package com.gxa.gxaxly2021;

import com.gxa.gxaxly2021.dao.DeptDao;
import com.gxa.gxaxly2021.entity.Dept;
import com.gxa.gxaxly2021.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MyTest {

    @Test
    public void contextLoads() {
        System.out.println(MD5Util.MD55("111111"));
    }

}