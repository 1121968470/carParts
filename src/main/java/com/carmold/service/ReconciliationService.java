package com.carmold.service;

import com.carmold.bean.Reconciliation;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;

public interface ReconciliationService {

    CallResult saveOrUpdate(Reconciliation contractDetails);

    PageInfo list(PageInfo pageInfo, String year, String month, String companyNameString, String typeOfMachine, String customer);

    PageInfo listTotal(PageInfo pageInfo, String year, String month, String companyNameString, String typeOfMachine, String customer);

    Reconciliation getId(String id);

    PageInfo listReport(PageInfo pageInfo, String year, String month, String companyNameString, String typeOfMachine, String customer);

}
