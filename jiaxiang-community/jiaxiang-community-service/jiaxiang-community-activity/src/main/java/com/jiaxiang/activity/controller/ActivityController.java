package com.jiaxiang.activity.controller;

import com.jiaxiang.activity.service.ActivityService;
import com.jiaxiang.model.activity.vos.ActivityPreviewVO;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/list_community_activities")
    public ResponseEntity<ResponseResult<?>> listCommunityActivitiesPreview(@RequestParam(value = "communityId") Long communityId) {
        List<ActivityPreviewVO> activityPreviewVOList = activityService.listCommunityActivitiesPreview(communityId);
        return ResponseWrapper.success(activityPreviewVOList);
    }
}
