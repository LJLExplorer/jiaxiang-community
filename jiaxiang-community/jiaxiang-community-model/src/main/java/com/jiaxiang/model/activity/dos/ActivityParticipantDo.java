package com.jiaxiang.model.activity.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动参加人员
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityParticipantDo {

    private Long id;

    private Long activityId;

    private String name;

    private String role;

    private String contactInfo;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
