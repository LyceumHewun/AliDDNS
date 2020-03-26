# AliDDNS
## 简介
AliDDNS是基于阿里云云解析DNS API实现的DDNS本地程序。
## 原理
循环请求[wtfismyip](https://www.wtfismyip.com/json)获取当前网络环境的外网IP，然后调用阿里云云解析DNS API添加/修改DNS解析。
## 使用
1.在程序目录添加配置文件(config.setting)，配置示例
```lombok.config
# 多长时间(毫秒)刷新一次DNS, 该时间建议不要低于TTL
refreshTime = 3600000
# 阿里云用户 AccessKey ID
# 设置地址 https://usercenter.console.aliyun.com/
aliyunAccessKeyId = yourAliyunAccessKeyId
# 阿里云用户 AccessKey Secret
aliyunAccessKeySecret = yourAliyunAccessKeySecret
# 域名
domain = yourDomain
# 域名主机记录
RR = www
# 解析生效时间
TTL = 600000
```
2.直接运行 ```cc.lyceum.AliDDNSLauncher.main``` 类
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
```