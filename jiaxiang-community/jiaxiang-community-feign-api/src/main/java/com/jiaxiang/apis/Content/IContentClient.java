package com.jiaxiang.apis.Content;

import com.jiaxiang.apis.Content.fallback.IContentClientFallback;
import com.jiaxiang.model.common.dtos.ResponseResult;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.jiaxiang.model.common.constant.ApiRouterConsts.CONTENT_URL_PREFIX;

@FeignClient(value = "jiaxiang-content", fallback = IContentClientFallback.class)
public interface IContentClient {

    @PostMapping(CONTENT_URL_PREFIX + "/save")
    public ResponseEntity<ResponseResult<?>> saveContent();

    @GetMapping(CONTENT_URL_PREFIX + "/community_profile")
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(@RequestParam Long communityId);
}
