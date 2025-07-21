package com.jiaxiang.model.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LawItemDTO {
    private String title;
    private List<String> basisList;
}
