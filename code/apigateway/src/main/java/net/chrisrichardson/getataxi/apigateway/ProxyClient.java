package net.chrisrichardson.getataxi.apigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface ProxyClient {
  ResponseEntity<String> get(String pathInfo, String queryString, MultiValueMap<String, String> headers);
}
