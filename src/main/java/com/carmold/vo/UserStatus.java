package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.annotation.Generated;

@Data
@Generated("com.robohorse.robopojogenerator")
public class UserStatus {

    @JSONField(name = "filled_forms")
    private boolean filledForms;

    @JSONField(name = "is_applied")
    private boolean isApplied;
}
