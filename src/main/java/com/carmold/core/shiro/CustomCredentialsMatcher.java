package com.carmold.core.shiro;

import com.carmold.util.MD5;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import java.io.UnsupportedEncodingException;

/**
 * 自定义验证匹配器
 * 加盐
 *
 * @author 林新强
 * @date 2017年8月28日 上午9:22:42
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        return true;
    }

    private String encrypt(SimpleAuthenticationInfo authenticationInfo, UsernamePasswordToken token)
            throws UnsupportedEncodingException {
        return MD5.encode(new String(token.getPassword())
                + new String(authenticationInfo.getCredentialsSalt().getBytes(), "utf-8"));
    }

}
