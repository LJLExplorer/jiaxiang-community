package com.jiaxiang.apis.Content;

import com.jiaxiang.apis.Content.fallback.IContentClientFallback;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.community.dtos.CommunityHonorDTO;
import com.jiaxiang.model.community.dtos.GridDTO;
import com.jiaxiang.model.community.dtos.ServePeopleInfoDTO;
import com.jiaxiang.model.community.dtos.StaffInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.COMMUNITY_URL_PREFIX;
import static com.jiaxiang.model.common.constant.ApiRouterConstant.CONTENT_URL_PREFIX;

@FeignClient(value = "jiaxiang-content", fallback = IContentClientFallback.class)
public interface IContentClient {

    @PostMapping(CONTENT_URL_PREFIX + "/save")
    public ResponseEntity<ResponseResult<?>> saveContent();

    @GetMapping(CONTENT_URL_PREFIX + "/community_profile")
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(@RequestParam Long communityId);

    @GetMapping(COMMUNITY_URL_PREFIX + "/grid_management")
    public ResponseEntity<ResponseResult<?>> listGridManagement(@RequestParam Long communityId);

    @PutMapping(COMMUNITY_URL_PREFIX + "/update_grid_management")
    ResponseEntity<ResponseResult<?>> updateGridManagement(@RequestBody GridDTO gridDO);

    @PostMapping(COMMUNITY_URL_PREFIX + "/add_grid_management")
    ResponseEntity<ResponseResult<?>> addGridManagement(@RequestBody GridDTO gridDO);

    @DeleteMapping(COMMUNITY_URL_PREFIX + "/delete_grid_management")
    ResponseEntity<ResponseResult<?>> deleteGridManagement(@RequestParam Long id);

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_committees_members")
    ResponseEntity<ResponseResult<?>> listCommitteesMembers(@RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/personal_info")
    ResponseEntity<ResponseResult<?>> listPersonalInfo(@RequestParam Long communityId, @RequestParam long id);

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_serve_people")
    ResponseEntity<ResponseResult<?>> listServePeople(@RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/serve_people_info")
    ResponseEntity<ResponseResult<?>> listServePeopleInfo(@RequestParam int id);

    @PostMapping(COMMUNITY_URL_PREFIX + "/add_serve_people_info")
    ResponseEntity<ResponseResult<?>> addServePeopleInfo(@RequestBody ServePeopleInfoDTO servePeopleInfoDTO);

    @PutMapping(COMMUNITY_URL_PREFIX + "/update_serve_people_info")
    ResponseEntity<ResponseResult<?>> updateServePeopleInfo(@RequestBody ServePeopleInfoDTO servePeopleInfDTO);

    @DeleteMapping(COMMUNITY_URL_PREFIX + "/delete_serve_people_info")
    ResponseEntity<ResponseResult<?>> deleteServePeopleInfo(@RequestParam Long id);

    @PostMapping(COMMUNITY_URL_PREFIX + "/add_personal_info")
    ResponseEntity<ResponseResult<?>> addPersonalInfo(@RequestBody StaffInfoDTO staffInfoDTO);

    @PutMapping(COMMUNITY_URL_PREFIX + "/update_personal_info")
    ResponseEntity<ResponseResult<?>> updatePersonalInfo(@RequestBody StaffInfoDTO staffInfoDTO);

    @DeleteMapping(COMMUNITY_URL_PREFIX + "/delete_personal_info")
    ResponseEntity<ResponseResult<?>> deletePersonalInfo(@RequestParam Long id);

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_matters")
    ResponseEntity<ResponseResult<?>> listMatters(@RequestParam Long communityId, @RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/community_honor")
    ResponseEntity<ResponseResult<?>> communityHonor(@RequestParam Long communityId, @RequestParam int pageNum, @RequestParam int pageSize);

    @PostMapping(COMMUNITY_URL_PREFIX + "/add_community_honor")
    ResponseEntity<ResponseResult<?>> addCommunityHonor(@RequestBody CommunityHonorDTO communityHonorDTO);

    @PutMapping(COMMUNITY_URL_PREFIX + "/update_community_honor")
    ResponseEntity<ResponseResult<?>> updateCommunityHonor(@RequestBody CommunityHonorDTO communityHonorDTO);

    @DeleteMapping(COMMUNITY_URL_PREFIX + "/delete_community_honor")
    ResponseEntity<ResponseResult<?>> deleteCommunityHonor(@RequestParam Long id);

    @GetMapping(COMMUNITY_URL_PREFIX + "/proof_documents")
    ResponseEntity<ResponseResult<?>> proofDocuments(@RequestParam Long communityId, @RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/proof_info")
    ResponseEntity<ResponseResult<?>> proofInfo(@RequestParam int id);

    @PostMapping(value = CONTENT_URL_PREFIX + "/upload_file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ResponseResult<?>> uploadFile(@RequestPart("file") MultipartFile file);

    @DeleteMapping(COMMUNITY_URL_PREFIX + "/delete_matters")
    ResponseEntity<ResponseResult<?>> deleteMattersById(@RequestParam String id);

}
