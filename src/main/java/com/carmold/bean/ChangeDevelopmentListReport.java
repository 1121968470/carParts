package com.carmold.bean;

import lombok.Data;

/**
 * mold_change_development_list
 * @author 
 */
@Data
public class ChangeDevelopmentListReport {

    /**
     * 序号
     */
    private String serialNumber;

    /**
     * 客户
     */
    private String customer;

    /**
     * 机种
     */
    private String model;

    /**
     * 名称
     */
    private String modelName;

    /**
     * 件号
     */
    private String itemId;

    /**
     * 模号
     */
    private String mouldId;

    /**
     * 设变开始加工时间
     */
    private String changeTimeOfProcessing;

    /**
     * 设变单号
     */
    private String changeOrderNumber;

    /**
     * 设变内容
     */
    private String changeContent;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 负责人
     */
    private String mouldDirector;

    /**
     * 材料-模架
     */
    private String materialFormwork;

    /**
     * 材料-内模件
     */
    private String materialInternalModule;

    /**
     * CNC
     */
    private String cnc;

    /**
     * CNC-电极材料
     */
    private String cncMaterial;

    /**
     * 电火花
     */
    private String electricSpark;

    /**
     * 线割
     */
    private String threadCutting;

    /**
     * 抛光/热流道/刻字
     */
    private String polishingHotrunnerLettering;

    /**
     * 配件
     */
    private String parts;

    /**
     * 咬花
     */
    private String bitingFlowers;

    /**
     * 其它加工
     */
    private String otherProcessing;

    /**
     * 修模或设变
     */
    private String repairOrChange;

    /**
     * 合计
     */
    private String total;

    /**
     * 操作人员
     */
    private String operator;

}