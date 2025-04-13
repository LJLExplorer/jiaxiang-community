package com.jiaxiang.model.community.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProofDocumentsPreviewVO {
    /**
     * 主键 ID
     */
    private Integer id;

    /**
     * 所属社区 ID
     */
    private Long communityId;

    /**
     * 材料标题
     */
    private String title;

}
