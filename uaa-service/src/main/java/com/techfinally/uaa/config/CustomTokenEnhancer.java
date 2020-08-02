package com.techfinally.uaa.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import com.techfinally.uaa.model.entity.Account;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Truong Duong
 */
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();

        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

        info.put("full_name", account.getFullName());
        info.put("user_name", account.getUsername());
        info.put("email", account.getEmail());

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }

}
