package com.jiaxiang.content.service;

import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.community.dos.CommunityHonorDO;
import com.jiaxiang.model.community.dos.ItemListDO;
import com.jiaxiang.model.community.dtos.*;
import com.jiaxiang.model.community.vos.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommuniyuService {

    /**
     * 网格管理
     *
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
     *
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
     *
     * @return 总数
     */
    Integer getServerPeopleCount();

    /**
     * 列出为民服务清单
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 列出为民服务清单
     */
    List<ServePeopleInfoVO> listServePeople(int pageNum, int pageSize);

    ServePeopleInfoVO listServePeopleInfo(int id);

    Integer getMattersCount();

    List<GuideCategoryVO> listMatters(Long communityId, int pageNum, int pageSize);

    Integer getHonorCount();

    List<CommunityHonorVO> communityHonor(Long communityId, int pageNum, int pageSize);

    Integer getProofDocumentsCount();

    List<ProofDocumentsPreviewVO> proofDocuments(Long communityId, int pageNum, int pageSize);

    ProofDocumentsDetailDO proofInfo(Long id);

    /**
     * 根据id查事项清单
     *
     * @param id
     * @return
     */
    long getMattersById(String id);

    /**
     * 根据id删除事项清单
     *
     * @param id
     * @return
     */
    long deleteMattersById(String id);

    ResponseEntity<ResponseResult<?>> addGridManagement(GridDTO gridDTO);

    ResponseEntity<ResponseResult<?>> updateGridManagement(GridDTO gridDTO);

    ResponseEntity<ResponseResult<?>> deleteGridManagement(Long id);

    ResponseEntity<ResponseResult<?>> addServePeopleInfo(ServePeopleInfoDTO servePeopleInfoDTO);

    ResponseEntity<ResponseResult<?>> updateServePeopleInfo(ServePeopleInfoDTO servePeopleInfoDTO);

    String getImageByIdServePersonalInfo(Long id);

    ResponseEntity<ResponseResult<?>> deleteServePeopleInfo(Long id);

    ResponseEntity<ResponseResult<?>> addPersonalInfo(StaffInfoDTO staffInfoDTO);

    ResponseEntity<ResponseResult<?>> deletePersonalInfo(Long id);

    ResponseEntity<ResponseResult<?>> updatePersonalInfo(StaffInfoDTO staffInfoDTO);

    ResponseEntity<ResponseResult<?>> updateCommunityHonor(CommunityHonorDTO communityHonorDTO);

    ResponseEntity<ResponseResult<?>> addCommunityHonor(CommunityHonorDTO communityHonorDTO);

    ResponseEntity<ResponseResult<?>> deleteCommunityHonor(Long id);

    void addProofInfo(Long communityId, ProofDocumentsDTO proofDocumentsDTO);

    void deleteProofInfoById(Long id);

    void save_item_content(ItemListDO itemListDO);
}
