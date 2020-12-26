package com.kiilin.sensitive.demo.controller;

import com.kiilin.sensitive.core.annotation.Sensitive;
import com.kiilin.sensitive.demo.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Sensitive
@RestController
public class TestController {


    @GetMapping("/test")
    @Sensitive
    public UserInfo test1() {

        return new UserInfo()
                .setName("张三")
                .setUsername("zhangsan")
                .setEmail("zhangsan@qq.com")
                .setIdCard("320321199808081234")
                .setPhone("13888888888")
                ;
    }
}
