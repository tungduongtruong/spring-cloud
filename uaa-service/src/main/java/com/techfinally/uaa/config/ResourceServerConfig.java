package com.techfinally.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author : Truong Duong
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-server-api";

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/oauth/revoke_token").permitAll()
                .antMatchers("/oauth/authorize").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and().httpBasic();
    }

}