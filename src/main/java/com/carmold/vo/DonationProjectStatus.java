package com.carmold.vo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DonationProjectStatus {

    @JSONField(name = "code")
    private int code;

    @JSONField(name = "name")
    private String name;

    @Override
    public String toString() {
        return
                "Status{" +
                        "code = '" + code + '\'' +
                        ",name = '" + name + '\'' +
                        "}";
    }
}
