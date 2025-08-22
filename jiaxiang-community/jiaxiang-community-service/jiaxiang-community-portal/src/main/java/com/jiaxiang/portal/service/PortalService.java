package com.jiaxiang.portal.service;

import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.community.dos.ItemListDO;
import com.jiaxiang.model.community.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PortalService {

    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    public ResponseEntity<ResponseResult<?>> listCommunityActivities(Long communityId);

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(Long communityId, Long activityId);

    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId);

    /**
     * 修改社区简介
     *
     * @param communityId
     * @param communityProfileDTO
     * @return
     */
    ResponseEntity<ResponseResult<?>> updateCommunityProfile(Long communityId, CommunityProfileDTO communityProfileDTO);


    /**
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId);

    /**
     * 列出两委成员列表
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 两委成员列表
     */
    ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize);

    /**
     * 履职信息
     *
     * @param communityId 社区id
     * @param id          个人id
     * @return 履职信息
     */
    ResponseEntity<ResponseResult<?>> listPersonalInfo(Long communityId, long id);

    /**
     * 列出为民服务清单
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 列出为民服务清单
     */
    ResponseEntity<ResponseResult<?>> listServePeople(int pageNum, int pageSize);

    ResponseEntity<ResponseResult<?>> listServePeopleInfo(int id);

    ResponseEntity<ResponseResult<?>> listMatters(Long communityId, int pageNum, int pageSize);

    ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize);

    ResponseEntity<ResponseResult<?>> proofDocuments(Long communityId, int pageNum, int pageSize);

    ResponseEntity<ResponseResult<?>> proofInfo(int id);

    // 测试
    ResponseEntity<ResponseResult<?>> saveContent(int id);

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    ResponseEntity<ResponseResult<?>> updateCommunityActivityDetail(Long communityId, Long id, ActivityDetailDto activityDetailDto);

    /**
     * 从md文件这种读取数据
     *
     * @param content
     * @param fileName
     * @return
     * @throws IOException
     */
    ItemListDO getItemMattersFromMarkdownFileContent(String content, String fileName, String id) throws IOException;

    /**
     * 保存事项清单
     *
     * @param content
     * @param name
     */
    void saveItemContent(String content, String name, String id) throws Exception;

    /**
     * 异步解析md文档
     *
     * @param content
     * @param fileName
     */
    void getItemContentAsync(String content, String fileName, String id);

    /**
     * 根据id删除事项清单
     *
     * @param id
     * @return
     */
    ResponseEntity<ResponseResult<?>> deleteMattersById(String id);

    ResponseEntity<ResponseResult<?>> addGridManagement(GridDTO gridDO);

    ResponseEntity<ResponseResult<?>> updateGridManagement(GridDTO gridDO);

    ResponseEntity<ResponseResult<?>> deleteGridManagement(Long id);

    ResponseEntity<ResponseResult<?>> addPersonalInfo(StaffInfoDTO staffInfoDTO);

    ResponseEntity<ResponseResult<?>> updatePersonalInfo(StaffInfoDTO staffInfoDTO);

    ResponseEntity<ResponseResult<?>> deletePersonalInfo(Long id);

    ResponseEntity<ResponseResult<?>> addServePeopleInfo(ServePeopleInfoDTO servePeopleInfoDTO);

    ResponseEntity<ResponseResult<?>> updateServePeopleInfo(ServePeopleInfoDTO servePeopleInfDTO);

    ResponseEntity<ResponseResult<?>> deleteServePeopleInfo(Long id);

    ResponseEntity<ResponseResult<?>> addCommunityHonor(CommunityHonorDTO communityHonorDTO);

    ResponseEntity<ResponseResult<?>> updateCommunityHonor(CommunityHonorDTO communityHonorDTO);

    ResponseEntity<ResponseResult<?>> deleteCommunityHonor(Long id);

    ResponseEntity<ResponseResult<?>> addCommunityActivityDetail(Long communityId, ActivityDetailDto activityDetailDto);

    ResponseEntity<ResponseResult<?>> deleteCommunityActivityDetail(Long communityId, Long id);

    ResponseEntity<ResponseResult<?>> addProofInfo(Long communityId, ProofDocumentsDTO proofDocumentsDTO);

    ResponseEntity<ResponseResult<?>> deleteProofInfoById(Long communityId, Long id);
}
