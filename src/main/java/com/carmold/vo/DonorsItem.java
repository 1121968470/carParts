package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DonorsItem {

    @JSONField(name = "amount")
    private int amount;

    @JSONField(name = "headimg")
    private String headimg;

    @JSONField(name = "user_id")
    private int userId;

    @JSONField(name = "user_name")
    private String userName;

    @JSONField(name = "com_id")
    private int comId;

    @JSONField(name = "sex")
    private int sex;

    @JSONField(name = "avatar_path")
    private String avatarPath;

    @JSONField(name = "created_at")
    private String createdAt;

    @JSONField(name = "id")
    private int id;

    @JSONField(name = "notified_at")
    private String notifiedAt;
}
