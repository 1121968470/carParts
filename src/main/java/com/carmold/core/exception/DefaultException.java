package com.carmold.core.exception;

import com.alibaba.fastjson.JSON;
import com.carmold.vo.CallResult;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class DefaultException {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String ERROR_TIPS = "发现异常，请稍后重试";
    private final static String ERROR_PAGE = "common/error";
    private final static String ERROR_GLOBAL_LOG_TIPS = "发现异常，请稍后重试!";
    private final static String ERROR_TIPS_AUTHORIRY = "没有资源访问权限!";
    private final static String ERROR_TIPS_PARAM = "发现异常,请检查参数或稍后重试";

    @SuppressWarnings("unchecked")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error(ERROR_GLOBAL_LOG_TIPS, ex);
        ModelAndView view = new ModelAndView();

        CallResult callResult = new CallResult();
        callResult.fail(getExceptionMessage(ex));
        view.addAllObjects(JSON.parseObject(JSON.toJSONString(callResult), Map.class));
        if (isAjaxRequest(request)) {
            view.setView(new MappingJackson2JsonView());
        } else if (isAppRequest(request)) {
            view.setStatus(getHttpStatus(ex));
        } else {
            view.setViewName(ERROR_PAGE);
            view.addAllObjects(BeanMap.create(callResult));
        }
        return view;
    }

    private String getExceptionMessage(Exception exception) {
        if (exception instanceof BindException) {
            return ERROR_TIPS_PARAM;
        }
        if (exception instanceof AuthorizationException) {
            return ERROR_TIPS_AUTHORIRY;
        }
        return ERROR_TIPS;
    }

    /**
     * 错误状态码
     *
     * @param exception
     * @return
     */
    private HttpStatus getHttpStatus(Exception exception) {
        return HttpStatus.OK;
    }

    /**
     * ajax
     *
     * @param request
     * @return
     */
    private static boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").equals("XMLHttpRequest"))) {
            return true;
        }
        return false;
    }

    /**
     * app请求
     *
     * @param request
     * @return
     */
    private static boolean isAppRequest(HttpServletRequest request) {
        if (request.getHeader("appId") != null) {
            return true;
        }
        return false;
    }

}
