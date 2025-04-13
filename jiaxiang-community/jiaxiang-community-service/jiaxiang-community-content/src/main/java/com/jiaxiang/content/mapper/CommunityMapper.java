package com.jiaxiang.content.mapper;

import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.community.vos.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityMapper {

    List<CommunityDO> listAllCommunity();

    Integer getCommitteesMembersCount();

    List<CommitteesMemberVO> listCommitteesMembers(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    CommitteesMemberVO listPersonalInfo(long id);

    Integer getServerPeopleCount();

    List<ServePeopleInfoVO> listServePeople(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    ServePeopleInfoVO listServePeopleInfo(int id);

    Integer getMattersCount();

    List<GuideCategoryVO> listMatters(@Param("communityId") Long communityId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Integer getHonorCount();

    List<CommunityHonorVO> communityHonor(@Param("communityId") Long communityId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

    Integer getProofDocumentsCount();

    List<ProofDocumentsPreviewVO> proofDocuments(@Param("communityId") Long communityId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

    ProofDocumentsDetailDO proofInfo(int id);
}
