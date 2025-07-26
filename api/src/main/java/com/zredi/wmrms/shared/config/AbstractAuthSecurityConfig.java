package com.zredi.wmrms.shared.config;

abstract class AbstractAuthSecurityConfig {

  

    protected AbstractAuthSecurityConfig() {

    }

    protected static final String[] AUTH_WHITELIST = {
        "/css/**",
        "/js/**",
        "/media/**",
        "/resources/**",
        "/actuator/health/**",
        "/actuator/info",
        "/actuator/prometheus",
        "/auth",
        "/login",
        "/authenticate",
        "/logout",
        "/oauth2/**",
        "/static/**",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-ui.html",
        "/swagger-ui/index.html"
    };

  }


