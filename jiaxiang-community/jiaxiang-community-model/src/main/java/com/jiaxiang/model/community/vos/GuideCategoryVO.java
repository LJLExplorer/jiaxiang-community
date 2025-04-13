package com.jiaxiang.model.community.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuideCategoryVO {


    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 所属社区 ID
     */
    private Long communityId;

    /**
     * 分类标题
     */
    private String title;

    /**
     * 分类内容/说明
     */
    private String content;

}
