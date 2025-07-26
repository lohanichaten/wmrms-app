package com.zredi.wmrms.shared.config;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zredi.wmrms.shared.model.WMRMSUser;
import com.zredi.wmrms.shared.repository.WMRMSUserRepository;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class WMRMSUserDetailService implements UserDetailsService{

  private final WMRMSUserRepository userRepository;
  

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   return userRepository.findById(Integer.parseInt(username))
               .map(dbUser->mapToUserDetails(dbUser))
               .orElseThrow(()->new UsernameNotFoundException("No user found with given details"));
    
  }


  private UserDetails mapToUserDetails(WMRMSUser dbUser) {
    
    Set<SimpleGrantedAuthority> authorites=dbUser.getAppRole()!=null?dbUser.getAppRole().getPermissions()
                                      .stream()
                                      .map(permission->new SimpleGrantedAuthority(permission.getPermissionName()))
                                      .collect(Collectors.toSet()):Collections.EMPTY_SET;
    
    
    
    return  User.withUsername(dbUser.getUserId().toString()) 
                  .password(dbUser.getPassword())
                  .authorities(authorites)
                  .build();

  }

}
