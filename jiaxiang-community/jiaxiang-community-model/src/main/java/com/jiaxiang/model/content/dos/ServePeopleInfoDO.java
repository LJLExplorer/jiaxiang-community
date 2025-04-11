package com.jiaxiang.model.content.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServePeopleInfoDO {
    private Long id;
    private String title;
    private String images;
    private String phone;
    private String openDaysAndTime;
    private String location;
    private String serviceIntro;
    private String serviceContent;
    private String serviceRules;
    private LocalDateTime startTime;
    private LocalDateTime updateTime;
}
