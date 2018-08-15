package com.chenjx.arms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chenjx")
public class ArmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmsApplication.class, args);
    }
}
