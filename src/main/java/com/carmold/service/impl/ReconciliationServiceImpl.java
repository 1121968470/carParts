package com.carmold.service.impl;

import com.carmold.bean.Reconciliation;
import com.carmold.dao.ReconciliationDao;
import com.carmold.service.ReconciliationService;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("reconciliationService")
public class ReconciliationServiceImpl implements ReconciliationService {
    @Autowired
    private ReconciliationDao reconciliationDao;

    @Override
    public CallResult saveOrUpdate(Reconciliation reconciliation) {
        CallResult callResult = new CallResult();
        reconciliation.setUpdateTime(new Date());
        if (StringUtils.isEmpty(reconciliation.getId())) {
            reconciliationDao.insert(reconciliation);
            callResult.succee("保存成功");
        } else {
            reconciliationDao.updateByPrimaryKeySelective(reconciliation);
            callResult.succee("修改成功");
        }
        return callResult;
    }

    @Override
    public PageInfo list(PageInfo page, String year, String month, String companyName, String typeOfMachine, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(reconciliationDao.list(year, month, companyName, typeOfMachine, customer));
    }

    @Override
    public PageInfo listTotal(PageInfo page, String year, String month, String companyName, String typeOfMachine, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(reconciliationDao.listTotal(year, month, companyName, typeOfMachine, customer));
    }

    @Override
    public Reconciliation getId(String id) {
        if(StringUtils.isEmpty(id)){
            return null;
        }
        Reconciliation reconciliation = reconciliationDao.selectByPrimaryKey(id);
        return reconciliation;
    }

    @Override
    public PageInfo listReport(PageInfo page, String year, String month, String companyName, String typeOfMachine, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(reconciliationDao.listReport(year, month, companyName, typeOfMachine, customer));
    }
}
