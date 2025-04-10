package com.jiaxiang.portal.service.impl;

import com.jiaxiang.apis.Activity.IActivityClient;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.portal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PortalServiceImpl implements PortalService {

    @Autowired
    private IActivityClient iActivityClient;


    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listJiaHeCommunityActivities(Long communityId) {
        return iActivityClient.listCommunityActivitiesPreview(communityId);
    }
}
