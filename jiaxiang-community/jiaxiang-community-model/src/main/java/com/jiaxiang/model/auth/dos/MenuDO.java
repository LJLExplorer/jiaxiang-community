package com.jiaxiang.model.auth.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDO {

    private Long id;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 中文名
     */
    private String cnName;

    /**
     * 英文名
     */
    private String enName;

    /**
     * 是否是父菜单（0 否 1 是）
     */
    private Integer isParent;

    /**
     * 父级菜单ID
     */
    private Long parentId;

    /**
     * 前端路由
     */
    private String path;

    /**
     * 前端页面路径
     */
    private String component;

    /**
     * 权限标识（例如 user:add）
     */
    private String perms;

    /**
     * 菜单类型（0.目录 1.菜单 2.按钮）
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序字段
     */
    private Integer orderNum;

    /**
     * 是否显示（0.隐藏 1.显示）
     */
    private Integer isVisible;

    /**
     * 是否启用（0.禁用 1.启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}