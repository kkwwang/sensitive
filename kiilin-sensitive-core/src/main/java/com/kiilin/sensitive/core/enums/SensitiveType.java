/**
 * Create time 2019-05-29 9:47
 * Create by wangkai kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.sensitive.core.enums;

/**
 * 脱敏字段类型
 *
 * @author wangkai
 * @version V 1.0
 * @since JDK1.8
 */
public enum SensitiveType {

    /**
     * 中文名 (张三 → 张*)
     */
    CHINESE_NAME("(?<=.{1}).", "*"),

    /**
     * 密码
     */
    PASSWORD(".", ""),

    /**
     * 身份证号
     */
    ID_CARD("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 座机号
     */
    FIXED_PHONE("(?<=\\w{3})\\w(?=\\w{2})", "*"),

    /**
     * 手机号
     */
    MOBILE_PHONE("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 地址
     */
    ADDRESS("(.{5}).+(.{4})", "$1*****$2"),

    /**
     * 电子邮件
     */
    EMAIL("(\\w+)\\w{3}@(\\w+)", "$1***@$2"),

    /**
     * 银行卡
     */
    BANK_CARD("(?<=\\w{4})\\w(?=\\w{4})", "*"),

    /**
     * 公司开户银行联号
     */
    CNAPS_CODE("(?<=\\w{4})\\w(?=\\w{4})", "*"),

    /**
     * 默认值
     */
    DEFAULT_TYPE("", "")

    /**************************************************************************************/
    ;

    /**
     * 输入格式(1,2,2)
     */
    public String pattern;

    public String targetChar;


    SensitiveType(String pattern, String targetChar) {
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    public String getPattern() {
        return pattern;
    }


    public String getTargetChar() {
        return targetChar;
    }

}
