package com.jiaxiang.portal.service.impl;

import com.jiaxiang.apis.Activity.IActivityClient;
import com.jiaxiang.apis.Content.IContentClient;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.portal.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PortalServiceImpl implements PortalService {

    @Autowired
    private IActivityClient iActivityClient;

    @Autowired
    private IContentClient iContentClient;


    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivities(Long communityId) {
        return iActivityClient.listCommunityActivitiesPreview(communityId);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(Long communityId, Long activityId) {
        return iActivityClient.listCommunityActivityDetail(communityId, activityId);
    }

    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId) {
        return iContentClient.listCommunityProfile(communityId);
    }

    /**
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        return iContentClient.listGridManagement(communityId);
    }

    /**
     * 列出两委成员列表
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 两委成员列表
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize) {
        return iContentClient.listCommitteesMembers(pageNum, pageSize);
    }
}
