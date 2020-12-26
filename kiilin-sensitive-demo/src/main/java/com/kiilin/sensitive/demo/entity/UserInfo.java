package com.kiilin.sensitive.demo.entity;


import com.kiilin.sensitive.core.annotation.SensitiveInfo;
import com.kiilin.sensitive.core.enums.SensitiveType;

public class UserInfo {

    @SensitiveInfo(pattern = "(.){2}(.*)", targetChar = "$1**")
    private String username;

    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    private String phone;

    @SensitiveInfo(SensitiveType.ID_CARD)
    private String idCard;

    @SensitiveInfo(SensitiveType.EMAIL)
    private String email;

    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String name;

    public String getUsername() {
        return username;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public UserInfo setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }
}
