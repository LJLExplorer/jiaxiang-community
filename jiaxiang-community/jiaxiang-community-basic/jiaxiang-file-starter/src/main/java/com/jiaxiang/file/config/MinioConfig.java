package com.jiaxiang.file.config;

import com.jiaxiang.file.service.FileStorageService;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
//@Configuration
@AutoConfiguration
// 使用MinioConfigProperties类的内容
@EnableConfigurationProperties({MinioConfigProperties.class})
//当引入FileStorageService接口时加载
@ConditionalOnClass(FileStorageService.class)
public class MinioConfig {

    @Autowired
    private MinioConfigProperties minioConfigProperties;

    @Bean
    public MinioClient buildMinioClient() {
        return MinioClient
                .builder()
                .credentials(minioConfigProperties.getAccessKey(), minioConfigProperties.getSecretKey())
                .endpoint(minioConfigProperties.getEndpoint())
                .build();
    }
}