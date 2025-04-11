package com.jiaxiang.model.content.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffInfoVO {
    private Long id;
    private String nameAndPosition;
    private String images;
    private String duty;
    private String dutyDescription;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
