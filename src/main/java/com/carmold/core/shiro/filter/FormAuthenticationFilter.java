package com.carmold.core.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.carmold.service.UserPowerService;
import com.carmold.vo.CallResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class FormAuthenticationFilter extends AccessControlFilter {

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return true;
        }

        HttpServletResponse servletResponse = (HttpServletResponse) response;
        CallResult callResult = new CallResult();
        callResult.fail("还没有登录哦");
        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        servletResponse.setContentType("application/json; charset=utf-8");
        servletResponse.getOutputStream().write(JSON.toJSONString(callResult).getBytes());
        servletResponse.getOutputStream().flush();
        return false;

    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;

    }

}
