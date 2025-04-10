package com.jiaxiang.apis.Content;

import com.jiaxiang.apis.Content.fallback.IContentClientFallback;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "jiaxiang-content", fallback = IContentClientFallback.class )
public interface IContentClient {

    @PostMapping("/content/save")
    public ResponseEntity<ResponseResult<?>> saveContent();
}
