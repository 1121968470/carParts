package com.carmold.bean;

import java.io.Serializable;

import com.carmold.mybatis.plugins.generator.Id;
import lombok.Data;

/**
 * user_power
 * @author
 */
@Data
public class UserPower implements Serializable {

    @Id
    private String id;

    /**
     * 用户权限
     */
    private String userPower;

    /**
     * 权限名称
     */
    private String powerName;

    private static final long serialVersionUID = 1L;
}
