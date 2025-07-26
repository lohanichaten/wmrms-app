package com.zredi.wmrms.shared.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


@Configuration
public class SpringSecurityAuditorAware implements AuditorAware<Integer>{

  @Override
  public Optional<Integer> getCurrentAuditor() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(authentication->(String)authentication.getName())
        .map(userName->Integer.valueOf(userName));
  }

}
