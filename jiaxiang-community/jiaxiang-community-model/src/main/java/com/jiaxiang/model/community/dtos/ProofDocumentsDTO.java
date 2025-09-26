package com.jiaxiang.model.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProofDocumentsDTO {
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

    /**
     * 图片地址（多个用逗号分隔）
     */
    private String images;

    /**
     * 材料内容详情
     */
    private String content;
}
