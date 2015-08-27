package net.chrisrichardson.getataxi.apigateway;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiGatewayControllerTest {

  private MockMvc mockMvc;
  private ProxyClient proxyClient;

  @Before
  public void setup() {
    this.proxyClient = mock(ProxyClient.class);
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ApiGatewayController(proxyClient)).build();
  }

  @Test
  public void shouldProxyGet() throws Exception {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

    MultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
    responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

    ResponseEntity<String> responseEntity = new ResponseEntity<>("XXX", responseHeaders, HttpStatus.OK);

    when(proxyClient.get("/hotels/bar/42", "foo=bar", headers)).thenReturn(responseEntity);

    mockMvc.perform(get("/hotels/bar/{id}?foo=bar", 42)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("XXX"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    verify(proxyClient).get("/hotels/bar/42", "foo=bar", headers);

  }



}