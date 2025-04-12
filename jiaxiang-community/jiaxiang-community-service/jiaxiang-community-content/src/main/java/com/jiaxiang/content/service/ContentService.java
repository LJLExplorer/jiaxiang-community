package com.jiaxiang.content.service;

import com.jiaxiang.model.community.vos.CommunityProfileVO;
import com.jiaxiang.model.community.vos.GridVO;
import com.jiaxiang.model.content.dos.ArticleFileDO;
import com.jiaxiang.model.content.vos.ContentVO;

import java.util.List;

public interface ContentService {

    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    CommunityProfileVO listCommunityProfile(Long communityId);

    /**
     *  根据社区id和文章类型列出文章内容
     * @param communityId 社区
     * @param type 类型
     * @return 文章
     */
    ContentVO listContentByCommunityAndType(Long communityId, String type);

    /**
     * 查询文章附件
     * @param articleId 文章id
     * @return ArticleFileDO
     */
    public List<ArticleFileDO> listArticleFileByArticleId(Long articleId);

    /**
     * 网格管理
     * @param communityId 社区id
     * @return 网格管理
     */
    public List<GridVO> listGridManagement(Long communityId);
}
