package com.example.demo.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("within(com.example.demo.controller..*)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void logMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Map<String, Object> parameters = getParameters(joinPoint);
		
		try {
			logger.info("[{}] incoming request to {}: {} ", getName(), signature.getName(), parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterReturning(pointcut = "pointcut()", returning = "response")
	public void logMethodAfter(JoinPoint joinPoint, Object response) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		try {
			logger.info("[{}] outgoing response from {}: {}", getName(), signature.getName(), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Map<String, Object> getParameters(JoinPoint joinPoint) {
		if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
			return null;
		}
		CodeSignature signature = (CodeSignature) joinPoint.getSignature();
		HashMap<String, Object> map = new HashMap<>();
		String[] parameterNames = signature.getParameterNames();
		for (int i = 0; i < parameterNames.length; i++) {
			map.put(parameterNames[i], joinPoint.getArgs()[i]);
		}
		return map;
	}
	
	private String getName() {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return "";
		}
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
