package com.jiaxiang.model.community.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GridDO {
    private Long id;
    private String communityEn;
    private String communityCn;
    private String icon;
    private String description;
}