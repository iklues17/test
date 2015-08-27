package net.chrisrichardson.getataxi.util.optimisticlocking;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.concurrent.TimeUnit;

@Aspect
public class OptimisticLockingRetryAspect {

  @Around("net.chrisrichardson.getataxi.util.architecture.SystemArchitecture.serviceMethod()")
  public Object retryAspect(ProceedingJoinPoint pjp) throws Throwable {
    Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
    String methodName = pjp.getStaticPart().getSignature().getDeclaringType().getName() + "." + pjp.getStaticPart().getSignature().getName();

    for (int i = 0; i < 4; i++) {
      try {
        return pjp.proceed();
      } catch (OptimisticLockingFailureException e) {
        if (i == 3) {
          logger.error("Too many Optimistic Locking Retries", e);
          throw e;
        }
        logger.info("retrying optimistic {}", i);
        try {
          TimeUnit.MILLISECONDS.sleep(10 + 10 * i);
        } catch (InterruptedException e1) {
          throw new RuntimeException(e1);
        }
      }
    }
    return null;
  }

}

