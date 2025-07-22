package com.jiaxiang.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jiaxiang.content.mapper.CommunityMapper;
import com.jiaxiang.content.service.CommuniyuService;
import com.jiaxiang.model.community.dos.CommunityDO;
import com.jiaxiang.model.community.dos.ItemListDO;
import com.jiaxiang.model.community.dtos.LawItemDTO;
import com.jiaxiang.model.community.vos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jiaxiang.utils.MdUtil.mdSting2Html;

@Slf4j
@Service
@Transactional
public class CommunityServiceImpl implements CommuniyuService {

    private final CommunityMapper communityMapper;

    private final MongoTemplate mongoTemplate;

    public CommunityServiceImpl(CommunityMapper communityMapper, MongoTemplate mongoTemplate) {
        this.communityMapper = communityMapper;
        this.mongoTemplate = mongoTemplate;
    }


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
        Query query = new Query();
        long count = mongoTemplate.count(query, ItemListDO.class);
//        return communityMapper.getMattersCount();
        return (int) count;
    }

    @Override
    public List<GuideCategoryVO> listMatters(Long communityId, int pageNum, int pageSize) {
        // 改成MongoDB查询
        Query query = new Query();
        query.skip((long) (pageNum - 1) * pageSize).limit(pageSize);
        List<ItemListDO> itemListDoS = mongoTemplate.find(query, ItemListDO.class);
        List<GuideCategoryVO> list = itemListDoS.stream().map(itemListDO -> {
            GuideCategoryVO guideCategoryVO = new GuideCategoryVO();
            BeanUtil.copyProperties(itemListDO, guideCategoryVO);
            guideCategoryVO.setTitle(itemListDO.getFileName());
            String mdContent = convertItemListDo2Markdown(itemListDO);
            log.info(mdContent);
            String mdSting2Html = mdSting2Html(mdContent);
            System.out.println();
            log.info("html" + mdSting2Html);
            guideCategoryVO.setContent(mdSting2Html);
            return guideCategoryVO;
        }).toList();
        return list;
//        return communityMapper.listMatters(communityId, (pageNum - 1) * pageSize, pageSize);
    }

    public String convertItemListDo2Markdown(ItemListDO itemListDO) {
        StringBuilder sb = new StringBuilder();

        List<LawItemDTO> lawItems = itemListDO.getLawItems();
        for (int i = 0; i < lawItems.size(); i++) {
            LawItemDTO item = lawItems.get(i);

            // 输出标题，带序号，加粗
            sb.append(i + 1).append(". ").append(item.getTitle()).append("**").append("\n");

            // 输出依据
            List<String> basisList = item.getBasisList();
            if (basisList != null && !basisList.isEmpty()) {
                sb.append("依据：").append("\n");
                for (String basis : basisList) {
                    sb.append(basis).append("\n");
                }
            }

            sb.append("\n"); // 每条之间加空行
        }

        return sb.toString();
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
