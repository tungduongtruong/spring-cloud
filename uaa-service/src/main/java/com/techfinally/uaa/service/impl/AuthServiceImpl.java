package com.techfinally.uaa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import com.techfinally.uaa.model.entity.Account;
import com.techfinally.uaa.model.request.AuthRequest;
import com.techfinally.uaa.service.AuthService;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Truong Duong
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private DefaultTokenServices tokenService;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public OAuth2AccessToken login(AuthRequest authRequest){
        switch (authRequest.getGrantType()) {
            case "password":
                UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
                return createAccessToken((Account) userDetails, authRequest);
            case "refresh_token":
//        return oAuth2Service.refreshToken(authRequest);
            default:
                return null;
        }
    }

    @Override
    public boolean logout(AuthRequest authRequest) {
        return tokenService.revokeToken(authRequest.getToken());
    }

    public OAuth2AccessToken createAccessToken(Account account, AuthRequest authRequest) {
        try {
            HashMap<String, String> authorizationParameters = new HashMap<String, String>();
            authorizationParameters.put("scope", "read");
            authorizationParameters.put("username", authRequest.getUsername());
            authorizationParameters.put("password", authRequest.getPassword());
            authorizationParameters.put("client_id", authRequest.getClientId());
            authorizationParameters.put("client_serect", authRequest.getClientSerect());
            authorizationParameters.put("grant_type", "password");

            Collection authorities = account.getAuthorities();

            Set<String> responseType = new HashSet<String>();
            responseType.add("password");

            Set<String> scopes = new HashSet<String>();
            scopes.add("read");
            scopes.add("write");

            OAuth2Request authorizationRequest = new OAuth2Request(authorizationParameters, authRequest.getClientId(), authorities, true,
                    scopes, null, "", responseType, null);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account,
                    null, authorities);

            OAuth2Authentication authenticationRequest = new OAuth2Authentication(authorizationRequest,
                    authenticationToken);
            authenticationRequest.setAuthenticated(true);
            OAuth2AccessToken accessToken = tokenService.createAccessToken(authenticationRequest);
            return accessToken;
        } catch (Exception ex) {
            return null;
        }
    }

    public OAuth2AccessToken refreshAccessToken() {
//        OAuth2AccessToken accessToken = tokenService.refreshAccessToken(authenticationRequest);
//        return accessToken;

        return null;
    }

}
