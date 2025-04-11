package com.jiaxiang.content.service;

import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.community.vos.CommunityProfileVO;
import com.jiaxiang.model.content.vos.ContentVO;

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
}
