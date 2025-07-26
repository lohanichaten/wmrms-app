package com.zredi.wmrms.shared.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zredi.wmrms.shared.dto.AppPermisionResponseDTO;
import com.zredi.wmrms.shared.dto.AppRoleResponseDTO;
import com.zredi.wmrms.shared.dto.AppUserResponseDTO;
import com.zredi.wmrms.shared.dto.CreateAppRoleRequestDTO;
import com.zredi.wmrms.shared.dto.CreateAppUserRequestDTO;
import com.zredi.wmrms.shared.dto.ModifyAppRoleRequest;
import com.zredi.wmrms.shared.dto.ResetPasswordRequestDTO;
import com.zredi.wmrms.shared.dto.UpdateUserRequestDTO;
import com.zredi.wmrms.shared.exception.AppRoleNotFoundException;
import com.zredi.wmrms.shared.exception.DuplcateAppRoleNameException;
import com.zredi.wmrms.shared.exception.UserAlreadyExistsException;
import com.zredi.wmrms.shared.exception.UserNotFoundException;
import com.zredi.wmrms.shared.mapper.RBACMapper;
import com.zredi.wmrms.shared.model.WMRMSAppRole;
import com.zredi.wmrms.shared.model.WMRMSUser;
import com.zredi.wmrms.shared.repository.WMRMSAppPermissionRespository;
import com.zredi.wmrms.shared.repository.WMRMSAppRoleRepository;
import com.zredi.wmrms.shared.repository.WMRMSUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RBACService {

  private final WMRMSAppPermissionRespository permissionRespository;
  private final WMRMSAppRoleRepository roleRepository;
  private final WMRMSUserRepository userRepository;
  
  private final RBACMapper  rbacMapper;
  private final PasswordEncoder passwordEncoder;
  
  
  public List<AppPermisionResponseDTO> getAllAppPermissions(){
    var appPermission= permissionRespository.findAll();
    return rbacMapper.mapEntitiesToAppPermissions(appPermission);                 
  }
  
  
  public List<AppRoleResponseDTO> getAllAppRole(){
    var appRoles=roleRepository.findAll();
    return rbacMapper.mapEntitiesToAppRoleResponse(appRoles);
  }
  
  
  public AppUserResponseDTO  getUserInformationUsingId(Integer userId) {
    var user=getUserById(userId);
    return rbacMapper.mapEntityToAppUser(user);
  }


  @Transactional
  public void resetPassword(Integer userId,ResetPasswordRequestDTO request) {
    var user=getUserById(userId);  
    String newPasswordHash=  encodePassword(request.getNewPassword());
    user.setPassword(newPasswordHash);
  }
  
  @Transactional
  public void createUser(CreateAppUserRequestDTO request) {
    Optional<WMRMSUser>  optionalUser=userRepository.findById(request.getUserId());
    if(!optionalUser.isEmpty()) {
        throw new UserAlreadyExistsException("User Already exists with given userid");
    }
    
    var appRole=getAppRoleById(request.getAppRoleId());
    
    WMRMSUser user=new WMRMSUser();
    user.setUserId(request.getUserId());
    user.setPassword(encodePassword(request.getPassword()));
    user.setFirstName(request.getFirstName());
    user.setMiddleName(request.getMiddleName());
    user.setLastName(request.getLastName());
    user.setAppRole(appRole);
    user.setStatus("A"); //fix it
    userRepository.save(user);
    
  }
  
  private WMRMSUser getUserById(Integer userId) {
    return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No User found with user id"+userId));
  }
  
  
  private WMRMSAppRole getAppRoleById(Integer roleId) {
    return roleRepository.findById(roleId).orElseThrow(()->new AppRoleNotFoundException("No Role found with id "+roleId));
  }
  
  private String encodePassword(String password) {
    return    passwordEncoder.encode(password);
  }


  @Transactional
  public void modifyUser(Integer userId, UpdateUserRequestDTO request) {
    var existingUser=getUserById(userId);
    
    var appRole=getAppRoleById(request.getAppRoleId());
    
    
    existingUser.setFirstName(request.getFirstName());
    existingUser.setLastName(request.getLastName());
    existingUser.setMiddleName(request.getMiddleName());
    existingUser.setAppRole(appRole);
    
  }


  @Transactional
  public AppRoleResponseDTO crateNewRole(CreateAppRoleRequestDTO request) {
      var optionalRoleName=roleRepository.findByRoleName(request.getRoleName());
      if(optionalRoleName.isPresent()) {
        throw new DuplcateAppRoleNameException("Role Already found with given name");
      }
      
      var permissions= permissionRespository.findAllById(request.getPermissionIds())
          .stream()
          .collect(Collectors.toSet());
      
      WMRMSAppRole appRole=new WMRMSAppRole();
      appRole.setRoleName(request.getRoleName());
      appRole.setRoleDesc(request.getRoleDescription());
      appRole.setPermissions(permissions);
      
      
      roleRepository.save(appRole);
      
      return rbacMapper.mapEntityToAppRoleResponse(appRole);

      
  }

  
  @Transactional
  public void modfiyExistingRole(Integer roleId, ModifyAppRoleRequest request) {
       var appRole=     getAppRoleById(roleId);
       var permissions= permissionRespository.findAllById(request.getPermissionIds())
                               .stream()
                               .collect(Collectors.toSet());
       appRole.setRoleName(request.getRoleName());
       appRole.setRoleDesc(request.getRoleDescription());
       appRole.setPermissions(permissions);
  }


  public AppRoleResponseDTO getApplicationRoleById(Integer roleId) {
    var appRole=     getAppRoleById(roleId);
    return rbacMapper.mapEntityToAppRoleResponse(appRole);
  }
  
}
