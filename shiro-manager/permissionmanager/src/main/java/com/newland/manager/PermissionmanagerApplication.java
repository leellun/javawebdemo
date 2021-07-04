package com.newland.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.newland.manager.mapper")
public class PermissionmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionmanagerApplication.class, args);
    }

}
