package net.chrisrichardson.getataxi.apigateway;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProxyClientImplTest {

  @Test
  public void shouldDoSomething() {
    RestTemplate restTemplate = mock(RestTemplate.class);
    LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    String targetUrl = "http://TRIP-MANAGEMENT/trips/1";
    ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
    when(restTemplate.exchange(targetUrl, HttpMethod.GET, new HttpEntity<Void>(headers), String.class)).thenReturn(response);

    ProxyClient proxyClient = new ProxyClientImpl(restTemplate, RoutesMother.routes());
    ResponseEntity<String> result = proxyClient.get("/trips/1", "", headers);

    verify(restTemplate).exchange(targetUrl, HttpMethod.GET, new HttpEntity<Void>(headers), String.class);

    assertSame(response, result);

    assertEquals(HttpStatus.OK, result.getStatusCode());
  }

}