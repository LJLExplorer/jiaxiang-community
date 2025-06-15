package com.jiaxiang.model.activity.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动详情
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDetailVO {
    private Long id;
    private Long communityId;
    private String title;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String organizer;
    private Integer participantCount;
    private String participantType;
    private String theme;
    private String introduction;
    private String content;
    private String address;
    private List<String> images;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
