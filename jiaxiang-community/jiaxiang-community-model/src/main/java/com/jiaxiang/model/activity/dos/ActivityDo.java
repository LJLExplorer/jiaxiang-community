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

    private String participantType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public ActivityDo(Long id, Long communityId, String title, String location, LocalDateTime startTime,
                      LocalDateTime endTime, String organizer, Integer participantCount) {
        this.id = id;
        this.communityId = communityId;
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.participantCount = participantCount;
    }

}
