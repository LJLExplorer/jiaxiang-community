package com.jiaxiang.model.activity.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动文件表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityFileDo {

    private Long id;

    private Long activityId;

    private String pathUrl;

    private String fileType;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
