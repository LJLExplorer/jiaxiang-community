package com.jiaxiang.model.community.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 两委成员表DO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffInfoDO {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名和职位
     */
    private String nameAndPosition;

    /**
     * 图片路径
     */
    private String images;

    /**
     * 职责
     */
    private String duty;

    /**
     * 职责描述
     */
    private String dutyDescription;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}