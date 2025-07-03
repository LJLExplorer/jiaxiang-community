package com.jiaxiang.apis.Content.fallback;

import com.jiaxiang.apis.Content.IContentClient;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public ResponseEntity<ResponseResult<?>> listPersonalInfo(Long communityId, long id) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listServePeople(int communityId, int pageSize) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listServePeopleInfo(int id) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listMatters(Long communityId, int pageNum, int pageSize) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> proofDocuments(Long communityId, int pageNum, int pageSize) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> proofInfo(int id) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "获取数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> uploadFile(MultipartFile file) {
        return ResponseWrapper.serverError(AppHttpCodeEnum.SERVER_ERROR.getCode(), "文件上传失败");
    }

}
