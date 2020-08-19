package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	//외부파일로 작성한 pointcut을 호출하여 사용할 수 있음
	@Around("PointcutCommon.allPointcut()") 
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable{
		String method = pjp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//클라이언트가 요청한 메소드 실행
		Object obj = pjp.proceed();
		
		stopWatch.stop();
		System.out.println(method+"()메소드 실행에 걸린 시간:"+stopWatch.getTotalTimeMillis()+"(ms)초");
		return obj;
	}

}
