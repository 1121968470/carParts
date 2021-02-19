package com.carmold.bean;

import lombok.Data;

/**
 * (mold_development_list)表实体类
 *
 * @author Mr.Li
 * @version 1.0
 * @since 2020/12/8 8:11
 */
@Data
public class DevelopmentListReport {

    /**
     * 序号
     */
    private String serialNumber;

    /**
     * 月份
     */
    private String time;

    /**
     * 客户
     */
    private String customer;

    /**
     * 机种
     */
    private String model;

    /**
     * 件名
     */
    private String itemName;

    /**
     * 件号
     */
    private String itemId;

    /**
     * 材质
     */
    private String material;

    /**
     * 模穴数
     */
    private String mouldHoleNumber;

    /**
     * 模具编号
     */
    private String mouldId;

    /**
     * 模具负责人
     */
    private String mouldDirector;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * T0试模
     */
    private String sendTime;

    /**
     * 收缩率
     */
    private String shrinkage;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 加工
     */
    private String machining;

    /**
     * 设计
     */
    private String design;

    /**
     * 设计费
     */
    private String designPrice;

    /**
     * 钳工
     */
    private String fitter;

    /**
     * 材料-模架
     */
    private String materialFormwork;

    /**
     * 材料-内模件
     */
    private String materialInternalModule;

    /**
     * 材料-比例
     */
    private String materialRatio;

    /**
     * CNC-小刘
     */
    private String cncLiu;

    /**
     * CNC-小池
     */
    private String cncChi;

    /**
     * CNC
     */
    private String cnc;

    /**
     * CNC-电极材料
     */
    private String cncMaterial;

    /**
     * CNC-比例
     */
    private String cncRatio;

    /**
     * 电火花
     */
    private String electricSpark;

    /**
     * 电火花-比例
     */
    private String electricSparkRatio;

    /**
     * 线割
     */
    private String threadCutting;

    /**
     * 线割-比例
     */
    private String threadCuttingRatio;

    /**
     * 抛光/热流道/刻字
     */
    private String polishingHotrunnerLettering;

    /**
     * 抛光/热流道/刻字-比例
     */
    private String polishingHotrunnerLetteringRatio;

    /**
     * 咬花
     */
    private String bitingFlowers;

    /**
     * 模具装配（组合）
     */
    private String dieAssembly;

    /**
     * 其它加工
     */
    private String otherProcessing;

    /**
     * 配件
     */
    private String parts;

    /**
     * 配件-比例
     */
    private String partsRatio;

    /**
     * 模具成本
     */
    private String mouldCost;

    /**
     * 承接模具价格
     */
    private String acceptMoldPrice;

    /**
     * 比例
     */
    private String ratio;

    /**
     * 模具重量
     */
    private String mouldWeight;

    /**
     * 颜色
     */
    private String color;

    /**
     * 单重
     */
    private String singleWeight;

    /**
     * 操作人员
     */
    private String operator;

}
