package com.jiaxiang.activity.service.impl;

import com.jiaxiang.activity.mapper.ActivityMapper;
import com.jiaxiang.activity.service.ActivityService;
import com.jiaxiang.file.service.impl.MinioFileStorageService;
import com.jiaxiang.model.activity.dos.ActivityDetailDo;
import com.jiaxiang.model.activity.dos.ActivityDo;
import com.jiaxiang.model.activity.dos.ActivityFileDo;
import com.jiaxiang.model.activity.dtos.ActivityDetailDto;
import com.jiaxiang.model.activity.vos.ActivityDetailVO;
import com.jiaxiang.model.activity.vos.ActivityPreviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.jiaxiang.model.common.enums.ViewContentTypeEnum.getContentType;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private MinioFileStorageService minioFileStorageService;

    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 返回活动预览图
     */
    @Override
    public List<ActivityPreviewVO> listCommunityActivitiesPreview(Long communityId) {
        return activityMapper.getActivitiesByCommunityId(communityId);
    }

    /**
     * 列出社区活动详情
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    @Override
    public ActivityDetailVO listCommunityActivityDetail(Long communityId, Long activityId) {
        ActivityDetailVO activityDetailVO = activityMapper.getActivityDetailWithOutImagesByActivityId(communityId, activityId);

        List<ActivityFileDo> activityFileDos = activityMapper.getFilesByActivityId(communityId, activityId);
        List<String> images = activityFileDos.stream().map(ActivityFileDo::getPathUrl).toList();
        activityDetailVO.setImages(images);
        return activityDetailVO;
    }

    /**
     * 更新活动详情
     *
     * @param communityId
     * @param activityDetailDto
     * @return
     */
    @Override
    public void updateCommunityActivityDetail(Long communityId, ActivityDetailDto activityDetailDto) {
        List<String> images = activityDetailDto.getImages();
        images.add(activityDetailDto.getCoverImage());
        for (String image : images) {
            boolean flag = minioFileStorageService.checkMinioFileExists(image);
            if (!flag) {
                throw new RuntimeException("图片 " + image + " 不存在！");
            }
        }
        ActivityDetailDo activityDetailDo = new ActivityDetailDo(activityDetailDto.getId(), activityDetailDto.getActivateId(), activityDetailDto.getTheme(),
                activityDetailDto.getIntroduction(), activityDetailDto.getContent());
        ActivityDo activityDo = new ActivityDo(activityDetailDto.getActivateId(), communityId, activityDetailDto.getTitle(),
                activityDetailDto.getLocation(), activityDetailDto.getStartTime(), activityDetailDto.getEndTime(),
                activityDetailDto.getOrganizer(), activityDetailDto.getParticipantCount());
        activityMapper.updateActivityDetailDoById(activityDetailDo);
        activityMapper.updateActivityDo(activityDo);

        List<ActivityFileDo> dbFiles = activityMapper.getFilesByActivityId(communityId, activityDetailDto.getActivateId());
        Set<String> dbFileUrls = dbFiles.stream().map(ActivityFileDo::getPathUrl).collect(Collectors.toSet());
        Set<String> newFileUrls = new HashSet<>(activityDetailDto.getImages());

        Set<String> toDelete = new HashSet<>(dbFileUrls);
        toDelete.removeAll(newFileUrls);

        Set<String> toInsert = new HashSet<>(newFileUrls);
        toInsert.removeAll(dbFileUrls);

        if (!toDelete.isEmpty()) {
            activityMapper.deleteByActivityIdAndUrls(activityDetailDto.getActivateId(), toDelete);
        }

        List<ActivityFileDo> insertList = new ArrayList<>();
        for (String url : toInsert) {
            ActivityFileDo fileDo = new ActivityFileDo();
            fileDo.setActivityId(activityDetailDto.getActivateId());
            fileDo.setPathUrl(url);
            fileDo.setIsCover(url.equals(activityDetailDto.getCoverImage()));
            fileDo.setFileType(getContentType(url));
            fileDo.setDescription(activityDetailDto.getTitle());
            insertList.add(fileDo);
        }
        if (!insertList.isEmpty()) {
            activityMapper.insertActivityFiles(insertList);
        }

        // 8. 更新现有图片的 isCover 字段
        for (ActivityFileDo file : dbFiles) {
            if (newFileUrls.contains(file.getPathUrl())) {
                boolean isCover = file.getPathUrl().equals(activityDetailDto.getCoverImage());
                if (!Objects.equals(file.getIsCover(), isCover)) {
                    ActivityFileDo activityFileDo = new ActivityFileDo(file.getId(), isCover);
                    activityMapper.updateActivityFileDo(activityFileDo);
                }
            }
        }
    }
}
