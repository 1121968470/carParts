package com.carmold.core.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 业务操作日志
 *
 * @author linxinqiang
 * @version 1.0
 * @date 2018年6月1日 下午2:23:56
 */
@Component
@Aspect
public class LogAspect {

    private static Logger logger = LogManager.getLogger(LogAspect.class);

    public LogAspect() {
    }

//	private PropertyFilter propertyFilter = new PropertyFilter() {
//		@Override
//		public boolean apply(Object object, String name, Object value) {
//			if (object instanceof BindingResult || object instanceof ServletResponse
//					|| object instanceof ServletRequest) {
//				return false;
//			}
//			return true;
//		}
//	};
//
//	@Pointcut("execution(* com.xrj.attend.controller.api.*Controller.*(..))")
//	private void cutMethod() {
//	}
//
//	@Around("cutMethod()")
//	public Object doAround(ProceedingJoinPoint point) throws Throwable {
//		Signature signature = point.getSignature();
//		MethodSignature methodSignature = (MethodSignature) signature;
//		Method method = point.getTarget()
//				.getClass()
//				.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
//		if (!method.isAnnotationPresent(Log.class)) {
//			return point.proceed();
//		}
//		Object result = point.proceed();
//		try {
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//					.getRequest();
//			Log log = method.getAnnotation(Log.class);
//			OpsUser user = ShiroUtils.getUser();
//			OpsUserLog opsUserLog = new OpsUserLog();
//			opsUserLog.setIp(getIpAddress(request));
//			//当前用户
//			if (user != null) {
//				opsUserLog.setNickname(user.getNickname());
//				opsUserLog.setUserId(user.getId());
//			}
//
//			opsUserLog.setOptType(log.logType());
//			opsUserLog.setDescription(log.description());
//			opsUserLog.setUserAgent(request.getHeader("User-Agent"));
//
//			if (point.getArgs().length > 0) {
//				List<Object> args = new ArrayList<Object>();
//				for (Object object2 : point.getArgs()) {
//					if (object2 instanceof ServletResponse) {
//						continue;
//					}
//					if (object2 instanceof BindingResult) {
//						continue;
//					}
//					args.add(object2);
//				}
//				opsUserLog.setParams(JSON.toJSONString(args));
//			} else {
//				opsUserLog.setParams("");
//			}
//			opsUserLog.setResult(result);
//			logger.info(String.format("写入日志-%s", JSON.toJSONString(opsUserLog)), opsUserLog);
//		} catch (Throwable e) {
//			logger.error("在写入控制层日志时获得异常", e);
//		}
//		return result;
//	}
//
//	public final static String getIpAddress(HttpServletRequest request) throws IOException {
//		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
//
//		String ip = request.getHeader("X-Forwarded-For");
//		if (logger.isInfoEnabled()) {
//			logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
//		}
//
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("Proxy-Client-IP");
//				if (logger.isInfoEnabled()) {
//					logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("WL-Proxy-Client-IP");
//				if (logger.isInfoEnabled()) {
//					logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_CLIENT_IP");
//				if (logger.isInfoEnabled()) {
//					logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//				if (logger.isInfoEnabled()) {
//					logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
//				}
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getRemoteAddr();
//				if (logger.isInfoEnabled()) {
//					logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
//				}
//			}
//		} else if (ip.length() > 15) {
//			String[] ips = ip.split(",");
//			for (int index = 0; index < ips.length; index++) {
//				String strIp = (String) ips[index];
//				if (!("unknown".equalsIgnoreCase(strIp))) {
//					ip = strIp;
//					break;
//				}
//			}
//		}
//		return ip;
//	}

}
