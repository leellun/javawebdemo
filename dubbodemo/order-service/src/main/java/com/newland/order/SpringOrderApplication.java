package com.newland.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring.xml")
public class SpringOrderApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringOrderApplication.class);
    }
}
