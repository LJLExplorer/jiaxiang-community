package com.jiaxiang.model.content.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleProfileDO {

    private Long id;

    private Integer authorId;

    private Integer communityId;

    private Integer articleTypeId;

    private String title;

    private Byte layout;

    private Integer commentNums;

    private Integer viewsNums;

    private LocalDateTime publishTime;

    private LocalDateTime createdTime;

    private LocalDateTime updateTime;
}
