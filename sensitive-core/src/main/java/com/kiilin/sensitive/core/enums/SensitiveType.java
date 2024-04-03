/**
 * Create time 2019-05-29 9:47
 * Create by kiilin kiilin@kiilin.com
 * Copyright 2019 kiilin http://www.kiilin.com
 */

package com.kiilin.sensitive.core.enums;

/**
 * 脱敏字段类型枚举，定义了不同数据类型的脱敏规则。
 *
 * @author kiilin
 * @version V 1.0
 * @since JDK1.8
 */
public enum SensitiveType {

    /**
     * 中文名脱敏规则，保留第一个字符，其余用"*"代替。
     */
    CHINESE_NAME("(?<=.{1}).", "*"),

    /**
     * 密码脱敏规则，全部用"*"代替。
     */
    PASSWORD(".", ""),

    /**
     * 身份证号脱敏规则，保留前三位和后四位，中间用"*"代替。
     */
    ID_CARD("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 座机号脱敏规则，保留前三位和后两位，中间用"*"代替。
     */
    FIXED_PHONE("(?<=\\w{3})\\w(?=\\w{2})", "*"),

    /**
     * 手机号脱敏规则，保留前三位和后四位，中间用"*"代替。
     */
    MOBILE_PHONE("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 地址脱敏规则，保留前五个字符和后四个字符，中间用"*****"代替。
     */
    ADDRESS("(.{5}).+(.{4})", "$1*****$2"),

    /**
     * 电子邮件脱敏规则，保留邮箱前缀的三个字符，@符号及其后的内容不变。
     */
    EMAIL("(\\w+)\\w{3}@(\\w+)", "$1***@$2"),

    /**
     * 银行卡号脱敏规则，保留前四位和后四位，中间用"*"代替。
     */
    BANK_CARD("(?<=\\w{4})\\w(?=\\w{4})", "*"),

    /**
     * 公司开户银行联号脱敏规则，保留前四位和后四位，中间用"*"代替。
     */
    CNAPS_CODE("(?<=\\w{4})\\w(?=\\w{4})", "*"),

    /**
     * 默认脱敏规则，不做任何处理。
     */
    DEFAULT_TYPE("", "")

    ;

    /**
     * 正则表达式模式，用于匹配需要脱敏的字符串部分。
     */
    private final String pattern;

    /**
     * 用于替换匹配到的部分的字符。
     */
    private final String targetChar;


    /**
     * 构造函数，设置脱敏规则的正则表达式模式和替换字符。
     *
     * @param pattern    正则表达式模式字符串
     * @param targetChar 替换目标字符字符串
     */
    SensitiveType(String pattern, String targetChar) {
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    /**
     * 获取正则表达式模式。
     *
     * @return 返回脱敏规则的正则表达式模式字符串。
     */
    public String getPattern() {
        return pattern;
    }


    /**
     * 获取替换字符。
     *
     * @return 返回用于替换匹配部分的字符字符串。
     */
    public String getTargetChar() {
        return targetChar;
    }

}
