package com.carmold.core.shiro;

import com.carmold.bean.User;
import com.carmold.core.model.UserInfoModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 *
 * @author 林新强
 * @date 2018年1月17日 上午9:25:22
 */
public class ShiroUtils {

    public static UserInfoModel getUserModel() {
        ShiroPrincipal principal = getPrincipal();
        if (principal != null)
            return principal.getUserModel();
        return null;
    }

    /**
     * 返回当前登录的认证实体ID
     *
     * @return
     */
    public static User getUser() {
        UserInfoModel infoModel = getUserModel();
        if (infoModel != null)
            return infoModel.getUser();
        return null;
    }

    /**
     * 返回当前登录的认证实体ID
     *
     * @return
     */
    public static String getUserId() {
        User user = getUser();
        if (user != null)
            return user.getId();
        return null;
    }

    /**
     * 获取当前登录的认证实体
     *
     * @return
     */
    public static ShiroPrincipal getPrincipal() {
        Subject subject = SecurityUtils.getSubject();
        return (ShiroPrincipal) subject.getPrincipal();
    }

}
