package com.gxa.gxaxly2021;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gxa.gxaxly2021.dao")//开启扫描mapper接口的包
public class GxaXly2021Application {

    public static void main(String[] args) {
        SpringApplication.run(GxaXly2021Application.class, args);
    }

}
