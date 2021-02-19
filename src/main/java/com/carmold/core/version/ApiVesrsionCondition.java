package com.carmold.core.version;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiVesrsionCondition implements RequestCondition<ApiVesrsionCondition> {

    // 路径中版本的前缀， 这里用 /v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    private int apiVersion;

    public ApiVesrsionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVesrsionCondition combine(ApiVesrsionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVesrsionCondition(other.getApiVersion());
    }

    @Override
    public int compareTo(ApiVesrsionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    @Override
    public ApiVesrsionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getServletPath());
        if (m.find()) {
            Integer version = Integer.valueOf(m.group(1));
            if (version >= this.apiVersion)
                return this;
        } else {
            return this;
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + apiVersion;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApiVesrsionCondition other = (ApiVesrsionCondition) obj;
        if (apiVersion != other.apiVersion)
            return false;
        return true;
    }

}
