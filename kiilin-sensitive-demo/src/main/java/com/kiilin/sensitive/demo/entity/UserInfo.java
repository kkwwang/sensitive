package com.kiilin.sensitive.demo.entity;


import com.kiilin.sensitive.core.annotation.SensitiveInfo;
import com.kiilin.sensitive.core.enums.SensitiveType;

public class UserInfo extends UserInfoSuper {

    @SensitiveInfo(pattern = "(.){2}(.*)", targetChar = "$1**")
    private String username;

    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    private String phone;

    @SensitiveInfo(SensitiveType.ID_CARD)
    private String idCard;


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
}
