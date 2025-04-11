package com.jiaxiang.model.content.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffInfoDO {
    private Long id;
    private String nameAndPosition;
    private String images;
    private String duty;
    private String dutyDescription;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
