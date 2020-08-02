package com.techfinally.uaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techfinally.uaa.model.reponse.RestResult;
import com.techfinally.uaa.model.request.AuthRequest;
import com.techfinally.uaa.service.AuthService;

/**
 * @author Truong Duong
 */
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

  @Autowired
  AuthService authService;

  @PostMapping(value = "/login/")
  public ResponseEntity login(@RequestBody AuthRequest authRequest) {
    RestResult result = new RestResult<>();
    try {
      OAuth2AccessToken oAuth2AccessToken = authService.login(authRequest);
      if (oAuth2AccessToken == null && oAuth2AccessToken.getValue() != null) {
        result.fail();
      } else {
        result.ok(oAuth2AccessToken);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      result.fail();
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(value = "/logout/")
  public ResponseEntity logout(@RequestBody AuthRequest authRequest) {
    RestResult result = new RestResult<>();
    result.ok();
    authService.logout(authRequest);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}