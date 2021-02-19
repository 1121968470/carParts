package com.carmold.core.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AutoAuthenticationFilter extends AccessControlFilter {

    //private static final Logger logger = LogManager.getLogger(AutoAuthenticationFilter.class);


    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return true;
        } else {
//			EduClass eduClass = eduClassService.get("gyx56");
//
//			try {
//				//密码
//				Subject currentUser = SecurityUtils.getSubject();
//				CusClazzPadConfig cusClazzPadConfig = cusClazzPadConfigService.findUnique(eduClass.getSchoolId());
//				if (cusClazzPadConfig != null && StringUtils.isNotEmpty(cusClazzPadConfig.getPassword())) {
//					MultiUsernamePasswordToken passwordToken = new MultiUsernamePasswordToken(
//							eduClass.getId(), cusClazzPadConfig.getPassword(), AuthType.token,
//							UserType.CLASSPAD);
//					passwordToken.setRememberMe(true);
//					currentUser.login(passwordToken);
//				}
//			} catch (Exception e) {
//				logger.error("在班牌进行token校验时获得异常", e);
//				return false;
//			}
            return true;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;

    }

}
