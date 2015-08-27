package net.chrisrichardson.getataxi.testutil.misc;

import java.io.IOException;
import java.net.ServerSocket;

public class AvailablePortUtil {

  public static int findPort() {
    try {
      ServerSocket s = new ServerSocket();
      s.bind(null);
      int port = s.getLocalPort();
      s.close();
      return port;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

