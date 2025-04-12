package com.jiaxiang.content.mapper;

import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.content.dos.ArticleFileDO;
import com.jiaxiang.model.content.vos.ContentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContentMapper {

    ContentVO listContentByCommunityAndType(@Param("communityId") Long communityId, @Param("type") String type);

    List<ArticleFileDO> listArticleFileByArticleId(@Param("articleId") Long articleId);

    List<CommunityDO> listAllCommunity();
}
