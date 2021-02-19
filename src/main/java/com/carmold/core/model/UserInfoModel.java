package com.carmold.core.model;

import com.carmold.bean.User;

import java.io.Serializable;


public class UserInfoModel implements Serializable {

    /**
     * @date 2018年9月20日 上午11:08:46
     * @author linxinqiang
     */

    private static final long serialVersionUID = 4278660051377568604L;
    private User user;

    public UserInfoModel(User CusUser) {
        this.user = CusUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
