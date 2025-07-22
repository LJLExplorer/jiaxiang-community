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
@Document("item_list")
public class ItemListDO {

    @Id
    private String id;

    private Long communityId;

    private String fileName;

    private List<LawItemDTO> lawItems;


}
