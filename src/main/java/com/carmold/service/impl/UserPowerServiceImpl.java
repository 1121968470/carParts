package com.carmold.service.impl;

import com.carmold.bean.User;
import com.carmold.bean.UserPower;
import com.carmold.dao.UserDao;
import com.carmold.dao.UserPowerDao;
import com.carmold.service.UserPowerService;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("userPowerService")
public class UserPowerServiceImpl implements UserPowerService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserPowerDao userPowerDao;

    @Override
    public CallResult saveOrUpdate(User user) {
        CallResult callResult = new CallResult();
        if (StringUtils.isEmpty(user.getId())) {
            user.setUpdateState("I");
            userDao.insert(user);
            callResult.succee("保存成功");
        } else {
            user.setUpdateState("U");
            userDao.updateByPrimaryKeySelective(user);
            callResult.succee("修改成功");
        }
        return callResult;
    }

    @Override
    public CallResult saveOrUpdatePower(UserPower userPower) {
        CallResult callResult = new CallResult();
        if (StringUtils.isEmpty(userPower.getId())) {
            userPowerDao.insert(userPower);
            callResult.succee("保存成功");
        } else {
            userPowerDao.updateByPrimaryKeySelective(userPower);
            callResult.succee("修改成功");
        }
        return callResult;
    }

    @Override
    public PageInfo list(PageInfo page, String userName, String loginStatus) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(userDao.list(userName, loginStatus));
    }

    @Override
    public User getId(String userId) {
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        User user = userDao.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public PageInfo getUserPower(PageInfo page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(userPowerDao.list());
    }

}
