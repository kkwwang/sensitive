/**
 * (#)com.kiilin.core.sensitive.enums.SensitiveType.java
 * Create time 2019-03-29 9:47
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.core.sensitive.enums;

/**
 * @author kiilin
 * @version V 1.0
 * @description 脱敏字段类型
 * @see {@link SensitiveType}
 * @since JDK1.8
 */

public enum SensitiveType {
    /**
     * 中文名(张三 -> 张*)
     */
    CHINESE_NAME("(?<=.{1}).","*"),

    /**
     * 身份证号
     */
    ID_CARD("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 座机号
     */
    FIXED_PHONE("(?<=\\w{3})\\w(?=\\w{2})","*"),

    /**
     * 手机号
     */
    MOBILE_PHONE("(?<=\\w{3})\\w(?=\\w{4})","*"),

    /**
     * 地址
     */
    ADDRESS("(.{5}).+(.{4})","$1*****$2"),

    /**
     * 电子邮件
     */
    EMAIL("(\\w+)\\w{3}@(\\w+)","$1***@$2"),

    /**
     * 银行卡
     */
    BANK_CARD("(?<=\\w{4})\\w(?=\\w{4})","*"),

    /**
     * 公司开户银行联号
     */
    CNAPS_CODE("(?<=\\w{4})\\w(?=\\w{4})","*"),

    /**************************************************************************************/
    ;

    /**
     * 输入格式(1,2,2)
     */
    private String pattern;

    private String targetChar = "*";


    SensitiveType() {
    }


    SensitiveType(String pattern, String targetChar) {
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    public String getPattern() {
        return pattern;
    }

    public SensitiveType setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public String getTargetChar() {
        return targetChar;
    }

    public SensitiveType setTargetChar(String targetChar) {
        this.targetChar = targetChar;
        return this;
    }
}
