#### 说明
> 项目依赖springMVC仅对`RestController`接口有效，即返回`JSON`格式的接口   
> 使用说明及demo仅编写`springboot`，使用`springmvc`框架请参考配置   

插件已推送至`maven`中央库
 ```java
 <dependency>
   <groupId>com.kiilin</groupId>
   <artifactId>kiilin-sensitive-core</artifactId>
   <version>latest_version</version>
 </dependency>
 ```

#### 使用方法
- 添加AOP依赖
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-aop</artifactId>
  </dependency>
  ```
  
- 启动类添加扫包注解
  ```java
  @ComponentScans({
    @ComponentScan("com.kiilin.sensitive")
  })
  ```

- 在需要脱敏的实体的字段上添加注解
  ```java
    // 使用自定义规则
    @SensitiveInfo(pattern = "(.{2})(.*)", targetChar = "$1**")
    // 使用预定义规则
    @SensitiveInfo(SensitiveType.EMAIL)
    // 若两种规则混用，自定义规则优先级更高
  ```


- 在需要脱敏的`Controller`方法或类上添加`@Sensitive`注解，方法上表示该接口需要脱敏，类表示该类所有方法均需脱敏


```java
// demo执行效果
GET http://localhost:8080/test

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 26 Dec 2020 06:23:01 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
"email": "zhang***@qq.com",
"name": "张*",
"username": "zh**",
"phone": "138****8888",
"idCard": "320***********1234"
}

Response code: 200; Time: 131ms; Content length: 108 bytes

```


### 欢迎提交新预定义规则，或对项目完善
