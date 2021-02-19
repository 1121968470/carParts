package com.carmold.core.aspect.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Log {
    /**
     * 日志类型
     *
     * @return
     * @date 2018年9月30日 下午1:43:39
     * @author linxinqiang
     */
    String logType();

    /**
     * 描述
     *
     * @return
     * @date 2018年9月30日 下午1:43:28
     * @author linxinqiang
     */
    String description();
}
