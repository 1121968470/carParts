package com.carmold.bean;

import lombok.Data;

/**
 * mold_reconciliation
 * @author 
 */
@Data
public class ReconciliationReport {

    /**
     * 供应商
     */
    private String companyName;

    /**
     * 月份
     */
    private String month;

    /**
     * 日期
     */
    private String date;

    /**
     * 单号
     */
    private String oddNumbers;

    /**
     * 客户
     */
    private String customer;

    /**
     * 机种
     */
    private String typeOfMachine;

    /**
     * 模具名称
     */
    private String moldName;

    /**
     * 模号
     */
    private String moldNumber;

    /**
     * 品名
     */
    private String productName;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private String number;

    /**
     * 工时
     */
    private String workingHours;

    /**
     * 单价
     */
    private String unitPrice;

    /**
     * 面积
     */
    private String area;

    /**
     * NC金额（元）
     */
    private String ncPrice;

    /**
     * 材料费
     */
    private String materialCost;

    /**
     * 金额
     */
    private String price;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 操作人员
     */
    private String operator;

}