package com.jiaxiang.model.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GridDTO {
    private Long id;
    private String communityEn;
    private String communityCn;
    private String icon;
    private String description;
}