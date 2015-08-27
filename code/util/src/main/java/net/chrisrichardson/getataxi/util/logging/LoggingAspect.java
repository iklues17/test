package net.chrisrichardson.getataxi.util.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {


  @Around("net.chrisrichardson.getataxi.util.architecture.SystemArchitecture.serviceMethod()")
  public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
    Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
    String methodName = pjp.getStaticPart().getSignature().getDeclaringType().getName() + "." + pjp.getStaticPart().getSignature().getName();
    logger.info("Entering: {}", methodName);
    Object retVal = pjp.proceed();
    logger.info("Leaving: {}", methodName);
    return retVal;
  }

}
