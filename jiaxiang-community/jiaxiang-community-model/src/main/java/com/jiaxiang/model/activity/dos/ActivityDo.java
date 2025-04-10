package com.jiaxiang.model.activity.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDo {


    private Long id;

    private Long communityId;

    private String title;

    private String location;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String organizer;

    private Integer participantCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
