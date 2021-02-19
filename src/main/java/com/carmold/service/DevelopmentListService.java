package com.carmold.service;

import com.carmold.bean.DevelopmentList;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;

public interface DevelopmentListService {

    CallResult saveOrUpdate(DevelopmentList developmentList);

    PageInfo list(PageInfo pageInfo, String year, String model, String itemId, String customer);

    DevelopmentList getId(String id);

}
