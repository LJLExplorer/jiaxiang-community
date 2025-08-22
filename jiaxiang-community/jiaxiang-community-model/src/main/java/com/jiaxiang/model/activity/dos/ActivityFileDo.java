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

    private Boolean isCover;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public ActivityFileDo(Long id, Boolean isCover) {
        this.id = id;
        this.isCover = isCover;
    }

    public ActivityFileDo(Long activityId, String pathUrl, String fileType, Boolean isCover, String description) {
        this.activityId = activityId;
        this.pathUrl = pathUrl;
        this.fileType = fileType;
        this.isCover = isCover;
        this.description = description;
    }

}
