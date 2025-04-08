package com.jiaxiang.model.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum ViewContentTypeEnum {

    // 默认
    DEFAULT("default", "application/octet-stream"),

    // 图片
    PNG("png", "image/png"),
    JPEG("jpeg", "image/jpeg"),
    JPG("jpg", "image/jpeg"),
    GIF("gif", "image/gif"),
    BMP("bmp", "image/bmp"),
    WBMP("wbmp", "image/vnd.wap.wbmp"),
    TIFF("tiff", "image/tiff"),
    TIF("tif", "image/tiff"),
    JPE("jpe", "image/jpeg"),
    JFIF("jfif", "image/jpeg"),
    ICO("ico", "image/x-icon"),
    SVG("svg", "image/svg+xml"),
    WEBP("webp", "image/webp"),
    HEIC("heic", "image/heic"),
    AVIF("avif", "image/avif"),

    // 文本 & 文档
    TXT("txt", "text/plain"),
    HTML("html", "text/html"),
    HTM("htm", "text/html"),
    CSS("css", "text/css"),
    JS("js", "application/javascript"),
    JSON("json", "application/json"),
    XML("xml", "application/xml"),
    PDF("pdf", "application/pdf"),
    DOC("doc", "application/msword"),
    DOCX("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    XLS("xls", "application/vnd.ms-excel"),
    XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    PPT("ppt", "application/vnd.ms-powerpoint"),
    PPTX("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),

    // 压缩包
    ZIP("zip", "application/zip"),
    RAR("rar", "application/vnd.rar"),
    _7Z("7z", "application/x-7z-compressed"),

    // 音频
    MP3("mp3", "audio/mpeg"),
    WAV("wav", "audio/wav"),
    OGG("ogg", "audio/ogg"),

    // 视频
    MP4("mp4", "video/mp4"),
    MOV("mov", "video/quicktime"),
    AVI("avi", "video/x-msvideo"),
    WEBM("webm", "video/webm"),

    // 其他（特殊）
    NET("net", "image/pnetvue"),
    RP("rp", "image/vnd.rn-realpix"),
    FAX("fax", "image/fax");

    private final String prefix;
    private final String type;

    private static final Map<String, ViewContentTypeEnum> ENUM_MAP = new HashMap<>();

    static {
        for (ViewContentTypeEnum value : values()) {
            ENUM_MAP.put(value.getPrefix().toLowerCase(), value);
        }
    }

    ViewContentTypeEnum(String prefix, String type) {
        this.prefix = prefix;
        this.type = type;
    }

    public static String getTypeByPrefix(String prefix) {
        ViewContentTypeEnum viewContentTypeEnum = ENUM_MAP.get(prefix.toLowerCase());
        return viewContentTypeEnum != null ? viewContentTypeEnum.getType() : DEFAULT.getType();
    }

    public static String getContentType(String filename) {
        if (StringUtils.isEmpty(filename) || !filename.contains(".")) {
            return DEFAULT.getType();
        }
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        return getTypeByPrefix(ext);
    }

    public String getPrefix() {
        return prefix;
    }

    public String getType() {
        return type;
    }
}