package com.techfinally.gateway.config;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class CustomRemoteTokenService implements ResourceServerTokenServices {

  private RestOperations restTemplate;

  private AccessTokenConverter tokenConverter = new CustomAccessTokenConverter();

  @Autowired
  public CustomRemoteTokenService() {
    restTemplate = new RestTemplate();
    ((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() != 400) {
          super.handleError(response);
        }
      }
    });
  }

  @Override
  public OAuth2Authentication loadAuthentication(String accessToken)
      throws AuthenticationException, InvalidTokenException {
    HttpHeaders headers = new HttpHeaders();
    Map<String, Object> map = executeGet(
        "http://localhost:9999/oauth/check_token?token=" + accessToken, headers);
    if (map == null || map.isEmpty() || map.get("error") != null) {
      throw new InvalidTokenException("Token not allowed");
    }
    return tokenConverter.extractAuthentication(map);
  }

  @Override
  public OAuth2AccessToken readAccessToken(String accessToken) {
    throw new UnsupportedOperationException("Not supported: read access token");
  }

  private Map<String, Object> executeGet(String path, HttpHeaders headers) {
    try {
      if (headers.getContentType() == null) {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      }
      headers.setBasicAuth("USER_CLIENT_APP", "password");
      @SuppressWarnings("rawtypes")
      Map map = restTemplate
          .exchange(path, HttpMethod.GET, new HttpEntity<>(null, headers), Map.class).getBody();
      @SuppressWarnings("unchecked")
      Map<String, Object> result = map;
      return result;
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return null;
  }

}