package com.zredi.wmrms.shared.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zredi.wmrms.shared.config.JwtUtil;
import com.zredi.wmrms.shared.config.UserDetailDTO;
import com.zredi.wmrms.shared.dto.AppPermisionResponseDTO;
import com.zredi.wmrms.shared.dto.AppRoleResponseDTO;
import com.zredi.wmrms.shared.dto.AppUserResponseDTO;
import com.zredi.wmrms.shared.dto.AuthenticationRequestDTO;
import com.zredi.wmrms.shared.dto.AuthenticationResponseDTO;
import com.zredi.wmrms.shared.dto.CreateAppRoleRequestDTO;
import com.zredi.wmrms.shared.dto.CreateAppUserRequestDTO;
import com.zredi.wmrms.shared.dto.ModifyAppRoleRequest;
import com.zredi.wmrms.shared.dto.ResetPasswordRequestDTO;
import com.zredi.wmrms.shared.dto.UpdateUserRequestDTO;
import com.zredi.wmrms.shared.exception.BadCredentailsException;
import com.zredi.wmrms.shared.service.RBACService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/security")
public class AuthenticationController {
  
  
  private final RBACService rbacService;
  
  private final AuthenticationManager authManager;
  private final JwtUtil jwtUtil;
  

  
  @PostMapping("/authenticate")
  public AuthenticationResponseDTO authentication(@Valid @RequestBody AuthenticationRequestDTO authRequest){
    Authentication authentication=new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword(), null);
  
    try {
      
      authentication= authManager.authenticate(authentication);
      String jwtToken=jwtUtil.generateToken(UserDetailDTO.builder()
                                                    .userName(authentication.getName())
                                                    .claims(Collections.singletonMap("permissions", authentication.getAuthorities()
                                                        .stream()
                                                        .map(authority->authority.getAuthority())
                                                        .collect(Collectors.toList())
                                                      )) 
                                                    .build()
          );
      return AuthenticationResponseDTO.builder().token(jwtToken).build();
     
    }catch( AuthenticationException ex) {
      log.error(ex.getMessage());
      throw new BadCredentailsException("Incorrect Username or password");
    }
    
  }

  
  
  @GetMapping("/role")
  public List<AppRoleResponseDTO> getAllRoles() {
    return rbacService.getAllAppRole();
  }

  
  @GetMapping("/permission")
  public List<AppPermisionResponseDTO> getAllPermission() {
    return rbacService.getAllAppPermissions();
  }
  
  
  @GetMapping("/user/{userId}")
  public AppUserResponseDTO getUserDetailById(@PathVariable("userId") Integer userId) {
    return rbacService.getUserInformationUsingId(userId);
  }

  
  @PostMapping("/user/{userId}/resetpassword")
  public void resetPassword(@PathVariable("userId") Integer userId,@Valid @RequestBody ResetPasswordRequestDTO request) {
    rbacService.resetPassword(userId,request);
  }
  
  @PostMapping("/user")
  public void createUser(@Valid @RequestBody CreateAppUserRequestDTO request ) {
    rbacService.createUser(request);
  }
  
  
  @PutMapping("/user/{userId}")
  public void updateUser(@PathVariable("userId") Integer userId  ,@Valid @RequestBody UpdateUserRequestDTO request) {
    rbacService.modifyUser(userId,request);
  }
  
  
  @PostMapping("/role")
  public AppRoleResponseDTO createRole(@RequestBody CreateAppRoleRequestDTO request) {
    return rbacService.crateNewRole(request);
  }
  
  @PutMapping("/role/{roleId}")
  public void modifyApplicationRole(@PathVariable("roleId") Integer roleId,@RequestBody ModifyAppRoleRequest request) {
    rbacService.modfiyExistingRole(roleId,request);
  }
  
  @GetMapping("/role/{roleId}")
  public AppRoleResponseDTO getAppRoleUsingId(@PathVariable("roleId") Integer roleId) {
     return  rbacService.getApplicationRoleById(roleId);
  }
}
