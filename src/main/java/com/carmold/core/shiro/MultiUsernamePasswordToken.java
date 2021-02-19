package com.carmold.core.shiro;

import com.carmold.core.model.AuthType;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 混合登录标志
 *
 * @author 林新强
 * @date 2017年8月31日 下午8:17:52
 */
public class MultiUsernamePasswordToken extends UsernamePasswordToken {

    /**
     * @date 2017年8月31日 下午8:21:18
     * @author 林新强
     */

    private static final long serialVersionUID = 1L;
    private AuthType authType;

    public MultiUsernamePasswordToken() {
        super();

    }

    public MultiUsernamePasswordToken(String username, String password) {
        super(username, password);
    }

    public MultiUsernamePasswordToken(String username, String password, AuthType authType) {
        super(username, password);
        this.authType = authType;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

}
