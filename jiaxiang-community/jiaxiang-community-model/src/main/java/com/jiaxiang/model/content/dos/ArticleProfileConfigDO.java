package com.jiaxiang.model.content.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleProfileConfigDO {
    private Long id;
    private Long articleProfileId;
    private Boolean isComment;
    private Boolean isShow;
    private Boolean isDelete;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
