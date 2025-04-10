package com.jiaxiang.apis.Activity;

import com.jiaxiang.apis.Activity.fallback.IActivityClientFallback;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "jiaxiang-activity", fallback = IActivityClientFallback.class )
public interface IActivityClient {

    @GetMapping("/activity/list_community_activities")
    public ResponseEntity<ResponseResult<?>> listCommunityActivitiesPreview(@RequestParam Long communityId);
}
