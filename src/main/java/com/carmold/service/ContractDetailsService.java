package com.carmold.service;

import com.carmold.bean.ContractDetails;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageInfo;

public interface ContractDetailsService {

    CallResult saveOrUpdate(ContractDetails contractDetails);

    PageInfo list(PageInfo pageInfo, String year, String typeOfMachine,
                  String partNumber, String customer, String contractSigningTime, String remarks);

    PageInfo listTotal(PageInfo pageInfo, String year, String typeOfMachine,
                       String partNumber, String customer, String contractSigningTime, String remarks);

    PageInfo directSummarize(PageInfo pageInfo, String year, String typeOfMachine,
                             String partNumber, String customer);

    PageInfo dieStall(PageInfo pageInfo, String year, String typeOfMachine,
                      String partNumber, String customer);

    PageInfo outProcess(PageInfo pageInfo, String year, String typeOfMachine,
                        String partNumber, String customer);

    PageInfo assumedChange(PageInfo pageInfo, String year, String typeOfMachine,
                           String partNumber, String customer);

    PageInfo dermatoglyph(PageInfo pageInfo, String year, String typeOfMachine,
                          String partNumber, String customer);

    ContractDetails getId(String id);

    CallResult delete(String id);

}
