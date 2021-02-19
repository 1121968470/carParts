package com.carmold.bean;

import java.util.Date;

import com.carmold.mybatis.plugins.generator.Id;
import lombok.Data;

/**
 * mold_reconciliation
 * @author 
 */
@Data
public class Reconciliation {

    @Id
    private String id;

    /**
     * 公司名
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
     * 年份
     */
    private String year;

    /**
     * 备用字段1
     */
    private String field1;

    /**
     * 备用字段2
     */
    private String field2;

    /**
     * 备用字段3
     */
    private String field3;

    /**
     * 备用字段4
     */
    private String field4;

    /**
     * 备用字段5
     */
    private String field5;

    /**
     * 备用字段6
     */
    private String field6;

    /**
     * 备用字段7
     */
    private String field7;

    /**
     * 备用字段8
     */
    private String field8;

    /**
     * 备用字段9
     */
    private String field9;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新状态（I新增，U更新，D删除）
     */
    private String updateState;

    /**
     * 操作人员
     */
    private String operator;

    private static final long serialVersionUID = 1L;
}