# 使用 OpenJDK 17 作为基础镜像
FROM eclipse-temurin:17-jdk

# 设置工作目录
WORKDIR /app

ADD target/jiaxiang-community-activity-1.0-SNAPSHOT.jar .

# 暴露容器的端口号
EXPOSE 8101

# 启动命令，指定端口号为 8300
ENTRYPOINT ["java", "-jar", "/app/jiaxiang-community-activity-1.0-SNAPSHOT.jar", "--server.port=8101", "--spring.profiles.active=prod"]