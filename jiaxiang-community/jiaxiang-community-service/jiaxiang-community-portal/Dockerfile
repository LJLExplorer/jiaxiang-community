# 使用 OpenJDK 17 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

ADD target/jiaxiang-community-portal-1.0-SNAPSHOT.jar .

# 暴露容器的端口号
EXPOSE 8100

# 启动命令，指定端口号为 8300
ENTRYPOINT ["java", "-jar", "/app/jiaxiang-community-portal-1.0-SNAPSHOT.jar", "--server.port=8100", "--spring.profiles.active=prod"]