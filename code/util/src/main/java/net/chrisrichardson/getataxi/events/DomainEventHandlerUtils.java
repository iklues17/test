package net.chrisrichardson.getataxi.events;


import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public class DomainEventHandlerUtils {
  public static Set<Class<?>> eventClassesFromHandlers(List<DomainEventHandler> domainEventHandlers) {
    return eventClassesFromHandlerClasses(domainEventHandlers.stream().map(eventHandler -> actualEventHandler(eventHandler).getClass()).collect(Collectors.toList()));
  }

  public static Set<Class<?>> eventClassesFromHandlerClasses(List<Class<? extends DomainEventHandler>> domainEventHandlerClasses) {
    return domainEventHandlerClasses.stream().flatMap(x -> eventClassesFromHandlerClass(x).stream()).collect(Collectors.toSet());
  }

  public static Set<Class<?>> eventClassesFromHandlerClass(Class<? extends DomainEventHandler> domainEventHandlerClass) {
    return Arrays.stream(domainEventHandlerClass.getMethods())
            .filter(DomainEventHandlerUtils::isHandlerMethod)
            .map(DomainEventHandlerUtils::handlerEventType)
            .collect(Collectors.toSet());
  }

  private static Class<? extends Event> handlerEventType(Method method) {
    return (Class<? extends Event>) ((ParameterizedType) method.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0];
  }

  private static boolean isHandlerMethod(Method method) {
    return method.getParameterCount() == 1 && EventEnvelope.class.isAssignableFrom(method.getParameters()[0].getType());
  }

  public static Map<String, List<EventHandlerMethod>> makeEventHandlersForEventMap(List<DomainEventHandler> domainEventHandlers) {
    Map<String, List<EventHandlerMethod>> result = new HashMap<>();

    for (DomainEventHandler eventHandler : domainEventHandlers) {
      Map<String, List<EventHandlerMethod>> methods = methodsForEventHandler(eventHandler);
      methods.forEach( (eventType, handlerMethods) -> {
        List<EventHandlerMethod> existing = result.get(eventType);
        if (existing == null) {
          existing = new ArrayList<EventHandlerMethod>();
          result.put(eventType, existing);
        }
        existing.addAll(handlerMethods);
      });
    }
    return result;
  }

  private static Map<String, List<EventHandlerMethod>> methodsForEventHandler(DomainEventHandler eventHandler) {
    DomainEventHandler eh = actualEventHandler(eventHandler);
    return Arrays.stream(eh.getClass().getMethods())
            .filter(DomainEventHandlerUtils::isHandlerMethod)
            .map(m -> new TypeEventHandlerMethod(handlerEventType(m), new EventHandlerMethod(eventHandler, m)))
            .collect(Collectors.toMap(tehm -> tehm.getHandlerEventType().getName(),
                     tehm -> Collections.singletonList(tehm.getMethod())));
  }

  private static DomainEventHandler actualEventHandler(DomainEventHandler eventHandler) {
    if (AopUtils.isCglibProxy(eventHandler))
      try {
        eventHandler = (DomainEventHandler)((Advised)eventHandler).getTargetSource().getTarget();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    return eventHandler;
  }

  private static class TypeEventHandlerMethod {
    private final Class<? extends Event> handlerEventType;
    private final EventHandlerMethod method;

    public TypeEventHandlerMethod(Class<? extends Event> handlerEventType, EventHandlerMethod method) {
      this.handlerEventType = handlerEventType;
      this.method = method;
    }

    public Class<? extends Event> getHandlerEventType() {
      return handlerEventType;
    }

    public EventHandlerMethod getMethod() {
      return method;
    }
  }
}
