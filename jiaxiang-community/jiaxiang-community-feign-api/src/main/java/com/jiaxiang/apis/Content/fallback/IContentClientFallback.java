package com.jiaxiang.apis.Content.fallback;

import com.jiaxiang.apis.Content.IContentClient;
import com.jiaxiang.common.exception.CustomException;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class IContentClientFallback implements IContentClient {

    @Override
    public ResponseEntity<ResponseResult<?>> saveContent() {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "保存数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取社区简介失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "列出网格管理失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取两位成员列表数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listPersonalInfo(Long communityId, long id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取履职信息失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listServePeople(int communityId, int pageSize) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取为人民服务数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listServePeopleInfo(int id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取为人民服务数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listMatters(Long communityId, int pageNum, int pageSize) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "保存事项清单列表失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "保存社区荣誉数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> proofDocuments(Long communityId, int pageNum, int pageSize) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "保存证明列表失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> proofInfo(int id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取证明数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> uploadFile(MultipartFile file) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "文件上传失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteMattersById(String id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "根据id删除失败");
    }

}
