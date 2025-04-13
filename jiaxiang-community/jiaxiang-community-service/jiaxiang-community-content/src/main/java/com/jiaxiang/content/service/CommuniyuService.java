package com.jiaxiang.content.service;

import com.jiaxiang.model.community.vos.CommitteesMemberVO;
import com.jiaxiang.model.community.vos.CommunityProfileVO;
import com.jiaxiang.model.community.vos.GridVO;
import com.jiaxiang.model.community.vos.ServePeopleInfoVO;
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
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 两委成员列表
     */
    List<CommitteesMemberVO> listCommitteesMembers(int pageNum, int pageSize);

    /**
     * 得到员工总数
     * @return 总数
     */
    Integer getCommitteesMembersCount();

    /**
     * 履职信息
     *
     * @param id 个人id
     * @return 履职信息
     */
    CommitteesMemberVO listPersonalInfo(long id);

    /**
     * 得到为民服务数量
     * @return 总数
     */
    Integer getServerPeopleCount();

    /**
     * 列出为民服务清单
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 列出为民服务清单
     */
    List<ServePeopleInfoVO> listServePeople(int pageNum, int pageSize);

    ServePeopleInfoVO listServePeopleInfo(int id);
}
