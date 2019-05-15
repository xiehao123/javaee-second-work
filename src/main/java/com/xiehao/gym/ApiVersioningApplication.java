package com.xiehao.gym;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiVersioningApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiVersioningApplication.class, args);
    }
}
