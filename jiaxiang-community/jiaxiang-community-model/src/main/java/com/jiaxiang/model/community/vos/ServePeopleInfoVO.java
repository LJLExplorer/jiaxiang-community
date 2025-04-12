package com.jiaxiang.model.community.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServePeopleInfoVO {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String images;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 开放时间（如：周一至周五 08:00-17:00）
     */
    private String openDaysAndTime;

    /**
     * 地点
     */
    private String location;

    /**
     * 服务简介
     */
    private String serviceIntro;

    /**
     * 服务内容
     */
    private String serviceContent;

    /**
     * 服务规则
     */
    private String serviceRules;

    /**
     * 创建时间
     */
    private LocalDateTime startTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
