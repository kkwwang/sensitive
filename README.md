# sensitive

#### 介绍
接口隐私数据脱敏工具包
### 脱敏序列化设计及使用方法
#### 使用方法
- 定义`SensitiveType`枚举项
    >其中`pattern`为`replaceAll`方法的正则表达式  
    >`targetChar`为替换字符  
- 再需要脱敏的实体的字段上添加`@SensitiveInfo(SensitiveType.ID_CARD)`
    >其中`SensitiveType`为上一步定义的枚举项
- 在需要脱敏的`Controller`接口上添加`@Sensitive`注解
    >上一步字段注解添加后并不会生效，需要接口上标记方可
    
#### 设计
- 编写json序列化过滤器
- 实现ResponseBodyAdvice的beforeBodyWrite方法，使用过滤器序列化对象后再反序列化