package net.chrisrichardson.getataxi.apigateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ProxyClientImpl implements ProxyClient {

  private final Map<String, String> routes;
  private RestTemplate restTemplate;

  public ProxyClientImpl(RestTemplate restTemplate, Map<String, String> routes) {
    this.restTemplate = restTemplate;
    this.routes = routes;
  }

  @Override
  @HystrixCommand
  public ResponseEntity<String> get(String pathInfo, String queryString, MultiValueMap<String, String> headers) {
    String targetUrl = makeTargetUrl(pathInfo, queryString);
    return restTemplate.exchange(targetUrl, HttpMethod.GET, new HttpEntity<Void>(headers), String.class);
  }

  private String makeTargetUrl(String pathInfo, String queryString) {
    int n = pathInfo.indexOf('/', 1);
    String prefix = pathInfo.substring(1, n);
    String logicalService = routes.get(prefix);
    return "http://" + logicalService + pathInfo;
  }
}
