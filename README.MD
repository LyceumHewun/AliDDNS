# AliDDNS

## 简介

AliDDNS是基于阿里云云解析DNS API实现的DDNS本地程序。

## 原理

循环请求[wtfismyip](https://www.wtfismyip.com/json)获取当前网络环境的外网IP，然后调用阿里云云解析DNS API添加/修改DNS解析。

## 使用

### 一、源码编译

1. 使用```mvn package```打包
2. 在jar包里的MANIFEST.MF文件上添加```Main-Class: cc.lyceum.AliDDNSLauncher```

### 二、下载编译好的JAR包

> 运行环境 JDK 1.8.0_211

1. [下载]()
2. 使用```java -jar```命令运行

### 配置

在程序目录添加配置文件(config.setting)，配置模板

```properties
# 多长时间(毫秒)刷新一次DNS, 该时间建议不要低于TTL
refreshTime = 3600000
# 阿里云用户 AccessKey ID
# 设置地址 https://usercenter.console.aliyun.com/
aliyunAccessKeyId = yourAliyunAccessKeyId
# 阿里云用户 AccessKey Secret
aliyunAccessKeySecret = yourAliyunAccessKeySecret
# 域名
domain = yourDomain
# 域名主机记录(小写)
RR = www
# 解析生效时间(秒, 600-86400)
TTL = 600
```

## 依赖

```xml
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>aliyun-java-sdk-alidns</artifactId>
    <version>2.0.10</version>
</dependency>
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>aliyun-java-sdk-core</artifactId>
    <version>4.3.2</version>
</dependency>
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.11.3</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.6</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-setting</artifactId>
    <version>5.2.4</version>
</dependency>
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

## License

```
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <https://unlicense.org>
```
