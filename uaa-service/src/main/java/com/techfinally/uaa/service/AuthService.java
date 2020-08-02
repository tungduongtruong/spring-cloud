package com.techfinally.uaa.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import com.techfinally.uaa.model.request.AuthRequest;

/**
 * @author : Truong Duong
 **/
public interface AuthService {

    OAuth2AccessToken login(AuthRequest authRequest);

    boolean logout(AuthRequest authRequest);

}