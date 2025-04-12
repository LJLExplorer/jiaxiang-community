package com.jiaxiang.content.controller;

import com.jiaxiang.content.service.CommuniyuService;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.community.vos.CommitteesMemberVO;
import com.jiaxiang.model.community.vos.GridVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jiaxiang.model.common.constant.ApiRouterConsts.COMMUNITY_URL_PREFIX;

@Slf4j
@RestController
@RequestMapping(COMMUNITY_URL_PREFIX)
public class CommunityController {

    @Autowired
    private CommuniyuService communiyuService;

    /**
     * 履职信息
     *
     * @param communityId 社区id
     * @param id          id
     * @return 履职信息
     */
    @GetMapping("/list_personal_info")
    public ResponseEntity<ResponseResult<?>> listPersonalInfoById(Long communityId, Long id) {
//        Todo 查询
        return ResponseWrapper.success(null);
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

    /**
     * 列出两委成员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list_committees_members")
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize) {
        Integer total = communiyuService.getCommitteesMembersCount();
        List<CommitteesMemberVO> committeesMemberVOList = communiyuService.listCommitteesMembers(pageNum, pageSize);
        return ResponseWrapper.successWithPage(committeesMemberVOList, pageNum, pageSize, total, total / pageSize);
    }
}
