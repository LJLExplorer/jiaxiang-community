package com.jiaxiang.content.controller;

import com.jiaxiang.content.service.CommuniyuService;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.community.vos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.COMMUNITY_URL_PREFIX;

@Slf4j
@RestController
@RequestMapping(COMMUNITY_URL_PREFIX)
public class CommunityController {

    @Autowired
    private CommuniyuService communiyuService;

    /**
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    @GetMapping("/grid_management")
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        List<GridVO> gridVOList = communiyuService.listGridManagement(communityId);
        return ResponseWrapper.success(gridVOList);
    }

    /**
     * 列出两委成员列表
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 两委成员列表
     */
    @GetMapping("/list_committees_members")
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize) {
        Integer total = communiyuService.getCommitteesMembersCount();
        List<CommitteesMemberVO> committeesMemberVOList = communiyuService.listCommitteesMembers(pageNum, pageSize);
        return ResponseWrapper.successWithPage(committeesMemberVOList, pageNum, pageSize, total, (total + pageSize - 1) / pageSize);
    }

    /**
     * 履职信息
     *
     * @param communityId 社区id
     * @param id          个人id
     * @return 履职信息
     */
    @GetMapping("/personal_info")
    public ResponseEntity<ResponseResult<?>> listPersonalInfo(Long communityId, long id) {
        CommitteesMemberVO committeesMemberVO = communiyuService.listPersonalInfo(id);
        return ResponseWrapper.success(committeesMemberVO);
    }

    /**
     * 列出为人民服务列表
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 为人民服务列表
     */
    @GetMapping("/list_serve_people")
    public ResponseEntity<ResponseResult<?>> listServePeople(int pageNum, int pageSize) {
        Integer total = communiyuService.getServerPeopleCount();
        List<ServePeopleInfoVO> servePeopleInfoVOList = communiyuService.listServePeople(pageNum, pageSize);
        return ResponseWrapper.successWithPage(servePeopleInfoVOList, pageNum, pageSize, total, (total + pageSize - 1) / pageSize);
    }

    @GetMapping("/serve_people_info")
    public ResponseEntity<ResponseResult<?>> listServePeopleInfo(int id) {
        ServePeopleInfoVO servePeopleInfoVO = communiyuService.listServePeopleInfo(id);
        return ResponseWrapper.success(servePeopleInfoVO);
    }

    @GetMapping("/list_matters")
    public ResponseEntity<ResponseResult<?>> listMatters(Long communityId, int pageNum, int pageSize) {
        Integer total = communiyuService.getMattersCount();
        List<GuideCategoryVO> guideCategoryVOList = communiyuService.listMatters(communityId, pageNum, pageSize);
        return ResponseWrapper.successWithPage(guideCategoryVOList, pageNum, pageSize, total, (total + pageSize - 1) / pageSize);
    }

    @GetMapping("/community_honor")
    public ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize) {
        Integer total = communiyuService.getHonorCount();
        List<CommunityHonorVO> communityHonorVOList = communiyuService.communityHonor(communityId, pageNum, pageSize);
        return ResponseWrapper.successWithPage(communityHonorVOList, pageNum, pageSize, total, (total + pageSize - 1) / pageSize);
    }

    @GetMapping("/proof_documents")
    public ResponseEntity<ResponseResult<?>> proofDocuments(Long communityId, int pageNum, int pageSize) {
        Integer total = communiyuService.getProofDocumentsCount();
        List<ProofDocumentsPreviewVO> proofDocumentsPreviewVOList = communiyuService.proofDocuments(communityId, pageNum, pageSize);
        return ResponseWrapper.successWithPage(proofDocumentsPreviewVOList, pageNum, pageSize, total, (total + pageSize - 1) / pageSize);
    }

    @GetMapping("/proof_info")
    public ResponseEntity<ResponseResult<?>> proofInfo(int id) {
        ProofDocumentsDetailDO proofDocumentsDetailDO = communiyuService.proofInfo(id);
        return ResponseWrapper.success(proofDocumentsDetailDO);
    }
}
