package com.jiaxiang.content.service;

import com.jiaxiang.model.community.dtos.CommunityProfileDTO;
import com.jiaxiang.model.community.vos.CommunityProfileVO;
import com.jiaxiang.model.community.vos.GridVO;
import com.jiaxiang.model.content.dos.ArticleFileDO;
import com.jiaxiang.model.content.vos.ContentVO;
import org.springframework.web.multipart.MultipartFile;

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
     * 更新社区简介
     * @param communityId
     * @return
     */
    Integer updateCommunityProfile(Long communityId, CommunityProfileDTO communityProfileDTO);

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
     * 上传文件接口
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);


}
