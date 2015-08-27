package net.chrisrichardson.getataxi.util.architecture;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {

  @Pointcut("execution(public * (@org.springframework.stereotype.Service *).*(..))")
  public void serviceMethod() {}

}
