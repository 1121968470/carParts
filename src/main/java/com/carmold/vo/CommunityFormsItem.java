package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class CommunityFormsItem {

    @JSONField(name = "is_preview")
    private boolean isPreview;

    @JSONField(name = "form_id")
    private String formId;

    @JSONField(name = "is_apply_join")
    private boolean isApplyJoin;

    @JSONField(name = "label")
    private String label;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "is_fixed")
    private boolean isFixed;

    @JSONField(name = "extended")
    private Extended extended;

    @JSONField(name = "is_required")
    private boolean isRequired;

    @JSONField(name = "user_info")
    private String userInfo;

    @JSONField(name = "is_export")
    private boolean isExport;

    @JSONField(name = "options")
    private String options;

    @JSONField(name = "is_user_required")
    private boolean isUserRequired;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "placeholder")
    private String placeholder;

    @JSONField(name = "is_modify")
    private boolean isModify;

    @JSONField(name = "order_num")
    private int orderNum;
}
