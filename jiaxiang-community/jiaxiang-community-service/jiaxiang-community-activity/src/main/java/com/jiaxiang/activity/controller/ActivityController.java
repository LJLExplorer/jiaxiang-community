package com.jiaxiang.activity.controller;

import com.jiaxiang.activity.service.ActivityService;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.activity.vos.ActivityDetailVO;
import com.jiaxiang.model.activity.vos.ActivityPreviewVO;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.ACTIVITY_URL_PREFIX;

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

    /**
     * 更新社区活动
     *
     * @param communityId       社区id
     * @param activityDetailDto 活动内容
     * @return
     */
    @PutMapping("/update_community_activity_detail")
    ResponseEntity<ResponseResult<?>> updateCommunityActivityDetail(@RequestParam("communityId") Long communityId, @RequestBody ActivityDetailDto activityDetailDto) {
        activityService.updateCommunityActivityDetail(communityId, activityDetailDto);
        return ResponseWrapper.success("更新成功！");
    }

    /**
     * 添加社区活动
     *
     * @param communityId       社区id
     * @param activityDetailDto 活动内容
     * @return
     */
    @PostMapping("/add_community_activity_detail")
    ResponseEntity<ResponseResult<?>> addCommunityActivityDetail(@RequestParam("communityId") Long communityId, @RequestBody ActivityDetailDto activityDetailDto) {
        activityService.addCommunityActivityDetail(communityId, activityDetailDto);
        return ResponseWrapper.success("添加活动成功！");
    }

    /**
     * 添加社区活动
     *
     * @param communityId       社区id
     * @param id 活动id
     * @return
     */
    @DeleteMapping("/delete_community_activity_detail")
    ResponseEntity<ResponseResult<?>> deleteCommunityActivityDetail(@RequestParam("communityId") Long communityId, @RequestParam("id") Long id) {
        activityService.deleteCommunityActivityDetail(communityId, id);
        return ResponseWrapper.success("删除活动成功！");
    }
}
