package com.jiaxiang.model.community.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityProfileDO {
    private Long articleProfileId;
    private String title;
    private String profile;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public CommunityProfileDO(Long articleProfileId, String title, String profile) {
        this.articleProfileId = articleProfileId;
        this.title = title;
        this.profile = profile;
    }
}
