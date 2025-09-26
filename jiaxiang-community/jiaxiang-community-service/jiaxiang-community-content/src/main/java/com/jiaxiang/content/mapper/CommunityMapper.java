package com.jiaxiang.content.mapper;

import com.jiaxiang.model.community.dos.*;
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

    int addPersonalInfo(StaffInfoDO staffInfoDO);

    int deletePersonalInfo(Long id);

    int updatePersonalInfo(StaffInfoDO staffInfoDO);

    Integer getServerPeopleCount();

    List<ServePeopleInfoVO> listServePeople(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    ServePeopleInfoVO listServePeopleInfo(int id);

    Integer getMattersCount();

    List<GuideCategoryVO> listMatters(@Param("communityId") Long communityId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Integer getHonorCount();

    List<CommunityHonorVO> communityHonor(@Param("communityId") Long communityId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

    Integer getProofDocumentsCount();

    List<ProofDocumentsPreviewVO> proofDocuments(@Param("communityId") Long communityId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

    ProofDocumentsDetailDO proofInfo(Long id);

    Integer addGridManagement(GridDO gridDO);

    Integer updateGridManagement(GridDO gridDO);

    Integer deleteGridManagement(Long id);

    int addServePeopleInfo(ServePeopleInfoDO servePeopleInfoDO);

    int updateServePeopleInfo(ServePeopleInfoDO servePeopleInfoDO);

    String getImageByIdServePersonalInfo(Long id);

    int deleteServePeopleInfo(Long id);

    int updateCommunityHonor(CommunityHonorDO communityHonorDO);

    int addCommunityHonor(CommunityHonorDO communityHonorDO);

    int deleteCommunityHonor(Long id);

    Integer addProofInfo(ProofDocumentsDO proofDocumentsDO);

    Integer deleteProofInfoById(Long id);
}
