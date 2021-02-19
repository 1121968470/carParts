package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class Settings {

    @JSONField(name = "is_member")
    private String isMember;

    @JSONField(name = "join_community_type")
    private String joinCommunityType;

    @JSONField(name = "comment")
    private String comment;

    @JSONField(name = "is_allow_anonymous")
    private String isAllowAnonymous;
}
