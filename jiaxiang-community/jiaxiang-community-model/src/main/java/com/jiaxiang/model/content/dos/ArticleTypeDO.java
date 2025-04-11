package com.jiaxiang.model.content.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTypeDO {
    private Integer id;
    private String type;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
