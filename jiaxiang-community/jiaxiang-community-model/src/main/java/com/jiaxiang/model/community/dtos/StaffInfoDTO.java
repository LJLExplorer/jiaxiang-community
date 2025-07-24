package com.jiaxiang.model.community.dtos;

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
public class StaffInfoDTO {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;


    /**
     * 职位
     */
    private String position;

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
}