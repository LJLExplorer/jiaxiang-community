package com.jiaxiang.content.listener;

import cn.hutool.json.JSONUtil;
import com.jiaxiang.model.community.dos.ItemListDO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import static com.jiaxiang.model.common.constant.MQConstant.ITEM_MATTERS_QUEUE;

@Component
public class MQListener {

    private final MongoTemplate mongoTemplate;

    public MQListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RabbitListener(queues = ITEM_MATTERS_QUEUE)
    public void saveItemContent(String message) {
        ItemListDO itemListDO = JSONUtil.parseObj(message).toBean(ItemListDO.class);
        mongoTemplate.save(itemListDO);
    }
}
