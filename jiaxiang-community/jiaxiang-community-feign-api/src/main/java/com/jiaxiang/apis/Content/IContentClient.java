package com.jiaxiang.apis.Content;

import com.jiaxiang.apis.Content.fallback.IContentClientFallback;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_committees_members")
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(@RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/personal_info")
    ResponseEntity<ResponseResult<?>> listPersonalInfo(@RequestParam Long communityId, @RequestParam long id);

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_serve_people")
    ResponseEntity<ResponseResult<?>> listServePeople(@RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/serve_people_info")
    ResponseEntity<ResponseResult<?>> listServePeopleInfo(@RequestParam int id);

    @GetMapping(COMMUNITY_URL_PREFIX + "/list_matters")
    ResponseEntity<ResponseResult<?>> listMatters(@RequestParam Long communityId, @RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/community_honor")
    ResponseEntity<ResponseResult<?>> communityHonor(@RequestParam Long communityId, @RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/proof_documents")
    ResponseEntity<ResponseResult<?>> proofDocuments(@RequestParam Long communityId, @RequestParam int pageNum, @RequestParam int pageSize);

    @GetMapping(COMMUNITY_URL_PREFIX + "/proof_info")
    ResponseEntity<ResponseResult<?>> proofInfo(@RequestParam int id);

    @PostMapping(value = CONTENT_URL_PREFIX + "/upload_file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ResponseResult<?>> uploadFile(@RequestPart("file") MultipartFile file);

}
