package com.jiaxiang.portal.controller;

import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.portal.service.PortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PortalController {

    @Autowired
    private PortalService portalService;

    /**
     * 列出社区活动预览
     *
     * @param communityId 社区id
     * @return 预览图
     */
    @GetMapping("/jiahe/list_community_activities")
    public ResponseEntity<ResponseResult<?>> listCommunityActivities(Long communityId) {
        return portalService.listCommunityActivities(communityId);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @GetMapping("/jiahe/community_activity_detail")
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(@RequestParam("communityId") Long communityId, @RequestParam("id") Long activityId) {
        return portalService.listCommunityActivityDetail(communityId, activityId);
    }


    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    @GetMapping("/jiahe/community_profile")
    public ResponseEntity<ResponseResult<?>> listJiaHeCommunityProfile(Long communityId) {
        return portalService.listCommunityProfile(communityId);
    }

    /**
     * 网格管理
     * @param communityId 社区id
     * @return 网格管理
     */
    @GetMapping("/jiahe/grid_management")
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        return portalService.listGridManagement(communityId);
    }
}
