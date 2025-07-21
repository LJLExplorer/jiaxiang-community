package com.jiaxiang.portal.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.jiaxiang.apis.Activity.IActivityClient;
import com.jiaxiang.apis.Content.IContentClient;
import com.jiaxiang.file.service.FileStorageService;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.community.dos.ItemListDO;
import com.jiaxiang.model.community.dtos.LawItemDTO;
import com.jiaxiang.portal.service.PortalService;
import com.jiaxiang.utils.AsyncTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jiaxiang.model.common.constant.MQConstant.ITEM_MATTERS_ROUTING_KEY_ADD;
import static com.jiaxiang.model.common.constant.MQConstant.ITEM_MATTERS_TOPIC_EXCHANGE;

@Service
public class PortalServiceImpl implements PortalService {

    private static final Logger log = LoggerFactory.getLogger(PortalServiceImpl.class);

    private final IActivityClient iActivityClient;

    private final IContentClient iContentClient;

    private final FileStorageService fileStorageService;

    private final AsyncTaskExecutor asyncTaskExecutor;

    private final RabbitTemplate rabbitTemplate;

    public PortalServiceImpl(IActivityClient iActivityClient, IContentClient iContentClient, FileStorageService fileStorageService, AsyncTaskExecutor asyncTaskExecutor, RabbitTemplate rabbitTemplate) {
        this.iActivityClient = iActivityClient;
        this.iContentClient = iContentClient;
        this.fileStorageService = fileStorageService;
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.rabbitTemplate = rabbitTemplate;
    }


    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivities(Long communityId) {
        return iActivityClient.listCommunityActivitiesPreview(communityId);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityActivityDetail(Long communityId, Long activityId) {
        return iActivityClient.listCommunityActivityDetail(communityId, activityId);
    }

    /**
     * 更新活动详情
     *
     * @param communityId
     * @param id
     * @param activityDetailDto
     * @return
     */
    @Override
    public ResponseEntity<ResponseResult<?>> updateCommunityActivityDetail(Long communityId, Long id, ActivityDetailDto activityDetailDto) {
        return iActivityClient.updateCommunityActivityDetail(communityId, activityDetailDto);
    }

    /**
     * 列出社区简介
     *
     * @param communityId 社区id
     * @return 社区简介
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommunityProfile(Long communityId) {
        return iContentClient.listCommunityProfile(communityId);
    }

    /**
     * 网格管理
     *
     * @param communityId 社区id
     * @return 网格管理
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listGridManagement(Long communityId) {
        return iContentClient.listGridManagement(communityId);
    }

    /**
     * 列出两委成员列表
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 两委成员列表
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listCommitteesMembers(int pageNum, int pageSize) {
        return iContentClient.listCommitteesMembers(pageNum, pageSize);
    }

    /**
     * 履职信息
     *
     * @param communityId 社区id
     * @param id          个人id
     * @return 履职信息
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listPersonalInfo(Long communityId, long id) {
        return iContentClient.listPersonalInfo(communityId, id);
    }

    /**
     * 列出为民服务清单
     *
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @return 列出为民服务清单
     */
    @Override
    public ResponseEntity<ResponseResult<?>> listServePeople(int pageNum, int pageSize) {
        return iContentClient.listServePeople(pageNum, pageSize);
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listServePeopleInfo(int id) {
        return iContentClient.listServePeopleInfo(id);
    }

    @Override
    public ResponseEntity<ResponseResult<?>> listMatters(Long communityId, int pageNum, int pageSize) {
        return iContentClient.listMatters(communityId, pageNum, pageSize);
    }

    @Override
    public ResponseEntity<ResponseResult<?>> communityHonor(Long communityId, int pageNum, int pageSize) {
        return iContentClient.communityHonor(communityId, pageNum, pageSize);
    }

    @Override
    public ResponseEntity<ResponseResult<?>> proofDocuments(Long communityId, int pageNum, int pageSize) {
        return iContentClient.proofDocuments(communityId, pageNum, pageSize);
    }

    @Override
    public ResponseEntity<ResponseResult<?>> proofInfo(int id) {
        return iContentClient.proofInfo(id);
    }

    // 测试
    @Override
    public ResponseEntity<ResponseResult<?>> saveContent(int id) {
        return iContentClient.saveContent();
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        return fileStorageService.uploadFile("", file);
//        return iContentClient.uploadFile(file);
    }

    /**
     * 解析md文件
     *
     * @param content
     * @param fileName
     * @return
     * @throws IOException
     */
    @Override
    public ItemListDO getItemMattersFromMarkdownFileContent(String content, String fileName, String id) throws IOException {
        String[] lines = content.split("\\r?\\n");
        List<LawItemDTO> lawItems = new ArrayList<>();

        Pattern titlePattern = Pattern.compile("^\\*{0,2}(\\d+[、\\.．\\)]?)\\s*(.*?)\\*{0,2}$");
        Pattern basisPattern = Pattern.compile("^依据[:：]?\\s*(.*)");

        String currentTitle = null;
        List<String> currentBasis = new ArrayList<>();

        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.isEmpty()) {
                continue;
            }

            Matcher titleMatcher = titlePattern.matcher(line);
            Matcher basisMatcher = basisPattern.matcher(line);

            if (titleMatcher.find()) {
                // 之前有记录，保存
                if (currentTitle != null) {
                    lawItems.add(new LawItemDTO(currentTitle, new ArrayList<>(currentBasis)));
                    currentBasis.clear();
                }
                currentTitle = titleMatcher.group(2).trim();
            } else if (basisMatcher.find()) {
                String[] basisLines = basisMatcher.group(1).split("[；;、]");
                for (String b : basisLines) {
                    if (!b.isBlank()) {
                        currentBasis.add(b.trim());
                    }
                }
            } else if (line.startsWith("《") || line.contains("法") || line.contains("条例") || line.contains("办法")) {
                currentBasis.add(line);
            }
        }

        // 最后一条别忘了加
        if (currentTitle != null) {
            lawItems.add(new LawItemDTO(currentTitle, currentBasis));
        }
        return new ItemListDO(id, 1L, fileName, lawItems);
//        mongoTemplate.insert();
    }

    // 异步解析md文件 但是解析错误不知道怎么返回，文件较小 改为同步
    @Override
    public void getItemContentAsync(String content, String fileName, String id) {
        asyncTaskExecutor.runAsync(() -> {
            try {
                Thread.sleep(10 * 1000);
                ItemListDO itemMattersFromMarkdownFileContent = getItemMattersFromMarkdownFileContent(content, fileName, id);

                log.info("解析后的数据: {}", itemMattersFromMarkdownFileContent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 保存事项清单
     *
     * @param content
     * @param name
     */
    @Override
    public void saveItemContent(String content, String name, String id) throws Exception {
        ItemListDO itemMattersFromMarkdownFileContent = getItemMattersFromMarkdownFileContent(content, name, id);
        String itemListDoStr = JSONUtil.toJsonStr(itemMattersFromMarkdownFileContent);
        rabbitTemplate.convertAndSend(ITEM_MATTERS_TOPIC_EXCHANGE, ITEM_MATTERS_ROUTING_KEY_ADD, itemListDoStr);
//        mongoTemplate.save(itemMattersFromMarkdownFileContent, "test");
        System.out.println(itemMattersFromMarkdownFileContent.getId());
    }
}
