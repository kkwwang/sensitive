package com.kiilin.sensitive.demo.controller;

import com.kiilin.sensitive.core.annotation.Sensitive;
import com.kiilin.sensitive.demo.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Sensitive
public class TestController {


    @GetMapping("/test")
    public UserInfo test1() {
        UserInfo userInfo = new UserInfo();

        userInfo.setName("张三");
        userInfo.setUsername("zhangsan");
        userInfo.setEmail("zhangsan@qq.com");
        userInfo.setIdCard("320321199808081234");
        userInfo.setPhone("13888888888");
        return userInfo;
    }
}
