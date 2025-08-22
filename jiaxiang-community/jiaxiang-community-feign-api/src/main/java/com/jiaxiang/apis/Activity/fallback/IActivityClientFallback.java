package com.jiaxiang.apis.Activity.fallback;

import com.jiaxiang.apis.Activity.IActivityClient;
import com.jiaxiang.common.exception.CustomException;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class IActivityClientFallback implements IActivityClient {

    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivitiesPreview(Long communityId) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取社区活动预览失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(Long communityId, Long activityId) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取社区活动详情失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updateCommunityActivityDetail(Long communityId, ActivityDetailDto activityDetailDto) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "更新活动详情失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> addCommunityActivityDetail(Long communityId, ActivityDetailDto activityDetailDto) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加活动详情失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> addCommunityActivity(Long communityId, ActivityDetailDto activityDetailDto) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加活动失败");
    }
}
