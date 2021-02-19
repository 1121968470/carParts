package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.annotation.Generated;
import java.util.List;

@Data
@Generated("com.robohorse.robopojogenerator")
public class ProjectDeailVO {

    @JSONField(name = "settings")
    private Settings settings;

    @JSONField(name = "user_status")
    private UserStatus userStatus;

    @JSONField(name = "max_donation_amount")
    private int maxDonationAmount;

    @JSONField(name = "logo_path")
    private String logoPath;

    @JSONField(name = "community_name")
    private String communityName;

    @JSONField(name = "community_forms")
    private List<CommunityFormsItem> communityForms;

    @JSONField(name = "com_id")
    private int comId;

    @JSONField(name = "single_donation_options")
    private List<Integer> singleDonationOptions;

    @JSONField(name = "min_donation_amount")
    private int minDonationAmount;

    @JSONField(name = "bind_id")
    private int bindId;

    @JSONField(name = "declare_id")
    private int declareId;

    @JSONField(name = "channels")
    private List<Integer> channels;

    @JSONField(name = "img_path")
    private String imgPath = "";

    @JSONField(name = "intro")
    private String intro;

    @JSONField(name = "appid")
    private String appid;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "join_community_type")
    private String joinCommunityType;

    @JSONField(name = "id")
    private int id;

    @JSONField(name = "project_forms")
    private List<ProjectFormsItem> projectForms;
}
