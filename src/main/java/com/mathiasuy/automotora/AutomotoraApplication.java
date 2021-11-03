package com.automotora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@ComponentScan({"com.automotora.dataaccess.*"})
public class AutomotoraApplication extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(AutomotoraApplication.class,args);
    }
}
