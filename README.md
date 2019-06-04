#### 使用方法
- 定义`SensitiveType`枚举项
    >其中`pattern`为`replaceAll`方法的正则表达式；`targetChar`为替换字符  
- 在需要脱敏的实体的字段上添加`@SensitiveInfo(SensitiveType.XXX)`
    >其中`SensitiveType`为上一步定义的枚举项
- 在需要脱敏的`Controller`方法或类上添加`@Sensitive`注解，方法上表示该接口需要脱敏，类表示该类所有方法均需脱敏
    >上一步字段注解添加后并不会生效，需要接口上标记方可