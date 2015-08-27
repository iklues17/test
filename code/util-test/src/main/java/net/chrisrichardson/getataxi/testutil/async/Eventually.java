package net.chrisrichardson.getataxi.testutil.async;

import java.util.concurrent.TimeUnit;

public class Eventually {

  public static void eventually(EventualBody body) {
    Throwable t = null;
    for (int i = 0; i < 10; i++) {
      try {
        System.out.println("trying");
        body.run();
        return;
      } catch (Throwable t1) {
        t = t1;
        try {
          TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
    throw new EventuallyException(String.format("Failed after %s iterations every %s milliseconds", 10, 250), t);
  }
}
