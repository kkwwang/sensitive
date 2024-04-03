package com.kiilin.sensitive.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序的入口类。
 *
 * @author kiilin
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * 程序的主入口函数。
     *
     * @param args 命令行传入的参数
     */
    public static void main(String[] args) {
        // 启动Spring Boot应用
        SpringApplication.run(DemoApplication.class, args);
    }

}
