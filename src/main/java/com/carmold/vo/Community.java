package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class Community {

    @JSONField(name = "keywords")
    private String keywords;

    @JSONField(name = "logo_path")
    private String logoPath;

    @JSONField(name = "is_certified")
    private boolean isCertified;

    @JSONField(name = "level_id")
    private int levelId;

    @JSONField(name = "cover_path")
    private String coverPath;

    @JSONField(name = "created_at")
    private String createdAt;

    @JSONField(name = "founded_date")
    private String foundedDate;

    @JSONField(name = "school_id")
    private int schoolId;

    @JSONField(name = "weibo")
    private String weibo;

    @JSONField(name = "intro")
    private String intro;

    @JSONField(name = "tel")
    private String tel;

    @JSONField(name = "id")
    private int id;

    @JSONField(name = "fax")
    private String fax;

    @JSONField(name = "email")
    private String email;

    @JSONField(name = "is_closed")
    private boolean isClosed;

    @JSONField(name = "website")
    private String website;

    @JSONField(name = "address")
    private String address;

    @JSONField(name = "type_id")
    private int typeId;

    @JSONField(name = "abbreviation")
    private String abbreviation;

    @JSONField(name = "is_verified")
    private boolean isVerified;

    @JSONField(name = "is_pfixed")
    private boolean isPfixed;

    @JSONField(name = "weixin")
    private String weixin;

    @JSONField(name = "user_id")
    private int userId;

    @JSONField(name = "province_id")
    private int provinceId;

    @JSONField(name = "org_id")
    private String orgId;

    @JSONField(name = "parent_id")
    private int parentId;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "update_at")
    private String updateAt;

    @JSONField(name = "country_id")
    private int countryId;

    @JSONField(name = "city_id")
    private int cityId;
}
