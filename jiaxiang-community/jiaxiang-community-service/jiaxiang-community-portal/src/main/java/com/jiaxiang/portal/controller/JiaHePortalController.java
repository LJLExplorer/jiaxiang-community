package com.jiaxiang.portal.controller;

import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.portal.service.PortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.jiaxiang.model.common.constant.ApiRouterConsts.JIA_HE_URL_PREFIX;

@Slf4j
@RestController
@RequestMapping(JIA_HE_URL_PREFIX)
public class JiaHePortalController {

    @Autowired
    private PortalService portalService;

    /**
     * 列出社区活动预览
     *
     * @param communityId 社区id
     * @return 预览图
     */
    @GetMapping("/list_community_activities")
    public ResponseEntity<ResponseResult<?>> listCommunityActivities(Long communityId) {
        return portalService.listCommunityActivities(communityId);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @GetMapping("/community_activity_detail")
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(@RequestParam("communityId") Long communityId, @RequestParam("id") Long activityId) {
        return portalService.listCommunityActivityDetail(communityId, activityId);
    }


    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    @GetMapping("/community_profile")
    public ResponseEntity<ResponseResult<?>> listJiaHeCommunityProfile(Long communityId) {
        return portalService.listCommunityProfile(communityId);
    }

    /**
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    @GetMapping("/grid_management")
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        return portalService.listGridManagement(communityId);
    }


    /**
     * 列出两委成员列表
     *
     * @param communityId 社区id,可能员工仅属于某个社区
     * @param pageNum     当前页
     * @param pageSize    页大小
     * @return 两委成员列表
     */
    @GetMapping("/list_committees_members")
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(Long communityId, int pageNum, int pageSize) {
        return portalService.listCommitteesMembers(pageNum, pageSize);
    }

    /**
     * 履职信息
     *
     * @param communityId 社区id
     * @param id          个人id
     * @return 履职信息
     */
    @GetMapping("/personal_info")
    public ResponseEntity<ResponseResult<?>> listPersonalInfo(Long communityId, long id) {
        return portalService.listPersonalInfo(communityId, id);
    }

    /**
     * 列出为人民服务列表
     *
     * @param communityId 社区id,可能员工仅属于某个社区
     * @param pageNum     当前页
     * @param pageSize    页大小
     * @return 为人民服务列表
     */
    @GetMapping("/list_serve_people")
    public ResponseEntity<ResponseResult<?>> listServePeople(Long communityId, int pageNum, int pageSize) {
        return portalService.listServePeople(pageNum, pageSize);
    }

    /**
     * 为人民服务
     *
     * @param communityId
     * @param id
     * @return
     */
    @GetMapping("/serve_people_info")
    public ResponseEntity<ResponseResult<?>> servePeopleInfo(Long communityId, int id) {
        return portalService.listServePeopleInfo(id);
    }


    @GetMapping("/list_matters")
    public ResponseEntity<ResponseResult<?>> listMatters(Long communityId, int pageNum, int pageSize) {
        return portalService.listMatters(communityId, pageNum, pageSize);
    }

    @GetMapping("/community_honor")
    public ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize) {
        return portalService.communityHonor(communityId, pageNum, pageSize);
    }
}
