package com.jiaxiang.portal.service;

import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.http.ResponseEntity;

public interface PortalService {

    /**
     * 根据社区id列出活动预览
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    public ResponseEntity<ResponseResult<?>> listJiaHeCommunityActivities(Long communityId);
}
