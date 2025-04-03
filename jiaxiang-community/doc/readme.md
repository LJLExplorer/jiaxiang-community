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

