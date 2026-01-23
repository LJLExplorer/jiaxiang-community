package com.jiaxiang.apis.Content.fallback;

import com.jiaxiang.apis.Content.IContentClient;
import com.jiaxiang.common.exception.CustomException;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import com.jiaxiang.model.community.dos.ItemListDO;
import com.jiaxiang.model.community.dtos.*;
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
    public ResponseEntity<ResponseResult<?>> updateCommunityProfile(Long communityId, CommunityProfileDTO communityProfileDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "更新社区简介失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "列出网格管理失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updateGridManagement(GridDTO gridDO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "更新网格内容失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> addGridManagement(GridDTO gridDO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加网格内容失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteGridManagement(Long id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "删除网格内容失败");
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
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取为人民服务列表失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listServePeopleInfo(int id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "获取为人民服务数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> addServePeopleInfo(ServePeopleInfoDTO servePeopleInfoDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加为人民服务数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updateServePeopleInfo(ServePeopleInfoDTO servePeopleInfDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "更新为人民服务数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteServePeopleInfo(Long id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "删除为人民服务数据失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> addPersonalInfo(StaffInfoDTO staffInfoDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加履职信息失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updatePersonalInfo(StaffInfoDTO staffInfoDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "更新履职信息失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deletePersonalInfo(Long id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "删除某人履职信息失败");
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
    public ResponseEntity<ResponseResult<?>> addCommunityHonor(CommunityHonorDTO communityHonorDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加社区荣誉失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updateCommunityHonor(CommunityHonorDTO communityHonorDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "更新社区荣誉失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteCommunityHonor(Long id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "删除社区荣誉失败");
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

    @Override
    public ResponseEntity<ResponseResult<?>> addProofInfo(Long communityId, ProofDocumentsDTO proofDocumentsDTO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加证明材料失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteProofInfoById(Long id) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "删除证明材料失败");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> saveItemContent(ItemListDO itemListDO) {
        throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "保存事项清单失败");
    }

}
