package com.jiaxiang.portal.service;

import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.http.ResponseEntity;

public interface PortalService {

    /**
     * 根据社区id列出活动预览
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    public ResponseEntity<ResponseResult<?>> listCommunityActivities(Long communityId);

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(Long communityId, Long activityId);

    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId);
}
