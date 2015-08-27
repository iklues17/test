package net.chrisrichardson.getataxi.util.misc;



import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
  public static AtomicLong idCount = new AtomicLong();

  public static String generateId() {
    return Long.toString(System.currentTimeMillis()) + "-" + Long.toString(idCount.incrementAndGet());
  }
}
