package com.jiaxiang.file.service.impl;

import com.jiaxiang.file.config.MinioConfig;
import com.jiaxiang.file.config.MinioConfigProperties;
import com.jiaxiang.file.service.FileStorageService;
import io.minio.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.jiaxiang.model.common.enums.ViewContentTypeEnum.getContentType;

@Slf4j
@Import(MinioConfig.class)
@EnableConfigurationProperties(MinioConfigProperties.class)
public class MinioFileStorageService implements FileStorageService {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfigProperties minioConfigProperties;

    private final static String SEPARATOR = "/";

    /**
     * 构建存在bucket下路径
     *
     * @param dirPath  前缀目录
     * @param filename yyyy/mm/dd/file.jpg
     * @return 创建bucket下的目录
     */
    public String builderFilePath(String dirPath, String filename) {
        StringBuilder stringBuilder = new StringBuilder(50);
        if (!StringUtils.isEmpty(dirPath)) {
            stringBuilder.append(dirPath).append(SEPARATOR);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayStr = sdf.format(new Date());
        stringBuilder.append(todayStr).append(SEPARATOR);
        stringBuilder.append(filename);
        return stringBuilder.toString();
    }

    /**
     * 得到bucket下文件路径
     *
     * @param pathUrl 文件地址
     * @return 文件路径
     */
    public String getFileKeyFromUrl(String pathUrl) {
        // 去掉 MinIO 服务地址部分，提取出相对路径
        String baseUrl = minioConfigProperties.getEndpoint() + "/" + minioConfigProperties.getBucket() + "/";

        if (pathUrl.startsWith(baseUrl)) {
            // 截去 MinIO 服务地址和 bucket 名称部分，返回剩下的路径作为对象 key
            return pathUrl.substring(baseUrl.length());
        } else {
            throw new IllegalArgumentException("The provided URL does not match the expected MinIO URL format.");
        }
    }

    /**
     * 上传文件
     *
     * @param prefix 前缀名(文件夹分类)
     * @param file   文件
     * @return 返回地址
     */
    @Override
    public String uploadFile(String prefix, MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = builderFilePath(prefix, fileName);
            InputStream inputStream = file.getInputStream();
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object(filePath)
                    .contentType(file.getContentType())
                    .bucket(minioConfigProperties.getBucket())
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            StringBuilder urlPath = new StringBuilder(minioConfigProperties.getReadPath());
            urlPath.append(SEPARATOR + minioConfigProperties.getBucket());
            urlPath.append(SEPARATOR);
            urlPath.append(filePath);
            return urlPath.toString();
        } catch (Exception e) {
            log.error("minio put file error.", e);
            throw new RuntimeException("上传文件失败");
        }
    }

    /**
     * 上传文件
     *
     * @param prefix      前缀名(文件夹分类)
     * @param fileName    文件名
     * @param inputStream 文件流
     * @return 文件地址
     */
    @Override
    public String uploadFile(String prefix, String fileName, InputStream inputStream) {
        String filePath = builderFilePath(prefix, fileName);
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object(filePath)
                    .contentType(getContentType(fileName))
                    .bucket(minioConfigProperties.getBucket())
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            StringBuilder urlPath = new StringBuilder(minioConfigProperties.getReadPath());
            urlPath.append(SEPARATOR + minioConfigProperties.getBucket());
            urlPath.append(SEPARATOR);
            urlPath.append(filePath);
            return urlPath.toString();
        } catch (Exception ex) {
            log.error("minio put file error.", ex);
            throw new RuntimeException("上传文件失败");
        }
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件地址
     */
    @Override
    public void delete(String fileUrl) {
        try {
            String key = fileUrl.replace(minioConfigProperties.getEndpoint() + SEPARATOR, "");
            int index = key.indexOf(SEPARATOR);
            String bucket = key.substring(0, index);
            String objectName = key.substring(index + 1);

            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build());

        } catch (Exception e) {
            log.error("删除文件失败: {}", fileUrl, e);
        }
    }

    /**
     * 批量删除文件
     *
     * @param fileUrls 文件地址集合
     */
    @Override
    public void deleteFiles(List<String> fileUrls) {

        List<DeleteObject> objectsToDelete = fileUrls.stream()
                .map(this::getFileKeyFromUrl)
                .map(key -> new DeleteObject(key))
                .collect(Collectors.toList());

        Iterable<Result<DeleteError>> results = minioClient.removeObjects(
                RemoveObjectsArgs.builder()
                        .bucket(minioConfigProperties.getBucket())
                        .objects(objectsToDelete)
                        .build());

        // 遍历删除结果
        for (Result<DeleteError> result : results) {
            try {
                // 获取删除的结果
                DeleteError deleteError = result.get();
                if (deleteError != null) {
                    // 处理删除失败的情况
                    System.out.println("Delete failed for object: " + deleteError.objectName());
                } else {
                    // 处理删除成功的情况
                    System.out.println("Delete succeeded for object: " + result.get().objectName());
                }
            } catch (Exception e) {
                // 处理异常
                log.error("Exception occurred while deleting object: " + e.getMessage());
            }
        }
    }

    /**
     * 下载文件
     *
     * @param fileUrl   文件地址
     * @param localPath 本地下载路径
     * @return 下载流
     */
    @Override
    public void downLoadFileToDisk(String fileUrl, String localPath) {
        try {
            // 提取 URL 中的文件名部分（从最后一个 "/" 之后的部分）
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

            // 如果 localPath 是一个文件夹路径，则自动添加文件名
            if (localPath.endsWith(File.separator)) {
                // 如果是文件夹路径，直接添加文件名
                localPath = localPath + fileName;
            } else {
                // 如果是文件路径且没有文件夹部分，则直接使用该路径
                File localFile = new File(localPath);
                if (localFile.isDirectory()) {
                    localPath = localPath + File.separator + fileName;
                }
            }

            // 解析 fileUrl，提取 bucket 和 objectName
            String key = fileUrl.replace(minioConfigProperties.getEndpoint() + SEPARATOR, "");
            int index = key.indexOf(SEPARATOR);
            String bucket = key.substring(0, index);
            String objectName = key.substring(index + 1);

            // 创建本地目录（如果不存在）
            File localFile = new File(localPath);
            File parentDir = localFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();  // 创建目录
            }

            // 下载文件
            try (InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build());
                 FileOutputStream fileOutputStream = new FileOutputStream(localFile)) {

                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }
                log.info("文件下载成功，保存路径：{}", localPath);
            }
        } catch (FileNotFoundException e) {
            log.error("文件未找到: {}", fileUrl, e);
            throw new RuntimeException("下载失败：文件未找到，路径：" + fileUrl);
        } catch (IOException e) {
            log.error("IO 错误：下载文件时发生 I/O 错误", e);
            throw new RuntimeException("下载失败：发生 I/O 错误，文件路径：" + fileUrl);
        } catch (Exception e) {
            log.error("文件下载失败: {}", fileUrl, e);
            throw new RuntimeException("下载失败：发生未知错误，请稍后再试！");
        }
    }


}
