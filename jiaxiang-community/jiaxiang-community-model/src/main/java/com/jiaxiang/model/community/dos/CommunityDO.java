package com.jiaxiang.model.community.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDO {
    private Long id;
    private String nameEn;
    private String nameCn;
    private String icon;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
