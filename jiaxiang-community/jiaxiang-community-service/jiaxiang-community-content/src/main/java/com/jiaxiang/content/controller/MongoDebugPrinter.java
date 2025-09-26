package com.jiaxiang.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

//@Component
public class MongoDebugPrinter implements CommandLineRunner {

//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("🔥 MongoTemplate 正在使用的数据库是：" + mongoTemplate.getDb().getName());
    }
}