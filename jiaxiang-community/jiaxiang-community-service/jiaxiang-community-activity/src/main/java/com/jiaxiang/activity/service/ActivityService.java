package com.jiaxiang.activity.service;

import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.activity.vos.ActivityDetailVO;
import com.jiaxiang.model.activity.vos.ActivityPreviewVO;

import java.util.List;

public interface ActivityService {

    /**
     * 根据社区id列出活动预览
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    List<ActivityPreviewVO> listCommunityActivitiesPreview(Long communityId);

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    ActivityDetailVO listCommunityActivityDetail(Long communityId, Long activityId);

    /**
     * 更新活动详情
     * @param communityId
     * @param activityDetailDto
     * @return
     */
    void updateCommunityActivityDetail(Long communityId, ActivityDetailDto activityDetailDto);
}
