package com.jiaxiang.content.service.impl;

import com.jiaxiang.content.mapper.CommunityMapper;
import com.jiaxiang.content.service.CommuniyuService;
import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.community.vos.CommitteesMemberVO;
import com.jiaxiang.model.community.vos.GridVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommunityServiceImpl implements CommuniyuService {

    @Autowired
    private CommunityMapper communityMapper;

    /**
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    @Override
    public List<GridVO> listGridManagement(Long communityId) {
        List<CommunityDO> communityDOList = communityMapper.listAllCommunity();
        return communityDOList.stream().map(communityDO -> {
            Map<String, String> meta = new HashMap<>();
            meta.put("profile", communityDO.getDescription());
            meta.put("title", communityDO.getNameCn() + "简介");
            return new GridVO(communityDO.getNameCn(), communityDO.getIcon(), meta);
        }).toList();
    }

    /**
     * 列出两委成员
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<CommitteesMemberVO> listCommitteesMembers(int pageNum, int pageSize) {
        return communityMapper.listCommitteesMembers((pageNum - 1) * pageSize, pageSize);
//        return null;
    }

    /**
     * 得到员工总数
     *
     * @return
     */
    @Override
    public Integer getCommitteesMembersCount() {
        return communityMapper.getCommitteesMembersCount();
    }
}
