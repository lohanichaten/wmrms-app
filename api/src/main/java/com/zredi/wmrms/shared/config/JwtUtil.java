package com.zredi.wmrms.shared.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class JwtUtil {

  private static final String SECRET_KEY = "MY_SECRET_KEY_123456789012345678901234567890";
  // key
  private static final long EXPIRATION_TIME = 86400000; // 1 day (in milliseconds)

  private SecretKey  getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }

//Generate JWT Token for loggedIn user
  public String generateToken(UserDetailDTO userDetail) {
    return Jwts.builder()
              .setSubject(userDetail.getUserName())
              .setIssuedAt(new Date())
              .addClaims(userDetail.getClaims())
              .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
              .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
  }

  public UserDetailDTO extractEmail(String token) {
    var claims=Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
  
    return new UserDetailDTO.UserDetailDTOBuilder()
              .userName(claims.getBody().getSubject())
              .claims(claims.getBody())
              .build();
  }


  public boolean validateJwtToken(String token) {
    try {
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
        return true;
    } catch (SecurityException e) {
        log.error("Invalid JWT signature: " + e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: " + e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: " + e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: " + e.getMessage());
    }
    return false;
}

}
