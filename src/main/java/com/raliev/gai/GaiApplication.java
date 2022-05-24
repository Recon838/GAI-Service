package com.raliev.gai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class GaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaiApplication.class, args);
    }
}
