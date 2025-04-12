package com.jiaxiang.content.mapper;

import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.community.vos.CommitteesMemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityMapper {

    List<CommunityDO> listAllCommunity();

    Integer getCommitteesMembersCount();

    List<CommitteesMemberVO> listCommitteesMembers(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    CommitteesMemberVO listPersonalInfo(long id);
}
