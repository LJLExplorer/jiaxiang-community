package com.jiaxiang.apis.Activity.fallback;

import com.jiaxiang.apis.Activity.IActivityClient;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class IActivityClientFallback implements IActivityClient {

    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivitiesPreview(Long communityId) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }
}
