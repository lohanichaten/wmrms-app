package com.zredi.wmrms.shared.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationTokeFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtil jwtUtils;

  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
  
    String jwt = parseJwt(request);
    if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        
      UserDetailDTO userDetail = jwtUtils.extractEmail(jwt);
      List<String> permissions=    (List<String>)userDetail.getClaims().get("permissions");
      Collection<GrantedAuthority> authorities= permissions.stream()
                                                            .map(permission->new SimpleGrantedAuthority(permission))
                                                            .collect(Collectors.toList());

      UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(
                    userDetail.getUserName(),
                      null,
                      authorities
              );
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } 
    } catch (Exception e) {
      System.out.println("Cannot set user authentication: " + e);
  }
   
    
    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }
    return null;
  }
}
