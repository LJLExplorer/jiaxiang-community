# 使用 OpenJDK 17 作为基础镜像
FROM eclipse-temurin:17-jdk

# 设置工作目录
WORKDIR /app

# 将构建好的 Spring Boot 应用的 JAR 文件复制到容器内
ADD target/community-gateway-1.0-SNAPSHOT.jar .

# 暴露容器的端口号
EXPOSE 8001

# 启动命令，指定端口号为 8300
ENTRYPOINT ["java", "-jar", "/app/community-gateway-1.0-SNAPSHOT.jar", "--server.port=8001", "--spring.profiles.active=prod"]