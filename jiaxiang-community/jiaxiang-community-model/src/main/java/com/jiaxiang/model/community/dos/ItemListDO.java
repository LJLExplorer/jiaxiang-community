package com.jiaxiang.model.community.dos;

import com.jiaxiang.model.community.dtos.LawItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemListDO {

    private String id;

    private Long communityId;

    private String title;

    private String fileName;

    private String content;

    private String url;

    public ItemListDO(Long communityId, String title, String fileName, String content, String url) {
        this.communityId = communityId;
        this.title = title;
        this.fileName = fileName;
        this.content = content;
        this.url = url;
    }

}
