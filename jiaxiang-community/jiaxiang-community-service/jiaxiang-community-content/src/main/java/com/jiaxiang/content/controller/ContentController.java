package com.jiaxiang.content.controller;

import com.jiaxiang.content.service.ContentService;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jiaxiang.model.common.constant.ApiRouterConsts.CONTENT_URL_PREFIX;

@RestController
@RequestMapping(CONTENT_URL_PREFIX)
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/save")
    public ResponseEntity<ResponseResult<?>> saveContent() {
        return null;
    }

    @GetMapping("/community_profile")
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId){
        return null;
    }
}
