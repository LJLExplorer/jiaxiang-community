package com.jiaxiang.content.service.impl;

import com.jiaxiang.content.mapper.ContentMapper;
import com.jiaxiang.content.service.ContentService;
import com.jiaxiang.file.service.FileStorageService;
import com.jiaxiang.model.community.vos.CommunityProfileVO;
import com.jiaxiang.model.content.dos.ArticleFileDO;
import com.jiaxiang.model.content.vos.ContentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.jiaxiang.model.common.constant.ArticleTypeConstant.COMMUNITY_PROFILE;

@Slf4j
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    private final ContentMapper contentMapper;

    private final FileStorageService fileStorageService;

    public ContentServiceImpl(ContentMapper contentMapper, FileStorageService fileStorageService) {
        this.contentMapper = contentMapper;
        this.fileStorageService = fileStorageService;
    }

    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    @Override
    public CommunityProfileVO listCommunityProfile(Long communityId) {
        ContentVO contentVO = listContentByCommunityAndType(communityId, COMMUNITY_PROFILE);
        List<ArticleFileDO> articleFileDOList = listArticleFileByArticleId(contentVO.getId());
        CommunityProfileVO communityProfileVO = new CommunityProfileVO();
        communityProfileVO.setTitle(contentVO.getTitle());
        communityProfileVO.setProfile(contentVO.getContent());
        communityProfileVO.setImages(Optional.ofNullable(articleFileDOList)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0).getPathUrl())
                .orElse(null));
        return communityProfileVO;
    }

    /**
     * 根据社区id和文章类型列出文章内容
     *
     * @param communityId 社区
     * @param type        类型
     * @return 文章
     */
    @Override
    public ContentVO listContentByCommunityAndType(Long communityId, String type) {
        return contentMapper.listContentByCommunityAndType(communityId, type);
    }

    /**
     * 查询文章附件
     *
     * @param articleId 文章id
     * @return ArticleFileDO
     */
    @Override
    public List<ArticleFileDO> listArticleFileByArticleId(Long articleId) {
        return contentMapper.listArticleFileByArticleId(articleId);
    }

    /**
     * 上传文件接口
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        return fileStorageService.uploadFile("", file);
    }
}
