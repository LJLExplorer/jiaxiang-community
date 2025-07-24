package com.jiaxiang.portal.controller;

import cn.hutool.core.lang.UUID;
import com.jiaxiang.common.exception.CustomException;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import com.jiaxiang.model.community.dtos.GridDTO;
import com.jiaxiang.model.community.dtos.ServePeopleInfoDTO;
import com.jiaxiang.model.community.dtos.StaffInfoDTO;
import com.jiaxiang.portal.service.PortalService;
import com.jiaxiang.utils.AsyncTaskExecutor;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.JIA_HE_URL_PREFIX;

@Slf4j
@RestController
@RequestMapping(JIA_HE_URL_PREFIX)
public class JiaHePortalController {

    private final PortalService portalService;

    private final AsyncTaskExecutor asyncTaskExecutor;


    public JiaHePortalController(PortalService portalService, AsyncTaskExecutor asyncTaskExecutor) {
        this.portalService = portalService;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }


    /**
     * 列出社区活动预览
     *
     * @param communityId 社区id
     * @return 预览图
     */
    @GetMapping("/list_community_activities")
    public ResponseEntity<ResponseResult<?>> listCommunityActivities(Long communityId) {
        return portalService.listCommunityActivities(communityId);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @GetMapping("/community_activity_detail")
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(@RequestParam("communityId") Long communityId, @RequestParam("id") Long activityId) {
        return portalService.listCommunityActivityDetail(communityId, activityId);
    }


    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    @GetMapping("/community_profile")
    public ResponseEntity<ResponseResult<?>> listJiaHeCommunityProfile(Long communityId) {
        return portalService.listCommunityProfile(communityId);
    }

    /**
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    @GetMapping("/grid_management")
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        return portalService.listGridManagement(communityId);
    }


    /**
     * 列出两委成员列表
     *
     * @param communityId 社区id,可能员工仅属于某个社区
     * @param pageNum     当前页
     * @param pageSize    页大小
     * @return 两委成员列表
     */
    @GetMapping("/list_committees_members")
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(Long communityId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "1000") Integer pageSize) {
        return portalService.listCommitteesMembers(pageNum, pageSize);
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
        return portalService.listPersonalInfo(communityId, id);
    }

    /**
     * 列出为人民服务列表
     *
     * @param communityId 社区id,可能员工仅属于某个社区
     * @param pageNum     当前页
     * @param pageSize    页大小
     * @return 为人民服务列表
     */
    @GetMapping("/list_serve_people")
    public ResponseEntity<ResponseResult<?>> listServePeople(Long communityId, int pageNum, int pageSize) {
        return portalService.listServePeople(pageNum, pageSize);
    }

    /**
     * 为人民服务
     *
     * @param communityId
     * @param id
     * @return
     */
    @GetMapping("/serve_people_info")
    public ResponseEntity<ResponseResult<?>> servePeopleInfo(Long communityId, int id) {
        return portalService.listServePeopleInfo(id);
    }


    @GetMapping("/list_matters")
    public ResponseEntity<ResponseResult<?>> listMatters(Long communityId, int pageNum, int pageSize) {
        return portalService.listMatters(communityId, pageNum, pageSize);
    }

    @GetMapping("/community_honor")
    public ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize) {
        return portalService.communityHonor(communityId, pageNum, pageSize);
    }

    @GetMapping("/proof_documents")
    public ResponseEntity<ResponseResult<?>> proofDocuments(Long communityId, int pageNum, int pageSize) {
        return portalService.proofDocuments(communityId, pageNum, pageSize);
    }


    @GetMapping("/proof_info")
    public ResponseEntity<ResponseResult<?>> proofInfo(Long communityId, int id) {
        return portalService.proofInfo(id);
    }

    // 测试接口
    @PostMapping("/save_content")
    public ResponseEntity<ResponseResult<?>> saveContent(Long communityId, int id) {
        return portalService.saveContent(id);
    }


    /**
     * 上传多个文件
     *
     * @param files 多个文件
     * @return
     */
    @PostMapping("/upload_file")
    public ResponseEntity<ResponseResult<?>> uploadFile(@RequestParam("file") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "文件上传失败,文件不能为空!");
        }
        List<String> urlPaths = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String urlPath = portalService.uploadFile(file);
                urlPaths.add(urlPath);
            }
        }
        return ResponseWrapper.success(urlPaths);
    }

    @PutMapping("/update_community_activity_detail/{id}")
    public ResponseEntity<ResponseResult<?>> updateCommunityActivityDetail(Long communityId, @PathParam("id") Long id, @RequestBody ActivityDetailDto activityDetailDto) {
        return portalService.updateCommunityActivityDetail(communityId, id, activityDetailDto);
    }

    /**
     * 添加事项清单
     *
     * @param communityId
     * @param file
     * @return
     */
    @PostMapping("/add_item_matters")
    public ResponseEntity<ResponseResult<?>> addItemMatters(Long communityId, @RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "文件上传失败,文件不能为空!");
        }
        asyncTaskExecutor.runAsync(() -> {
            String urlPath = portalService.uploadFile(file);
            log.info("MD文件上传存储地址: {}", urlPath);
        });
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        String name = file.getOriginalFilename();
        System.out.println(content + "name : " + name);
        String id = UUID.randomUUID().toString();
//        portalService.getItemContentAsync(content, name, id);
        portalService.saveItemContent(content, name, id);
        return ResponseWrapper.success("添加事项" + name + "成功");
    }

    @DeleteMapping("/delete_matters")
    public ResponseEntity<ResponseResult<?>> deleteMattersById(Long communityId, String id) {
        return portalService.deleteMattersById(id);
    }

    /**
     * 网格
     */
    @PostMapping("/add_grid_management")
    public ResponseEntity<ResponseResult<?>> addGridManagement(@RequestBody GridDTO gridDTO) {
        return portalService.addGridManagement(gridDTO);
    }

    @PutMapping("/update_grid_management")
    public ResponseEntity<ResponseResult<?>> updateGridManagement(@RequestBody GridDTO gridDTO) {
        return portalService.updateGridManagement(gridDTO);
    }

    @DeleteMapping("/delete_grid_management")
    public ResponseEntity<ResponseResult<?>> deleteGridManagement(@RequestParam Long id) {
        return portalService.deleteGridManagement(id);
    }

    /**
     履职
     */
    @PostMapping("/add_personal_info")
    public ResponseEntity<ResponseResult<?>> addPersonalInfo(@RequestBody StaffInfoDTO staffInfoDTO){
        return portalService.addPersonalInfo(staffInfoDTO);
    }
    @PutMapping("/update_personal_info")
    public ResponseEntity<ResponseResult<?>> updatePersonalInfo(@RequestBody StaffInfoDTO staffInfoDTO){
        return portalService.updatePersonalInfo(staffInfoDTO);
    }
    @DeleteMapping("/delete_personal_info")
    public ResponseEntity<ResponseResult<?>> deletePersonalInfo(@RequestParam Long id){
        return portalService.deletePersonalInfo(id);
    }

    /**
     为人民服务
     */

    @PostMapping("/add_serve_people_info")
    public ResponseEntity<ResponseResult<?>> addServePeopleInfo(@RequestBody ServePeopleInfoDTO servePeopleInfoDTO){
        return portalService.addServePeopleInfo(servePeopleInfoDTO);
    }
    @PutMapping("/update_serve_people_info")
    public ResponseEntity<ResponseResult<?>> updateServePeopleInfo(@RequestBody ServePeopleInfoDTO servePeopleInfDTO){
        return portalService.updateServePeopleInfo(servePeopleInfDTO);
    }
    @DeleteMapping("/delete_serve_people_info")
    public ResponseEntity<ResponseResult<?>> deleteServePeopleInfo(@RequestParam Long id){
        return portalService.deleteServePeopleInfo(id);
    }
}
