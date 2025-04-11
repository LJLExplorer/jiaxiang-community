package com.jiaxiang.activity.controller;

import com.jiaxiang.activity.service.ActivityService;
import com.jiaxiang.model.activity.vos.ActivityDetailVO;
import com.jiaxiang.model.activity.vos.ActivityPreviewVO;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jiaxiang.model.common.constant.ApiRouterConsts.ACTIVITY_URL_PREFIX;

@RestController
@RequestMapping(ACTIVITY_URL_PREFIX)
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 列出社区活动预览
     *
     * @param communityId 社区id
     * @return 预览图
     */
    @GetMapping("/list_community_activities")
    public ResponseEntity<ResponseResult<?>> listCommunityActivitiesPreview(@RequestParam(value = "communityId") Long communityId) {
        List<ActivityPreviewVO> activityPreviewVOList = activityService.listCommunityActivitiesPreview(communityId);
        return ResponseWrapper.success(activityPreviewVOList);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @GetMapping("/list_community_activity_detail")
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(@RequestParam(value = "communityId") Long communityId, @RequestParam(value = "activityId") Long activityId) {
        ActivityDetailVO activityPreviewVOList = activityService.listCommunityActivityDetail(communityId, activityId);
        return ResponseWrapper.success(activityPreviewVOList);
    }
}
