package com.jiaxiang.content.service;

import com.jiaxiang.model.community.vos.CommitteesMemberVO;
import com.jiaxiang.model.community.vos.CommunityProfileVO;
import com.jiaxiang.model.community.vos.GridVO;
import com.jiaxiang.model.content.dos.ArticleFileDO;
import com.jiaxiang.model.content.vos.ContentVO;

import java.util.List;

public interface CommuniyuService {

    /**
     * 网格管理
     * @param communityId 社区id
     * @return 网格管理
     */
    public List<GridVO> listGridManagement(Long communityId);

    /**
     * 列出两委成员
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CommitteesMemberVO> listCommitteesMembers(int pageNum, int pageSize);

    /**
     * 得到员工总数
     * @return
     */
    Integer getCommitteesMembersCount();
}
