package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class DonationDetailRequest {
    float amount;
    int channel = 100;
    @JSONField(name = "project_id")
    String projectId;
    @JSONField(name = "is_anonymous")
    int isAnonymous;

    String message;
}
