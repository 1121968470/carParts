package com.carmold.core.shiro;

import com.carmold.core.model.AuthType;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * 认证信息
 *
 * @author 林新强
 * @date 2017年9月1日 上午9:28:14
 */
public class MultiAuthenticationInfo extends SimpleAuthenticationInfo {

    /**
     * @date 2018年1月17日 上午10:39:38
     * @author 林新强
     */

    private static final long serialVersionUID = 1L;
    private AuthType authType;

    public MultiAuthenticationInfo() {
    }

    public MultiAuthenticationInfo(Object principal, Object credentials, String realmName) {
        super(principal, credentials, realmName);
    }

    public MultiAuthenticationInfo(Object principal, Object credentials, String realmName, AuthType authType) {
        super(principal, credentials, realmName);
        this.authType = authType;
    }

    public MultiAuthenticationInfo(Object principal, Object credentials, String realmName, AuthType authType,
                                   SimpleByteSource simpleByteSource) {
        super(principal, credentials, realmName);
        super.setCredentialsSalt(simpleByteSource);
        this.authType = authType;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

}
