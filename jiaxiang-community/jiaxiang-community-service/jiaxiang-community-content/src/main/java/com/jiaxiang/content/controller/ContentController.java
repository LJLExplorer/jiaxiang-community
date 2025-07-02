package com.jiaxiang.content.controller;

import com.jiaxiang.content.service.ContentService;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.community.vos.CommunityProfileVO;
import com.jiaxiang.model.content.vos.ContentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.CONTENT_URL_PREFIX;
import static com.jiaxiang.model.common.constant.SystemConstant.MESSAGE;

@Slf4j
@RestController
@RequestMapping(CONTENT_URL_PREFIX)
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('user:add')")
    public ResponseEntity<ResponseResult<?>> saveContent() {
        log.info("权限测试成功，有 user:add 权限");
        Map<String, Object> map = new HashMap<>();
        map.put(MESSAGE, "权限测试成功，有 user:add 权限");
        return ResponseWrapper.success(map);
    }

    /**
     * 根据内容类型列出文章内容
     *
     * @param communityId 社区id
     * @return 文章内容
     */
    @GetMapping("/community_profile")
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId) {
        CommunityProfileVO communityProfileVO = contentService.listCommunityProfile(communityId);
        return ResponseWrapper.success(communityProfileVO);
    }

    /**
     * 根据内容类型列出文章内容
     *
     * @param communityId 社区id
     * @param type        文字类型
     * @return 文章内容
     */
    @GetMapping("/list_content_by_type")
    public ResponseEntity<ResponseResult<?>> listContentByType(Long communityId, String type) {
        ContentVO contentVO = contentService.listContentByCommunityAndType(communityId, type);
        return ResponseWrapper.success(contentVO);
    }
}
