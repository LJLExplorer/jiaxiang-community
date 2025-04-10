package com.jiaxiang.portal.controller;

import com.jiaxiang.apis.Activity.IActivityClient;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.portal.service.PortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PortalController {

    @Autowired
    private PortalService portalService;

    @GetMapping("/jiahe/list_community_activities")
    public ResponseEntity<ResponseResult<?>> listJiaHeCommunityActivities(Long communityId) {
        return portalService.listJiaHeCommunityActivities(communityId);
    }
}
