package com.jiaxiang.content.service.impl;

import com.jiaxiang.content.mapper.CommunityMapper;
import com.jiaxiang.content.service.CommuniyuService;
import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.community.vos.*;
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
            return new GridVO(communityDO.getId(), communityDO.getNameCn(), communityDO.getIcon(), meta);
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

    /**
     * 履职信息
     *
     * @param id 个人id
     * @return 履职信息
     */
    @Override
    public CommitteesMemberVO listPersonalInfo(long id) {
        return communityMapper.listPersonalInfo(id);
    }

    /**
     * 得到为民服务数量
     *
     * @return 总数
     */
    @Override
    public Integer getServerPeopleCount() {
        return communityMapper.getServerPeopleCount();
    }

    /**
     * 列出为民服务清单
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 列出为民服务清单
     */
    @Override
    public List<ServePeopleInfoVO> listServePeople(int pageNum, int pageSize) {
        return communityMapper.listServePeople((pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public ServePeopleInfoVO listServePeopleInfo(int id) {
        return communityMapper.listServePeopleInfo(id);
    }

    @Override
    public Integer getMattersCount() {
        return communityMapper.getMattersCount();
    }

    @Override
    public List<GuideCategoryVO> listMatters(Long communityId, int pageNum, int pageSize) {
        return communityMapper.listMatters(communityId, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getHonorCount() {
        return communityMapper.getHonorCount();
    }

    @Override
    public List<CommunityHonorVO> communityHonor(Long communityId, int pageNum, int pageSize) {
        return communityMapper.communityHonor(communityId, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public Integer getProofDocumentsCount() {
        return communityMapper.getProofDocumentsCount();
    }

    @Override
    public List<ProofDocumentsPreviewVO> proofDocuments(Long communityId, int pageNum, int pageSize) {
        return communityMapper.proofDocuments(communityId, (pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public ProofDocumentsDetailDO proofInfo(int id) {
        return communityMapper.proofInfo(id);
    }
}
