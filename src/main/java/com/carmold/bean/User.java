package com.carmold.bean;

import com.carmold.mybatis.plugins.generator.Id;
import lombok.Data;

@Data
public class User {

    @Id
    private String id;

    private String userName;

    /**
     * 用户id
     */
    private String userId;

    private String password;

    /**
     * 权限
     */
    private String power;

    /**
     * 登录状态
     */
    private String loginStatus;

    /**
     * 权限名称
     */
    private String powerName;

    /**
     * 更新状态（I新增，U更新，D删除）
     */
    private String updateState;

    private static final long serialVersionUID = 1L;
}
