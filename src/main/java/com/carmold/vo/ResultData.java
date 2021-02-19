package com.carmold.vo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

@Data
public class ResultData {
    @Data
    private class MyPageInfo {
        int page;
        int per_page;
        long total_items;
        long total_page;
    }

    private MyPageInfo counts;
    Object items;

    public void setPage(PageInfo page) {
        MyPageInfo counts = new MyPageInfo();
        counts.page = page.getPageNum();
        counts.per_page = page.getPageSize();
        counts.total_items = page.getTotal();
        counts.total_page = page.getPages();
        this.counts = counts;
    }
}
