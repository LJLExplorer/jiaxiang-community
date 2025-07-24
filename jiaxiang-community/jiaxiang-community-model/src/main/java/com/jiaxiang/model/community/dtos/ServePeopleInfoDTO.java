package com.jiaxiang.model.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServePeopleInfoDTO {
    private Long id;
    private String title;
    private String images;
    private String phone;
    private String openDaysAndTime;
    private String location;
    private String serviceIntro;
    private String serviceContent;
    private String serviceRules;
}
