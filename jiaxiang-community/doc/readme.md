##  端口号范围规划

| 服务类型                  | 建议端口范围                   | 说明                                        |
| ------------------------- | ------------------------------ | ------------------------------------------- |
| 🌐 网关服务                | `8000 ~ 8099`                  | 统一接入入口，通常一个或多个网关            |
| ⚙️ Sentinel 控制台监听端口 | `8719`（默认）或 `9000 ~ 9099` | Sentinel 的客户端会监听本地端口和控制台通信 |
| 📦 业务微服务              | `8100 ~ 8999`                  | 每个服务一个端口，避免冲突                  |
| 📋 管理/监控类             | `9100 ~ 9199`                  | 比如：Nacos、Prometheus、Admin 服务等       |
| 🧪 测试/预发环境           | `9200 ~ 9299`                  | 与线上区分开，便于本地部署调试              |

9001已默认分配给sentinel dashboard

#### jiahe-community-dev.yaml

```yaml
server:
  port: ${port:9001}
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: test
    url: jdbc:mysql://127.0.0.1:3306/community_springclould?characterEncoding=utf-8&useSSL=false
  jackson:
    serialization:
      WRITE_DATES_AS_TIMESTAMPS: false
      FAIL_ON_EMPTY_BEANS: true
    time-zone: UTC
mybatis:
# 与数据库直接操作的别名
  type-aliases-package: com.jiaxiang.model.community.dos
  mapper-locations: classpath:mapper/*.xml

```
#### jiaxiang-article-dev.yaml

```yaml
server:
  port: ${port:9002}
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: test
    url: jdbc:mysql://127.0.0.1:3306/community_springclould?characterEncoding=utf-8&useSSL=false
  jackson:
    serialization:
      WRITE_DATES_AS_TIMESTAMPS: false
      FAIL_ON_EMPTY_BEANS: true
    time-zone: UTC
mybatis:
# 与数据库直接操作的别名
  type-aliases-package: com.jiaxiang.model.article.dos
  mapper-locations: classpath:mapper/*.xml
```

#### jiahe-gateway-dev.yaml

```
server:
  port: 8000
spring:
  cloud:
    gateway:
      routes:
        # 路由id，可以随意写
        - id: jiahe-community-route
          # 代理的服务地址
          uri:
            lb://jiahe-community
            #http://127.0.0.1:8080
          # 路由断言，可以配置映射路径
          predicates:
            # - Path=/cf/**
            # - Path=/**
            - Path=/api/jiahe/**
          filters:
            # 添加请求路径的前缀
            # - PrefixPath=/cf
            # 表示过滤1个路径，2表示两个路径，以此类推
            - StripPrefix=1
            # 自定义的过滤器如 HAHAGatewayFilterFactory 去掉 GatewayFilterFactory
            # - HAHA=name
            # - HAHA=age
        # 第二个路由规则：访问/cf/**不做任何处理
        - id: article-route
          uri:
            lb://jiaxiang-article
          predicates:
            - Path=/api/article/**
      # globalcors 用于配置全局的 CORS（跨域资源共享）设置。
      globalcors:
        # corsConfigurations: 定义 CORS 配置的路径模式
        cors-configurations:
          # 匹配所有路径。
          '[/**]':
            # 允许的源，* 表示允许所有源。
            allowed-origins: "*"
            allowed-methods:
              # 允许来自上面网址的所有GET方法跨域
              - get
```

