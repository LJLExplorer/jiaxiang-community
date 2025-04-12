package com.jiaxiang.apis.Content.fallback;

import com.jiaxiang.apis.Content.IContentClient;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class IContentClientFallback implements IContentClient {

    @Override
    public ResponseEntity<ResponseResult<?>> saveContent() {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

}
