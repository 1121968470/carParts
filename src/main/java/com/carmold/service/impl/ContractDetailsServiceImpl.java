package com.carmold.service.impl;

import com.carmold.bean.ContractDetails;
import com.carmold.dao.ContractDetailsMapper;
import com.carmold.service.ContractDetailsService;
import com.carmold.vo.CallResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("contractDetailsService")
public class ContractDetailsServiceImpl implements ContractDetailsService {
    @Autowired
    private ContractDetailsMapper contractDetailsDao;

    @Override
    public CallResult saveOrUpdate(ContractDetails contractDetails) {
        CallResult callResult = new CallResult();
        contractDetails.setUpdateTime(new Date());
        if (StringUtils.isEmpty(contractDetails.getId())) {
            contractDetailsDao.insert(contractDetails);
            callResult.succee("保存成功");
        } else {
            String id = contractDetails.getId();
            String[] list = id != null ? id.split(","):null;
            if(list.length>1){
                for (String i : list) {
                    contractDetails.setId(i);
                    contractDetailsDao.updateByPrimaryKeySelective(contractDetails);
                }
            } else {
                contractDetailsDao.updateByPrimaryKeySelective(contractDetails);
            }
            callResult.succee("修改成功");
        }
        return callResult;
    }

    @Override
    public CallResult delete(String id) {
        CallResult callResult = new CallResult();
        contractDetailsDao.deleteByPrimaryKey(id);
        callResult.succee("删除成功");
        return callResult;
    }

    @Override
    public ContractDetails getId(String id) {
        if(StringUtils.isEmpty(id)){
            return null;
        }
        ContractDetails contractDetails = contractDetailsDao.selectByPrimaryKey(id);
        return contractDetails;
    }

    @Override
    public PageInfo list(PageInfo page, String year, String typeOfMachine,
                         String partNumber, String customer, String contractSigningTime, String remarks) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(contractDetailsDao.list(year, typeOfMachine, partNumber, customer,
                contractSigningTime, remarks));
    }

    @Override
    public PageInfo listTotal(PageInfo page, String year, String typeOfMachine,
                              String partNumber, String customer, String contractSigningTime, String remarks) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(contractDetailsDao.listTotal(year, typeOfMachine, partNumber, customer,
                contractSigningTime, remarks));
    }

    @Override
    public PageInfo directSummarize(PageInfo page, String year, String typeOfMachine,
                                    String partNumber, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(contractDetailsDao.directSummarize(year, typeOfMachine, partNumber, customer));
    }

    @Override
    public PageInfo dieStall(PageInfo page, String year, String typeOfMachine,
                                    String partNumber, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(contractDetailsDao.dieStall(year, typeOfMachine, partNumber, customer));
    }

    @Override
    public PageInfo outProcess(PageInfo page, String year, String typeOfMachine,
                             String partNumber, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(contractDetailsDao.outProcess(year, typeOfMachine, partNumber, customer));
    }

    @Override
    public PageInfo assumedChange(PageInfo page, String year, String typeOfMachine,
                               String partNumber, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(contractDetailsDao.assumedChange(year, typeOfMachine, partNumber, customer));
    }

    @Override
    public PageInfo dermatoglyph(PageInfo page, String year, String typeOfMachine,
                               String partNumber, String customer) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return PageInfo.of(contractDetailsDao.dermatoglyph(year, typeOfMachine, partNumber, customer));
    }

}
