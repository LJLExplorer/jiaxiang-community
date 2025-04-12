package com.jiaxiang.content.mapper;

import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.community.vos.CommitteesMemberVO;
import com.jiaxiang.model.community.vos.ServePeopleInfoVO;
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

    List<ServePeopleInfoVO> listServePeople(int pageNum, int pageSize);
}
