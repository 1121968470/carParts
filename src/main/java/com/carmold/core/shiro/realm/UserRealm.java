package com.carmold.core.shiro.realm;

import com.carmold.bean.User;
import com.carmold.core.model.AuthType;
import com.carmold.core.model.UserInfoModel;
import com.carmold.core.shiro.MultiAuthenticationInfo;
import com.carmold.core.shiro.MultiUsernamePasswordToken;
import com.carmold.core.shiro.ShiroPrincipal;
import com.carmold.service.UserPowerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;

/**
 * demo
 * 自定义校验器 用户登录校验，权限拉取 *
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LogManager.getLogger(UserRealm.class);

    @Autowired
    @Qualifier("userPowerService")
    private UserPowerService userPowerService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        MultiUsernamePasswordToken multiToken = (MultiUsernamePasswordToken) token;
        String username = multiToken.getUsername();
        User user = null;
        if (AuthType.openId.equals(multiToken.getAuthType())) {
             user = userPowerService.getId(username);
             if(user==null){
                 user=new User();
//                 user.setWechatOpenId(username);
             }
        } else {
            //user = userService.(username);
        }
        if (user == null) {
            throw new AuthenticationException("该用户未注册");
        }
        ShiroPrincipal subject = new ShiroPrincipal(new UserInfoModel(user));
        return new MultiAuthenticationInfo(subject, null
                , getName(), multiToken.getAuthType(),
				null);

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取当前登录的用户名
        ShiroPrincipal subject = (ShiroPrincipal) super.getAvailablePrincipal(principals);
        String customerId = subject.getUserName();
        try {
            if (!subject.isAuthorized()) {
                synchronized (subject) {
                    if (!subject.isAuthorized()) {
                        //current user.
                        //CusUser user = ShiroUtils.getUser();

                        HashSet<String> roles = new HashSet<String>();
                        HashSet<String> permissions = new HashSet<String>();
                        //load user resouces here.
                        permissions.add("demoPermission");
                        roles.add("demoRole");

                        subject.setAuthorities(permissions);
                        subject.setRoles(roles);
                        subject.setAuthorized(true);
                        if (logger.isDebugEnabled()) {
                            logger.info("用户【" + customerId + "】已授权......");
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            throw new AuthorizationException("用户【" + customerId + "】授权失败");
        }
        // 给当前用户设置权限
        info.addStringPermissions(subject.getAuthorities());
        info.addRoles(subject.getRoles());
        return info;
    }

}
