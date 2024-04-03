package com.kiilin.sensitive.demo.entity;

import com.kiilin.sensitive.core.annotation.SensitiveInfo;
import com.kiilin.sensitive.core.enums.SensitiveType;

/**
 * 用户信息类，继承自UserInfoSuper，用于演示敏感信息的处理
 */
public class UserInfo extends UserInfoSuper {

    // 用户名，标注为中文姓名的敏感类型
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String username;

    // 手机号，标注为手机号的敏感类型
    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    private String phone;

    // 身份证号，标注为身份证号的敏感类型
    @SensitiveInfo(SensitiveType.ID_CARD)
    private String idCard;

    /**
     * 获取用户名
     *
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     * @return UserInfo 当前对象实例
     */
    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * 获取手机号
     *
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     * @return UserInfo 当前对象实例
     */
    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * 获取身份证号
     *
     * @return idCard 身份证号
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号
     *
     * @param idCard 身份证号
     * @return UserInfo 当前对象实例
     */
    public UserInfo setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }
}
