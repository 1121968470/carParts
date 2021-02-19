package com.carmold.core.shiro;


import com.carmold.bean.User;
import com.carmold.core.model.UserInfoModel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义认证主体
 *
 * @author 林新强
 * @date 2018年1月17日 上午9:23:26
 */
public class ShiroPrincipal implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1428196040744555722L;
    //用户模型
    private UserInfoModel userModel;
    //用户权限资源列表
    private Set<String> authorities = new HashSet<String>();
    //用户菜单
    private Set<String> menus = new HashSet<String>();
    //用户角色列表
    private Set<String> roles = new HashSet<String>();
    //是否已授权。如果已授权，则不需要再从数据库中获取权限信息，减少数据库访问
    //这里会导致修改权限时，需要重新登录方可有效
    private boolean isAuthorized = false;

    /**
     * 构造函数，参数为User对象
     * 根据User对象属性，赋值给Principal相应的属性上
     */
    public ShiroPrincipal(UserInfoModel userModel) {
        super();
        this.userModel = userModel;
    }

    public UserInfoModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserInfoModel userModel) {
        this.userModel = userModel;
    }

    public Set<String> getMenus() {
        return menus;
    }

    public void setMenus(Set<String> menus) {
        this.menus = menus;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public User getUser() {
        return this.getUserModel().getUser();
    }

    public String getUserName() {
        return getUser().getUserName();
    }

//    @Override
//    public String toString() {
//        return this.getUser().getPhone();
//    }
}
