package net.chrisrichardson.getataxi.testutil.async;

public class EventuallyException extends RuntimeException {
  public EventuallyException(String message, Throwable cause) {
    super(message, cause);
  }
}
