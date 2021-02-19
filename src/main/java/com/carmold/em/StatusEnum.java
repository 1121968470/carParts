package com.carmold.em;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 状态枚举
 *
 * @author :李志文
 * @date 2019/9/3 20:33
 */
public enum StatusEnum {
    NORMAL(1, "正常"), DELETE(0, "删除"), DISABLE(2, "不可用"), DRAFT(3, "草稿"),

    AUDIT(4, "待审核"), AUDIT_PASS(5, "已通过"), AUDIT_NOPASS(6, "不通过");


    private int index;
    private String name;

    // 构造方法
    private StatusEnum(int index, String name) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Map<String, Object>> getStatusTypes() {
        List<Map<String, Object>> statusTypes = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StatusEnum.NORMAL.name());
        map.put("index", StatusEnum.NORMAL.getIndex());
        statusTypes.add(map);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", StatusEnum.DISABLE.name());
        map2.put("index", StatusEnum.DISABLE.getIndex());
        statusTypes.add(map2);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", StatusEnum.DELETE.name());
        map3.put("index", StatusEnum.DELETE.getIndex());
        statusTypes.add(map3);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("name", StatusEnum.AUDIT.name());
        map4.put("index", StatusEnum.AUDIT.getIndex());
        statusTypes.add(map4);
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("name", StatusEnum.AUDIT_PASS.name());
        map5.put("index", StatusEnum.AUDIT_PASS.getIndex());
        statusTypes.add(map5);
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("name", StatusEnum.AUDIT_NOPASS.name());
        map6.put("index", StatusEnum.AUDIT_NOPASS.getIndex());
        statusTypes.add(map6);
        return statusTypes;
    }

}
