package com.jiaxiang.model.content.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleFileDO {

    private Long id;

    private Long articleId;

    private String pathUrl;

    private String fileType;

    private Boolean isCover;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
