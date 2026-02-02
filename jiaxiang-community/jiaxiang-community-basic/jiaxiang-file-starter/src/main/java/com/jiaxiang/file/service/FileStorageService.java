package com.jiaxiang.file.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface FileStorageService {

    /**
     * 上传文件
     * @param prefix 前缀名(文件夹分类)
     * @param fileName 文件名
     * @param inputStream 文件流
     * @return 文件地址
     */
    public String uploadFile(String prefix, String fileName, InputStream inputStream);

    /**
     * 上传文件
     * @param prefix
     * @param file
     * @return
     */
    public String uploadFile(String prefix, MultipartFile file);

    /**
     * 删除文件
     * @param fileUrl 文件地址
     */
    public void delete(String fileUrl);

    /**
     * 批量删除文件
     * @param fileUrls 文件地址集合
     */
    public void deleteFiles(List<String> fileUrls);

    /**
     * 下载文件
     * @param fileUrl 文件地址
     * @param localPath 本地下载路径
     * @return 下载流
     */
    public void downLoadFileToDisk(String fileUrl, String localPath);

    public void downloadByUrl(String fileUrl, HttpServletResponse response);
}
