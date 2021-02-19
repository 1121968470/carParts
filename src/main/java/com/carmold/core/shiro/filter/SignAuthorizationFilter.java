package com.carmold.core.shiro.filter;

import com.carmold.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class SignAuthorizationFilter extends AccessControlFilter {
    private final static String appSecret = "9f645d9b3fd84808b205eccb2283ae1d";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String sign = httpRequest.getParameter("sign");
        String appId = httpRequest.getParameter("appid");
        if (StringUtils.isEmpty(appId)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        if (StringUtils.isEmpty(appId) || !Sign.signer.validate(appId, sign, httpRequest.getParameterMap())) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    interface Sign {
        public static final Sign signer = new SortSign();

        public String generate(String appId, Map parameters);

        public boolean validate(String appId, String sign, Map parameters);
    }

    private static class SortSign implements Sign {

        @Override
        public String generate(String appId, Map parameters) {
//			UserType userType = UserType.getType(appId);
//			if (userType == null) {
//				return null;
//			}
            // sort by key [0-9][A-Z][a-z]
            List<Map.Entry<String, Object>> entries = new ArrayList<>(parameters.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<String, Object>>() {
                @Override
                public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            StringBuilder builder = new StringBuilder();
            builder.append(appSecret);//FIXME

            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals("sign")) {
                    continue;
                }
                builder.append(entry.getKey());
                builder.append(((String[]) entry.getValue())[0]);
            }
            return MD5.encode(builder.toString()).toUpperCase(Locale.getDefault());
        }

        @Override
        public boolean validate(String appId, String validateSign, Map parameters) {
            String sign = this.generate(appId, parameters);
            if (validateSign.equals(sign)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
