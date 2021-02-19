package com.carmold.bean;

import java.util.Date;

import com.carmold.mybatis.plugins.generator.Id;
import lombok.Data;

/**
 * mold_contract_details
 * @author
 */
@Data
public class ContractDetails  {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    /**
     * 年份
     */
    private String year;

    /**
     * 项次
     */
    private String item;

    /**
     * 客户
     */
    private String customer;

    /**
     * 合同图片
     */
    private String contractImg;

    /**
     * 合同签订时间
     */
    private String contractSigningTime;

    /**
     * 机种
     */
    private String typeOfMachine;

    /**
     * 模具付数
     */
    private String mouldPayment;

    /**
     * 有无模摊
     */
    private String tfModelStall;

    /**
     * 模摊比例
     */
    private String modelStallRatio;

    /**
     * 收款名称
     */
    private String beneficiaryName;

    /**
     * 收款方式
     */
    private String paymentMethod;

    /**
     * 模（检）具未税总金额
     */
    private String totalAmountNotaxMould;

    /**
     * 模（检）具总金额
     */
    private String totalAmountMould;

    /**
     * 除模摊部分-可收
     */
    private String moldRemovalAcceptable;

    /**
     * 除模摊部分-已收
     */
    private String moldRemovalReceived;

    /**
     * 模摊部分-可收
     */
    private String moldStallAcceptable;

    /**
     * 模摊部分-已收
     */
    private String moldStallReceived;

    /**
     * 未收总金额
     */
    private String totalAmountNotReceived;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 量产时间
     */
    private String batchTime;

    /**
     * 件号
     */
    private String partNumber;

    /**
     * 制品单价
     */
    private String productsPrice;

    /**
     * 模具编号
     */
    private String dieNumber;

    /**
     * 皮纹费
     */
    private String dermatoglyphPaid;

    /**
     * 委外-厂商
     */
    private String outManufacturer;

    /**
     * 委外-合同图片
     */
    private String outContractImg;

    /**
     * 委外-合同签订时间
     */
    private String outContractSigningTime;

    /**
     * 委外-含税总额
     */
    private String outTotalTaxIncluded;

    /**
     * 委外-已付金额
     */
    private String outAmountPaid;

    /**
     * 委外-未付余额
     */
    private String outAmountUnpaid;

    /**
     * 委外-备注
     */
    private String outRemarks;

    /**
     * 设变-合同图片
     */
    private String changeContractImg;

    /**
     * 设变-客户合同签订时间
     */
    private String changeContractSigningTime;

    /**
     * 设变-件号
     */
    private String changePartNumber;

    /**
     * 设变-内容
     */
    private String changeContent;

    /**
     * 设变-含税金额
     */
    private String changeTaxIncluded;

    /**
     * 设变-已付金额
     */
    private String changeAmountPaid;

    /**
     * 设变-未付金额
     */
    private String changeAmountUnpaid;

    /**
     * 设变-备注
     */
    private String changeRemarks;

    /**
     * 皮纹-合同图片
     */
    private String dermatoglyphContractImg;

    /**
     * 皮纹-客户合同签订时间
     */
    private String dermatoglyphContractSigningTime;

    /**
     * 皮纹-含税金额
     */
    private String dermatoglyphTaxIncluded;

    /**
     * 皮纹-内容
     */
    private String dermatoglyphContent;

    /**
     * 皮纹-件号
     */
    private String dermatoglyphPartNumber;

    /**
     * 皮纹-已付金额
     */
    private String dermatoglyphAmountPaid;

    /**
     * 皮纹-未付金额
     */
    private String dermatoglyphAmountUnpaid;

    /**
     * 皮纹-备注
     */
    private String dermatoglyphRemarks;

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

}
