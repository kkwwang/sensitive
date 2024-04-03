package com.kiilin.sensitive.demo.controller;

import com.kiilin.sensitive.core.annotation.Sensitive;
import com.kiilin.sensitive.demo.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试敏感信息处理的控制器
 * @author kiilin
 */
@RestController
@Sensitive
public class TestController {

    /**
     * 通过用户传入的信息，返回一个用户信息对象。如果用户没有传入信息，则返回一个预设的用户信息。
     *
     * @param userInfo 用户信息对象，可选参数
     * @return 返回填充好的用户信息对象
     */
    @RequestMapping("/test")
    public UserInfo test1(@RequestBody(required = false) UserInfo userInfo) {
        // 如果userInfo为空，创建一个新的用户信息实例并填充默认值
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

    /**
     * 返回一个预设的用户信息对象。
     *
     * @return 返回填充好的用户信息对象
     */
    @RequestMapping("/test1")
    public UserInfo test1() {
        // 创建并填充用户信息实例
        UserInfo userInfo = new UserInfo();
        userInfo.setName("张三");
        userInfo.setUsername("zhangsan");
        userInfo.setEmail("zhangsan@qq.com");
        userInfo.setIdCard("320321199808081234");
        userInfo.setPhone("13888888888");
        return userInfo;
    }
}
