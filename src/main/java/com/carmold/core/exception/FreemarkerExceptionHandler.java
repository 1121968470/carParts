package com.carmold.core.exception;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;

/**
 * spring mvc 通用处理
 *
 * @author 林新强
 * @date 2017年8月8日 上午11:46:45
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
    private final static String ERROR_TIPS = "发现异常，请稍后重试";

    @Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
        try {
            out.write("[" + ERROR_TIPS + "]");
        } catch (IOException e) {
            throw new TemplateException("Failed to print error message. Cause: " + e, env);
        }
    }

}
