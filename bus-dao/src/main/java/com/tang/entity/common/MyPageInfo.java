package com.tang.entity.common;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 自定义的PageInfo类，用于返回给前端
 * @author tang
 * @since 2020/7/22
 */
@Getter
@Setter
public class MyPageInfo<T> {
    private int pageNum;
    private int pageSize;
    private int size;
    private int pages;
    private List<T> list;

    public MyPageInfo(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.size = pageInfo.getSize();
        this.pages = pageInfo.getPages();
        this.list = list;
    }
}