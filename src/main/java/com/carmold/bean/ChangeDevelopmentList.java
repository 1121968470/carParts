package com.carmold.bean;

import com.carmold.mybatis.plugins.generator.Id;
import lombok.Data;

import java.util.Date;

/**
 * mold_change_development_list
 * @author 
 */
@Data
public class ChangeDevelopmentList {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 序号
     */
    private String serialNumber;

    /**
     * 年份
     */
    private String year;

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新状态（I新增，U更新，D删除）
     */
    private String updateState;

    /**
     * 合计
     */
    private String total;

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