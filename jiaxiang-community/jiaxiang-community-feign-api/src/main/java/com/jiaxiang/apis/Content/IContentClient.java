package com.jiaxiang.apis.Content;

import com.jiaxiang.apis.Content.fallback.IContentClientFallback;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.jiaxiang.model.common.constant.ApiRouterConsts.COMMUNITY_URL_PREFIX;
import static com.jiaxiang.model.common.constant.ApiRouterConsts.CONTENT_URL_PREFIX;

@FeignClient(value = "jiaxiang-content", fallback = IContentClientFallback.class)
public interface IContentClient {

    @PostMapping(CONTENT_URL_PREFIX + "/save")
    public ResponseEntity<ResponseResult<?>> saveContent();

    @GetMapping(CONTENT_URL_PREFIX + "/community_profile")
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(@RequestParam Long communityId);

    @GetMapping(COMMUNITY_URL_PREFIX + "/grid_management")
    public ResponseEntity<ResponseResult<?>> listGridManagement(@RequestParam Long communityId);

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_committees_members")
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(@RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/personal_info")
    ResponseEntity<ResponseResult<?>> listPersonalInfo(@RequestParam Long communityId, @RequestParam long id);

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_serve_people")
    ResponseEntity<ResponseResult<?>> listServePeople(@RequestParam int pageNum, @RequestParam int pageSize);
}
