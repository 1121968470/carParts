package com.carmold.service.impl;

import com.carmold.bean.ChangeDevelopmentList;
import com.carmold.bean.DevelopmentList;
import com.carmold.dao.ChangeDevelopmentListDao;
import com.carmold.service.ChangeDevelopmentListService;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("changeDevelopmentListService")
public class ChangeDevelopmentListServiceImpl implements ChangeDevelopmentListService {
    @Autowired
    private ChangeDevelopmentListDao changeDevelopmentListDao;

    @Override
    public CallResult saveOrUpdate(ChangeDevelopmentList changeDevelopmentList) {
        CallResult callResult = new CallResult();
        changeDevelopmentList.setUpdateTime(new Date());
        if (StringUtils.isEmpty(changeDevelopmentList.getId())) {
            changeDevelopmentListDao.insert(changeDevelopmentList);
            callResult.succee("保存成功");
        } else {
            changeDevelopmentListDao.updateByPrimaryKeySelective(changeDevelopmentList);
            callResult.succee("修改成功");
        }
        return callResult;
    }

    @Override
    public PageInfo list(PageInfo page, String type, String year, String model,
                         String itemId, String customer, String repairOrChange) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(changeDevelopmentListDao.list(type, year, model, itemId, customer, repairOrChange));
    }

    @Override
    public ChangeDevelopmentList getId(String id) {
        if(StringUtils.isEmpty(id)){
            return null;
        }
        ChangeDevelopmentList developmentList = changeDevelopmentListDao.selectByPrimaryKey(id);
        return developmentList;
    }

}
