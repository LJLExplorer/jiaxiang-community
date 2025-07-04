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

    public ActivityDetailDo(Long id, Long activityId, String theme, String introduction, String content) {
        this.id = id;
        this.activityId = activityId;
        this.theme = theme;
        this.introduction = introduction;
        this.content = content;
    }

}
