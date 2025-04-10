package com.jiaxiang.activity.service;

import com.jiaxiang.model.activity.vos.ActivityPreviewVO;

import java.util.List;

public interface ActivityService {

    /**
     * 根据社区id列出活动预览
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    List<ActivityPreviewVO> listCommunityActivitiesPreview(Long communityId);
}
