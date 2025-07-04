package com.jiaxiang.apis.Activity.fallback;

import com.jiaxiang.apis.Activity.IActivityClient;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class IActivityClientFallback implements IActivityClient {

    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivitiesPreview(Long communityId) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(Long communityId, Long activityId) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updateCommunityActivityDetail(Long communityId, ActivityDetailDto activityDetailDto) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "更新活动详情失败");
    }
}
