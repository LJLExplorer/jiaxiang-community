package com.jiaxiang.portal.service;

import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.http.ResponseEntity;

public interface PortalService {

    /**
     * 根据社区id列出活动预览
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
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId);

    /**
     * 列出两委成员列表
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @return 两委成员列表
     */
    ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize);

    /**
     * 履职信息
     *
     * @param communityId 社区id
     * @param id 个人id
     * @return 履职信息
     */
    ResponseEntity<ResponseResult<?>> listPersonalInfo(Long communityId, long id);

    /**
     * 列出为民服务清单
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

    //TODO 测试
    ResponseEntity<ResponseResult<?>> saveContent(int id);
}
