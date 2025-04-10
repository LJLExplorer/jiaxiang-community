package com.jiaxiang.model.activity.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动详情表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDetailDo {

    private Long id;

    private Long activityId;

    private String theme;

    private String introduction;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
