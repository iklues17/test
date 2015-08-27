package net.chrisrichardson.getataxi.events;

public class EventHandlerInvocationException extends RuntimeException {
  public EventHandlerInvocationException(String message, Throwable cause) {
    super(message, cause);
  }
}
