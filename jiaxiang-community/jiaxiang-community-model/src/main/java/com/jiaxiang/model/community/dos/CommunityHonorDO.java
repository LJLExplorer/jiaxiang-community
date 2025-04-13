package com.jiaxiang.model.community.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityHonorDO {
    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 所属社区 ID
     */
    private Long communityId;

    /**
     * 荣誉详情
     */
    private String honorDetail;
}
