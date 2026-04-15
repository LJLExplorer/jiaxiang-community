package com.jiaxiang.portal.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP和设备信息获取工具类
 */
public class IpDeviceUtil {
    // 匹配IP的正则表达式
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

    /**
     * 获取客户端真实IP（兼容Nginx、Apache、负载均衡等反向代理场景）
     */
    public static String getClientIp(HttpServletRequest request) {
        // 依次从反向代理头、原生请求头获取IP，优先取非unknown的有效IP
        String[] ipHeads = {
                "X-Forwarded-For",  // 常用反向代理头（Nginx/Apache）
                "Proxy-Client-IP",   // 代理服务器头
                "WL-Proxy-Client-IP",// WebLogic代理头
                "HTTP_CLIENT_IP",    // 部分代理头
                "HTTP_X_FORWARDED_FOR"
        };
        String ip = null;
        for (String head : ipHeads) {
            ip = request.getHeader(head);
            if (isValidIp(ip)) {
                // X-Forwarded-For可能有多个IP（逗号分隔），取第一个真实IP
                if (ip.contains(",")) {
                    ip = ip.split(",")[0].trim();
                }
                return ip;
            }
        }
        // 若无代理，直接获取原生请求IP
        return request.getRemoteAddr();
    }

    /**
     * 解析设备信息（从User-Agent头），包含：浏览器类型、操作系统、终端类型（PC/移动端）
     */
    public static String getDeviceInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null || userAgent.trim().isEmpty()) {
            return "未知设备";
        }
        userAgent = userAgent.toLowerCase();

        // 解析操作系统
        String os = "未知系统";
        if (userAgent.contains("windows")) {
            os = "Windows";
        } else if (userAgent.contains("mac os") || userAgent.contains("macos")) {
            os = "macOS";
        } else if (userAgent.contains("android")) {
            os = "Android";
        } else if (userAgent.contains("ios") || userAgent.contains("iphone") || userAgent.contains("ipad")) {
            os = "iOS";
        } else if (userAgent.contains("linux")) {
            os = "Linux";
        }

        // 解析浏览器
        String browser = "未知浏览器";
        if (userAgent.contains("chrome")) {
            browser = "Chrome";
        } else if (userAgent.contains("firefox")) {
            browser = "Firefox";
        } else if (userAgent.contains("safari") && !userAgent.contains("chrome")) {
            browser = "Safari";
        } else if (userAgent.contains("edge")) {
            browser = "Edge";
        } else if (userAgent.contains("ie") || userAgent.contains("trident")) {
            browser = "IE";
        }

        // 解析终端类型
        String terminal = "PC端";
        if (userAgent.contains("mobile") || userAgent.contains("android") || userAgent.contains("ios")
                || userAgent.contains("iphone") || userAgent.contains("ipad") || userAgent.contains("wechat")) {
            terminal = "移动端";
        }

        // 拼接设备信息
        return String.format("%s | %s | %s", terminal, os, browser);
    }

    /**
     * 校验IP是否有效（非null、非empty、非unknown）
     */
    private static boolean isValidIp(String ip) {
        return ip != null && !ip.trim().isEmpty() && !"unknown".equalsIgnoreCase(ip.trim())
                && IP_PATTERN.matcher(ip.trim()).matches();
    }
}