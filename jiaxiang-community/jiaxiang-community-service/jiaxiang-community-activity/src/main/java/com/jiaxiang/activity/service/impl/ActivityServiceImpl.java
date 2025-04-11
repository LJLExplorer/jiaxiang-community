package com.jiaxiang.activity.service.impl;

import com.jiaxiang.activity.mapper.ActivityMapper;
import com.jiaxiang.activity.service.ActivityService;
import com.jiaxiang.model.activity.dos.ActivityDo;
import com.jiaxiang.model.activity.dos.ActivityFileDo;
import com.jiaxiang.model.activity.vos.ActivityDetailVO;
import com.jiaxiang.model.activity.vos.ActivityPreviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    @Override
    public List<ActivityPreviewVO> listCommunityActivitiesPreview(Long communityId) {
        return activityMapper.getActivitiesByCommunityId(communityId);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @Override
    public ActivityDetailVO listCommunityActivityDetail(Long communityId, Long activityId) {
        ActivityDetailVO activityDetailVO = activityMapper.getActivityDetailWithOutImagesByActivityId(communityId, activityId);
        List<ActivityFileDo> activityFileDos = activityMapper.getFilesByActivityId(communityId, activityId);
        List<String> images = activityFileDos.stream().map(ActivityFileDo::getPathUrl).toList();
        activityDetailVO.setImages(images);
        return activityDetailVO;
    }
}
