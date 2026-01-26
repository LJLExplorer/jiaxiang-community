package com.jiaxiang.content.controller;

import com.jiaxiang.common.exception.CustomException;
import com.jiaxiang.content.service.CommuniyuService;
import com.jiaxiang.file.service.impl.MinioFileStorageService;
import com.jiaxiang.model.common.constant.CacheConstant;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import com.jiaxiang.model.community.dos.ItemListDO;
import com.jiaxiang.model.community.dtos.*;
import com.jiaxiang.model.community.vos.*;
import com.jiaxiang.utils.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.COMMUNITY_URL_PREFIX;

@Slf4j
@RestController
@RequestMapping(COMMUNITY_URL_PREFIX)
public class CommunityController {

    private final CommuniyuService communiyuService;

    private final MinioFileStorageService minioFileStorageService;


    public CommunityController(CommuniyuService communiyuService, MinioFileStorageService minioFileStorageService, CacheUtils cacheUtils) {
        this.communiyuService = communiyuService;
        this.minioFileStorageService = minioFileStorageService;
    }

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

    @PostMapping("/add_grid_management")
    public ResponseEntity<ResponseResult<?>> addGridManagement(@RequestBody GridDTO gridDTO) {
        return communiyuService.addGridManagement(gridDTO);
    }

    @PutMapping("/update_grid_management")
    public ResponseEntity<ResponseResult<?>> updateGridManagement(@RequestBody GridDTO gridDTO) {
        return communiyuService.updateGridManagement(gridDTO);
    }

    @DeleteMapping("/delete_grid_management")
    public ResponseEntity<ResponseResult<?>> deleteGridManagement(@RequestParam Long id) {
        return communiyuService.deleteGridManagement(id);
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

    @PostMapping("/add_personal_info")
    public ResponseEntity<ResponseResult<?>> addPersonalInfo(@RequestBody StaffInfoDTO staffInfoDTO) {
        return communiyuService.addPersonalInfo(staffInfoDTO);
    }

    @DeleteMapping("/delete_personal_info")
    public ResponseEntity<ResponseResult<?>> deletePersonalInfo(@RequestParam Long id) {
        return communiyuService.deletePersonalInfo(id);
    }

    @PutMapping("/update_personal_info")
    public ResponseEntity<ResponseResult<?>> updatePersonalInfo(@RequestBody StaffInfoDTO staffInfoDTO) {
        return communiyuService.updatePersonalInfo(staffInfoDTO);
    }

    /**
     * 添加为人民服务
     *
     * @param servePeopleInfoDTO
     * @return
     */
    @PostMapping("/add_serve_people_info")
    public ResponseEntity<ResponseResult<?>> addServePeopleInfo(@RequestBody ServePeopleInfoDTO servePeopleInfoDTO) {
        if (servePeopleInfoDTO.getImages() != null && !minioFileStorageService.checkMinioFileExists(servePeopleInfoDTO.getImages())) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "图片不存在");
        }
        return communiyuService.addServePeopleInfo(servePeopleInfoDTO);
    }

    /**
     * 更新为人民服务
     *
     * @param servePeopleInfoDTO
     * @return
     */
    @PutMapping("/update_serve_people_info")
    public ResponseEntity<ResponseResult<?>> updateServePeopleInfo(@RequestBody ServePeopleInfoDTO servePeopleInfoDTO) {
        if (servePeopleInfoDTO.getImages() != null && !minioFileStorageService.checkMinioFileExists(servePeopleInfoDTO.getImages())) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "图片不存在");
        }
        return communiyuService.updateServePeopleInfo(servePeopleInfoDTO);
    }

    /**
     * 删除为人民服务
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete_serve_people_info")
    public ResponseEntity<ResponseResult<?>> deleteServePeopleInfo(@RequestParam Long id) {
        return communiyuService.deleteServePeopleInfo(id);
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

    @DeleteMapping("/delete_matters")
    public ResponseEntity<ResponseResult<?>> deleteMatters(Long communityId, String id) {
        long count = communiyuService.deleteMattersById(id);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "找不到待删除文件");
        }
        return ResponseWrapper.success("删除" + id + "成功！");
    }

    @GetMapping("/community_honor")
    public ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize) {
        Integer total = communiyuService.getHonorCount();
        List<CommunityHonorVO> communityHonorVOList = communiyuService.communityHonor(communityId, pageNum, pageSize);
        return ResponseWrapper.successWithPage(communityHonorVOList, pageNum, pageSize, total, (total + pageSize - 1) / pageSize);
    }

    @PutMapping("/update_community_honor")
    public ResponseEntity<ResponseResult<?>> updateCommunityHonor(@RequestBody CommunityHonorDTO communityHonorDTO) {
        return communiyuService.updateCommunityHonor(communityHonorDTO);
    }

    @PostMapping("/add_community_honor")
    public ResponseEntity<ResponseResult<?>> addCommunityHonor(@RequestBody CommunityHonorDTO communityHonorDTO) {
        return communiyuService.addCommunityHonor(communityHonorDTO);
    }

    @DeleteMapping("/delete_community_honor")
    public ResponseEntity<ResponseResult<?>> deleteCommunityHonor(@RequestParam Long id) {
        return communiyuService.deleteCommunityHonor(id);
    }

    @GetMapping("/proof_documents")
    public ResponseEntity<ResponseResult<?>> proofDocuments(Long communityId, int pageNum, int pageSize) {
        Integer total = communiyuService.getProofDocumentsCount();
        List<ProofDocumentsPreviewVO> proofDocumentsPreviewVOList = communiyuService.proofDocuments(communityId, pageNum, pageSize);
        return ResponseWrapper.successWithPage(proofDocumentsPreviewVOList, pageNum, pageSize, total, (total + pageSize - 1) / pageSize);
    }

    @GetMapping("/proof_info")
    public ResponseEntity<ResponseResult<?>> proofInfo(Long id) {
        ProofDocumentsDetailDO proofDocumentsDetailDO = communiyuService.proofInfo(id);
        return ResponseWrapper.success(proofDocumentsDetailDO);
    }

    @PostMapping("/add_proof_info")
    public ResponseEntity<ResponseResult<?>> addProofInfo(Long communityId, @RequestBody ProofDocumentsDTO proofDocumentsDTO) {
        communiyuService.addProofInfo(communityId, proofDocumentsDTO);
        return ResponseWrapper.success("添加证明材料失败");
    }

    @DeleteMapping("/delete_proof_info")
    public ResponseEntity<ResponseResult<?>> deleteProofInfoById(Long id) {
        communiyuService.deleteProofInfoById(id);
        return ResponseWrapper.success("删除证明内容成功");
    }

    @PostMapping("/save_item_content")
    public ResponseEntity<ResponseResult<?>> save_item_content(@RequestBody ItemListDO itemListDO) {
        communiyuService.save_item_content(itemListDO);
        return ResponseWrapper.success("添加事项清单失败");
    }

}
