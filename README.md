[![OSCS Status](https://www.oscs1024.com/platform/badge/kkwwang/sensitive.svg?size=large)](https://www.oscs1024.com/project/kkwwang/sensitive?ref=badge_large)

#### 说明

> 项目依赖springMVC仅对`RestController`接口有效，即返回`JSON`格式的接口   
> 使用说明及demo仅编写`springboot`，使用`springmvc`框架请参考配置   
> 支持`springboot`版本`1.4.0.RELEASE-2.7.0`   
> 使用`springmvc`需自行注册bean，版本支持请自行验证   

插件已推送至`maven`[中央库](https://central.sonatype.com/namespace/com.kiilin)   
 ```xml
<dependency>
    <groupId>com.kiilin</groupId>
    <artifactId>sensitive-spring-boot-starter</artifactId>
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

~~- 启动类添加启用注解~~
  ```java
  package com.kiilin.sensitive.demo;
  
  import com.kiilin.sensitive.core.annotation.EnableSensitive;
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  
  @SpringBootApplication
  @EnableSensitive
  public class DemoApplication {
        public static void main(String[] args) {
          SpringApplication.run(DemoApplication.class, args);
      }
  }
  ```
- 增加自动装配，默认启用脱敏，增加系统环境变量`SENSITIVE_ENABLE=false`或springboot配置`sensitive.enable=false`关闭

- 在需要脱敏的实体的字段上添加注解

```java
// 使用自定义规则
@SensitiveInfo(pattern = "(.{2})(.*)", targetChar = "$1**")
// 使用预定义规则
@SensitiveInfo(SensitiveType.EMAIL)
// 若两种规则混用，自定义规则优先级更高
  ```

- 在需要脱敏的`Controller`方法或类上添加`@Sensitive`注解，方法上表示该接口需要脱敏，类表示该类所有方法均需脱敏

```http request
POST http://localhost:8080/test
Content-Type: application/json
sensitive-placeholder: (^_^) // 增加隐藏小彩蛋；请求头中携带sensitive-placeholder参数可以替换默认的*占位符

{
  "email": "kiilin@kiilin.com",
  "idCard": "6222222222222222",
  "name": "云声",
  "phone": "18888888888",
  "username": "kiilin"
}

### 
http://localhost:8080/test

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 02 Nov 2022 03:31:36 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "email": "kii(^_^)(^_^)(^_^)@kiilin.com",
  "name": "云(^_^)",
  "username": "ki(^_^)(^_^)",
  "phone": "188(^_^)(^_^)(^_^)(^_^)8888",
  "idCard": "622(^_^)(^_^)(^_^)(^_^)(^_^)(^_^)(^_^)(^_^)(^_^)2222"
}

Response code: 200; Time: 3ms (3 ms); Content length: 185 bytes (185 B)
```
> [测试地址](http://localhost:8080/doc.html#/default/test-controller/test1UsingPOST)   
> [在线测试地址](http://101.43.64.92:8080/doc.html#/default/test-controller/test1UsingPOST)   

