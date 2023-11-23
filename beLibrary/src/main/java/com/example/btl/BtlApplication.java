package com.example.btl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtlApplication.class, args);
    }

}
