package net.chrisrichardson.getataxi.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventHandlerMethod {

  private DomainEventHandler eventHandler;
  private Method m;

  public EventHandlerMethod(DomainEventHandler eventHandler, Method m) {
    this.eventHandler = eventHandler;
    this.m = m;
  }

  public void handle(EventEnvelope eventEnvelope) {
    try {
      m.invoke(eventHandler, eventEnvelope);
    } catch (InvocationTargetException e) {
      throw new EventHandlerInvocationException(handleMethodName(m), e.getCause());
    } catch (Exception e) {
      throw new EventHandlerInvocationException(handleMethodName(m), e);
    }
  }

  private String handleMethodName(Method m) {
    return m.getDeclaringClass().getName() + "." + m.getName() + " failed";
  }
}
