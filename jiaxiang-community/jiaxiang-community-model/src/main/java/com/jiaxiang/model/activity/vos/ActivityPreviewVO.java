package com.jiaxiang.model.activity.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动预览
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityPreviewVO {
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private String images;
}
