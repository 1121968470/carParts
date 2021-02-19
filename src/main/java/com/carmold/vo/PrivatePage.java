package com.carmold.vo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

@Data
public class PrivatePage {
    int per_page;
    int page;

    public PageInfo createPage() {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(this.per_page);
        pageInfo.setPageNum(this.page);
        return pageInfo;
    }
}
