package com.zredi.wmrms.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.zredi.wmrms.shared.config.auth.AuthEntryPointJwt;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity(debug = true)
@Slf4j
public class JwtAuthSecurityConfig extends AbstractAuthSecurityConfig{

  
  
  @Bean
  AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
    return new AuthEntryPointJwt();
  }
  
  @Bean
   JwtAuthenticationTokeFilter authenticationJwtTokenFilter() {
      return new JwtAuthenticationTokeFilter();
  }
  @Bean
   AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
  }
  
  @Bean
  PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Bean
  SecurityFilterChain  defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    
     return http
         .csrf(csrf->csrf.disable())
         .sessionManagement(sessionManagement ->
                           sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         )
         .exceptionHandling(exceptionHandling->exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint()))
         .authorizeHttpRequests(requests->requests.requestMatchers(AUTH_WHITELIST)
                                         .permitAll()
                                         .anyRequest()
                                         .authenticated())
         .addFilterBefore(authenticationJwtTokenFilter(), BasicAuthenticationFilter.class)
        .build();
    
  }
  
  
  
}
