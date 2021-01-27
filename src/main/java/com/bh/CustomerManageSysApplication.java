package com.bh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ServletComponentScan    //启动器启动时，扫描本目录以及子目录带有的@WebServlet注解的
public class CustomerManageSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManageSysApplication.class, args);
    }

}
