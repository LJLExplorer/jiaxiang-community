package com.jiaxiang.file.config;


import com.jiaxiang.file.service.FileStorageService;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
// 读取配置文件minio开头的
@ConfigurationProperties(prefix = "minio")
//当引入FileStorageService接口时生效
@ConditionalOnClass(FileStorageService.class)
public class MinioConfigProperties implements Serializable {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String endpoint;
    private String readPath;
    // 得到外网ip，docker部署用到
    private String publicEndpoint;
}
