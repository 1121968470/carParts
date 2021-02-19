package com.carmold.service;

import com.carmold.bean.ChangeDevelopmentList;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;

public interface ChangeDevelopmentListService {

    CallResult saveOrUpdate(ChangeDevelopmentList developmentList);

    PageInfo list(PageInfo pageInfo, String type, String year,
                  String model, String itemId, String customer, String repairOrChange);

    ChangeDevelopmentList getId(String id);
}
