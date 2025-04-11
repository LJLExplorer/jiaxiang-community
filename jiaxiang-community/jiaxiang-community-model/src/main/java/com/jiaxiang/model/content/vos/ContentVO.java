package com.jiaxiang.model.content.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentVO {
    private Long id;
    private Long communityId;
    private String title;
    private short layout;
    private Integer commentNums;
    private Integer viewsNums;
    private LocalDateTime publishTime;
    private boolean isComment;
    private boolean isShow;
    private boolean isDelete;
    private String content;
    private String type;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
}
