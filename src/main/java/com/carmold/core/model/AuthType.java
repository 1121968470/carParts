package com.carmold.core.model;

/**
 * 用户类型枚举
 *
 * @author 林新强
 * @date 2017年8月30日 上午11:16:14
 */
public enum AuthType {
    account("用户登录"), openId("微信登录"), code("短信登录");
    private String title;

    private AuthType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
