package com.carmold.core.shiro.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.xerces.util.URI;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

public class CrossFilter extends AccessControlFilter {

    private static final Logger logger = LogManager.getLogger(CrossFilter.class);
    private List<String> hosts;
    private List<String> urls;

    @SuppressWarnings({"unchecked"})
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String originHeader = servletRequest.getHeader("Origin");
        if (isAllowHost(originHeader) || isAllowUrl(servletRequest.getRequestURI())) {
            servletResponse.setHeader("Vary", "Accept-Encoding,Origin");
            servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
            servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            // ie,p3p规范
            servletResponse.setHeader("P3P", "CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
            if ("OPTIONS".equals(servletRequest.getMethod())) {
                Enumeration<String> headerNames = servletRequest.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String key = headerNames.nextElement();
                    servletResponse.addHeader(key, servletRequest.getHeader(key));
                }
                servletResponse.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS,DELETE");
                servletResponse.addHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,Origin,Accept");
                servletResponse.addHeader("Access-Control-Max-Age", "3600");
                servletResponse.setStatus(HttpServletResponse.SC_OK);
                servletResponse.flushBuffer();
                return false;
            }
            return true;
        } else {
            if (logger.isDebugEnabled()) {
                logger.warn("cross:" + originHeader);
            }
            return false;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    private boolean isAllowUrl(String url) {
        if (urls == null) {
            return false;
        }
        for (String _url : urls) {
            if (_url.indexOf(url) != -1 || url.indexOf(_url) != -1) {
                return true;
            }
        }
        return false;
    }

    private boolean isAllowHost(String host) {
        if (host == null) {
            return true;
        }
        try {
            URI uri = new URI(host);
            if (hosts.contains(uri.getHost()) || uri.getHost().startsWith("192.168.")) {
                return true;
            }
            //兼容动态增加二级域名允许
            for (String _host : hosts) {
                if (host != null && host.toLowerCase().indexOf(_host.toLowerCase()) != -1) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error("在处理跨域时获得异常", e);
        }
        return false;
    }
}
