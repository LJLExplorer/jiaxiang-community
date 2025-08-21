package com.jiaxiang.model.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityProfileDTO {
    private Long artiProfileId;
    private String title;
    private String images;
    private String profile;
}
