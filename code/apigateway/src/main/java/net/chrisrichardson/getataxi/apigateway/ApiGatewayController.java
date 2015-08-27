package net.chrisrichardson.getataxi.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
public class ApiGatewayController {

  private ProxyClient proxyClient;

  @Autowired
  public ApiGatewayController(ProxyClient proxyClient) {
    this.proxyClient = proxyClient;
  }

  @RequestMapping(name = "/**", method= RequestMethod.GET)
  public Object get(HttpServletRequest request) {
    System.out.println("url=" + request.getRequestURL());
    System.out.println("servletPath=" + request.getServletPath());
    System.out.println("pathInfo=" + request.getPathInfo());
    System.out.println("getQueryString=" + request.getQueryString());

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    for (String headerName : Collections.list(request.getHeaderNames())) {
      for (String headerValue : Collections.list(request.getHeaders(headerName))){
        headers.add(headerName, headerValue);
      }
    }

    System.out.println("headers=" + headers);

    StringBuffer sb = new StringBuffer();
    if (request.getServletPath() != null)
      sb.append(request.getServletPath());
    if (request.getPathInfo() != null)
      sb.append(request.getPathInfo());

    return proxyClient.get(sb.toString(), request.getQueryString(), headers);
  }
}
