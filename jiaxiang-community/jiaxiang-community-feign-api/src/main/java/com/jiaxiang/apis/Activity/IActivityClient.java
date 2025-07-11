package com.jiaxiang.apis.Activity;

import com.jiaxiang.apis.Activity.fallback.IActivityClientFallback;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.ACTIVITY_URL_PREFIX;

@FeignClient(value = "jiaxiang-activity", fallback = IActivityClientFallback.class)
public interface IActivityClient {

    @GetMapping(ACTIVITY_URL_PREFIX + "/list_community_activities")
    ResponseEntity<ResponseResult<?>> listCommunityActivitiesPreview(@RequestParam Long communityId);

    @GetMapping(ACTIVITY_URL_PREFIX + "/list_community_activity_detail")
    ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(@RequestParam Long communityId, @RequestParam Long activityId);

    @PutMapping(ACTIVITY_URL_PREFIX + "/update_community_activity_detail")
    ResponseEntity<ResponseResult<?>> updateCommunityActivityDetail(@RequestParam("communityId") Long communityId,@RequestBody ActivityDetailDto activityDetailDto);
 
    @PostMapping(ACTIVITY_URL_PREFIX + "/addCommunityActivity")
    ResponseEntity<ResponseResult<?>> addCommunityActivity(@RequestParam("communityId") Long communityId,@RequestBody ActivityDetailDto dto);
}
