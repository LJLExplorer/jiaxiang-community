package com.jiaxiang.activity.mapper;

import com.jiaxiang.model.activity.dos.ActivityDetailDo;
import com.jiaxiang.model.activity.dos.ActivityDo;
import com.jiaxiang.model.activity.dos.ActivityFileDo;
import com.jiaxiang.model.activity.vos.ActivityDetailVO;
import com.jiaxiang.model.activity.vos.ActivityPreviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface ActivityMapper {

    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 活动
     */
    List<ActivityPreviewVO> getActivitiesByCommunityId(@Param("communityId") Long communityId);

    /**
     * 列出社区活动详情 无图版
     *
     * @param communityId 社区id
     * @return 活动详情
     */
    ActivityDetailVO getActivityDetailWithOutImagesByActivityId(@Param("communityId") Long communityId, @Param("activityId") Long activityId);

    List<ActivityFileDo> getFilesByActivityId(@Param("communityId") Long communityId, @Param("activityId") Long activityId);

    /**
     * 根据活动Id更新活动详情
     *
     * @param activityDetailDo
     */
    void updateActivityDetailDoById(ActivityDetailDo activityDetailDo);

    /**
     * 添加活动详情
     * @param activityDo
     */
    Integer addActivityDo(ActivityDo activityDo);

    Integer addActivityDetail(ActivityDetailDo activityDetailDo);

    /**
     * 批量插入
     * @param activityFileDoList
     * @return
     */
    Integer batchInsertActivityFiles(List<ActivityFileDo> activityFileDoList);



    /**
     * 根据活动id更新活动
     *
     * @param activityDo
     */
    void updateActivityDo(ActivityDo activityDo);

    /**
     * 根据活动id删除toDelete的数据
     * @param activateId
     * @param toDelete
     */
    void deleteByActivityIdAndUrls(@Param("activateId") Long activateId, @Param("toDelete") Set<String> toDelete);

    /**
     * 插入文件
     * @param insertList
     */
    void insertActivityFiles(List<ActivityFileDo> insertList);

    /**
     * 更新活动文件
     * @param activityFileDo
     */
    void updateActivityFileDo(ActivityFileDo activityFileDo);


}
