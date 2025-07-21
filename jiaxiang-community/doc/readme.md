##  端口号范围规划

| 服务类型                  | 建议端口范围                   | 说明                                        |
| ------------------------- | ------------------------------ | ------------------------------------------- |
| 🌐 网关服务                | `8000 ~ 8099`                  | 统一接入入口，通常一个或多个网关            |
| ⚙️ Sentinel 控制台监听端口 | `8719`（默认）或 `9000 ~ 9099` | Sentinel 的客户端会监听本地端口和控制台通信 |
| 📦 业务微服务              | `8100 ~ 8999`                  | 每个服务一个端口，避免冲突                  |
| 📋 管理/监控类             | `9100 ~ 9199`                  | 比如：Nacos、Prometheus、Admin 服务等       |
| 🧪 测试/预发环境           | `9200 ~ 9299`                  | 与线上区分开，便于本地部署调试              |

9001已默认分配给sentinel dashboard

auth认证端口是 8300

#### community-gateway-dev.yaml

```
server:
  port: 8001
spring:
  data:
    redis: 
      host: localhost
      port: 6379
      password: test
      database: 0
  cloud:
    gateway:
      routes:
        - id: auth-route
          uri: lb://jiaxiang-auth
          predicates:
          - Path=/api/auth/**
          filters:
            - StripPrefix=1
        # 路由id，可以随意写
        - id: jiahe-community-route
          # 代理的服务地址
          uri:
            lb://jiaxiang-portal
          # 路由断言，可以配置映射路径
          predicates:
            - Path=/api/jiahe/**
          filters:
            # 添加请求路径的前缀
            # 表示过滤1个路径，2表示两个路径，以此类推
            - StripPrefix=1
            - AddRequestParameter=communityId,1
        # 第二个路由规则：访问/cf/**不做任何处理
        - id: article-route
          uri:
            lb://jiaxiang-article
          predicates:
            - Path=/api/article/**
          filters:
            - StripPrefix=1
           
      # globalcors 用于配置全局的 CORS（跨域资源共享）设置。
      globalcors:
        # corsConfigurations: 定义 CORS 配置的路径模式
        corsConfigurations:
          # 匹配所有路径。
          '[/**]':
            # 允许的源，* 表示允许所有源。
            allowed-origins: "*"
            allowed-methods:
              # 允许来自上面网址的所有GET方法跨域
              - GET
              - POST
              - PUT
              - DELETE
            allowedHeaders: "*"
common:
  security:
    jwt-filter-enabled: false
    security-filter-chain-enabled: false
gateway:
  whitelist: 
    - /api/auth/login
    - /api/auth/register
    - /api/jiahe/list_community_activities
    - /api/jiahe/community_activity_detail
    - /api/jiahe/community_profile
    - /api/jiahe/grid_management
    - /api/jiahe/list_committees_members
    - /api/jiahe/personal_info
    - /api/jiahe/list_serve_people
    - /api/jiahe/serve_people_info
    - /api/jiahe/list_matters
    - /api/jiahe/community_honor
    - /api/jiahe/proof_documents
    - /api/jiahe/proof_info
```

#### jiaxiang-portal-dev.yaml

```
server:
  port: 8100
spring:
  servlet:
    multipart:
      # 单个文件最大允许大小
      max-file-size: 100MB
      # 整个文件最大允许大小
      max-request-size: 1024MB
  data:
    redis: 
      host: localhost
      port: 6379
      password: test
      database: 0
    mongodb: 
      uri: mongodb://localhost:27017/community_springcloud
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: test
    url: jdbc:mysql://127.0.0.1:3306/community_springcloud?characterEncoding=utf-8&useSSL=false
  jackson:
    # date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    # serialization:
      # write-dates-as-timestamps: false
  rabbitmq:
    enabled: true
    host: 127.0.0.1
    port: 5672
    username: root
    password: test
mybatis:
# 与数据库直接操作的别名
  type-aliases-package: com.jiaxiang.model.activity.dos
  mapper-locations: classpath:mapper/*.xml
# 下划线回应驼峰命名
  configuration:
    map-underscore-to-camel-case: true
common:
  security:
    jwt-filter-enabled: true
    security-filter-chain-enabled: true
    whitelist:
      - /jiahe/list_community_activities
      - /jiahe/community_activity_detail
      - /jiahe/community_profile
      - /jiahe/grid_management
      - /jiahe/list_committees_members
      - /jiahe/personal_info
      - /jiahe/list_serve_people
      - /jiahe/serve_people_info
      - /jiahe/list_matters
      - /jiahe/community_honor
      - /jiahe/proof_documents
      - /jiahe/proof_info
minio:
  accessKey: minio
  secretKey: minio123
  bucket: jiaxiang
  endpoint: http://127.0.0.1:9000
  readPath: http://127.0.0.1:9000
```

#### jiaxiang-activity-dev.yaml

```
server:
  port: 8101
spring:
  data:
    redis: 
      host: localhost
      port: 6379
      password: test
      database: 0
  jackson:
    # date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    # serialization:
      # write-dates-as-timestamps: false
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: test
    url: jdbc:mysql://127.0.0.1:3306/community_springcloud?characterEncoding=utf-8&useSSL=false
mybatis:
# 与数据库直接操作的别名
  type-aliases-package: com.jiaxiang.model.activity.dos
  mapper-locations: classpath:mapper/*.xml
# 下划线回应驼峰命名
  configuration:
    map-underscore-to-camel-case: true
common:
  security:
    jwt-filter-enabled: true
    security-filter-chain-enabled: true
    whitelist:
      - /activity/list_community_activities
      - /activity/list_community_activity_detail
minio:
  accessKey: minio
  secretKey: minio123
  bucket: jiaxiang
  endpoint: http://127.0.0.1:9000
  readPath: http://127.0.0.1:9000
```

#### jiaxiang-content-dev.yaml

```
server:
  port: 8102
spring:
  data:
    redis: 
      host: localhost
      port: 6379
      password: test
      database: 0
    mongodb:
      uri: mongodb://localhost:27017/community_springcloud
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: test
    url: jdbc:mysql://127.0.0.1:3306/community_springcloud?characterEncoding=utf-8&useSSL=false
  jackson:
    # date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    # serialization:
      # write-dates-as-timestamps: false
  rabbitmq:
    enabled: true
    host: 127.0.0.1
    port: 5672
    username: root
    password: test
mybatis:
# 与数据库直接操作的别名
  type-aliases-package: com.jiaxiang.model.content.dos
  mapper-locations: classpath:mapper/*.xml
# 下划线回应驼峰命名
  configuration:
    map-underscore-to-camel-case: true
common:
  security:
    jwt-filter-enabled: true
    security-filter-chain-enabled: true
    whitelist:
      - /community/grid_management
      - /community/list_committees_members
      - /community/personal_info
      - /community/list_serve_people
      - /community/serve_people_info
      - /community/list_matters
      - /community/community_honor
      - /community/proof_documents
      - /community/proof_info
      - /content/community_profile
      - /content/list_content_by_type
minio:
  accessKey: minio
  secretKey: minio123
  bucket: jiaxiang
  endpoint: http://127.0.0.1:9000
  readPath: http://127.0.0.1:9000
```

#### jiaxiang-auth-dev.yaml

```
server:
  port:  8300
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: test
    url: jdbc:mysql://127.0.0.1:3306/community_springcloud?characterEncoding=utf-8&useSSL=false
  data:
    redis: 
      host: localhost
      port: 6379
      password: test
      database: 0
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    serialization:
      write-dates-as-timestamps: false
mybatis:
# 与数据库直接操作的别名
  type-aliases-package: com.jiaxiang.model.auth.dos
  mapper-locations: classpath:mapper/*.xml
# 下划线回应驼峰命名
  configuration:
    map-underscore-to-camel-case: true
common:
  security:
    jwt-filter-enabled: false
    security-filter-chain-enabled: false
```

