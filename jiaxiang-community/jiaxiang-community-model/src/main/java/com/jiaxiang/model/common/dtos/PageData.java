package com.jiaxiang.model.common.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageData<T> implements Serializable {
    // 当前页码
    private Integer currentPage;
    // 每页大小
    private Integer size;
    // 数据总数
    private Integer total;
    // 总页数
    private Integer pages;

    // 数据列表
    private T records;

    public PageData(Integer currentPage, Integer size, Integer total, int pages, T records) {
        this.currentPage = currentPage;
        this.size = size;
        this.total = total;
        this.pages = pages;
        this.records = records;
    }

    public PageData() {
    }
}
