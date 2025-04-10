package com.jiaxiang.activity.mapper;

import com.jiaxiang.model.activity.vos.ActivityPreviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 根据社区id列出活动预览
     *
     * @param communityId 社区id
     * @return 活动
     */
    List<ActivityPreviewVO> getActivitiesByCommunityId(@Param("communityId") Long communityId);
}
