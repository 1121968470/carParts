package com.carmold.service;

import com.carmold.bean.User;
import com.carmold.bean.UserPower;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;

public interface UserPowerService {

    CallResult saveOrUpdate(User user);

    PageInfo list(PageInfo pageInfo, String userName, String loginStatus);

    User getId(String userId);

    PageInfo getUserPower(PageInfo pageInfo);

    CallResult saveOrUpdatePower(UserPower user);

}
