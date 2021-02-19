package com.carmold.service.impl;

import com.carmold.bean.DevelopmentList;
import com.carmold.dao.DevelopmentListMapper;
import com.carmold.service.DevelopmentListService;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("developmentListService")
public class DevelopmentListServiceImpl implements DevelopmentListService {
    @Autowired
    private DevelopmentListMapper developmentListDao;

    @Override
    public CallResult saveOrUpdate(DevelopmentList developmentList) {
        CallResult callResult = new CallResult();
        developmentList.setUpdateTime(new Date());
        if (StringUtils.isEmpty(developmentList.getId())) {
            developmentListDao.insert(developmentList);
            callResult.succee("保存成功");
        } else {
            developmentListDao.updateByPrimaryKeySelective(developmentList);
            callResult.succee("修改成功");
        }
        return callResult;
    }

    @Override
    public PageInfo list(PageInfo page, String year, String model, String itemId, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(developmentListDao.list(year, model, itemId, customer));
    }

    @Override
    public DevelopmentList getId(String id) {
        if(StringUtils.isEmpty(id)){
            return null;
        }
        DevelopmentList developmentList = developmentListDao.selectByPrimaryKey(id);
        return developmentList;
    }

}
