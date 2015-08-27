package net.chrisrichardson.getataxi.apigateway;

import java.util.HashMap;
import java.util.Map;

public class RoutesMother {

  public static Map<String, String> routes() {
    Map<String, String> routes = new HashMap<>();
    routes.put("trips", "TRIP-MANAGEMENT");
    return routes;
  }
}
