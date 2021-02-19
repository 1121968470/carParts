package com.carmold.core.shiro.filter;

import com.carmold.core.model.AuthType;
import com.carmold.core.shiro.MultiUsernamePasswordToken;
import com.carmold.service.UserPowerService;
import com.carmold.util.WxBaseService;
import com.carmold.vo.WechatAccessTokenResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TokenAuthorizationFilter extends AccessControlFilter {

    //private Logger logger = LogManager.getLogger(TokenAuthorizationFilter.class);
    @Autowired
	WxBaseService wxUserManager;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)  {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Subject currentUser = SecurityUtils.getSubject();
		if ((!currentUser.isAuthenticated())) {
			String code = httpRequest.getParameter("code");
//			WechatAccessTokenResponse wxWebUser = wxUserManager.getAccessToken(code);
			MultiUsernamePasswordToken token=new MultiUsernamePasswordToken();
			token.setAuthType(AuthType.openId);
//			token.setUsername(wxWebUser.getOpenid());
			currentUser.login(token);
		}
        return true;
    }

}
