package com.kiilin.sensitive.demo.controller;

import com.kiilin.sensitive.core.annotation.Sensitive;
import com.kiilin.sensitive.demo.entity.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Sensitive
public class TestController {


    @PostMapping("/test")
    public UserInfo test1(@RequestBody(required = false) UserInfo userInfo) {
        if (userInfo == null) {
            userInfo = new UserInfo();

            userInfo.setName("张三");
            userInfo.setUsername("zhangsan");
            userInfo.setEmail("zhangsan@qq.com");
            userInfo.setIdCard("320321199808081234");
            userInfo.setPhone("13888888888");
        }

        return userInfo;
    }
}
