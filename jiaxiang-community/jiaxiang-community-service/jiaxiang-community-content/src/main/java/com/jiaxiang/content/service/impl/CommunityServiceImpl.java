package com.jiaxiang.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jiaxiang.common.exception.CustomException;
import com.jiaxiang.content.mapper.CommunityMapper;
import com.jiaxiang.content.service.CommuniyuService;
import com.jiaxiang.file.service.impl.MinioFileStorageService;
import com.jiaxiang.model.common.constant.CacheConstant;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import com.jiaxiang.model.community.dos.*;
import com.jiaxiang.model.community.dtos.*;
import com.jiaxiang.model.community.vos.*;
import com.jiaxiang.utils.AsyncTaskExecutor;
import com.jiaxiang.utils.CacheUtils;
import com.jiaxiang.utils.RedisUtils;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.jiaxiang.utils.MdUtil.mdSting2Html;

@Slf4j
@Service
@Transactional
public class CommunityServiceImpl implements CommuniyuService {

    private final CommunityMapper communityMapper;

    private final MongoTemplate mongoTemplate;

    private final MinioFileStorageService minioFileStorageService;

    private final AsyncTaskExecutor asyncTaskExecutor;

    private final CacheUtils cacheUtils;

    public CommunityServiceImpl(CommunityMapper communityMapper, MongoTemplate mongoTemplate, MinioFileStorageService minioFileStorageService, AsyncTaskExecutor asyncTaskExecutor, RedisUtils redisUtils, CacheUtils cacheUtils) {
        this.communityMapper = communityMapper;
        this.mongoTemplate = mongoTemplate;
        this.minioFileStorageService = minioFileStorageService;
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.cacheUtils = cacheUtils;
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

    @Override
    public ResponseEntity<ResponseResult<?>> addGridManagement(GridDTO gridDTO) {
        GridDO gridDO = new GridDO();
        BeanUtil.copyProperties(gridDTO, gridDO);
        Integer count = communityMapper.addGridManagement(gridDO);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST, "更新失败，未找到" + gridDTO.getCommunityCn() + "社区信息");
        }
        return ResponseWrapper.success("添加成功!");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updateGridManagement(GridDTO gridDTO) {
        GridDO gridDO = new GridDO();
        BeanUtil.copyProperties(gridDTO, gridDO);
        Integer count = communityMapper.updateGridManagement(gridDO);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST, "更新失败，未找到" + gridDTO.getCommunityCn() + "社区信息");
        }
        return ResponseWrapper.success("更新" + gridDTO.getCommunityCn() + "内容成功!");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteGridManagement(Long id) {
        Integer count = communityMapper.deleteGridManagement(id);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST, "删除失败，未找到待删除社区信息");
        }
        return ResponseWrapper.success("删除社区信息成功!");
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

    @Override
    public ResponseEntity<ResponseResult<?>> addPersonalInfo(StaffInfoDTO staffInfoDTO) {
        if (staffInfoDTO.getImages() != null && !minioFileStorageService.checkMinioFileExists(staffInfoDTO.getImages())) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "图片不存在");
        }
        StaffInfoDO staffInfoDO = new StaffInfoDO();
        BeanUtil.copyProperties(staffInfoDTO, staffInfoDO);
        int count = communityMapper.addPersonalInfo(staffInfoDO);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR, "添加失败，请稍后重试！");
        }
        return ResponseWrapper.success("添加" + staffInfoDTO.getName() + "信息成功");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deletePersonalInfo(Long id) {
        int count = communityMapper.deletePersonalInfo(id);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "删除失败，员工不存在！");
        }
        return ResponseWrapper.success("删除成功！");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> updatePersonalInfo(StaffInfoDTO staffInfoDTO) {
        if (staffInfoDTO.getImages() != null && !minioFileStorageService.checkMinioFileExists(staffInfoDTO.getImages())) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "更新失败，图片不存在！");
        }
        StaffInfoDO staffInfoDO = new StaffInfoDO();
        BeanUtil.copyProperties(staffInfoDTO, staffInfoDO);
        int count = communityMapper.updatePersonalInfo(staffInfoDO);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "更新失败，员工不存在！");
        }
        return ResponseWrapper.success("更新成功！");
    }


    @Override
    public ResponseEntity<ResponseResult<?>> addServePeopleInfo(ServePeopleInfoDTO servePeopleInfoDTO) {
        ServePeopleInfoDO servePeopleInfoDO = new ServePeopleInfoDO();
        BeanUtil.copyProperties(servePeopleInfoDTO, servePeopleInfoDO);
        cacheUtils.deleteCachePage(CacheConstant.SERVER_PEOPLE_PAGE_KEY);
        int count = communityMapper.addServePeopleInfo(servePeopleInfoDO);
        cacheUtils.delayDeleteCachePage(CacheConstant.SERVER_PEOPLE_PAGE_KEY);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "添加失败，请稍后重试");
        }
        return ResponseWrapper.success("添加" + servePeopleInfoDTO.getTitle() + "成功！");
    }

    /**
     * 更新为人民服务
     *
     * @param servePeopleInfoDTO
     * @return
     */
    @Override
    public ResponseEntity<ResponseResult<?>> updateServePeopleInfo(ServePeopleInfoDTO servePeopleInfoDTO) {
        ServePeopleInfoDO servePeopleInfoDO = new ServePeopleInfoDO();
        BeanUtil.copyProperties(servePeopleInfoDTO, servePeopleInfoDO);
        cacheUtils.deleteCachePage(CacheConstant.SERVER_PEOPLE_PAGE_KEY);
//        log.info("执行sql开始时间{}", System.currentTimeMillis());
        int count = communityMapper.updateServePeopleInfo(servePeopleInfoDO);
//        log.info("执行sql结束时间{}", System.currentTimeMillis());
        cacheUtils.delayDeleteCachePage(CacheConstant.SERVER_PEOPLE_PAGE_KEY);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "更新失败，被更新数据不存在");
        }
        return ResponseWrapper.success("更新成功！");
    }

    @Override
    public String getImageByIdServePersonalInfo(Long id) {
        return communityMapper.getImageByIdServePersonalInfo(id);
    }


    public void deleteImageByIdServePersonalInfoAsync(Long id) {
        asyncTaskExecutor.runAsync(() -> {
            minioFileStorageService.delete(getImageByIdServePersonalInfo(id));
        });
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteServePeopleInfo(Long id) {
//        deleteImageByIdServePersonalInfoAsync(id);
        cacheUtils.deleteCachePage(CacheConstant.SERVER_PEOPLE_PAGE_KEY);
        int count = communityMapper.deleteServePeopleInfo(id);
        cacheUtils.delayDeleteCachePage(CacheConstant.SERVER_PEOPLE_PAGE_KEY);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "待删除信息不存在");
        }
        return ResponseWrapper.success("删除成功!");
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
        return cacheUtils.getCacheByPage(pageNum, pageSize, CacheConstant.SERVER_PEOPLE_PAGE_KEY + pageNum + ":" + pageSize,
                10L, TimeUnit.MINUTES, ServePeopleInfoVO.class, communityMapper::listServePeople);
//        return communityMapper.listServePeople((pageNum - 1) * pageSize, pageSize);
    }

    @Override
    public ServePeopleInfoVO listServePeopleInfo(int id) {
        return cacheUtils.getCache(id, CacheConstant.SERVER_PEOPLE_INFO_KEY, ServePeopleInfoVO.class,
                communityMapper::listServePeopleInfo, 10L, TimeUnit.MINUTES);
//        return communityMapper.listServePeopleInfo(id);
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
        return itemListDoS.stream().map(itemListDO -> {
            GuideCategoryVO guideCategoryVO = new GuideCategoryVO();
            BeanUtil.copyProperties(itemListDO, guideCategoryVO);
            guideCategoryVO.setTitle(itemListDO.getFileName());
            String mdContent = convertItemListDo2Markdown(itemListDO);
            String mdSting2Html = mdSting2Html(mdContent);
            guideCategoryVO.setContent(mdSting2Html);
            return guideCategoryVO;
        }).toList();
//        return communityMapper.listMatters(communityId, (pageNum - 1) * pageSize, pageSize);
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
    public ResponseEntity<ResponseResult<?>> updateCommunityHonor(CommunityHonorDTO communityHonorDTO) {
        CommunityHonorDO communityHonorDO = new CommunityHonorDO();
        BeanUtil.copyProperties(communityHonorDTO, communityHonorDO);
        int count = communityMapper.updateCommunityHonor(communityHonorDO);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "社区荣誉不存在！");
        }
        return ResponseWrapper.success("更新社区荣誉成功");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> addCommunityHonor(CommunityHonorDTO communityHonorDTO) {
        CommunityHonorDO communityHonorDO = new CommunityHonorDO();
        BeanUtil.copyProperties(communityHonorDTO, communityHonorDO);
        int count = communityMapper.addCommunityHonor(communityHonorDO);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "添加社区荣誉失败，请稍后重试！");
        }
        return ResponseWrapper.success("添加社区荣誉成功");
    }

    @Override
    public ResponseEntity<ResponseResult<?>> deleteCommunityHonor(Long id) {
        int count = communityMapper.deleteCommunityHonor(id);
        if (count <= 0) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "删除失败，没有待删除数据，请刷新后重试！");
        }
        return ResponseWrapper.success("删除成功");
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
    public ProofDocumentsDetailDO proofInfo(Long id) {
        return communityMapper.proofInfo(id);
    }

    @Override
    public void addProofInfo(Long communityId, ProofDocumentsDTO proofDocumentsDTO) {
        ProofDocumentsDO proofDocumentsDO = new ProofDocumentsDO();
        BeanUtil.copyProperties(proofDocumentsDTO, proofDocumentsDO);
        proofDocumentsDTO.setCommunityId(communityId);
        if (proofDocumentsDO.getImages() != null) {
            boolean b = minioFileStorageService.checkMinioFileExists(proofDocumentsDO.getImages());
            if (!b) {
                throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "图片不存在!");
            }
        }
        Integer flag = communityMapper.addProofInfo(proofDocumentsDO);
        if (flag < 1) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "添加证明材料失败");
        }
    }

    @Override
    public void deleteProofInfoById(Long id) {
        Integer flag = communityMapper.deleteProofInfoById(id);
        if (flag < 1) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID, "删除证明材料失败");
        }
    }

    /**
     * 根据id查事项清单
     *
     * @param id
     * @return
     */
    @Override
    public long getMattersById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.count(query, ItemListDO.class);
    }

    /**
     * 根据id删除事项清单
     *
     * @param id
     * @return
     */
    @Override
    public long deleteMattersById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        DeleteResult remove = mongoTemplate.remove(query, ItemListDO.class);
        return remove.getDeletedCount();
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

}
