package com.kiilin.sensitive.demo.entity;

import com.kiilin.sensitive.core.annotation.SensitiveInfo;
import com.kiilin.sensitive.core.enums.SensitiveType;

/**
 * 用户信息实体类，用于演示敏感信息的处理
 * @author kiilin
 */
public class UserInfoSuper {

    // 邮箱信息，使用@SensitiveInfo注解标记为敏感类型EMAIL
    @SensitiveInfo(SensitiveType.EMAIL)
    private String email;

    // 姓名信息，使用@SensitiveInfo注解进行自定义敏感处理
    // pattern定义了匹配规则，targetChar定义了替换规则
    @SensitiveInfo(pattern = "(.{1})(.*)", targetChar = "$1**")
    private String name;

    /**
     * 获取邮箱地址
     * @return 邮箱地址字符串
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     * @param email 邮箱地址字符串
     * @return 返回当前UserInfoSuper实例，用于链式调用
     */
    public UserInfoSuper setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * 获取姓名
     * @return 姓名字符串
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     * @param name 姓名字符串
     * @return 返回当前UserInfoSuper实例，用于链式调用
     */
    public UserInfoSuper setName(String name) {
        this.name = name;
        return this;
    }
}
