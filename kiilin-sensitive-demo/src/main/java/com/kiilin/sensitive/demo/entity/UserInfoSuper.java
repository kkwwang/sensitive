package com.kiilin.sensitive.demo.entity;

import com.kiilin.sensitive.core.annotation.SensitiveInfo;
import com.kiilin.sensitive.core.enums.SensitiveType;

public class UserInfoSuper {


    @SensitiveInfo(SensitiveType.EMAIL)
    private String email;

    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String name;

    public String getEmail() {
        return email;
    }

    public UserInfoSuper setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInfoSuper setName(String name) {
        this.name = name;
        return this;
    }
}
