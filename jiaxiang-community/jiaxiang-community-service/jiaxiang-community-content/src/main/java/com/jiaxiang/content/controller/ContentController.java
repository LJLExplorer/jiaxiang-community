package com.jiaxiang.content.controller;

import com.jiaxiang.content.service.ContentService;
import com.jiaxiang.model.common.dtos.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/save")
    public ResponseEntity<ResponseResult<?>> saveContent() {
        return null;
    }


}
