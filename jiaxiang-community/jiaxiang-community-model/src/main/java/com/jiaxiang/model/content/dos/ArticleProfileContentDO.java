package com.jiaxiang.model.content.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleProfileContentDO {
    private Long id;
    private Long articleProfileId;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
