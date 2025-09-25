# 使用 OpenJDK 17 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将构建好的 Spring Boot 应用的 JAR 文件复制到容器内
# 请确保构建后的 JAR 文件路径正确，例如 target/jiaxiang-auth.jar
COPY target/jiaxiang-community-auth-1.0-SNAPSHOT.jar app.jar

# 将 bootstrap.yml 配置文件复制到容器内
COPY src/main/resources/bootstrap.yml /app/bootstrap.yml

# 设置环境变量以确保 Spring Boot 应用正确读取配置
ENV SPRING_CONFIG_LOCATION=/app/bootstrap.yml

# 暴露容器的端口号
EXPOSE 8300

# 启动命令，指定端口号为 8300
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8300"]